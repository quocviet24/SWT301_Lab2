package com.nishikatakagi.ProductDigital.service;

import java.util.List;

import com.nishikatakagi.ProductDigital.model.CartItem;

public interface CartItemService {
    // List<CartItem> findByUserId(int userId);

    List<CartItem> getCartItemsForUser(int userId);

    String addItemToCart(String username, Integer cardTypeId, Integer quantity);

    void removeItemFromCart(int userId, int cartItemId);

    CartItem updateItemQuantity(int userId, int cartItemId, int newQuantity);

    double calculateCartTotal(int userId);

    // List<CartItem> findByUserIdAndCardTypeId(int userId, int cardTypeId);

    // void deleteByUserIdAndId(int userId, int cartItemId);

}
