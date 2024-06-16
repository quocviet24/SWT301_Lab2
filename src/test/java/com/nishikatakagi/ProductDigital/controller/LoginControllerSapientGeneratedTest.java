package com.nishikatakagi.ProductDigital.controller;

import org.junit.jupiter.api.Timeout;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import com.nishikatakagi.ProductDigital.service.CaptchaService;
import com.nishikatakagi.ProductDigital.dto.UserSessionDto;
import com.nishikatakagi.ProductDigital.model.User;
import java.util.Map;
import org.springframework.ui.Model;
import java.util.HashMap;
import com.nishikatakagi.ProductDigital.service.AuthService;
import com.nishikatakagi.ProductDigital.service.UserService;
import org.springframework.validation.BindingResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.BeanUtils;
import org.mockito.stubbing.Answer;
import com.nishikatakagi.ProductDigital.dto.UserLoginRequestDto;
import org.springframework.validation.FieldError;
import java.util.concurrent.ConcurrentHashMap;
import org.mockito.MockedStatic;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import static org.mockito.Mockito.doNothing;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mockStatic;
import org.junit.jupiter.api.Disabled;

@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
class LoginControllerSapientGeneratedTest {

    private final HttpSession httpSessionMock = mock(HttpSession.class, "httpSession");

    private final AuthService authServiceMock = mock(AuthService.class, "authService");

    private final UserService serviceMock = mock(UserService.class, "service");

    private final CaptchaService captchaServiceMock = mock(CaptchaService.class, "captchaService");

    private final HttpServletResponse responseMock = mock(HttpServletResponse.class, "response");

    private AutoCloseable autoCloseableMocks;

    @InjectMocks()
    private LoginController target;

    @AfterEach()
    public void afterTest() throws Exception {
        if (autoCloseableMocks != null)
            autoCloseableMocks.close();
    }

