package com.nishikatakagi.ProductDigital.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
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
            session.setAttribute("rawUser-forgotpassword", user);
            return "redirect:/forgotPassword/otp";
        }
        // If no user found with either email, return error
        return "redirect:/forgotPassword?error"; // No user found
    }

    @GetMapping("/forgotPassword/otp")
    public String showOtp(Model model) throws MessagingException, InterruptedException, ExecutionException{
        User user = (User) session.getAttribute("rawUser-forgotpassword");
        if (user == null) {
            return "redirect:/";
        } else {
            // tạo mã otp
            String otp = emailService.createOtp();
            // nếu mã otp đã tồn tại - vừa gửi thì không gửi nữa, quay lại trang forgot password với dòng thông báo
            // mã otp đã được gửi
            if (session.getAttribute("otp-forgotpassword") != null) {
                model.addAttribute("action", "/forgotPassword/otp");
                model.addAttribute("actionResend", "/forgotPassword/resend-otp");
                return "publics/verify-otp.html";
            }

            // create OTP if this doesn't exit
            session.setAttribute("otp-forgotpassword", otp);
            session.setMaxInactiveInterval(60 * 5);
            emailService.sendEmail(user.getEmail(), otp);
        // nếu session timesEnterOtp null thì mới tạo mới
        if (session.getAttribute("timesEnterOtp-forgotpassword") == null) {
            session.setAttribute("timesEnterOtp-forgotpassword", 5);
        }
        // gửi email với mã otp

            model.addAttribute("action", "/forgotPassword/otp");
            model.addAttribute("actionResend", "/forgotPassword/resend-otp");
            //model.addAttribute("emailSent", statusEmailSent);
            return "publics/verify-otp.html";
        }
    }

    @GetMapping("forgotPassword/resend-otp")
    public String resendOtp(Model model) throws MessagingException {
        User user = (User) session.getAttribute("rawUser-forgotpassword");
        if (user != null) {
            String otp = emailService.createOtp();
            session.setAttribute("otp-forgotpassword", otp);
            session.setMaxInactiveInterval(60 * 5);

            // xóa countdown enter otp và tạo lại
            session.removeAttribute("timesEnterOtp-forgotpassword");
            session.setAttribute("timesEnterOtp-forgotpassword", 5);

            // gửi otp cho email
            emailService.sendEmail(user.getEmail(), otp);

            model.addAttribute("action", "/forgotPassword/otp");
            model.addAttribute("actionResend", "/forgotPassword/resend-otp");
            return "publics/verify-otp.html";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/forgotPassword/otp")
    public String verifyOtp(Model model, @RequestParam String otp) {
        String code = (String) session.getAttribute("otp-forgotpassword");
        if (code != null && code.equals(otp)) {
            session.removeAttribute("otp-forgotpassword");
            return "redirect:/forgotPassword/reset";
        } else if (code == null) {
            model.addAttribute("action", "/forgotPassword/otp");
            model.addAttribute("actionResend", "/forgotPassword/resend-otp");
            model.addAttribute("error", "OTP is expired");
            return "publics/verify-otp.html";
        } else {
            int timesEnterOtp = (int) session.getAttribute("timesEnterOtp-forgotpassword") - 1;
            String error = "OTP không đúng, bạn còn " + timesEnterOtp + " lần nhập";
            session.setAttribute("timesEnterOtp-forgotpassword",timesEnterOtp);
            if (timesEnterOtp != 0){
                model.addAttribute("action", "/forgotPassword/otp");
                model.addAttribute("actionResend", "/forgotPassword/resend-otp");
                model.addAttribute("error", error);
                return "publics/verify-otp.html";
            } else {
                listCheckEnterOTPToBlockEmail5M.put(user.getEmail(),true);
                TimerTask task = new TimerTask() {
                    public void run() {
                        listCheckEnterOTPToBlockEmail5M.remove(user.getEmail());
                        System.out.println("Now user can forgot password");
                    }
                };

                Timer timer = new Timer();
                timer.schedule(task, 60 * 1000);
                session.removeAttribute("otp");
                session.removeAttribute("timesEnterOtp");
                return "redirect:/forgotPassword";
            }
        }
    }

    @GetMapping("/forgotPassword/reset")
    public String showPage(Model model) {
        User user = (User) session.getAttribute("rawUser-forgotpassword");
        if (user == null) {
            return "redirect:/";
        } else {
            model.addAttribute("user", new UserResetPasswordDto());
            return "publics/reset-password.html";
        }
    }

    @PostMapping("/forgotPassword/reset")
    public String changePassword(@Valid @ModelAttribute("user") UserResetPasswordDto userResetPasswordDto,
            BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        User rawUser = (User) session.getAttribute("rawUser-forgotpassword");
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
}
