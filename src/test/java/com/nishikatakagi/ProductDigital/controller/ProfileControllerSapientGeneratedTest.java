package com.nishikatakagi.ProductDigital.controller;

import org.junit.jupiter.api.Timeout;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import com.nishikatakagi.ProductDigital.dto.UserSessionDto;
import com.nishikatakagi.ProductDigital.service.EmailService;
import org.springframework.ui.Model;
import com.nishikatakagi.ProductDigital.service.UserService;
import com.nishikatakagi.ProductDigital.service_impl.SecurityServiceImpl;
import jakarta.servlet.http.HttpSession;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.doReturn;
import jakarta.mail.MessagingException;
import org.springframework.validation.FieldError;
import com.nishikatakagi.ProductDigital.model.User;
import org.springframework.validation.BindingResult;
import static org.mockito.Mockito.doNothing;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;

@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
class ProfileControllerSapientGeneratedTest {

    private final UserService userServiceMock = mock(UserService.class, "userService");

    private final SecurityServiceImpl securityMock = mock(SecurityServiceImpl.class, "security");

    private final EmailService emailServiceMock = mock(EmailService.class, "emailService");

    private final HttpSession sessionMock = mock(HttpSession.class, "session");

    private AutoCloseable autoCloseableMocks;

    @InjectMocks()
    private ProfileController target;

    @AfterEach()
    public void afterTest() throws Exception {
        if (autoCloseableMocks != null)
            autoCloseableMocks.close();
    }

