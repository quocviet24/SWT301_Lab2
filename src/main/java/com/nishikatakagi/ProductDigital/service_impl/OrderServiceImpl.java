package com.nishikatakagi.ProductDigital.service_impl;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.nishikatakagi.ProductDigital.dto.CheckoutItemDTO;
import com.nishikatakagi.ProductDigital.model.*;
import com.nishikatakagi.ProductDigital.repository.CardRepository;
import com.nishikatakagi.ProductDigital.repository.CardTypeRepository;
import com.nishikatakagi.ProductDigital.repository.CartItemRepository;
import com.nishikatakagi.ProductDigital.repository.OrderDetailRepository;
import com.nishikatakagi.ProductDigital.service.CardTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private CardTypeRepository cardTypeRepository;
    @Autowired
    CardTypeService cardTypeService;
    @Autowired
    CartItemRepository cartItemRepository;

    @Override
    public List<Order> findOrdersByUser(User user) {
        return orderRepository.findByUser(user);
    }

    @Override
    public List<Card> findCardsByOrderId(int orderId) {
        // Fetch all OrderDetails for the given orderId
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(orderId);

        // Map OrderDetails to Cards and collect them in a list
        return orderDetails.stream()
                .map(OrderDetail::getCard)
                .collect(Collectors.toList());
    }

    @Override
    public String doCheckout(Integer cardTypeId, Integer quantity, User user) {
        if (cardTypeId == null || quantity == null) {
            return "Vui lòng chọn sản phẩm và số lượng!";
        }

        Optional<CardType> cardTypeOpt = cardTypeRepository.findById(cardTypeId);
        if (!cardTypeOpt.isPresent()) {
            return "Loại thẻ không tồn tại!";
        }

        CardType cardType = cardTypeOpt.get();

        if (quantity <= 0 || quantity > cardType.getInStock()) {
            return "Số lượng không hợp lệ!";
        }

        List<Card> availableCards = cardRepository.findByCardTypeAndIsDeletedOrderByExpiryDateAsc(cardType, false);
        if (availableCards.size() < quantity) {
            return "Không đủ thẻ để thanh toán!";
        }

        // Lấy danh sách thẻ để thanh toán
        List<Card> cardsToCheckout = availableCards.subList(0, quantity);
        cardsToCheckout.forEach(card -> {
            card.setIsDeleted(true);
            card.setDeletedDate(new Date(System.currentTimeMillis()));
            card.setDeletedBy(user);
            cardRepository.save(card);
        });

        // Tạo đơn hàng mới
        Order order = new Order();
        order.setUser(user);
        order.setTotalMoney(cardType.getMoney().getUnitFund() * quantity);
        order.setStatus("complete");
        order.setOrderDate(new Date(System.currentTimeMillis()));
        orderRepository.save(order);

        // Tạo chi tiết đơn hàng mới
        cardsToCheckout.forEach(card -> {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setCard(card);
            orderDetailRepository.save(orderDetail);
        });

        // Cập nhật thông tin số lượng của CardType
        cardType.setInStock(cardType.getInStock() - quantity);
        cardType.setSoldQuantity(cardType.getSoldQuantity() + quantity);
        cardTypeRepository.save(cardType);

        return "Thanh toán thành công!";
    }

    //@Transactional
    @Override
    public String checkoutFromCart(List<CheckoutItemDTO> selectedItems, User user) {
        if (selectedItems == null || selectedItems.isEmpty()) {
            return "No items selected for checkout.";
        }

        // Create a new order
        Order order = new Order();
        order.setUser(user);
        order.setStatus("Pending");
        order.setOrderDate(new Date(System.currentTimeMillis()));
        orderRepository.save(order);

        for (CheckoutItemDTO item : selectedItems) {
            CardType cardType = cardTypeRepository.findById(item.getCardTypeId()).orElse(null);
            if (cardType == null) {
                return "Invalid card type selected.";
            }

            if (cardType.getInStock() < item.getQuantity()) {
                return "Insufficient stock for " + cardType.getPublisher().getName() + ".";
            }

            List<Card> cards = cardRepository.findByCardTypeAndIsDeletedOrderByExpiryDateAsc(cardType, false);
            if (cards.size() < item.getQuantity()) {
                return "Not enough cards to checkout.";
            }

            List<Card> cardsToCheckout = cards.subList(0, item.getQuantity());
            for (Card card : cardsToCheckout) {
                card.setIsDeleted(true);
                card.setDeletedDate(new Date(System.currentTimeMillis()));
                card.setDeletedBy(user);
                cardRepository.save(card);

                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrder(order);
                orderDetail.setCard(card);
                orderDetail.setQuantity(1);
                orderDetail.setTotal(card.getCardType().getMoney().getUnitFund());
                orderDetailRepository.save(orderDetail);
            }

            cardType.setInStock(cardType.getInStock() - item.getQuantity());
            cardType.setSoldQuantity(cardType.getSoldQuantity() + item.getQuantity());
            cardTypeRepository.save(cardType);

            CartItem cartItem = cartItemRepository.findByCardTypeAndUser(cardType, user);
            if (cartItem != null) {
                cartItemRepository.delete(cartItem);
            }
        }

        double totalAmount = selectedItems.stream()
                .mapToDouble(item -> item.getQuantity()
                        * cardTypeRepository.findById(item.getCardTypeId()).orElseThrow().getMoney().getUnitFund())
                .sum();
        order.setTotalMoney(totalAmount);
        order.setStatus("complete");
        orderRepository.save(order);

        return "Thanh toán thành công!";
    }

}
