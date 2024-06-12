package com.nishikatakagi.ProductDigital.service_impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nishikatakagi.ProductDigital.model.Money;
import com.nishikatakagi.ProductDigital.repository.MoneyRepository;
import com.nishikatakagi.ProductDigital.service.MoneyService;

@Service
public class MoneyServiceImpl implements MoneyService {
    @Autowired
    MoneyRepository moneyRepository;
    @Override
    public List<Money> getAll() {
        return moneyRepository.findAll();
    }
    
}
