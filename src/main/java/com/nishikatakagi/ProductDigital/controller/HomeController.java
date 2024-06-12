package com.nishikatakagi.ProductDigital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@RequestMapping("")
@Controller
public class HomeController {

	@Autowired
	private HttpSession session;
	
	public HomeController() {
	}
	
	
	@GetMapping("logout")
    public String showRegisterPage() {
       session.removeAttribute("user_sess");
		return "redirect:/";
    }

}
