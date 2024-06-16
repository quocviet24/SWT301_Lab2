package com.nishikatakagi.ProductDigital.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nishikatakagi.ProductDigital.dto.UserRecoverPasswordDto;
import com.nishikatakagi.ProductDigital.dto.UserResetPasswordDto;
import com.nishikatakagi.ProductDigital.dto.UserSessionDto;
import com.nishikatakagi.ProductDigital.model.User;
import com.nishikatakagi.ProductDigital.service.EmailService;
import com.nishikatakagi.ProductDigital.service.SecurityService;
import com.nishikatakagi.ProductDigital.service.UserService;

@Controller
@EnableAsync
public class ForgotPasswordController {

    @Autowired
    HttpSession session;

    @Autowired
    private UserService userService;

    @Autowired
    EmailService emailService;

    @Autowired
    SecurityService security;

    private User user ;

    private Map<String, Boolean> listCheckEnterOTPToBlockEmail5M = new HashMap<>();


    public Map<Integer, String> listOtp = new ConcurrentHashMap<Integer, String>();
    public Map<Integer, String> listEmailEnterOtp = new ConcurrentHashMap<Integer, String>();

    @GetMapping("/forgotPassword")
    public String forgotPassword(Model model) {
        // Check if the user is login or not
        UserSessionDto user = (UserSessionDto) session.getAttribute("user_sess");
        System.out.println(listCheckEnterOTPToBlockEmail5M.toString());
        if (user != null) {
            return "redirect:/";
        } else {
            UserRecoverPasswordDto userDTO = new UserRecoverPasswordDto();
            model.addAttribute("user", userDTO);
            return "/publics/forgot-password";
        }
    }

    @PostMapping("/forgotPassword")
    public String forgotPasswordProcess(@ModelAttribute UserRecoverPasswordDto userDTO,Model model) {

        user = userService.findUserDBByUserEmail(userDTO.getEmail());
        System.out.println(listCheckEnterOTPToBlockEmail5M.toString());
        if (user != null) {
            if(user.isDeleted()){
                model.addAttribute("user",userDTO);
                model.addAttribute("error", "Tài khoản của bạn đã bị khóa");
                return "publics/forgot-password";
            }
            if(listCheckEnterOTPToBlockEmail5M.containsKey(userDTO.getEmail())){
                model.addAttribute("user",userDTO);
                model.addAttribute("errorForgot5M",true);
                return "publics/forgot-password";
            }
            session.setAttribute("flashMemoryEmail",userDTO.getEmail());
            return "redirect:/forgotPassword/otp";
        }
        // If no user found with either email, return error
        return "redirect:/forgotPassword?error"; // No user found
    }

    @GetMapping("/forgotPassword/otp")
    public String showOtp(Model model) throws MessagingException, InterruptedException, ExecutionException{

            // Lấy email từ hàm trên truyền xuống
            String email = (String) session.getAttribute("flashMemoryEmail");
            session.removeAttribute("flashMemoryEmail");

            if(email == null){
                return "redirect:/forgotPassword";
            }

            // Nếu trong list có email rồi - nghĩa là đã gửi otp rồi thì load lại sẽ không gửi nữa
            if (listEmailEnterOtp.containsValue(email)) {
                Integer idOtp = getKeyFromValue(listEmailEnterOtp, email);
                model.addAttribute("idOtp",idOtp);
                model.addAttribute("email",email);
                model.addAttribute("action", "/forgotPassword/otp");
                model.addAttribute("actionResend", "/forgotPassword/resend-otp");
                return "publics/verify-otp.html";
            } else {
                // tạo mã otp
                String otp = emailService.createOtp();
                // Tạo id cho otp
                int idOtp = emailService.createIDOTP();
                listOtp.put(idOtp,otp);
                listEmailEnterOtp.put(idOtp,email);

                emailService.sendEmail(user.getEmail(), otp);
                // nếu session timesEnterOtp null thì mới tạo mới
                if (session.getAttribute(email + "times") == null) {
                    session.setAttribute(email + "times", 5);
                    session.setMaxInactiveInterval(60 * 5);
                }
                TimerTask task = new TimerTask() {
                    public void run() {
                        System.out.println("Delete otp of email " + listEmailEnterOtp.get(idOtp));
                        listOtp.remove(idOtp);
                        listEmailEnterOtp.remove(idOtp);
                    }
                };

                Timer timer = new Timer();
                timer.schedule(task, 60 * 1000);

                model.addAttribute("idOtp",idOtp);
                model.addAttribute("email",email);
                model.addAttribute("action", "/forgotPassword/otp");
                model.addAttribute("actionResend", "/forgotPassword/resend-otp");
                return "publics/verify-otp.html";
            }
        }


