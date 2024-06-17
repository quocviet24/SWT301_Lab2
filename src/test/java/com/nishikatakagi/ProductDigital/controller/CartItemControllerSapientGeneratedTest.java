package com.nishikatakagi.ProductDigital.controller;

import org.junit.jupiter.api.Timeout;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import com.nishikatakagi.ProductDigital.service.CartItemService;
import org.mockito.MockitoAnnotations;
import com.nishikatakagi.ProductDigital.dto.UserSessionDto;
import java.util.Map;
import org.springframework.ui.Model;
import java.util.HashMap;
import com.nishikatakagi.ProductDigital.service.UserService;
import com.nishikatakagi.ProductDigital.service.CardTypeService;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.nishikatakagi.ProductDigital.model.CartItem;
import org.mockito.MockedStatic;
import org.springframework.http.HttpStatus;
import static org.mockito.Mockito.doNothing;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.doReturn;
import org.junit.jupiter.api.Disabled;

@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
class CartItemControllerSapientGeneratedTest {

    private final HttpSession sessionMock = mock(HttpSession.class, "session");

    private final CardTypeService cardTypeServiceMock = mock(CardTypeService.class, "cardTypeService");

    private final CartItemService cartItemServiceMock = mock(CartItemService.class, "cartItemService");

    private final UserService userServiceMock = mock(UserService.class, "userService");

    private AutoCloseable autoCloseableMocks;

    @InjectMocks()
    private CartItemController target;

    @AfterEach()
    public void afterTest() throws Exception {
        if (autoCloseableMocks != null)
            autoCloseableMocks.close();
    }

