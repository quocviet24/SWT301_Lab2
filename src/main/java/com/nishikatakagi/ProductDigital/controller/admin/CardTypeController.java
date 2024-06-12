package com.nishikatakagi.ProductDigital.controller.admin;

import com.nishikatakagi.ProductDigital.dto.UserSessionDto;
import com.nishikatakagi.ProductDigital.model.CardType;
import com.nishikatakagi.ProductDigital.service.CardTypeService;
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
@RequestMapping("/cardType")
public class CardTypeController {
    @Autowired
    CardTypeService cardTypeService;
    @Autowired
    HttpSession session;
    Logger logger = LoggerFactory.getLogger(CardTypeController.class);

    @GetMapping("")
    public String displayCardType(Model model) {
        List<CardType> listCT = cardTypeService.findAllCardTypes();

        for (CardType cardType : listCT) {
            logger.info(cardType.toString());
        }

        model.addAttribute("listCT", listCT);
        return "pages/cardType/cardType.html";
    }

    @GetMapping("/setActive")
    public String setActiveCardType(@RequestParam("id") int id, @RequestParam("isDeleted") boolean toDelete,RedirectAttributes model) {
        //Get user for delete
        UserSessionDto user = (UserSessionDto) session.getAttribute("user_sess");
        if (user == null) {
            return "redirect:/login";
        }
        cardTypeService.setActiveById(id, user, toDelete);
        return "redirect:/cardType";
    }
}
