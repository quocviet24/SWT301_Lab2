package com.nishikatakagi.ProductDigital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nishikatakagi.ProductDigital.model.Money;

@Repository
public interface MoneyRepository extends JpaRepository<Money,Integer>{
    
}
