package com.nishikatakagi.ProductDigital.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nishikatakagi.ProductDigital.model.CartItem;
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    List<CartItem> findByUserId(int userId);

    CartItem findByUserIdAndCardTypeId(int userId, int cardTypeId);

    void deleteByUserIdAndId(int userId, int cartItemId);

    CartItem findById(int cartItemId);
}
