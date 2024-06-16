package com.nishikatakagi.ProductDigital.service;

import com.nishikatakagi.ProductDigital.dto.UserSessionDto;
import com.nishikatakagi.ProductDigital.model.Card;

import java.util.List;

public interface CardService {
    public List<Card> findAllCards();

    public void setActiveById(int id, UserSessionDto user, boolean toDelete);

    public Card findById(int id);
}
