package com.nishikatakagi.ProductDigital.controller;

import org.junit.jupiter.api.Timeout;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import com.nishikatakagi.ProductDigital.dto.CheckoutItemDTO;
import com.nishikatakagi.ProductDigital.service.OrderService;
import org.mockito.MockitoAnnotations;
import com.nishikatakagi.ProductDigital.model.Order;
import com.nishikatakagi.ProductDigital.dto.UserSessionDto;
import com.nishikatakagi.ProductDigital.model.User;
import org.springframework.ui.Model;
import com.nishikatakagi.ProductDigital.service.UserService;
import java.util.ArrayList;
import jakarta.servlet.http.HttpSession;
import com.nishikatakagi.ProductDigital.model.Card;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.mock;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.mockito.Mockito.doReturn;
import static org.hamcrest.Matchers.is;

@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
class OrderListControllerSapientGeneratedTest {

    private final HttpSession sessionMock = mock(HttpSession.class, "session");

    private final UserService userServiceMock = mock(UserService.class, "userService");

    private final OrderService orderServiceMock = mock(OrderService.class, "orderService");

    private AutoCloseable autoCloseableMocks;

    @InjectMocks()
    private OrderListController target;

    @AfterEach()
    public void afterTest() throws Exception {
        if (autoCloseableMocks != null)
            autoCloseableMocks.close();
    }

