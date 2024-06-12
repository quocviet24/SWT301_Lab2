package com.nishikatakagi.ProductDigital.service_impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nishikatakagi.ProductDigital.model.CardType;
import com.nishikatakagi.ProductDigital.model.CartItem;
import com.nishikatakagi.ProductDigital.model.User;
import com.nishikatakagi.ProductDigital.repository.CartItemRepository;
import com.nishikatakagi.ProductDigital.service.CardTypeService;
import com.nishikatakagi.ProductDigital.service.CartItemService;
import com.nishikatakagi.ProductDigital.service.UserService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    UserService userService;
    @Autowired
    CardTypeService cardTypeService;

    @Override
    public List<CartItem> getCartItemsForUser(int userId) {
        return cartItemRepository.findByUserId(userId);
    }

    @Override
    public String addItemToCart(String username, Integer cardTypeId, Integer quantity) {
        User user = userService.findByUsername(username);
        CardType cardType = cardTypeService.findById(cardTypeId);
        if (cardTypeId == null || quantity == null) {
            return "Vui lòng chọn sản phẩm và số lượng!";
        }
        if (quantity <= 0 || quantity > cardType.getInStock()) {
            return "Số lượng không hợp lệ!";
        }
        // Check if item already exists in cart and update quantity if needed
        CartItem cartItem = cartItemRepository.findByUserIdAndCardTypeId(user.getId(), cardTypeId);
        if (cartItem != null) {
            if (quantity <= 0 || quantity + cartItem.getQuantity() > cardType.getInStock()) {
                return "Thêm vào giỏ hàng thất bại, sản phẩm đã tồn tại trong giỏ hàng, số lượng bạn nhập thêm kho không đủ để cung cấp!";
            }
            // Update existing item with the same cardTypeId
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartItem.setTotal(cartItem.getTotal() + cardType.getMoney().getUnitFund() * quantity);
            cartItemRepository.save(cartItem);
            return "Thêm vào giỏ hàng thành công!";
        } else {
            if (quantity <= 0 || quantity > cardType.getInStock()) {
                return "Số lượng không hợp lệ!";
            }
            // Create new CartItem
            cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setCardType(cardType);
            cartItem.setQuantity(quantity);
            cartItem.setTotal(cardType.getMoney().getUnitFund() * quantity);
            cartItemRepository.save(cartItem);
            return "Thêm vào giỏ hàng thành công!";
        }
    }

    @Override
    public void removeItemFromCart(int userId, int cartItemId) {
        cartItemRepository.deleteByUserIdAndId(userId, cartItemId);
    }

    @Override
    public CartItem updateItemQuantity(int userId, int cartItemId, int newQuantity) {
        CartItem cartItem = cartItemRepository.findById(cartItemId);
        if (cartItem.getUser().getId() == userId) {
            cartItem.setQuantity(newQuantity);
            return cartItemRepository.save(cartItem);
        }
        return null;
    }

    @Override
    public double calculateCartTotal(int userId) {
        List<CartItem> cartItems = cartItemRepository.findByUserId(userId);
        return cartItems.stream()
                .mapToDouble(CartItem::getTotal)
                .sum();
    }

}