    @GetMapping("forgotPassword/resend-otp")
    public String resendOtp(Model model,  @RequestParam(value = "id", required = false ) Integer idOtp, @RequestParam(value = "email", required = false ) String email) throws MessagingException {
        if (email != null) {
            session.removeAttribute(email + "times");
            if (idOtp != null) {
                listOtp.remove(idOtp);
                listEmailEnterOtp.remove(idOtp);
            }

            // tạo mã otp mới
            String otp = emailService.createOtp();
            // Tạo id mới cho otp
            int newIdOtp = emailService.createIDOTP();
            listOtp.put(newIdOtp,otp);
            listEmailEnterOtp.put(newIdOtp,email);

            // gửi otp cho email
            emailService.sendEmail(email, otp);

            // set countdown
            session.setAttribute(email + "times", 5);

            model.addAttribute("idOtp",newIdOtp);
            model.addAttribute("email",email);
            model.addAttribute("action", "/forgotPassword/otp");
            model.addAttribute("actionResend", "/forgotPassword/resend-otp");
            return "publics/verify-otp.html";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/forgotPassword/otp")
    public String verifyOtp(Model model, @RequestParam String otp,  @RequestParam(value = "id", required = false ) int idOtp,@RequestParam(value = "email", required = false ) String email) {
//        String code = (String) session.getAttribute("otp-forgotpassword");
        String code = listOtp.get(idOtp);
        if (code != null && code.equals(otp)) {
            session.removeAttribute(email + "times");
            listOtp.remove(idOtp);
            listEmailEnterOtp.remove(idOtp);
            session.setAttribute("flashMemorySendEmail",email);
            return "redirect:/forgotPassword/reset";
        } else if (code == null) {
            model.addAttribute("idOtp",idOtp);
            model.addAttribute("email",email);
            model.addAttribute("action", "/forgotPassword/otp");
            model.addAttribute("actionResend", "/forgotPassword/resend-otp");
            model.addAttribute("error", "OTP is expired");
            return "publics/verify-otp.html";
        } else {
            int timesEnterOtp = (int) session.getAttribute(email + "times") - 1;
            String error = "OTP không đúng, bạn còn " + timesEnterOtp + " lần nhập";
            session.setAttribute(email + "times",timesEnterOtp);
            if (timesEnterOtp > 0){
                model.addAttribute("idOtp",idOtp);
                model.addAttribute("email",email);
                model.addAttribute("action", "/forgotPassword/otp");
                model.addAttribute("actionResend", "/forgotPassword/resend-otp");
                model.addAttribute("error", error);
                return "publics/verify-otp.html";
            } else {
                listCheckEnterOTPToBlockEmail5M.put(email,true);
                TimerTask task = new TimerTask() {
                    public void run() {
                        listCheckEnterOTPToBlockEmail5M.remove(email);
                        System.out.println("Now user can forgot password");
                    }
                };

                Timer timer = new Timer();
                timer.schedule(task, 60 * 1000);
                listOtp.remove(idOtp);
                listEmailEnterOtp.remove(idOtp);
                session.removeAttribute(email + "times");
                return "redirect:/forgotPassword";
            }
        }
    }

    @GetMapping("/forgotPassword/reset")
    public String showPage(Model model) {
        String email = (String) session.getAttribute("flashMemorySendEmail");
        session.removeAttribute("flashMemorySendEmail");
        User user = userService.findUserDBByUserEmail(email);
        System.out.println(email);
        if (user == null) {
            return "redirect:/";
        } else {
            model.addAttribute("email",email);
            model.addAttribute("user", new UserResetPasswordDto());
            return "publics/reset-password.html";
        }
    }

    @PostMapping("/forgotPassword/reset")
    public String changePassword(@Valid @ModelAttribute("user") UserResetPasswordDto userResetPasswordDto,
            BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,
                                 @RequestParam(value = "email", required = false ) String email) {
        User rawUser = userService.findUserDBByUserEmail(email);
        if (rawUser == null) {
            return "redirect:/";
        }

        // Check if new passwords match
        if (!userResetPasswordDto.getNewPassword().equals(userResetPasswordDto.getConfirmPassword())) {
            bindingResult.addError(new FieldError("user", "confirmPassword", "Mật khẩu mới và xác nhận mật khẩu không khớp!"));
        }

        if (bindingResult.hasErrors()) {
            return "publics/reset-password";
        }

        // Change the user's password
        userService.changePassword(rawUser.getId(), userResetPasswordDto.getNewPassword());
        redirectAttributes.addFlashAttribute("passwordResetted", true);
        return "redirect:/login";
    }

    private Integer getKeyFromValue(Map<Integer, String> map, String value) {
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
