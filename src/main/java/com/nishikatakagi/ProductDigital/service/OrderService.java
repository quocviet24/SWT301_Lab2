package com.nishikatakagi.ProductDigital.service;

import java.util.List;

import com.nishikatakagi.ProductDigital.dto.CheckoutItemDTO;
import com.nishikatakagi.ProductDigital.model.Card;
import com.nishikatakagi.ProductDigital.model.Order;
import com.nishikatakagi.ProductDigital.model.User;

public interface OrderService {
    List<Order> findOrdersByUser(User user);

    List<Card> findCardsByOrderId(int orderId);

    String doCheckout(Integer cardTypeId, Integer quantity, User user);

    String checkoutFromCart(List<CheckoutItemDTO> selectedItems, User user);
}
