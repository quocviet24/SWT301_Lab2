package com.nishikatakagi.ProductDigital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nishikatakagi.ProductDigital.dto.UserSessionDto;
import com.nishikatakagi.ProductDigital.model.User;
import com.nishikatakagi.ProductDigital.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@RequestMapping("/profile")
@Controller
public class ProfileController {
	@Autowired
	private UserService userService;

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
			HttpSession session, Model model) {
		// Kiểm tra các dữ liệu nhập đầu vào có valid không
		// Số điện thoại phải có 10 số
		
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
				// thông tin được lưu tại userDto lấy từ
				// Trang html update lưu trữ lại
				userService.updateUser(userDto, user);

				// update session with new userDto
				session.setAttribute("user_sess", userDto);
			}
			return "redirect:/profile";
		}

	}

	@GetMapping("changeEmail")
	public String showPageConfirmPassword(){

		return null;
	}
}
