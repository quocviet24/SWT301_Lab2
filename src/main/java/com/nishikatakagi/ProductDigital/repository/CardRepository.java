package com.nishikatakagi.ProductDigital.repository;

import com.nishikatakagi.ProductDigital.model.Card;
import com.nishikatakagi.ProductDigital.model.CardType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
    List<Card> findByCardTypeAndIsDeletedOrderByExpiryDateAsc(CardType cardType, Boolean isDeleted);

    Optional<Card> findById(int id);
}