    //Sapient generated method id: ${showProfilePageWhenUserIsNotNull}, hash: 9E23B3CDBC1439FE049FD28E1A8E4729
    @Test()
    void showProfilePageWhenUserIsNotNull() {
        /* Branches:
         * (user != null) : true
         */
         //Arrange Statement(s)
        HttpSession sessionMock2 = mock(HttpSession.class);
        UserSessionDto userSessionDtoMock = mock(UserSessionDto.class);
        doReturn(userSessionDtoMock).when(sessionMock2).getAttribute("user_sess");
        target = new ProfileController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        Model modelMock = mock(Model.class);
        
        //Act Statement(s)
        String result = target.showProfilePage(modelMock, sessionMock2);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("publics/profile.html"));
            verify(sessionMock2).getAttribute("user_sess");
        });
    }

    //Sapient generated method id: ${showProfilePageWhenUserIsNull}, hash: 3B99BAB4DB1AA929CC48D8CE70382CAC
    @Test()
    void showProfilePageWhenUserIsNull() {
        /* Branches:
         * (user != null) : false
         */
         //Arrange Statement(s)
        HttpSession sessionMock2 = mock(HttpSession.class);
        doReturn(null).when(sessionMock2).getAttribute("user_sess");
        target = new ProfileController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        Model modelMock = mock(Model.class);
        
        //Act Statement(s)
        String result = target.showProfilePage(modelMock, sessionMock2);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("publics/index.html"));
            verify(sessionMock2).getAttribute("user_sess");
        });
    }

    //Sapient generated method id: ${showProfileUpdatePageWhenUserIsNotNull}, hash: F67085146D56274E2A47C49F9F3E32AB
    @Test()
    void showProfileUpdatePageWhenUserIsNotNull() {
        /* Branches:
         * (user != null) : true
         */
         //Arrange Statement(s)
        HttpSession sessionMock2 = mock(HttpSession.class);
        UserSessionDto userSessionDtoMock = mock(UserSessionDto.class);
        doReturn(userSessionDtoMock).when(sessionMock2).getAttribute("user_sess");
        Model modelMock = mock(Model.class);
        Model modelMock2 = mock(Model.class);
        doReturn(modelMock2).when(modelMock).addAttribute("User", userSessionDtoMock);
        target = new ProfileController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        
        //Act Statement(s)
        String result = target.showProfileUpdatePage(sessionMock2, modelMock);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("publics/profileUpdate"));
            verify(sessionMock2).getAttribute("user_sess");
            verify(modelMock).addAttribute("User", userSessionDtoMock);
        });
    }

    //Sapient generated method id: ${showProfileUpdatePageWhenUserIsNull}, hash: 411057CFFFF9A85AC8F267F71E8F6C0F
    @Test()
    void showProfileUpdatePageWhenUserIsNull() {
        /* Branches:
         * (user != null) : false
         */
         //Arrange Statement(s)
        HttpSession sessionMock2 = mock(HttpSession.class);
        doReturn(null).when(sessionMock2).getAttribute("user_sess");
        target = new ProfileController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        Model modelMock = mock(Model.class);
        
        //Act Statement(s)
        String result = target.showProfileUpdatePage(sessionMock2, modelMock);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("publics/index.html"));
            verify(sessionMock2).getAttribute("user_sess");
        });
    }

    //Sapient generated method id: ${updateProfilePageWhenUserDtoGetPhoneLengthGreaterThan0AndResultHasErrors}, hash: A96AE2650FD4D1DB7009E3FA0FDE50B2
    @Test()
    void updateProfilePageWhenUserDtoGetPhoneLengthGreaterThan0AndResultHasErrors() {
        /* Branches:
         * (userDto.getPhone().length() != 10) : true
         * (userDto.getPhone().length() > 0) : true
         * (result.hasErrors()) : true
         */
         //Arrange Statement(s)
        BindingResult resultMock = mock(BindingResult.class);
        doNothing().when(resultMock).addError((FieldError) any());
        doReturn(true).when(resultMock).hasErrors();
        Model modelMock = mock(Model.class);
        Model modelMock2 = mock(Model.class);
        UserSessionDto userSessionDto = new UserSessionDto();
        userSessionDto.setPhone("A");
        doReturn(modelMock2).when(modelMock).addAttribute("User", userSessionDto);
        target = new ProfileController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        
        //Act Statement(s)
        String result = target.updateProfilePage(userSessionDto, resultMock, modelMock);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("publics/profileUpdate"));
            verify(resultMock).addError((FieldError) any());
            verify(resultMock).hasErrors();
            verify(modelMock).addAttribute("User", userSessionDto);
        });
    }

    //Sapient generated method id: ${updateProfilePageWhenResultNotHasErrorsAndUserIsNull}, hash: B74D85FEBFEAC9338C50C8A9AA9EE7C1
    @Test()
    void updateProfilePageWhenResultNotHasErrorsAndUserIsNull() {
        /* Branches:
         * (userDto.getPhone().length() != 10) : true
         * (userDto.getPhone().length() > 0) : true
         * (result.hasErrors()) : false
         * (user == null) : true
         */
         //Arrange Statement(s)
        BindingResult resultMock = mock(BindingResult.class);
        doNothing().when(resultMock).addError((FieldError) any());
        doReturn(false).when(resultMock).hasErrors();
        target = new ProfileController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        UserSessionDto userSessionDtoMock = mock(UserSessionDto.class);
        doReturn(userSessionDtoMock).when(sessionMock).getAttribute("user_sess");
        doReturn(null).when(userServiceMock).findUserDBByUserSession(userSessionDtoMock);
        UserSessionDto userSessionDto = new UserSessionDto();
        userSessionDto.setPhone("A");
        Model modelMock = mock(Model.class);
        
        //Act Statement(s)
        String result = target.updateProfilePage(userSessionDto, resultMock, modelMock);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("publics/index.html"));
            verify(resultMock).addError((FieldError) any());
            verify(resultMock).hasErrors();
            verify(sessionMock).getAttribute("user_sess");
            verify(userServiceMock).findUserDBByUserSession(userSessionDtoMock);
        });
    }

    //Sapient generated method id: ${updateProfilePageWhenResultNotHasErrorsAndUserIsNotNull}, hash: 80BFCE4F4163EE797BB53586B8A122CA
    @Test()
    void updateProfilePageWhenResultNotHasErrorsAndUserIsNotNull() {
        /* Branches:
         * (userDto.getPhone().length() != 10) : true
         * (userDto.getPhone().length() > 0) : true
         * (result.hasErrors()) : false
         * (user == null) : false
         */
         //Arrange Statement(s)
        BindingResult resultMock = mock(BindingResult.class);
        doNothing().when(resultMock).addError((FieldError) any());
        doReturn(false).when(resultMock).hasErrors();
        target = new ProfileController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        UserSessionDto userSessionDtoMock = mock(UserSessionDto.class);
        doReturn(userSessionDtoMock).when(sessionMock).getAttribute("user_sess");
        User user = new User();
        user.setEmail("email1");
        doReturn(user).when(userServiceMock).findUserDBByUserSession(userSessionDtoMock);
        UserSessionDto userSessionDto = new UserSessionDto();
        userSessionDto.setPhone("A");
        userSessionDto.setEmail("email1");
        doNothing().when(userServiceMock).updateUser(userSessionDto, user);
        doNothing().when(sessionMock).setAttribute("user_sess", userSessionDto);
        Model modelMock = mock(Model.class);
        
        //Act Statement(s)
        String result = target.updateProfilePage(userSessionDto, resultMock, modelMock);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("redirect:/profile"));
            verify(resultMock).addError((FieldError) any());
            verify(resultMock).hasErrors();
            verify(sessionMock).getAttribute("user_sess");
            verify(userServiceMock).findUserDBByUserSession(userSessionDtoMock);
            verify(userServiceMock).updateUser(userSessionDto, user);
            verify(sessionMock).setAttribute("user_sess", userSessionDto);
        });
    }

    //Sapient generated method id: ${showPageConfirmPasswordTest}, hash: EDD520A34F63FBAEC20AE8EDDECEAB6D
    @Test()
    void showPageConfirmPasswordTest() {
        //Arrange Statement(s)
        target = new ProfileController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        
        //Act Statement(s)
        String result = target.showPageConfirmPassword();
        
        //Assert statement(s)
        assertAll("result", () -> assertThat(result, equalTo("publics/ConfirmPass.html")));
    }

    //Sapient generated method id: ${ConfirmPasswordWhenUserGetPasswordNotEqualsSecurityEncodePassword}, hash: 301F9C9E174D8F10D8B21B2C0CE18B18
    @Test()
    void ConfirmPasswordWhenUserGetPasswordNotEqualsSecurityEncodePassword() {
        /* Branches:
         * (!user.getPassword().equals(security.encode(Password))) : true
         */
         //Arrange Statement(s)
        Model modelMock = mock(Model.class);
        Model modelMock2 = mock(Model.class);
        doReturn(modelMock2).when(modelMock).addAttribute("error", "password kh\u00F4ng \u0111\u00FAng");
        target = new ProfileController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        UserSessionDto userSessionDtoMock = mock(UserSessionDto.class);
        doReturn(userSessionDtoMock).when(sessionMock).getAttribute("user_sess");
        User user = new User();
        user.setPassword("A");
        doReturn(user).when(userServiceMock).findUserDBByUserSession(userSessionDtoMock);
        doReturn("B").when(securityMock).encode("Password1");
        
        //Act Statement(s)
        String result = target.ConfirmPassword(modelMock, "Password1");
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("publics/ConfirmPass.html"));
            verify(modelMock).addAttribute("error", "password kh\u00F4ng \u0111\u00FAng");
            verify(sessionMock).getAttribute("user_sess");
            verify(userServiceMock).findUserDBByUserSession(userSessionDtoMock);
            verify(securityMock).encode("Password1");
        });
    }

    //Sapient generated method id: ${ConfirmPasswordWhenUserGetPasswordEqualsSecurityEncodePassword}, hash: D56A07B2A12DFFC10A6431C490D3BC7B
    @Test()
    void ConfirmPasswordWhenUserGetPasswordEqualsSecurityEncodePassword() {
        /* Branches:
         * (!user.getPassword().equals(security.encode(Password))) : false
         */
         //Arrange Statement(s)
        target = new ProfileController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        UserSessionDto userSessionDtoMock = mock(UserSessionDto.class);
        doReturn(userSessionDtoMock).when(sessionMock).getAttribute("user_sess");
        User user = new User();
        user.setPassword("A");
        doReturn(user).when(userServiceMock).findUserDBByUserSession(userSessionDtoMock);
        doReturn("A").when(securityMock).encode("Password1");
        Model modelMock = mock(Model.class);
        
        //Act Statement(s)
        String result = target.ConfirmPassword(modelMock, "Password1");
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("publics/EnterNewEmail.html"));
            verify(sessionMock).getAttribute("user_sess");
            verify(userServiceMock).findUserDBByUserSession(userSessionDtoMock);
            verify(securityMock).encode("Password1");
        });
    }

    //Sapient generated method id: ${showPageEnterEmailWhenUserServiceCheckEmailExistEmail}, hash: 5941879A2FCEF51B7D1AF72410FB4987
    @Test()
    void showPageEnterEmailWhenUserServiceCheckEmailExistEmail() throws MessagingException {
        /* Branches:
         * (userService.checkEmailExist(email)) : true
         */
         //Arrange Statement(s)
        Model modelMock = mock(Model.class);
        Model modelMock2 = mock(Model.class);
        doReturn(modelMock2).when(modelMock).addAttribute("exit", true);
        target = new ProfileController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        doReturn(true).when(userServiceMock).checkEmailExist("email1");
        
        //Act Statement(s)
        String result = target.showPageEnterEmail(modelMock, "email1");
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("publics/EnterNewEmail.html"));
            verify(modelMock).addAttribute("exit", true);
            verify(userServiceMock).checkEmailExist("email1");
        });
    }

    //Sapient generated method id: ${showPageEnterEmailWhenSessionGetAttributeOtp_newemailIsNotNull}, hash: C68A36CA71F6E5E492F3A06C26056E29
    @Test()
    void showPageEnterEmailWhenSessionGetAttributeOtp_newemailIsNotNull() throws MessagingException {
        /* Branches:
         * (userService.checkEmailExist(email)) : false
         * (session.getAttribute("otp-newemail") != null) : true
         */
         //Arrange Statement(s)
        Model modelMock = mock(Model.class);
        Model modelMock2 = mock(Model.class);
        doReturn(modelMock2).when(modelMock).addAttribute("email", "email1");
        Model modelMock3 = mock(Model.class);
        doReturn(modelMock3).when(modelMock).addAttribute("action", "/profile/otp");
        Model modelMock4 = mock(Model.class);
        doReturn(modelMock4).when(modelMock).addAttribute("actionResend", "aa");
        target = new ProfileController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        doReturn(false).when(userServiceMock).checkEmailExist("email1");
        doReturn("return_of_createOtp1").when(emailServiceMock).createOtp();
        Object object = new Object();
        doReturn(object).when(sessionMock).getAttribute("otp-newemail");
        
        //Act Statement(s)
        String result = target.showPageEnterEmail(modelMock, "email1");
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("publics/verify-otp.html"));
            verify(modelMock).addAttribute("email", "email1");
            verify(modelMock).addAttribute("action", "/profile/otp");
            verify(modelMock).addAttribute("actionResend", "aa");
            verify(userServiceMock).checkEmailExist("email1");
            verify(emailServiceMock).createOtp();
            verify(sessionMock).getAttribute("otp-newemail");
        });
    }

    //Sapient generated method id: ${showPageEnterEmailWhenSessionGetAttributeTimesEnterOtp_newemailIsNull}, hash: 372BF26B485FDCB1C3A48650C0EF131E
    @Test()
    void showPageEnterEmailWhenSessionGetAttributeTimesEnterOtp_newemailIsNull() throws MessagingException {
        /* Branches:
         * (userService.checkEmailExist(email)) : false
         * (session.getAttribute("otp-newemail") != null) : false
         * (session.getAttribute("timesEnterOtp-newemail") == null) : true
         */
         //Arrange Statement(s)
        Model modelMock = mock(Model.class);
        Model modelMock2 = mock(Model.class);
        doReturn(modelMock2).when(modelMock).addAttribute("email", "email1");
        Model modelMock3 = mock(Model.class);
        doReturn(modelMock3).when(modelMock).addAttribute("action", "/profile/otp");
        Model modelMock4 = mock(Model.class);
        doReturn(modelMock4).when(modelMock).addAttribute("actionResend", "aa");
        target = new ProfileController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        doReturn(false).when(userServiceMock).checkEmailExist("email1");
        doReturn("return_of_createOtp1").when(emailServiceMock).createOtp();
        doReturn(null).when(sessionMock).getAttribute("otp-newemail");
        doNothing().when(sessionMock).setAttribute("otp-newemail", "return_of_createOtp1");
        doNothing().when(sessionMock).setMaxInactiveInterval(60);
        doNothing().when(emailServiceMock).sendEmail("email1", "return_of_createOtp1");
        doReturn(null).when(sessionMock).getAttribute("timesEnterOtp-newemail");
        doNothing().when(sessionMock).setAttribute("timesEnterOtp-newemail", 5);
        
        //Act Statement(s)
        String result = target.showPageEnterEmail(modelMock, "email1");
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("publics/verify-otp.html"));
            verify(modelMock).addAttribute("email", "email1");
            verify(modelMock).addAttribute("action", "/profile/otp");
            verify(modelMock).addAttribute("actionResend", "aa");
            verify(userServiceMock).checkEmailExist("email1");
            verify(emailServiceMock).createOtp();
            verify(sessionMock).getAttribute("otp-newemail");
            verify(sessionMock).setAttribute("otp-newemail", "return_of_createOtp1");
            verify(sessionMock).setMaxInactiveInterval(60);
            verify(emailServiceMock).sendEmail("email1", "return_of_createOtp1");
            verify(sessionMock).getAttribute("timesEnterOtp-newemail");
            verify(sessionMock).setAttribute("timesEnterOtp-newemail", 5);
        });
    }

    //Sapient generated method id: ${showPageEnterOtpTest}, hash: 057F930680F88EB5C12E5EE4CAC93EB4
    @Test()
    void showPageEnterOtpTest() throws MessagingException {
        //Arrange Statement(s)
        Model modelMock = mock(Model.class);
        Model modelMock2 = mock(Model.class);
        doReturn(modelMock2).when(modelMock).addAttribute("action", "/profile/otp");
        Model modelMock3 = mock(Model.class);
        doReturn(modelMock3).when(modelMock).addAttribute("actionResend", "aa");
        target = new ProfileController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        
        //Act Statement(s)
        String result = target.showPageEnterOtp(modelMock);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("publics/verify-otp.html"));
            verify(modelMock).addAttribute("action", "/profile/otp");
            verify(modelMock).addAttribute("actionResend", "aa");
        });
    }

    //Sapient generated method id: ${handlerEnterEmailWhenCodeEqualsOtp}, hash: 32CFC6D8CCF05F9F258E43FAEE59AE3D
    @Test()
    void handlerEnterEmailWhenCodeEqualsOtp() {
        /* Branches:
         * (code != null) : true
         * (code.equals(otp)) : true
         */
         //Arrange Statement(s)
        target = new ProfileController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        doReturn("A").when(sessionMock).getAttribute("otp-newemail");
        doNothing().when(sessionMock).removeAttribute("otp-newemail");
        UserSessionDto userSessionDto = new UserSessionDto();
        userSessionDto.setEmail("email1");
        doReturn(userSessionDto).when(sessionMock).getAttribute("user_sess");
        User userMock = mock(User.class);
        doReturn(userMock).when(userServiceMock).findUserDBByUserSession(userSessionDto);
        doNothing().when(userServiceMock).updateUserEmail(userMock, "email1");
        doNothing().when(sessionMock).setAttribute("user_sess", userSessionDto);
        Model modelMock = mock(Model.class);
        
        //Act Statement(s)
        String result = target.handlerEnterEmail(modelMock, "A", "email1");
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("redirect:/profile"));
            verify(sessionMock).getAttribute("otp-newemail");
            verify(sessionMock).removeAttribute("otp-newemail");
            verify(sessionMock).getAttribute("user_sess");
            verify(userServiceMock).findUserDBByUserSession(userSessionDto);
            verify(userServiceMock).updateUserEmail(userMock, "email1");
            verify(sessionMock).setAttribute("user_sess", userSessionDto);
        });
    }

    //Sapient generated method id: ${handlerEnterEmailWhenCodeIsNull}, hash: 45E71ED3D6202D70206B4AE89C930885
    @Test()
    void handlerEnterEmailWhenCodeIsNull() {
        /* Branches:
         * (code != null) : false
         * (code == null) : true
         */
         //Arrange Statement(s)
        Model modelMock = mock(Model.class);
        Model modelMock2 = mock(Model.class);
        doReturn(modelMock2).when(modelMock).addAttribute("action", "/profile/otp");
        Model modelMock3 = mock(Model.class);
        doReturn(modelMock3).when(modelMock).addAttribute("actionResend", "aa");
        Model modelMock4 = mock(Model.class);
        doReturn(modelMock4).when(modelMock).addAttribute("error", "OTP is expired");
        target = new ProfileController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        doReturn(null).when(sessionMock).getAttribute("otp-newemail");
        
        //Act Statement(s)
        String result = target.handlerEnterEmail(modelMock, "otp1", "email1");
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("publics/verify-otp.html"));
            verify(modelMock).addAttribute("action", "/profile/otp");
            verify(modelMock).addAttribute("actionResend", "aa");
            verify(modelMock).addAttribute("error", "OTP is expired");
            verify(sessionMock).getAttribute("otp-newemail");
        });
    }

    //Sapient generated method id: ${handlerEnterEmailWhenTimesEnterOtpCENotEquals0}, hash: 4657BDBD07EC1AA08C7045C43697E44B
    @Test()
    void handlerEnterEmailWhenTimesEnterOtpCENotEquals0() {
        /* Branches:
         * (code != null) : true
         * (code.equals(otp)) : false
         * (code == null) : false
         * (timesEnterOtpCE != 0) : true
         */
         //Arrange Statement(s)
        Model modelMock = mock(Model.class);
        Model modelMock2 = mock(Model.class);
        doReturn(modelMock2).when(modelMock).addAttribute("action", "/profile/otp");
        Model modelMock3 = mock(Model.class);
        doReturn(modelMock3).when(modelMock).addAttribute("actionResend", "aa");
        Model modelMock4 = mock(Model.class);
        doReturn(modelMock4).when(modelMock).addAttribute("error", "OTP kh\u00F4ng \u0111\u00FAng, b\u1EA1n c\u00F2n 2 l\u1EA7n nh\u1EADp");
        target = new ProfileController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        doReturn("A").when(sessionMock).getAttribute("otp-newemail");
        doReturn(3).when(sessionMock).getAttribute("timesEnterOtp-newemail");
        doNothing().when(sessionMock).setAttribute("timesEnterOtp-newemail", 2);
        
        //Act Statement(s)
        String result = target.handlerEnterEmail(modelMock, "B", "email1");
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("publics/verify-otp.html"));
            verify(modelMock).addAttribute("action", "/profile/otp");
            verify(modelMock).addAttribute("actionResend", "aa");
            verify(modelMock).addAttribute("error", "OTP kh\u00F4ng \u0111\u00FAng, b\u1EA1n c\u00F2n 2 l\u1EA7n nh\u1EADp");
            verify(sessionMock).getAttribute("otp-newemail");
            verify(sessionMock).getAttribute("timesEnterOtp-newemail");
            verify(sessionMock).setAttribute("timesEnterOtp-newemail", 2);
        });
    }

    //Sapient generated method id: ${handlerEnterEmailWhenTimesEnterOtpCEEquals0}, hash: A770D915B7F7A0E1DEC2E76F083962C7
    @Test()
    void handlerEnterEmailWhenTimesEnterOtpCEEquals0() {
        /* Branches:
         * (code != null) : true
         * (code.equals(otp)) : false
         * (code == null) : false
         * (timesEnterOtpCE != 0) : false
         */
         //Arrange Statement(s)
        target = new ProfileController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        doReturn("A").when(sessionMock).getAttribute("otp-newemail");
        doReturn(1).when(sessionMock).getAttribute("timesEnterOtp-newemail");
        doNothing().when(sessionMock).setAttribute("timesEnterOtp-newemail", 0);
        doNothing().when(sessionMock).removeAttribute("otp-newemail");
        doNothing().when(sessionMock).removeAttribute("timesEnterOtp-newemail");
        Model modelMock = mock(Model.class);
        
        //Act Statement(s)
        String result = target.handlerEnterEmail(modelMock, "B", "email1");
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("redirect:/profile"));
            verify(sessionMock).getAttribute("otp-newemail");
            verify(sessionMock).getAttribute("timesEnterOtp-newemail");
            verify(sessionMock).setAttribute("timesEnterOtp-newemail", 0);
            verify(sessionMock).removeAttribute("otp-newemail");
            verify(sessionMock).removeAttribute("timesEnterOtp-newemail");
        });
    }
}
