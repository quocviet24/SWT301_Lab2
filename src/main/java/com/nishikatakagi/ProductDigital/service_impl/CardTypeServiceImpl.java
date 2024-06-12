package com.nishikatakagi.ProductDigital.service_impl;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.nishikatakagi.ProductDigital.dto.UserSessionDto;
import com.nishikatakagi.ProductDigital.model.User;
import com.nishikatakagi.ProductDigital.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nishikatakagi.ProductDigital.model.CardType;
import com.nishikatakagi.ProductDigital.repository.CardTypeRepository;
import com.nishikatakagi.ProductDigital.service.CardTypeService;

@Service
public class CardTypeServiceImpl implements CardTypeService {

    @Autowired
    CardTypeRepository cardTypeRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<CardType> findAllCardTypes() {
        return cardTypeRepository.findAll();
    }

    @Override
    public CardType findById(int id) {
        Optional<CardType> optionalCardType = cardTypeRepository.findById(id);
        return optionalCardType.orElseThrow(); // Corrected
    }

    @Override
    public List<CardType> getCardTypeByPublisher(int publisherId) {
        return cardTypeRepository.findByPublisherId(publisherId);
    }

    @Override
    public CardType findCardTypeByPublisherAndMoney(Integer publisherId, Integer moneyId) {
        List<CardType> cardTypes = cardTypeRepository.findAll();
        for (CardType cardType : cardTypes) {
            if (cardType.getPublisher().getId() == publisherId && cardType.getMoney().getId() == moneyId) {
                return cardType;
            }
        }
        return null;
    }

    @Override
    public void setActiveById(int id, UserSessionDto userDTO, boolean toDeleted) {
        CardType ct = findById(id);
        User user = userRepository.findUserByUsername(userDTO.getUsername());
        ct.setIsDeleted(toDeleted);
        Date currentTime = Date.valueOf(LocalDateTime.now().toLocalDate());
        if (toDeleted) {
            ct.setDeletedDate(currentTime);
            ct.setDeletedBy(user);
        } else {
            ct.setDeletedBy(null);
        }
        cardTypeRepository.save(ct);
    }

}
