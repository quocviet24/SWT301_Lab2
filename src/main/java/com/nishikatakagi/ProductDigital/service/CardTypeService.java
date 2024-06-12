package com.nishikatakagi.ProductDigital.service;

import com.nishikatakagi.ProductDigital.dto.UserSessionDto;
import com.nishikatakagi.ProductDigital.model.CardType;
import java.util.List;

public interface CardTypeService {
    public List<CardType> findAllCardTypes();

    public CardType findById(int id);

    public List<CardType> getCardTypeByPublisher(int publisherId);

    public CardType findCardTypeByPublisherAndMoney(Integer publisherId, Integer moneyId);

    public void setActiveById(int id, UserSessionDto user, boolean toDeleted);
}
