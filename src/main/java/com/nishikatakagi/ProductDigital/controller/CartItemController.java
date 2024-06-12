package com.nishikatakagi.ProductDigital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nishikatakagi.ProductDigital.dto.UserSessionDto;
import com.nishikatakagi.ProductDigital.service.CardTypeService;
import com.nishikatakagi.ProductDigital.service.CartItemService;
import com.nishikatakagi.ProductDigital.service.UserService;

import jakarta.servlet.http.HttpSession;


@RequestMapping("/cart")
@Controller
public class CartItemController {

    @Autowired
    HttpSession session;
    @Autowired
    CardTypeService cardTypeService;
    @Autowired
    CartItemService cartItemService;
    @Autowired
    UserService userService;

    @GetMapping("/add")
    public String addToCart(RedirectAttributes redirectAttributes,@RequestParam(value="cardTypeId",required = false) Integer cardTypeId, @RequestParam(value="quantity",required = false) Integer quantity) {
        UserSessionDto userSession = (UserSessionDto) session.getAttribute("user_sess");
        if(userSession == null) {
            return "redirect:/login";
        }
        String message=cartItemService.addItemToCart(userSession.getUsername(), cardTypeId, quantity);
        redirectAttributes.addFlashAttribute("message",message);
        return "redirect:/product";
    }
    
}
