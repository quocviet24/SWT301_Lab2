package com.nishikatakagi.ProductDigital.controller;

import com.nishikatakagi.ProductDigital.service.EmailService;
import com.nishikatakagi.ProductDigital.service_impl.SecurityServiceImpl;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import com.nishikatakagi.ProductDigital.dto.UserSessionDto;
import com.nishikatakagi.ProductDigital.model.User;
import com.nishikatakagi.ProductDigital.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import java.util.Timer;
import java.util.TimerTask;


@RequestMapping("/profile")
@Controller
public class ProfileController {
	@Autowired
	private UserService userService;
	@Autowired
	SecurityServiceImpl security;

	@Autowired
	EmailService emailService;

	@Autowired
	HttpSession session;

	public ProfileController() {

	}

	@GetMapping("")
	public String showProfilePage(Model model, HttpSession session) {
		// có thể findById trả về null nên phải cho điều kiện orElse để xử lý
		// Get session user từ login
		UserSessionDto user = (UserSessionDto) session.getAttribute("user_sess");
		if (user != null) {
			// Proceed with user data
			return "publics/profile.html";
		} else {
			// Handle the case where the user is not found
			return "publics/index.html";
		}
	}

	@GetMapping("/update")
	public String showProfileUpdatePage(HttpSession session, Model model) {
		UserSessionDto user = (UserSessionDto) session.getAttribute("user_sess");
		if (user != null) {
			model.addAttribute("User", user);
			return "publics/profileUpdate";
		} else {
			return "publics/index.html"; // Redirect to home page if user is not in session
		}
	}

	@PostMapping("/update")
	public String updateProfilePage(@Valid @ModelAttribute("User") UserSessionDto userDto, BindingResult result,
			 Model model) {
		// Kiểm tra các dữ liệu nhập đầu vào có valid không
		if (userDto.getPhone().length() != 10 && userDto.getPhone().length() > 0) {
			result.addError(
					new FieldError("UserSessionDto", "phone", "Số điện thoại phải có 10 chữ số"));
		}
		if (result.hasErrors()) {
			model.addAttribute("User", userDto);
			return "publics/profileUpdate";
		} else {
			// Lấy đối tượng userSession trên phiên làm việc hiện tại
			UserSessionDto userDtoSession = (UserSessionDto) session.getAttribute("user_sess");
			// Lấy userName của user đó ( gần như là lấy khóa chính )
			// Tìm đối tượng user ( với đầy đủ data ) ở trong database theo userName
			User user = userService.findUserDBByUserSession(userDtoSession);
			// Kiểm tra có tìm được user không
			if (user == null) {
				return "publics/index.html";
			} else {
				// Nếu tìm thấy user tương ứng trong database thì tiến hành update use đó theo
				// thông tin được lưu tại userDto lấy từ trang html
				userService.updateUser(userDto, user);
				userDto.setEmail(user.getEmail());

				// update session with new userDto
				session.setAttribute("user_sess", userDto);
			}
			return "redirect:/profile";
		}

	}

	@GetMapping("confirmPass")
	public String showPageConfirmPassword(){
		return "publics/ConfirmPass.html";
	}

	@PostMapping("confirmPass")
	public String ConfirmPassword(Model model, @RequestParam("Password") String Password){
		UserSessionDto userDtoSession = (UserSessionDto) session.getAttribute("user_sess");
		User user = userService.findUserDBByUserSession(userDtoSession);
		if (!user.getPassword().equals(security.encode(Password))) {
			model.addAttribute("error","password không đúng");
			return "publics/ConfirmPass.html";
		}
		return "publics/EnterNewEmail.html";
	}

	@PostMapping("/EnterEmail")
	public String showPageEnterEmail(Model model, @RequestParam("email") String email) throws MessagingException {
		if (userService.checkEmailExist(email)){
			// check username exist or not
			model.addAttribute("exit",true);
			return "publics/EnterNewEmail.html";
		}
		String otp = emailService.createOtp();
		// nếu mã otp đã tồn tại - vừa gửi thì không gửi nữa, quay lại trang với dòng thông báo
		// mã otp đã được gửi
		if (session.getAttribute("otp-newemail") != null) {
			model.addAttribute("email",email);
			model.addAttribute("action","/profile/otp");
			model.addAttribute("actionResend","aa");
			return "publics/verify-otp.html";
		}

		// create OTP if this doesn't exit
		session.setAttribute("otp-newemail", otp);
		session.setMaxInactiveInterval(60 * 1);
		emailService.sendEmail(email,otp);
		// nếu session timesEnterOtp null thì mới tạo mới
		if (session.getAttribute("timesEnterOtp-newemail") == null) {
			session.setAttribute("timesEnterOtp-newemail", 5);
		}
		model.addAttribute("email",email);
		model.addAttribute("action","/profile/otp");
		model.addAttribute("actionResend","aa");
		return "publics/verify-otp.html";
	}

	@GetMapping("/otp")
	public String showPageEnterOtp(Model model) throws MessagingException {
		model.addAttribute("action","/profile/otp");
		model.addAttribute("actionResend","aa");
		return "publics/verify-otp.html";
	}

	@PostMapping("/otp")
	public String handlerEnterEmail(Model model, @RequestParam String otp, @RequestParam("email") String email){
		String code = (String) session.getAttribute("otp-newemail");
		if (code != null && code.equals(otp)) {
			session.removeAttribute("otp-newemail");
			UserSessionDto userDtoSession = (UserSessionDto) session.getAttribute("user_sess");
			userDtoSession.setEmail(email);
			User user = userService.findUserDBByUserSession(userDtoSession);
			userService.updateUserEmail(user,email);
			session.setAttribute("user_sess", userDtoSession);
			return "redirect:/profile";
		} else if (code == null) {
			model.addAttribute("action","/profile/otp");
			model.addAttribute("actionResend","aa");
			model.addAttribute("error", "OTP is expired");
			return "publics/verify-otp.html";
		} else {
			int timesEnterOtpCE = (int) session.getAttribute("timesEnterOtp-newemail") - 1;
			String error = "OTP không đúng, bạn còn " + timesEnterOtpCE + " lần nhập";
			session.setAttribute("timesEnterOtp-newemail",timesEnterOtpCE);
			if (timesEnterOtpCE != 0){
				model.addAttribute("action","/profile/otp");
				model.addAttribute("actionResend","aa");
				model.addAttribute("error", error);
				return "publics/verify-otp.html";
			} else {
//				listCheckEnterOTPToBlockEmail5M.put(user.getEmail(),true);
//				TimerTask task = new TimerTask() {
//					public void run() {
//						listCheckEnterOTPToBlockEmail5M.remove(user.getEmail());
//						System.out.println("Now user can forgot password");
//					}
//				};
//
//				Timer timer = new Timer();
//				timer.schedule(task, 60 * 1000);
				session.removeAttribute("otp-newemail");
				session.removeAttribute("timesEnterOtp-newemail");
				return "redirect:/profile";
			}
		}
	}
}
