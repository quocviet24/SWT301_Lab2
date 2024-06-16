package com.nishikatakagi.ProductDigital.controller;

import java.util.List;

import com.nishikatakagi.ProductDigital.dto.CheckoutItemDTO;
import com.nishikatakagi.ProductDigital.dto.UserSessionDto;
import com.nishikatakagi.ProductDigital.model.Card;

import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nishikatakagi.ProductDigital.model.Order;
import com.nishikatakagi.ProductDigital.model.User;
import com.nishikatakagi.ProductDigital.service.OrderService;
import com.nishikatakagi.ProductDigital.service.UserService;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/order")
@Controller
public class OrderListController {
    @Autowired
    private HttpSession session;
    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    Logger logger = LoggerFactory.getLogger(OrderListController.class);

    @GetMapping("")
    public String showOrderList(Model model) {
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

    @GetMapping("/checkout")
    public String doCheckout(Model model, @RequestParam(value = "cardTypeId", required = false) Integer cardTypeId,
            @RequestParam(value = "quantity", required = false) Integer quantity) {
        User user = userService.findUserDBByUserSession((UserSessionDto) session.getAttribute("user_sess"));
        String result = orderService.doCheckout(cardTypeId, quantity, user);
        if (!result.equals("Thanh toán thành công!")) {
            model.addAttribute("error", result);
            return "redirect:/product";
        }

        // After checkout, fetch the latest order for the user
        List<Order> orders = orderService.findOrdersByUser(user);
        Order latestOrder = orders.get(orders.size() - 1); // Assuming the latest order is the last one in the list
        List<Card> cards = orderService.findCardsByOrderId(latestOrder.getId());

        model.addAttribute("cards", cards);
        model.addAttribute("order", latestOrder);

        return "publics/OrderDetail";
    }

    @PostMapping("/checkout")
    public String checkoutFromCart(@RequestBody List<CheckoutItemDTO> selectedItems, Model model) {
        User user = userService.findUserDBByUserSession((UserSessionDto) session.getAttribute("user_sess"));
        logger.info(user.toString());
        String result = orderService.checkoutFromCart(selectedItems, user);

        for (CheckoutItemDTO item : selectedItems){
            logger.info(item.toString());
        }

        if (!result.equals("Thanh toán thành công!")) {
            model.addAttribute("error", result);
            return "redirect:/cart";
        }

        // After checkout, fetch the latest order for the user
        List<Order> orders = orderService.findOrdersByUser(user);
        Order latestOrder = orders.get(orders.size() - 1); // Assuming the latest order is the last one in the list
        List<Card> cards = orderService.findCardsByOrderId(latestOrder.getId());

        model.addAttribute("cards", cards);
        model.addAttribute("order", latestOrder);

        return "publics/OrderDetail";
    }
}