    //Sapient generated method id: ${ShowLoginPageWhenHttpSessionGetAttributeUser_sessIsNotNull}, hash: 69352DEAAF0C755554C4CD110B308729
    @Test()
    void ShowLoginPageWhenHttpSessionGetAttributeUser_sessIsNotNull() {
        /* Branches:
         * (httpSession.getAttribute("user_sess") != null) : true
         */
        //Arrange Statement(s)
        target = new LoginController(httpSessionMock);
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        Object object = new Object();
        doReturn(object).when(httpSessionMock).getAttribute("user_sess");
        Model modelMock = mock(Model.class);
        //Act Statement(s)
        String result = target.ShowLoginPage(modelMock, "userName1");
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("redirect:"));
            verify(httpSessionMock).getAttribute("user_sess");
        });
    }

    //Sapient generated method id: ${ShowLoginPageWhenUserNameIsNotNull}, hash: 8C8851E5B54C5F96AEAF12ABEBCA8BED
    @Disabled()
    @Test()
    void ShowLoginPageWhenUserNameIsNotNull() {
        /* Branches:
         * (httpSession.getAttribute("user_sess") != null) : false
         * (userName != null) : true
         *
         * TODO: Help needed! Please adjust the input/test parameter values manually to satisfy the requirements of the given test scenario.
         *  The test code, including the assertion statements, has been successfully generated.
         */
        //Arrange Statement(s)
        Model modelMock = mock(Model.class);
        Model modelMock2 = mock(Model.class);
        doReturn(modelMock2).when(modelMock).addAttribute(eq("userLoginRequestDto"), (UserLoginRequestDto) any());
        Object object = new Object();
        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("passwordResetted", object);
        doReturn(stringObjectMap).when(modelMock).asMap();
        Model modelMock3 = mock(Model.class);
        doReturn(modelMock3).when(modelMock).addAttribute("passwordResetted", false);
        Model modelMock4 = mock(Model.class);
        doReturn(modelMock4).when(modelMock).addAttribute("idCaptcha", 1);
        Model modelMock5 = mock(Model.class);
        String[] stringArray = new String[] {};
        Map<Integer, String[]> integerStringMap = new ConcurrentHashMap<>();
        integerStringMap.put(1, stringArray);
        doReturn(modelMock5).when(modelMock).addAttribute(eq("captchaSvg"), eq(integerStringMap));
        target = spy(new LoginController(httpSessionMock));
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        doReturn(null).when(httpSessionMock).getAttribute("user_sess");
        doReturn(1).when(captchaServiceMock).createIDCaptcha();
        doReturn(stringArray).when(captchaServiceMock).captchaValueHTML(9, 6, 2, 7);
        Map<Integer, String> integerStringMap2 = new ConcurrentHashMap<>();
        integerStringMap2.put(1, "9627");
        doNothing().when(target).printCaptchaValueMap(eq(integerStringMap2));
        //Act Statement(s)
        String result = target.ShowLoginPage(modelMock, "userName1");
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("publics/login"));
            verify(modelMock).addAttribute(eq("userLoginRequestDto"), (UserLoginRequestDto) any());
            verify(modelMock).asMap();
            verify(modelMock).addAttribute("passwordResetted", false);
            verify(modelMock).addAttribute("idCaptcha", 1);
            verify(modelMock).addAttribute(eq("captchaSvg"), eq(integerStringMap));
            verify(httpSessionMock).getAttribute("user_sess");
            verify(captchaServiceMock).createIDCaptcha();
            verify(captchaServiceMock).captchaValueHTML(9, 6, 2, 7);
            verify(target).printCaptchaValueMap(eq(integerStringMap2));
        });
    }

    //Sapient generated method id: ${ShowLoginPageWhenUserNameIsNull}, hash: 2929F1B39A752275FABB6EBE34DF3AA6
    @Disabled()
    @Test()
    void ShowLoginPageWhenUserNameIsNull() {
        /* Branches:
         * (httpSession.getAttribute("user_sess") != null) : false
         * (userName != null) : false
         *
         * TODO: Help needed! Please adjust the input/test parameter values manually to satisfy the requirements of the given test scenario.
         *  The test code, including the assertion statements, has been successfully generated.
         */
        //Arrange Statement(s)
        Model modelMock = mock(Model.class);
        Model modelMock2 = mock(Model.class);
        doReturn(modelMock2).when(modelMock).addAttribute(eq("userLoginRequestDto"), (UserLoginRequestDto) any());
        Object object = new Object();
        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("passwordResetted", object);
        doReturn(stringObjectMap).when(modelMock).asMap();
        Model modelMock3 = mock(Model.class);
        doReturn(modelMock3).when(modelMock).addAttribute("passwordResetted", false);
        Model modelMock4 = mock(Model.class);
        doReturn(modelMock4).when(modelMock).addAttribute("idCaptcha", 1);
        Model modelMock5 = mock(Model.class);
        String[] stringArray = new String[] {};
        Map<Integer, String[]> integerStringMap = new ConcurrentHashMap<>();
        integerStringMap.put(1, stringArray);
        doReturn(modelMock5).when(modelMock).addAttribute(eq("captchaSvg"), eq(integerStringMap));
        target = spy(new LoginController(httpSessionMock));
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        doReturn(null).when(httpSessionMock).getAttribute("user_sess");
        doReturn(1).when(captchaServiceMock).createIDCaptcha();
        doReturn(stringArray).when(captchaServiceMock).captchaValueHTML(2, 0, 6, 2);
        Map<Integer, String> integerStringMap2 = new ConcurrentHashMap<>();
        integerStringMap2.put(1, "2062");
        doNothing().when(target).printCaptchaValueMap(eq(integerStringMap2));
        //Act Statement(s)
        String result = target.ShowLoginPage(modelMock, (String) null);
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("publics/login"));
            verify(modelMock).addAttribute(eq("userLoginRequestDto"), (UserLoginRequestDto) any());
            verify(modelMock).asMap();
            verify(modelMock).addAttribute("passwordResetted", false);
            verify(modelMock).addAttribute("idCaptcha", 1);
            verify(modelMock).addAttribute(eq("captchaSvg"), eq(integerStringMap));
            verify(httpSessionMock).getAttribute("user_sess");
            verify(captchaServiceMock).createIDCaptcha();
            verify(captchaServiceMock).captchaValueHTML(2, 0, 6, 2);
            verify(target).printCaptchaValueMap(eq(integerStringMap2));
        });
    }

    //Sapient generated method id: ${printCaptchaValueMapWhenCaptchaValueEntrySetIsNotEmpty}, hash: 9FEF66FEA93D38577785C6DD7ABD7B1B
    @Test()
    void printCaptchaValueMapWhenCaptchaValueEntrySetIsNotEmpty() {
        /* Branches:
         * (for-each(captchaValue.entrySet())) : true
         */
        //Arrange Statement(s)
        target = new LoginController(httpSessionMock);
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        Map<Integer, String> integerStringMap = new HashMap<>();
        integerStringMap.put(2, "A");
        //Act Statement(s)
        target.printCaptchaValueMap(integerStringMap);
    }

    //Sapient generated method id: ${LoginWhenListCheckUserLoginGetUserLoginRequestDtoGetUserNameLessThan5}, hash: B2E97AD2264455CF79621D20F5D3C8E4
    @Disabled()
    @Test()
    void LoginWhenListCheckUserLoginGetUserLoginRequestDtoGetUserNameLessThan5() {
        /* Branches:
         * (result.hasFieldErrors("password")) : true
         * (listCheckUserLogin.get(userLoginRequestDto.getUserName()) < 5) : true
         * (!userLoginRequestDto.getCaptcha().equals(value)) : false
         * (result.hasErrors()) : true
         * (listCheckUserLogin.get(userLoginRequestDto.getUserName()) != null) : true
         * (listCheckUserLogin.get(userLoginRequestDto.getUserName()) >= 5) : false
         *
         * TODO: Help needed! Please adjust the input/test parameter values manually to satisfy the requirements of the given test scenario.
         *  The test code, including the assertion statements, has been successfully generated.
         */
        //Arrange Statement(s)
        Model modelMock = mock(Model.class);
        Model modelMock2 = mock(Model.class);
        Map<Integer, String> integerStringMap = new ConcurrentHashMap<>();
        integerStringMap.put(1, "1567");
        doReturn(modelMock2).when(modelMock).addAttribute(eq("listCapthca_ID"), eq(integerStringMap));
        Model modelMock3 = mock(Model.class);
        String[] stringArray = new String[] {};
        Map<Integer, String[]> integerStringMap2 = new ConcurrentHashMap<>();
        integerStringMap2.put(1, stringArray);
        Model modelMock4 = mock(Model.class);
        String[] stringArray2 = new String[] {};
        doReturn(modelMock4).when(modelMock).addAttribute("captchaSvg", stringArray2);
        Model modelMock5 = mock(Model.class);
        UserLoginRequestDto userLoginRequestDto = new UserLoginRequestDto();
        userLoginRequestDto.setCaptcha("null");
        userLoginRequestDto.setUserName("userName1");
        doReturn(modelMock5).when(modelMock).addAttribute("userLoginRequestDto", userLoginRequestDto);
        Model modelMock6 = mock(Model.class);
        doReturn(modelMock6).when(modelMock).addAttribute("idCaptcha", 1);
        Model modelMock7 = mock(Model.class);
        doReturn(modelMock3, modelMock7).when(modelMock).addAttribute(eq("captchaSvg"), eq(integerStringMap2));
        target = new LoginController(httpSessionMock);
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        BindingResult bindingResultMock = mock(BindingResult.class);
        BindingResult bindingResultMock2 = mock(BindingResult.class);
        doReturn(bindingResultMock).when(authServiceMock).Login(userLoginRequestDto, bindingResultMock2);
        doReturn(true).when(bindingResultMock).hasFieldErrors("password");
        doReturn(true).when(bindingResultMock).hasErrors();
        User userMock = mock(User.class);
        doReturn(userMock).when(serviceMock).findByUsername("userName1");
        doReturn(stringArray2).when(captchaServiceMock).createCaptcha();
        doReturn(1).when(captchaServiceMock).createIDCaptcha();
        doReturn(stringArray).when(captchaServiceMock).captchaValueHTML(1, 5, 6, 7);
        //Act Statement(s)
        String result = target.Login(userLoginRequestDto, bindingResultMock2, modelMock, 1, false);
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("publics/login"));
            verify(modelMock).addAttribute(eq("listCapthca_ID"), eq(integerStringMap));
            verify(modelMock, times(2)).addAttribute(eq("captchaSvg"), eq(integerStringMap2));
            verify(modelMock).addAttribute("captchaSvg", stringArray2);
            verify(modelMock).addAttribute("userLoginRequestDto", userLoginRequestDto);
            verify(modelMock).addAttribute("idCaptcha", 1);
            verify(authServiceMock).Login(userLoginRequestDto, bindingResultMock2);
            verify(bindingResultMock).hasFieldErrors("password");
            verify(bindingResultMock).hasErrors();
            verify(serviceMock).findByUsername("userName1");
            verify(captchaServiceMock).createCaptcha();
            verify(captchaServiceMock).createIDCaptcha();
            verify(captchaServiceMock).captchaValueHTML(1, 5, 6, 7);
        });
    }

    //Sapient generated method id: ${LoginWhenRememberMeAndUserBDGetRoleIdEquals1AndUserBDIsNotNull2}, hash: DED6BE92E466EF4388D73FED6E5A850B
    @Test()
    void LoginWhenRememberMeAndUserBDGetRoleIdEquals1AndUserBDIsNotNull2() {
        /* Branches:
         * (result.hasFieldErrors("password")) : true
         * (listCheckUserLogin.get(userLoginRequestDto.getUserName()) < 5) : true
         * (!userLoginRequestDto.getCaptcha().equals(value)) : true
         * (captchaValue.get(idCaptcha) == null) : true
         * (result.hasErrors()) : false
         * (listCheckUserLogin.get(userLoginRequestDto.getUserName()) != null) : true
         * (listCheckUserLogin.get(userLoginRequestDto.getUserName()) >= 5) : false
         * (rememberMe) : true
         * (userBD.getRoleId() == 1) : true
         * (userBD != null) : true
         */
        //Arrange Statement(s)
        BindingResult bindingResultMock = mock(BindingResult.class);
        BindingResult bindingResultMock2 = mock(BindingResult.class);
        Model modelMock = mock(Model.class);
        try (MockedStatic<BeanUtils> beanUtils = mockStatic(BeanUtils.class)) {
            User user = new User();
            user.setRoleId(1);
            beanUtils.when(() -> BeanUtils.copyProperties(eq(user), (UserSessionDto) any())).thenAnswer((Answer<Void>) invocation -> null);
            target = new LoginController(httpSessionMock);
            autoCloseableMocks = MockitoAnnotations.openMocks(this);
            UserLoginRequestDto userLoginRequestDto = new UserLoginRequestDto();
            userLoginRequestDto.setCaptcha("A");
            userLoginRequestDto.setUserName("userName1");
            doReturn(bindingResultMock).when(authServiceMock).Login(userLoginRequestDto, bindingResultMock2);
            doReturn(true).when(bindingResultMock).hasFieldErrors("password");
            doNothing().when(bindingResultMock).addError((FieldError) any());
            doReturn(false).when(bindingResultMock).hasErrors();
            doReturn(user).when(serviceMock).findByUsername("userName1");
            doNothing().when(httpSessionMock).setAttribute(eq("user_sess"), (UserSessionDto) any());
            doNothing().when(responseMock).addCookie((Cookie) any());
            //Act Statement(s)
            String result = target.Login(userLoginRequestDto, bindingResultMock2, modelMock, 1, true);
            //Assert statement(s)
            assertAll("result", () -> {
                assertThat(result, equalTo("redirect:/admin"));
                beanUtils.verify(() -> BeanUtils.copyProperties(eq(user), (UserSessionDto) any()));
                verify(authServiceMock).Login(userLoginRequestDto, bindingResultMock2);
                verify(bindingResultMock).hasFieldErrors("password");
                verify(bindingResultMock).addError((FieldError) any());
                verify(bindingResultMock).hasErrors();
                verify(serviceMock).findByUsername("userName1");
                verify(httpSessionMock).setAttribute(eq("user_sess"), (UserSessionDto) any());
                verify(responseMock).addCookie((Cookie) any());
            });
        }
    }

    //Sapient generated method id: ${LoginWhenRememberMeAndUserBDGetRoleIdNotEquals1AndUserBDGetRoleIdNotEquals22}, hash: 0D9F30767E81F034E302143179B1ED50
    @Test()
    void LoginWhenRememberMeAndUserBDGetRoleIdNotEquals1AndUserBDGetRoleIdNotEquals22() {
        /* Branches:
         * (result.hasFieldErrors("password")) : true
         * (listCheckUserLogin.get(userLoginRequestDto.getUserName()) < 5) : true
         * (!userLoginRequestDto.getCaptcha().equals(value)) : true
         * (captchaValue.get(idCaptcha) == null) : true
         * (result.hasErrors()) : false
         * (listCheckUserLogin.get(userLoginRequestDto.getUserName()) != null) : true
         * (listCheckUserLogin.get(userLoginRequestDto.getUserName()) >= 5) : false
         * (rememberMe) : true
         * (userBD.getRoleId() == 1) : false
         * (userBD.getRoleId() == 2) : false
         *
         * TODO: Help needed! This method is not unit testable!
         *  A variable could not be isolated/mocked when calling a method - Variable name: usernameCookie - Method: setMaxAge
         *  Suggestions:
         *  You can pass them as constructor arguments or create a setter for them (avoid new operator)
         *  or adjust the input/test parameter values manually to satisfy the requirements of the given test scenario.
         *  The test code, including the assertion statements, has been successfully generated.
         */
        //Arrange Statement(s)
        BindingResult bindingResultMock = mock(BindingResult.class);
        BindingResult bindingResultMock2 = mock(BindingResult.class);
        Model modelMock = mock(Model.class);
        try (MockedStatic<BeanUtils> beanUtils = mockStatic(BeanUtils.class)) {
            User user = new User();
            user.setRoleId(3);
            beanUtils.when(() -> BeanUtils.copyProperties(eq(user), (UserSessionDto) any())).thenAnswer((Answer<Void>) invocation -> null);
            target = new LoginController(httpSessionMock);
            autoCloseableMocks = MockitoAnnotations.openMocks(this);
            UserLoginRequestDto userLoginRequestDto = new UserLoginRequestDto();
            userLoginRequestDto.setCaptcha("A");
            userLoginRequestDto.setUserName("userName1");
            doReturn(bindingResultMock).when(authServiceMock).Login(userLoginRequestDto, bindingResultMock2);
            doReturn(true).when(bindingResultMock).hasFieldErrors("password");
            doNothing().when(bindingResultMock).addError((FieldError) any());
            doReturn(false).when(bindingResultMock).hasErrors();
            doReturn(user).when(serviceMock).findByUsername("userName1");
            doNothing().when(httpSessionMock).setAttribute(eq("user_sess"), (UserSessionDto) any());
            doNothing().when(responseMock).addCookie((Cookie) any());
            //Act Statement(s)
            String result = target.Login(userLoginRequestDto, bindingResultMock2, modelMock, 1, true);
            //Assert statement(s)
            assertAll("result", () -> {
                assertThat(result, equalTo("redirect:"));
                beanUtils.verify(() -> BeanUtils.copyProperties(eq(user), (UserSessionDto) any()));
                verify(authServiceMock).Login(userLoginRequestDto, bindingResultMock2);
                verify(bindingResultMock).hasFieldErrors("password");
                verify(bindingResultMock).addError((FieldError) any());
                verify(bindingResultMock).hasErrors();
                verify(serviceMock).findByUsername("userName1");
                verify(httpSessionMock).setAttribute(eq("user_sess"), (UserSessionDto) any());
                verify(responseMock).addCookie((Cookie) any());
            });
        }
    }

    //Sapient generated method id: ${LoginWhenRememberMeAndUserBDGetRoleIdNotEquals1AndUserBDGetRoleIdEquals2AndUserBDIsNotNull3}, hash: 58E58EC9D020669BE393B711A78A16B7
    @Test()
    void LoginWhenRememberMeAndUserBDGetRoleIdNotEquals1AndUserBDGetRoleIdEquals2AndUserBDIsNotNull3() {
        /* Branches:
         * (result.hasFieldErrors("password")) : true
         * (listCheckUserLogin.get(userLoginRequestDto.getUserName()) < 5) : true
         * (!userLoginRequestDto.getCaptcha().equals(value)) : true
         * (captchaValue.get(idCaptcha) == null) : true
         * (result.hasErrors()) : false
         * (listCheckUserLogin.get(userLoginRequestDto.getUserName()) != null) : true
         * (listCheckUserLogin.get(userLoginRequestDto.getUserName()) >= 5) : false
         * (rememberMe) : true
         * (userBD.getRoleId() == 1) : false
         * (userBD.getRoleId() == 2) : true
         * (userBD != null) : true
         *
         * TODO: Help needed! This method is not unit testable!
         *  A variable could not be isolated/mocked when calling a method - Variable name: usernameCookie - Method: setMaxAge
         *  Suggestions:
         *  You can pass them as constructor arguments or create a setter for them (avoid new operator)
         *  or adjust the input/test parameter values manually to satisfy the requirements of the given test scenario.
         *  The test code, including the assertion statements, has been successfully generated.
         */
        //Arrange Statement(s)
        BindingResult bindingResultMock = mock(BindingResult.class);
        BindingResult bindingResultMock2 = mock(BindingResult.class);
        Model modelMock = mock(Model.class);
        try (MockedStatic<BeanUtils> beanUtils = mockStatic(BeanUtils.class)) {
            User user = new User();
            user.setRoleId(2);
            beanUtils.when(() -> BeanUtils.copyProperties(eq(user), (UserSessionDto) any())).thenAnswer((Answer<Void>) invocation -> null);
            target = new LoginController(httpSessionMock);
            autoCloseableMocks = MockitoAnnotations.openMocks(this);
            UserLoginRequestDto userLoginRequestDto = new UserLoginRequestDto();
            userLoginRequestDto.setCaptcha("");
            userLoginRequestDto.setUserName("userName1");
            doReturn(bindingResultMock).when(authServiceMock).Login(userLoginRequestDto, bindingResultMock2);
            doReturn(true).when(bindingResultMock).hasFieldErrors("password");
            doNothing().when(bindingResultMock).addError((FieldError) any());
            doReturn(false).when(bindingResultMock).hasErrors();
            doReturn(user).when(serviceMock).findByUsername("userName1");
            doNothing().when(httpSessionMock).setAttribute(eq("user_sess"), (UserSessionDto) any());
            doNothing().when(responseMock).addCookie((Cookie) any());
            //Act Statement(s)
            String result = target.Login(userLoginRequestDto, bindingResultMock2, modelMock, 1, true);
            //Assert statement(s)
            assertAll("result", () -> {
                assertThat(result, equalTo("redirect:"));
                beanUtils.verify(() -> BeanUtils.copyProperties(eq(user), (UserSessionDto) any()));
                verify(authServiceMock).Login(userLoginRequestDto, bindingResultMock2);
                verify(bindingResultMock).hasFieldErrors("password");
                verify(bindingResultMock).addError((FieldError) any());
                verify(bindingResultMock).hasErrors();
                verify(serviceMock).findByUsername("userName1");
                verify(httpSessionMock).setAttribute(eq("user_sess"), (UserSessionDto) any());
                verify(responseMock).addCookie((Cookie) any());
            });
        }
    }
}
