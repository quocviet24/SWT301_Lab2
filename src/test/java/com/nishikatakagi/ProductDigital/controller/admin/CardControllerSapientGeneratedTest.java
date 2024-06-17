package com.nishikatakagi.ProductDigital.controller.admin;

import org.junit.jupiter.api.Timeout;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.mockito.MockitoAnnotations;
import com.nishikatakagi.ProductDigital.dto.UserSessionDto;
import org.springframework.ui.Model;
import com.nishikatakagi.ProductDigital.service.CardService;
import java.util.ArrayList;
import jakarta.servlet.http.HttpSession;
import com.nishikatakagi.ProductDigital.model.Card;
import static org.mockito.Mockito.doNothing;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.doReturn;

@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
class CardControllerSapientGeneratedTest {

    private final CardService cardServiceMock = mock(CardService.class, "cardService");

    private final HttpSession sessionMock = mock(HttpSession.class, "session");

    private AutoCloseable autoCloseableMocks;

    @InjectMocks()
    private CardController target;

    @AfterEach()
    public void afterTest() throws Exception {
        if (autoCloseableMocks != null)
            autoCloseableMocks.close();
    }

    //Sapient generated method id: ${displayCardTypeWhenListCardIsNotEmpty}, hash: 619BC613937ABFFB0485E7E2D8F4DD39
    @Test()
    void displayCardTypeWhenListCardIsNotEmpty() {
        /* Branches:
         * (for-each(listCard)) : true
         */
         //Arrange Statement(s)
        Model modelMock = mock(Model.class);
        Model modelMock2 = mock(Model.class);
        Card cardMock = mock(Card.class, "object");
        List<Card> cardList = new ArrayList<>();
        cardList.add(cardMock);
        doReturn(modelMock2).when(modelMock).addAttribute("listCard", cardList);
        target = new CardController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        doReturn(cardList).when(cardServiceMock).findAllCards();
        
        //Act Statement(s)
        String result = target.displayCardType(modelMock);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("pages/card/card.html"));
            verify(modelMock).addAttribute("listCard", cardList);
            verify(cardServiceMock).findAllCards();
        });
    }

    //Sapient generated method id: ${setActiveCardTypeWhenUserIsNull}, hash: 03952012708F67130EE47475F8C988A9
    @Test()
    void setActiveCardTypeWhenUserIsNull() {
        /* Branches:
         * (user == null) : true
         */
         //Arrange Statement(s)
        target = new CardController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        doReturn(null).when(sessionMock).getAttribute("user_sess");
        RedirectAttributes redirectAttributesMock = mock(RedirectAttributes.class);
        
        //Act Statement(s)
        String result = target.setActiveCardType(0, false, redirectAttributesMock);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("redirect:/login"));
            verify(sessionMock).getAttribute("user_sess");
        });
    }

    //Sapient generated method id: ${setActiveCardTypeWhenUserIsNotNull}, hash: 7DD3C02A69357AB473CA8813259C9D00
    @Test()
    void setActiveCardTypeWhenUserIsNotNull() {
        /* Branches:
         * (user == null) : false
         */
         //Arrange Statement(s)
        target = new CardController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        UserSessionDto userSessionDtoMock = mock(UserSessionDto.class);
        doReturn(userSessionDtoMock).when(sessionMock).getAttribute("user_sess");
        doNothing().when(cardServiceMock).setActiveById(0, userSessionDtoMock, false);
        RedirectAttributes redirectAttributesMock = mock(RedirectAttributes.class);
        
        //Act Statement(s)
        String result = target.setActiveCardType(0, false, redirectAttributesMock);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("redirect:/cardAdmin"));
            verify(sessionMock).getAttribute("user_sess");
            verify(cardServiceMock).setActiveById(0, userSessionDtoMock, false);
        });
    }
}
