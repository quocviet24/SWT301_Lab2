package com.nishikatakagi.ProductDigital.service_impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.nishikatakagi.ProductDigital.model.Card;
import com.nishikatakagi.ProductDigital.model.OrderDetail;
import com.nishikatakagi.ProductDigital.repository.CardRepository;
import com.nishikatakagi.ProductDigital.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nishikatakagi.ProductDigital.model.Order;
import com.nishikatakagi.ProductDigital.model.User;
import com.nishikatakagi.ProductDigital.repository.OrderRepository;
import com.nishikatakagi.ProductDigital.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Override
    public List<Order> findOrdersByUser(User user) {
        return orderRepository.findByUser(user);
    }

    @Override
    public List<Card> findCardsByOrderId(int orderId) {
        // Fetch the OrderDetail from the repository
        Optional<OrderDetail> orderDetailOptional = orderDetailRepository.findById(orderId);

        // Check if the OrderDetail exists
        if (!orderDetailOptional.isPresent()) {
            return Collections.emptyList();
        }

        // Get the OrderDetail object
        OrderDetail orderDetail = orderDetailOptional.get();

        // Get the Card associated with the OrderDetail
        Card card = orderDetail.getCard();

        // Fetch the Card from the repository and return it as a list
        Optional<Card> cardOptional = cardRepository.findById(card.getId());
        if (cardOptional.isPresent()) {
            return Collections.singletonList(cardOptional.get());
        } else {
            return Collections.emptyList();
        }
    }


    /*public List<Card> findCardsByOrderId(int orderId) {
        OrderDetail orderDetail = (OrderDetail) orderDetailRepository.findById(orderId).get();
        Card cardId = orderDetail.getCard();
        List<Card> cardList = cardRepository.findAllById(cardId.getId());
    }*/
}
