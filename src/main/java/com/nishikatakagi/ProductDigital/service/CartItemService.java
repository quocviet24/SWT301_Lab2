package com.nishikatakagi.ProductDigital.service;

import java.util.List;

import com.nishikatakagi.ProductDigital.model.CartItem;

public interface CartItemService {
    // List<CartItem> findByUserId(int userId);

    List<CartItem> getCartItemsForUser(int userId);

    String addItemToCart(String username, Integer cardTypeId, Integer quantity);

    CartItem updateItemQuantity(String username, int cardTypeId, int newQuantity);

    double calculateCartTotal(int userId);

    List<CartItem> getCartDetails(String username);

    void deleteCartItem(int cartItemId);

    void updateQuantity(int cartItemId, int quantity);

    int getCartItemCount(String username);

}
