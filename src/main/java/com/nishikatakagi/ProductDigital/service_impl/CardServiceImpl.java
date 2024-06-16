package com.nishikatakagi.ProductDigital.service_impl;

import com.nishikatakagi.ProductDigital.dto.UserSessionDto;
import com.nishikatakagi.ProductDigital.model.Card;
import com.nishikatakagi.ProductDigital.model.User;
import com.nishikatakagi.ProductDigital.repository.CardRepository;
import com.nishikatakagi.ProductDigital.repository.UserRepository;
import com.nishikatakagi.ProductDigital.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Card> findAllCards() {
        return cardRepository.findAll();
    }

    @Override
    public Card findById(int id) {
        Optional<Card> optionalCardType = cardRepository.findById(id);
        return optionalCardType.orElseThrow();
    }

    @Override
    public void setActiveById(int id, UserSessionDto userDTO, boolean toDelete) {
        Card c = findById(id);
        User user = userRepository.findUserByUsername(userDTO.getUsername());
        c.setIsDeleted(toDelete);
        Date currentTime = Date.valueOf(LocalDateTime.now().toLocalDate());
        if (toDelete) {
            c.setDeletedDate(currentTime);
            c.setDeletedBy(user);
        } else {
            c.setLastUpdated(currentTime);
            c.setUpdatedBy(user);
        }
        cardRepository.save(c);
    }
}
