package com.nishikatakagi.ProductDigital.controller;

import java.security.Principal;
import java.util.List;

import com.nishikatakagi.ProductDigital.dto.UserSessionDto;
import com.nishikatakagi.ProductDigital.model.Card;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nishikatakagi.ProductDigital.model.Order;
import com.nishikatakagi.ProductDigital.model.User;
import com.nishikatakagi.ProductDigital.service.OrderService;
import com.nishikatakagi.ProductDigital.service.UserService;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/orderlist")
@Controller
public class OrderListController {
    @Autowired
    private HttpSession session;
    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @GetMapping("")
    public String showOrderList( Model model) {
        User user = userService.findUserDBByUserSession((UserSessionDto) session.getAttribute("user_sess"));
        List<Order> orders = orderService.findOrdersByUser(user);
        model.addAttribute("orders", orders);

        return "publics/OrderList";
    }

    @GetMapping("/detail")
    public String showOrderDetail(Model model, @RequestParam int id) {
        List<Card> cards = orderService.findCardsByOrderId(id);
        model.addAttribute("cards", cards);

        return "publics/OrderDetail";
    }
}
