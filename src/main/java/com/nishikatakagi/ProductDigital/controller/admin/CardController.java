package com.nishikatakagi.ProductDigital.controller.admin;

import com.nishikatakagi.ProductDigital.dto.UserSessionDto;
import com.nishikatakagi.ProductDigital.model.Card;
import com.nishikatakagi.ProductDigital.service.CardService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/cardAdmin")
public class CardController {
    @Autowired
    CardService cardService;
    @Autowired
    HttpSession session;
    Logger logger = LoggerFactory.getLogger(CardController.class);

    @GetMapping("")
    public String displayCardType(Model model) {
        List<Card> listCard = cardService.findAllCards();

        for (Card c : listCard) {
            logger.info(c.toString());
        }

        model.addAttribute("listCard", listCard);
        return "pages/card/card.html";
    }

    @GetMapping("/setActive")
    public String setActiveCardType(@RequestParam("id") int id, @RequestParam("isDeleted") boolean toDelete,
            RedirectAttributes model) {
        // Get user for delete
        UserSessionDto user = (UserSessionDto) session.getAttribute("user_sess");
        if (user == null) {
            return "redirect:/login";
        }
        cardService.setActiveById(id, user, toDelete);
        return "redirect:/cardAdmin";
    }
}
