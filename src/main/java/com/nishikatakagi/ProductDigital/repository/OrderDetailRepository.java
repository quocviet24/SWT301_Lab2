package com.nishikatakagi.ProductDigital.repository;

import com.nishikatakagi.ProductDigital.model.Order;
import com.nishikatakagi.ProductDigital.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

}