    //Sapient generated method id: ${showOrderListTest}, hash: 1FA44F6AAC47C6C99809DBE45C37810A
    @Test()
    void showOrderListTest() {
        //Arrange Statement(s)
        Model modelMock = mock(Model.class);
        Model modelMock2 = mock(Model.class);
        List<Order> orderList = new ArrayList<>();
        doReturn(modelMock2).when(modelMock).addAttribute("orders", orderList);
        target = new OrderListController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        UserSessionDto userSessionDtoMock = mock(UserSessionDto.class);
        doReturn(userSessionDtoMock).when(sessionMock).getAttribute("user_sess");
        User userMock = mock(User.class);
        doReturn(userMock).when(userServiceMock).findUserDBByUserSession(userSessionDtoMock);
        doReturn(orderList).when(orderServiceMock).findOrdersByUser(userMock);
        
        //Act Statement(s)
        String result = target.showOrderList(modelMock);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("publics/OrderList"));
            verify(modelMock).addAttribute("orders", orderList);
            verify(sessionMock).getAttribute("user_sess");
            verify(userServiceMock).findUserDBByUserSession(userSessionDtoMock);
            verify(orderServiceMock).findOrdersByUser(userMock);
        });
    }

    //Sapient generated method id: ${showOrderDetailTest}, hash: 7B5B114E9F1775BCF67D29B6F8A802D1
    @Test()
    void showOrderDetailTest() {
        //Arrange Statement(s)
        Model modelMock = mock(Model.class);
        Model modelMock2 = mock(Model.class);
        List<Card> cardList = new ArrayList<>();
        doReturn(modelMock2).when(modelMock).addAttribute("cards", cardList);
        target = new OrderListController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        doReturn(cardList).when(orderServiceMock).findCardsByOrderId(0);
        
        //Act Statement(s)
        String result = target.showOrderDetail(modelMock, 0);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("publics/OrderDetail"));
            verify(modelMock).addAttribute("cards", cardList);
            verify(orderServiceMock).findCardsByOrderId(0);
        });
    }

    //Sapient generated method id: ${doCheckoutWhenResultNotEqualsThanh_to_n_th_nh_c_ng_}, hash: D69490018E0CFB7B63E054B5D7768CFF
    @Test()
    void doCheckoutWhenResultNotEqualsThanh_to_n_th_nh_c_ng_() {
        /* Branches:
         * (!result.equals("Thanh toán thành công!")) : true
         */
         //Arrange Statement(s)
        Model modelMock = mock(Model.class);
        Model modelMock2 = mock(Model.class);
        doReturn(modelMock2).when(modelMock).addAttribute("error", "A");
        target = new OrderListController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        UserSessionDto userSessionDtoMock = mock(UserSessionDto.class);
        doReturn(userSessionDtoMock).when(sessionMock).getAttribute("user_sess");
        User userMock = mock(User.class);
        doReturn(userMock).when(userServiceMock).findUserDBByUserSession(userSessionDtoMock);
        doReturn("A").when(orderServiceMock).doCheckout(0, 0, userMock);
        
        //Act Statement(s)
        String result = target.doCheckout(modelMock, 0, 0);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("redirect:/product"));
            verify(modelMock).addAttribute("error", "A");
            verify(sessionMock).getAttribute("user_sess");
            verify(userServiceMock).findUserDBByUserSession(userSessionDtoMock);
            verify(orderServiceMock).doCheckout(0, 0, userMock);
        });
    }

    //Sapient generated method id: ${doCheckoutWhenResultEqualsThanh_to_n_th_nh_c_ng_}, hash: F560DEAB516DFD182BEE1BA6A48BE0E0
    @Test()
    void doCheckoutWhenResultEqualsThanh_to_n_th_nh_c_ng_() {
        /* Branches:
         * (!result.equals("Thanh toán thành công!")) : false
         */
         //Arrange Statement(s)
        Model modelMock = mock(Model.class);
        Model modelMock2 = mock(Model.class);
        List<Card> cardList = new ArrayList<>();
        doReturn(modelMock2).when(modelMock).addAttribute("cards", cardList);
        Model modelMock3 = mock(Model.class);
        Order order = new Order();
        order.setId(0);
        doReturn(modelMock3).when(modelMock).addAttribute("order", order);
        target = new OrderListController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        UserSessionDto userSessionDtoMock = mock(UserSessionDto.class);
        doReturn(userSessionDtoMock).when(sessionMock).getAttribute("user_sess");
        User userMock = mock(User.class);
        doReturn(userMock).when(userServiceMock).findUserDBByUserSession(userSessionDtoMock);
        doReturn("Thanh to\u00E1n th\u00E0nh c\u00F4ng!").when(orderServiceMock).doCheckout(0, 0, userMock);
        List<Order> orderList = new ArrayList<>();
        orderList.add(order);
        doReturn(orderList).when(orderServiceMock).findOrdersByUser(userMock);
        doReturn(cardList).when(orderServiceMock).findCardsByOrderId(0);
        
        //Act Statement(s)
        String result = target.doCheckout(modelMock, 0, 0);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("publics/OrderDetail"));
            verify(modelMock).addAttribute("cards", cardList);
            verify(modelMock).addAttribute("order", order);
            verify(sessionMock).getAttribute("user_sess");
            verify(userServiceMock).findUserDBByUserSession(userSessionDtoMock);
            verify(orderServiceMock).doCheckout(0, 0, userMock);
            verify(orderServiceMock).findOrdersByUser(userMock);
            verify(orderServiceMock).findCardsByOrderId(0);
        });
    }

    //Sapient generated method id: ${checkoutFromCartWhenResultNotEqualsThanh_to_n_th_nh_c_ng_}, hash: 2232EC371879B99220FA935D66F14D09
    @Test()
    void checkoutFromCartWhenResultNotEqualsThanh_to_n_th_nh_c_ng_() {
        /* Branches:
         * (for-each(selectedItems)) : true
         * (!result.equals("Thanh toán thành công!")) : true
         */
         //Arrange Statement(s)
        Model modelMock = mock(Model.class);
        Model modelMock2 = mock(Model.class);
        doReturn(modelMock2).when(modelMock).addAttribute("error", "A");
        target = new OrderListController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        UserSessionDto userSessionDtoMock = mock(UserSessionDto.class);
        doReturn(userSessionDtoMock).when(sessionMock).getAttribute("user_sess");
        User userMock = mock(User.class, "user");
        doReturn(userMock).when(userServiceMock).findUserDBByUserSession(userSessionDtoMock);
        CheckoutItemDTO checkoutItemDTOMock = mock(CheckoutItemDTO.class, "object2");
        List<CheckoutItemDTO> checkoutItemDTOList = new ArrayList<>();
        checkoutItemDTOList.add(checkoutItemDTOMock);
        doReturn("A").when(orderServiceMock).checkoutFromCart(checkoutItemDTOList, userMock);
        
        //Act Statement(s)
        String result = target.checkoutFromCart(checkoutItemDTOList, modelMock);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("redirect:/cart"));
            assertThat(checkoutItemDTOList.size(), equalTo(1));
            assertThat(checkoutItemDTOList.get(0), is(instanceOf(CheckoutItemDTO.class)));
            verify(modelMock).addAttribute("error", "A");
            verify(sessionMock).getAttribute("user_sess");
            verify(userServiceMock).findUserDBByUserSession(userSessionDtoMock);
            verify(orderServiceMock).checkoutFromCart(checkoutItemDTOList, userMock);
        });
    }

    //Sapient generated method id: ${checkoutFromCartWhenResultEqualsThanh_to_n_th_nh_c_ng_}, hash: BE025E2DE98C2B25C914EFD106497949
    @Test()
    void checkoutFromCartWhenResultEqualsThanh_to_n_th_nh_c_ng_() {
        /* Branches:
         * (for-each(selectedItems)) : true
         * (!result.equals("Thanh toán thành công!")) : false
         *
         * TODO: Help needed! Please adjust the input/test parameter values manually to satisfy the requirements of the given test scenario.
         *  The test code, including the assertion statements, has been successfully generated.
         */
         //Arrange Statement(s)
        Model modelMock = mock(Model.class);
        Model modelMock2 = mock(Model.class);
        List<Card> cardList = new ArrayList<>();
        doReturn(modelMock2).when(modelMock).addAttribute("cards", cardList);
        Model modelMock3 = mock(Model.class);
        Order order = new Order();
        order.setId(0);
        doReturn(modelMock3).when(modelMock).addAttribute("order", order);
        target = new OrderListController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        UserSessionDto userSessionDtoMock = mock(UserSessionDto.class);
        doReturn(userSessionDtoMock).when(sessionMock).getAttribute("user_sess");
        User userMock = mock(User.class, "user");
        doReturn(userMock).when(userServiceMock).findUserDBByUserSession(userSessionDtoMock);
        CheckoutItemDTO checkoutItemDTOMock = mock(CheckoutItemDTO.class, "object2");
        List<CheckoutItemDTO> checkoutItemDTOList = new ArrayList<>();
        checkoutItemDTOList.add(checkoutItemDTOMock);
        doReturn("Thanh to\u00E1n th\u00E0nh c\u00F4ng!").when(orderServiceMock).checkoutFromCart(checkoutItemDTOList, userMock);
        List<Order> orderList = new ArrayList<>();
        orderList.add(order);
        doReturn(orderList).when(orderServiceMock).findOrdersByUser(userMock);
        doReturn(cardList).when(orderServiceMock).findCardsByOrderId(0);
        
        //Act Statement(s)
        String result = target.checkoutFromCart(checkoutItemDTOList, modelMock);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("publics/OrderDetail"));
            verify(modelMock).addAttribute("cards", cardList);
            verify(modelMock).addAttribute("order", order);
            verify(sessionMock).getAttribute("user_sess");
            verify(userServiceMock).findUserDBByUserSession(userSessionDtoMock);
            verify(orderServiceMock).checkoutFromCart(checkoutItemDTOList, userMock);
            verify(orderServiceMock).findOrdersByUser(userMock);
            verify(orderServiceMock).findCardsByOrderId(0);
        });
    }
}