    //Sapient generated method id: ${addToCartWhenUserSessionIsNull}, hash: A015F83FD9476F714E870052BCF02BA3
    @Test()
    void addToCartWhenUserSessionIsNull() {
        /* Branches:
         * (userSession == null) : true
         */
        //Arrange Statement(s)
        target = new CartItemController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        doReturn(null).when(sessionMock).getAttribute("user_sess");
        RedirectAttributes redirectAttributesMock = mock(RedirectAttributes.class);
        //Act Statement(s)
        String result = target.addToCart(redirectAttributesMock, 0, 0);
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("redirect:/login"));
            verify(sessionMock).getAttribute("user_sess");
        });
    }

    //Sapient generated method id: ${addToCartWhenUserSessionIsNotNull}, hash: 4F300B9D963F4ABA21CFD4F676BCF6C5
    @Test()
    void addToCartWhenUserSessionIsNotNull() {
        /* Branches:
         * (userSession == null) : false
         */
        //Arrange Statement(s)
        RedirectAttributes redirectAttributesMock = mock(RedirectAttributes.class);
        RedirectAttributes redirectAttributesMock2 = mock(RedirectAttributes.class);
        doReturn(redirectAttributesMock2).when(redirectAttributesMock).addFlashAttribute("message", "return_of_addItemToCart1");
        target = new CartItemController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        UserSessionDto userSessionDto = new UserSessionDto();
        userSessionDto.setUsername("username1");
        doReturn(userSessionDto).when(sessionMock).getAttribute("user_sess");
        doReturn("return_of_addItemToCart1").when(cartItemServiceMock).addItemToCart("username1", 0, 0);
        //Act Statement(s)
        String result = target.addToCart(redirectAttributesMock, 0, 0);
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("redirect:/product"));
            verify(redirectAttributesMock).addFlashAttribute("message", "return_of_addItemToCart1");
            verify(sessionMock).getAttribute("user_sess");
            verify(cartItemServiceMock).addItemToCart("username1", 0, 0);
        });
    }

    //Sapient generated method id: ${showCartDetailsWhenUserIsNotNull}, hash: 70D50A18472C53C2159E212B83DBD524
    @Test()
    void showCartDetailsWhenUserIsNotNull() {
        /* Branches:
         * (user != null) : true
         */
        //Arrange Statement(s)
        Model modelMock = mock(Model.class);
        Model modelMock2 = mock(Model.class);
        List<CartItem> cartItemList = new ArrayList<>();
        doReturn(modelMock2).when(modelMock).addAttribute("cart", cartItemList);
        target = new CartItemController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        UserSessionDto userSessionDto = new UserSessionDto();
        userSessionDto.setUsername("username1");
        doReturn(userSessionDto).when(sessionMock).getAttribute("user_sess");
        doReturn(cartItemList).when(cartItemServiceMock).getCartDetails("username1");
        //Act Statement(s)
        String result = target.showCartDetails(modelMock);
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("publics/cart"));
            verify(modelMock).addAttribute("cart", cartItemList);
            verify(sessionMock).getAttribute("user_sess");
            verify(cartItemServiceMock).getCartDetails("username1");
        });
    }

    //Sapient generated method id: ${showCartDetailsWhenUserIsNull}, hash: BE900848FBF2CFFEE8E7F0ABBC5C27CC
    @Test()
    void showCartDetailsWhenUserIsNull() {
        /* Branches:
         * (user != null) : false
         */
        //Arrange Statement(s)
        target = new CartItemController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        doReturn(null).when(sessionMock).getAttribute("user_sess");
        Model modelMock = mock(Model.class);
        //Act Statement(s)
        String result = target.showCartDetails(modelMock);
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("redirect:/login"));
            verify(sessionMock).getAttribute("user_sess");
        });
    }

    //Sapient generated method id: ${deleteCartItemWhenUserIsNotNull}, hash: 1565FDD6C2CB3EE602807EBC6D91CE5C
    @Test()
    void deleteCartItemWhenUserIsNotNull() {
        /* Branches:
         * (user != null) : true
         */
        //Arrange Statement(s)
        target = new CartItemController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        UserSessionDto userSessionDtoMock = mock(UserSessionDto.class);
        doReturn(userSessionDtoMock).when(sessionMock).getAttribute("user_sess");
        doNothing().when(cartItemServiceMock).deleteCartItem(0);
        RedirectAttributes redirectAttributesMock = mock(RedirectAttributes.class);
        //Act Statement(s)
        String result = target.deleteCartItem(0, redirectAttributesMock);
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("redirect:/cart"));
            verify(sessionMock).getAttribute("user_sess");
            verify(cartItemServiceMock).deleteCartItem(0);
        });
    }

    //Sapient generated method id: ${deleteCartItemWhenUserIsNull}, hash: A1F6E53DD42CD28F98321137BA80DE09
    @Test()
    void deleteCartItemWhenUserIsNull() {
        /* Branches:
         * (user != null) : false
         */
        //Arrange Statement(s)
        target = new CartItemController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        doReturn(null).when(sessionMock).getAttribute("user_sess");
        RedirectAttributes redirectAttributesMock = mock(RedirectAttributes.class);
        //Act Statement(s)
        String result = target.deleteCartItem(0, redirectAttributesMock);
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("redirect:/login"));
            verify(sessionMock).getAttribute("user_sess");
        });
    }

    //Sapient generated method id: ${updateQuantityWhenUserIsNull}, hash: 675D4EECE668B907B59C3AA017CA3580
    @Test()
    void updateQuantityWhenUserIsNull() {
        /* Branches:
         * (user != null) : false
         */
        //Arrange Statement(s)
        target = new CartItemController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        doReturn(null).when(sessionMock).getAttribute("user_sess");
        //Act Statement(s)
        ResponseEntity<String> result = target.updateQuantity(0, 0);
        ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        ResponseEntity<String> responseEntity = bodyBuilder.body("User not logged in");
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo(responseEntity));
            verify(sessionMock).getAttribute("user_sess");
        });
    }

    //Sapient generated method id: ${updateQuantityWhenUpdatedCartItemIsNotNull}, hash: 2FBE1451694963DD1CD94676DCFA2938
    @Test()
    void updateQuantityWhenUpdatedCartItemIsNotNull() {
        /* Branches:
         * (user != null) : true
         * (updatedCartItem != null) : true
         */
        //Arrange Statement(s)
        target = new CartItemController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        UserSessionDto userSessionDto = new UserSessionDto();
        userSessionDto.setUsername("username1");
        doReturn(userSessionDto).when(sessionMock).getAttribute("user_sess");
        CartItem cartItemMock = mock(CartItem.class);
        doReturn(cartItemMock).when(cartItemServiceMock).updateItemQuantity("username1", 0, 0);
        //Act Statement(s)
        ResponseEntity<String> result = target.updateQuantity(0, 0);
        ResponseEntity<String> responseEntity = ResponseEntity.ok("Quantity updated successfully");
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo(responseEntity));
            verify(sessionMock).getAttribute("user_sess");
            verify(cartItemServiceMock).updateItemQuantity("username1", 0, 0);
        });
    }

    //Sapient generated method id: ${updateQuantityWhenUpdatedCartItemIsNull}, hash: 7E228617175D726DCF580DA151AFA469
    @Test()
    void updateQuantityWhenUpdatedCartItemIsNull() {
        /* Branches:
         * (user != null) : true
         * (updatedCartItem != null) : false
         */
        //Arrange Statement(s)
        target = new CartItemController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        UserSessionDto userSessionDto = new UserSessionDto();
        userSessionDto.setUsername("username1");
        doReturn(userSessionDto).when(sessionMock).getAttribute("user_sess");
        doReturn(null).when(cartItemServiceMock).updateItemQuantity("username1", 0, 0);
        //Act Statement(s)
        ResponseEntity<String> result = target.updateQuantity(0, 0);
        ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.badRequest();
        ResponseEntity<String> responseEntity = bodyBuilder.body("Failed to update quantity");
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo(responseEntity));
            verify(sessionMock).getAttribute("user_sess");
            verify(cartItemServiceMock).updateItemQuantity("username1", 0, 0);
        });
    }

    //Sapient generated method id: ${updateQuantityWhenCaughtException}, hash: D4545E51D4660FBDAD2DE2445F4BD90B
    @Test()
    void updateQuantityWhenCaughtException() {
        /* Branches:
         * (user != null) : true
         * (updatedCartItem != null) : true
         * (catch-exception (Exception)) : true
         */
        //Arrange Statement(s)
        CartItem cartItemMock = mock(CartItem.class);
        try (MockedStatic<ResponseEntity> responseEntity = mockStatic(ResponseEntity.class, CALLS_REAL_METHODS)) {
            RuntimeException runtimeException = new RuntimeException();
            responseEntity.when(() -> ResponseEntity.ok("Quantity updated successfully")).thenThrow(runtimeException);
            target = new CartItemController();
            autoCloseableMocks = MockitoAnnotations.openMocks(this);
            UserSessionDto userSessionDto = new UserSessionDto();
            userSessionDto.setUsername("username1");
            doReturn(userSessionDto).when(sessionMock).getAttribute("user_sess");
            doReturn(cartItemMock).when(cartItemServiceMock).updateItemQuantity("username1", 0, 0);
            //Act Statement(s)
            ResponseEntity<String> result = target.updateQuantity(0, 0);
            ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
            ResponseEntity<String> responseEntity2 = bodyBuilder.body("Error occurred during update");
            //Assert statement(s)
            assertAll("result", () -> {
                assertThat(result, equalTo(responseEntity2));
                responseEntity.verify(() -> ResponseEntity.ok("Quantity updated successfully"), atLeast(1));
                verify(sessionMock, atLeast(1)).getAttribute("user_sess");
                verify(cartItemServiceMock, atLeast(1)).updateItemQuantity("username1", 0, 0);
            });
        }
    }

    //Sapient generated method id: ${updateQuantityWhenUpdatedCartItemIsNullAndCaughtException}, hash: 91CDD57FF3833B02E67FC34CAB571DE2
    @Disabled()
    @Test()
    void updateQuantityWhenUpdatedCartItemIsNullAndCaughtException() {
        /* Branches:
         * (user != null) : true
         * (updatedCartItem != null) : false
         * (catch-exception (Exception)) : true
         */
        //Arrange Statement(s)
        target = new CartItemController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        UserSessionDto userSessionDto = new UserSessionDto();
        userSessionDto.setUsername("username1");
        doReturn(userSessionDto).when(sessionMock).getAttribute("user_sess");
        doReturn(null).when(cartItemServiceMock).updateItemQuantity("username1", 0, 0);
        //Act Statement(s)
        ResponseEntity<String> result = target.updateQuantity(0, 0);
        ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        ResponseEntity<String> responseEntity = bodyBuilder.body("Error occurred during update");
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo(responseEntity));
            verify(sessionMock).getAttribute("user_sess");
            verify(cartItemServiceMock).updateItemQuantity("username1", 0, 0);
        });
    }

    //Sapient generated method id: ${getCartItemCountWhenUserIsNotNull}, hash: 390C701395D3BB339F99E889BEA6FD15
    @Test()
    void getCartItemCountWhenUserIsNotNull() {
        /* Branches:
         * (user != null) : true
         * (user != null) : true
         */
        //Arrange Statement(s)
        ResponseEntity<Map> responseEntityMock = mock(ResponseEntity.class);
        try (MockedStatic<ResponseEntity> responseEntity = mockStatic(ResponseEntity.class)) {
            responseEntity.when(() -> ResponseEntity.ok(anyMap())).thenReturn(responseEntityMock);
            target = new CartItemController();
            autoCloseableMocks = MockitoAnnotations.openMocks(this);
            UserSessionDto userSessionDto = new UserSessionDto();
            userSessionDto.setUsername("username1");
            doReturn(userSessionDto).when(sessionMock).getAttribute("user_sess");
            doReturn(1).when(cartItemServiceMock).getCartItemCount("username1");
            //Act Statement(s)
            ResponseEntity<?> result = target.getCartItemCount();
            //Assert statement(s)
            assertAll("result", () -> {
                assertThat(result, equalTo(responseEntityMock));
                responseEntity.verify(() -> ResponseEntity.ok(anyMap()));
                verify(sessionMock).getAttribute("user_sess");
                verify(cartItemServiceMock).getCartItemCount("username1");
            });
        }
    }

    //Sapient generated method id: ${getCartItemCountWhenUserIsNull}, hash: 2CBF3A4D9BB52D6B581D15F5977F2FDF
    @Test()
    void getCartItemCountWhenUserIsNull() {
        /* Branches:
         * (user != null) : false
         * (user != null) : false
         */
        //Arrange Statement(s)
        target = new CartItemController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        doReturn(null).when(sessionMock).getAttribute("user_sess");
        //Act Statement(s)
        ResponseEntity<?> result = target.getCartItemCount();
        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("loggedIn", false);
        stringObjectMap.put("cartItemCount", 0);
        ResponseEntity<Map> responseEntity = ResponseEntity.ok(stringObjectMap);
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo(responseEntity));
            verify(sessionMock).getAttribute("user_sess");
        });
    }

    //Sapient generated method id: ${getCartItemCountWhenCaughtException}, hash: 6DA218A7B40997783BEE9D676C6957DF
    @Test()
    void getCartItemCountWhenCaughtException() {
        /* Branches:
         * (user != null) : true
         * (user != null) : true
         * (catch-exception (Exception)) : true
         */
        //Arrange Statement(s)
        target = new CartItemController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        UserSessionDto userSessionDto = new UserSessionDto();
        userSessionDto.setUsername("username1");
        doReturn(userSessionDto).when(sessionMock).getAttribute("user_sess");
        RuntimeException runtimeException = new RuntimeException();
        doThrow(runtimeException).when(cartItemServiceMock).getCartItemCount("username1");
        //Act Statement(s)
        ResponseEntity<?> result = target.getCartItemCount();
        ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        ResponseEntity responseEntity = bodyBuilder.build();
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo(responseEntity));
            verify(sessionMock).getAttribute("user_sess");
            verify(cartItemServiceMock).getCartItemCount("username1");
        });
    }
}
