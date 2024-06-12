package com.nishikatakagi.ProductDigital.repository;

import com.nishikatakagi.ProductDigital.model.Card;
import com.nishikatakagi.ProductDigital.model.Order;
import com.nishikatakagi.ProductDigital.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
    Optional<Card> findById(int id);
}
