package com.nishikatakagi.ProductDigital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nishikatakagi.ProductDigital.model.CardType;
import com.nishikatakagi.ProductDigital.model.Money;
import com.nishikatakagi.ProductDigital.model.Publisher;
import com.nishikatakagi.ProductDigital.service.CardTypeService;
import com.nishikatakagi.ProductDigital.service.MoneyService;
import com.nishikatakagi.ProductDigital.service.PublisherService;


@Controller
@RequestMapping("/product")
public class ViewCardTypeController {

    @Autowired
    CardTypeService cardTypeService;
    @Autowired
    PublisherService publisherService;
    @Autowired
    MoneyService moneyService;

    @GetMapping
    public String showPage(Model model) {
        List<Publisher> publishers = publisherService.getAllPublisher();
        model.addAttribute("publishers", publishers);
        List<Money> moneys = moneyService.getAll();
        model.addAttribute("moneys", moneys);
        return "/publics/cardtype-list";
    }
    @PostMapping
    public String showCardTypes(RedirectAttributes model,@RequestParam(value="publisherId",required = false) Integer publisherId,@RequestParam(value="moneyId",required = false) Integer moneyId) {
        model.addFlashAttribute("publisherId", publisherId);
        model.addFlashAttribute("moneyId", moneyId);
        if(publisherId == null || moneyId == null) {
            model.addFlashAttribute("error", "Vui lòng chọn nhà cung cấp và mệnh giá!");
            return "redirect:/product";
        }else{
            CardType cardType = cardTypeService.findCardTypeByPublisherAndMoney(publisherId, moneyId);
            if(cardType == null) {
                model.addFlashAttribute("error", "Không tìm thấy sản phẩm phù hợp!");
                return "redirect:/product";
            }
            model.addFlashAttribute("cardType", cardType);
            return "redirect:/product";
        }
    }
    
    

}
