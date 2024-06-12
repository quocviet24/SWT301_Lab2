package com.nishikatakagi.ProductDigital.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nishikatakagi.ProductDigital.dto.UserRegisterRequestDto;
import com.nishikatakagi.ProductDigital.mapper.UserRegisterMapper;
import com.nishikatakagi.ProductDigital.model.User;
import com.nishikatakagi.ProductDigital.service.CaptchaService;
import com.nishikatakagi.ProductDigital.service.EmailService;
import com.nishikatakagi.ProductDigital.service.UserService;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@EnableAsync
@RequestMapping("/register")
@Controller
public class RegisterController {

    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private CaptchaService captchaService;
    @Autowired
    HttpSession session;
    @Autowired
    private UserRegisterMapper userRegisterMapper;

    public Map<Integer, String[]> listCaptchaRegister = new ConcurrentHashMap<>();

    public Map<Integer, String> captchaValueRegister = new ConcurrentHashMap<>();

    public RegisterController() {
    }

    @GetMapping("")
    public String showRegisterPage(Model model) {
        // check if the user is already logged in
        if (session.getAttribute("user_sess") != null) {
            return "redirect:/";
        } else {
            //Tạo một id cho captcha
            int idCaptcha = captchaService.createIDCaptcha();

            // Tạo 4 giá trị captcha và lưu thành 1 string
            String value = "";
            int one = (int) (Math.random() * 10);
            value += one;
            int two = (int) (Math.random() * 10);
            value += two;
            int three = (int) (Math.random() * 10);
            value += three;
            int four = (int) (Math.random() * 10);
            value += four;

            // Lưu trữ giá trị captcha và id vào map
            captchaValueRegister.put(idCaptcha, value);
            // Lưu trữ captcha id và các giá trị captcha chuyển đổi sang html
            listCaptchaRegister.put(idCaptcha, captchaService.captchaValueHTML(one, two, three, four));

            // set model của id mới nhất
            model.addAttribute("idCaptcha", idCaptcha);
            model.addAttribute("captchaSvg", listCaptchaRegister);

            model.addAttribute("user", new UserRegisterRequestDto());
            return "publics/register";
        }

    }

    @PostMapping("")
    public String processRegistrationForm(
            @ModelAttribute("user") @Valid UserRegisterRequestDto user, BindingResult result, Model model, @RequestParam("idCaptcha") int idCaptcha) {

        String captchaValue = captchaValueRegister.get(idCaptcha);

        if (user.getRepassword() != null && !user.getPassword().equals(user.getRepassword())) {
            // check password and re-password
            result.addError(new FieldError("user", "repassword", "Mật khẩu không trùng khớp"));
        }
        if (!user.getPhone().isEmpty() && user.getPhone().length() != 10) {
            // check phone number
            result.addError(new FieldError("user", "phone", "Số điện thoại phải có 10 chữ số"));
        }
        if (!user.getCaptcha().equals(captchaValue)) {
            // check captcha
            result.addError(new FieldError("user", "captcha", "Captcha không đúng!"));
        }
        if (userService.checkUsernameExist(user.getUsername())) {
            // check username exist or not
            result.addError(new FieldError("user", "username", "Tên đăng nhập đã tồn tại"));
        }
        if (userService.checkEmailExist(user.getEmail())) {
            // check email exist or not
            result.addError(new FieldError("user", "email", "Email đã tồn tại"));
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);

            //Tạo một id cho captcha
            int idCaptchaAgain = captchaService.createIDCaptcha();

            // Tạo 4 giá trị captcha và lưu thành 1 string
            String newValue = "";
            int one = (int) (Math.random() * 10);
            newValue += one;
            int two = (int) (Math.random() * 10);
            newValue += two;
            int three = (int) (Math.random() * 10);
            newValue += three;
            int four = (int) (Math.random() * 10);
            newValue += four;

            // Lưu trữ giá trị captcha và id vào map
            captchaValueRegister.put(idCaptchaAgain, newValue);
            // Lưu trữ captcha id và các giá trị captcha chuyển đổi sang html
            listCaptchaRegister.put(idCaptchaAgain, captchaService.captchaValueHTML(one, two, three, four));

            // set model của id mới nhất
            model.addAttribute("idCaptcha", idCaptchaAgain);
            model.addAttribute("captchaSvg", listCaptchaRegister);

            return "publics/register";
        } else {
            User u = userRegisterMapper.toUser(user);
            u.setVerified(false);
            u.setDeleted(true);
            session.setAttribute("rawUser-register", u);
            userService.saveUser(u);
            return "redirect:register/otp";
        }
    }

    @GetMapping("/otp")
    public String showOtp(Model model) throws MessagingException {
        User user = (User) session.getAttribute("rawUser-register");
        if (user == null) {
            return "redirect:/";
        } else {
            // need to return then send email
            String otp = emailService.createOtp();
            emailService.sendEmail(user.getEmail(), otp);
            session.setAttribute("otp-register", otp);
            session.setMaxInactiveInterval(60 * 5);

            // nếu session timesEnterOtp null thì mới tạo mới
            if (session.getAttribute("timesEnterOtp-register") == null) {
                session.setAttribute("timesEnterOtp-register", 5);
            }
            model.addAttribute("action", "/register/otp");
            return "publics/verify-otp.html";
        }
    }

    @PostMapping("/otp")
    public String verifyOtp(Model model, @RequestParam String otp, RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("rawUser-register");
        if (user == null) {
            return "redirect:/";
        }
        String code = (String) session.getAttribute("otp-register");
        if (code != null && code.equals(otp)) {
            user.setVerified(true);
            user.setDeleted(false);
            userService.saveUser(user);
            session.removeAttribute("rawUser-register");
            session.removeAttribute("otp-register");
            redirectAttributes.addFlashAttribute("accountCreated", true);
            return "redirect:/login";
        } else if (code == null) {
            model.addAttribute("action", "/register/otp");
            model.addAttribute("error", "OTP is expired");
            userService.deleteUser(user);
            session.removeAttribute("rawUser-register");
            session.removeAttribute("otp-register");
            return "publics/verify-otp.html";
        } else {
            int timesEnterOtp = (int) session.getAttribute("timesEnterOtp-register") - 1;
            session.setAttribute("timesEnterOtp-register", timesEnterOtp);
            if (timesEnterOtp > 0) {
                model.addAttribute("action", "/register/otp");
                model.addAttribute("error", "OTP chưa đúng. Bạn còn " + timesEnterOtp + " lần thử");
                return "publics/verify-otp.html";
            } else {
                userService.deleteUser(user);  // Xóa người dùng nếu nhập sai OTP quá 5 lần
                session.removeAttribute("rawUser-register");
                session.removeAttribute("otp-register");
                session.removeAttribute("timesEnterOtp-register");
                return "redirect:/register";
            }
        }
    }

}
