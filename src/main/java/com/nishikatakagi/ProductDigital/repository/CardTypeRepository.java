package com.nishikatakagi.ProductDigital.repository;

import com.nishikatakagi.ProductDigital.model.CardType;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardTypeRepository extends JpaRepository<CardType, Integer> {
    List<CardType> findByPublisherId(int publisherId);
    //Lacel
}

