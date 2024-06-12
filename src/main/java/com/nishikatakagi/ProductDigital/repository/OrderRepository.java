package com.nishikatakagi.ProductDigital.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nishikatakagi.ProductDigital.model.Order;
import com.nishikatakagi.ProductDigital.model.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUser(User user);
}
