package com.nishikatakagi.ProductDigital.controller;

import org.junit.jupiter.api.Timeout;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import jakarta.mail.MessagingException;
import com.nishikatakagi.ProductDigital.dto.UserRegisterRequestDto;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.validation.FieldError;
import org.mockito.MockitoAnnotations;
import com.nishikatakagi.ProductDigital.service.CaptchaService;
import com.nishikatakagi.ProductDigital.mapper.UserRegisterMapper;
import com.nishikatakagi.ProductDigital.service.EmailService;
import com.nishikatakagi.ProductDigital.model.User;
import java.util.Map;
import org.springframework.ui.Model;
import java.util.concurrent.ConcurrentHashMap;
import com.nishikatakagi.ProductDigital.service.UserService;
import org.springframework.validation.BindingResult;
import jakarta.servlet.http.HttpSession;
import static org.mockito.Mockito.doNothing;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.doReturn;
import org.junit.jupiter.api.Disabled;

@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
class RegisterControllerSapientGeneratedTest {

    private final HttpSession sessionMock = mock(HttpSession.class, "session");

    private final UserService userServiceMock = mock(UserService.class, "userService");

    private final EmailService emailServiceMock = mock(EmailService.class, "emailService");

    private final CaptchaService captchaServiceMock = mock(CaptchaService.class, "captchaService");

    private final UserRegisterMapper userRegisterMapperMock = mock(UserRegisterMapper.class, "userRegisterMapper");

    private AutoCloseable autoCloseableMocks;

    @InjectMocks()
    private RegisterController target;

    @AfterEach()
    public void afterTest() throws Exception {
        if (autoCloseableMocks != null)
            autoCloseableMocks.close();
    }

    //Sapient generated method id: ${showRegisterPageWhenSessionGetAttributeUser_sessIsNotNull}, hash: F8DA067475D80E5783401B74CD492CBD
    @Test()
    void showRegisterPageWhenSessionGetAttributeUser_sessIsNotNull() {
        /* Branches:
         * (session.getAttribute("user_sess") != null) : true
         */
        //Arrange Statement(s)
        target = new RegisterController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        Object object = new Object();
        doReturn(object).when(sessionMock).getAttribute("user_sess");
        Model modelMock = mock(Model.class);
        //Act Statement(s)
        String result = target.showRegisterPage(modelMock);
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("redirect:/"));
            verify(sessionMock).getAttribute("user_sess");
        });
    }

    //Sapient generated method id: ${showRegisterPageWhenSessionGetAttributeUser_sessIsNull}, hash: 351C5C77F6DF4CC26E6B2DDA9B8A5A38
    @Disabled()
    @Test()
    void showRegisterPageWhenSessionGetAttributeUser_sessIsNull() {
        /* Branches:
         * (session.getAttribute("user_sess") != null) : false
         */
        //Arrange Statement(s)
        Model modelMock = mock(Model.class);
        Model modelMock2 = mock(Model.class);
        doReturn(modelMock2).when(modelMock).addAttribute("idCaptcha", 1);
        Model modelMock3 = mock(Model.class);
        String[] stringArray = new String[] {};
        Map<Integer, String[]> integerStringMap = new ConcurrentHashMap<>();
        integerStringMap.put(1, stringArray);
        doReturn(modelMock3).when(modelMock).addAttribute(eq("captchaSvg"), eq(integerStringMap));
        Model modelMock4 = mock(Model.class);
        doReturn(modelMock4).when(modelMock).addAttribute(eq("user"), (UserRegisterRequestDto) any());
        target = new RegisterController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        doReturn(null).when(sessionMock).getAttribute("user_sess");
        doReturn(1).when(captchaServiceMock).createIDCaptcha();
        doReturn(stringArray).when(captchaServiceMock).captchaValueHTML(3, 8, 4, 4);
        //Act Statement(s)
        String result = target.showRegisterPage(modelMock);
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("publics/register"));
            verify(modelMock).addAttribute("idCaptcha", 1);
            verify(modelMock).addAttribute(eq("captchaSvg"), eq(integerStringMap));
            verify(modelMock).addAttribute(eq("user"), (UserRegisterRequestDto) any());
            verify(sessionMock).getAttribute("user_sess");
            verify(captchaServiceMock).createIDCaptcha();
            verify(captchaServiceMock).captchaValueHTML(3, 8, 4, 4);
        });
    }

    //Sapient generated method id: ${processRegistrationFormWhenUserServiceCheckEmailExistUserGetEmailAndResultHasErrors2}, hash: 01E3672FAABC30AC5E52C06A803851B9
    @Disabled()
    @Test()
    void processRegistrationFormWhenUserServiceCheckEmailExistUserGetEmailAndResultHasErrors2() {
        /* Branches:
         * (user.getRepassword() != null) : true
         * (!user.getPassword().equals(user.getRepassword())) : true
         * (!user.getPhone().isEmpty()) : true
         * (user.getPhone().length() != 10) : true
         * (!user.getCaptcha().equals(captchaValue)) : true
         * (userService.checkUsernameExist(user.getUsername())) : true
         * (userService.checkEmailExist(user.getEmail())) : true
         * (result.hasErrors()) : true
         */
        //Arrange Statement(s)
        BindingResult resultMock = mock(BindingResult.class);
        doNothing().when(resultMock).addError((FieldError) any());
        doReturn(true).when(resultMock).hasErrors();
        Model modelMock = mock(Model.class);
        Model modelMock2 = mock(Model.class);
        UserRegisterRequestDto userRegisterRequestDto = new UserRegisterRequestDto();
        userRegisterRequestDto.setPassword("C");
        userRegisterRequestDto.setRepassword("D");
        userRegisterRequestDto.setPhone("A");
        userRegisterRequestDto.setCaptcha("B");
        userRegisterRequestDto.setEmail("email1");
        userRegisterRequestDto.setUsername("username1");
        doReturn(modelMock2).when(modelMock).addAttribute("user", userRegisterRequestDto);
        Model modelMock3 = mock(Model.class);
        doReturn(modelMock3).when(modelMock).addAttribute("idCaptcha", 1);
        Model modelMock4 = mock(Model.class);
        String[] stringArray = new String[] {};
        Map<Integer, String[]> integerStringMap = new ConcurrentHashMap<>();
        integerStringMap.put(1, stringArray);
        doReturn(modelMock4).when(modelMock).addAttribute(eq("captchaSvg"), eq(integerStringMap));
        target = new RegisterController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        doReturn(true).when(userServiceMock).checkUsernameExist("username1");
        doReturn(true).when(userServiceMock).checkEmailExist("email1");
        doReturn(1).when(captchaServiceMock).createIDCaptcha();
        doReturn(stringArray).when(captchaServiceMock).captchaValueHTML(8, 1, 3, 6);
        //Act Statement(s)
        String result = target.processRegistrationForm(userRegisterRequestDto, resultMock, modelMock, 1);
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("publics/register"));
            verify(resultMock, atLeast(5)).addError((FieldError) any());
            verify(resultMock).hasErrors();
            verify(modelMock).addAttribute("user", userRegisterRequestDto);
            verify(modelMock).addAttribute("idCaptcha", 1);
            verify(modelMock).addAttribute(eq("captchaSvg"), eq(integerStringMap));
            verify(userServiceMock).checkUsernameExist("username1");
            verify(userServiceMock).checkEmailExist("email1");
            verify(captchaServiceMock).createIDCaptcha();
            verify(captchaServiceMock).captchaValueHTML(8, 1, 3, 6);
        });
    }

    //Sapient generated method id: ${processRegistrationFormWhenUserServiceCheckEmailExistUserGetEmailAndResultNotHasErrors4}, hash: 2A7AB55083AC6FAEE86C761152F85D4F
    @Test()
    void processRegistrationFormWhenUserServiceCheckEmailExistUserGetEmailAndResultNotHasErrors4() {
        /* Branches:
         * (user.getRepassword() != null) : true
         * (!user.getPassword().equals(user.getRepassword())) : true
         * (!user.getPhone().isEmpty()) : true
         * (user.getPhone().length() != 10) : true
         * (!user.getCaptcha().equals(captchaValue)) : true
         * (userService.checkUsernameExist(user.getUsername())) : true
         * (userService.checkEmailExist(user.getEmail())) : true
         * (result.hasErrors()) : false
         */
        //Arrange Statement(s)
        BindingResult resultMock = mock(BindingResult.class);
        doNothing().when(resultMock).addError((FieldError) any());
        doReturn(false).when(resultMock).hasErrors();
        target = new RegisterController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        doReturn(true).when(userServiceMock).checkUsernameExist("username1");
        doReturn(true).when(userServiceMock).checkEmailExist("email1");
        User userMock = mock(User.class);
        UserRegisterRequestDto userRegisterRequestDto = new UserRegisterRequestDto();
        userRegisterRequestDto.setPassword("C");
        userRegisterRequestDto.setRepassword("D");
        userRegisterRequestDto.setPhone("A");
        userRegisterRequestDto.setCaptcha("B");
        userRegisterRequestDto.setEmail("email1");
        userRegisterRequestDto.setUsername("username1");
        doReturn(userMock).when(userRegisterMapperMock).toUser(userRegisterRequestDto);
        doNothing().when(userMock).setVerified(false);
        doNothing().when(userMock).setDeleted(true);
        doNothing().when(sessionMock).setAttribute("rawUser-register", userMock);
        User userMock2 = mock(User.class);
        doReturn(userMock2).when(userServiceMock).saveUser(userMock);
        Model modelMock = mock(Model.class);
        //Act Statement(s)
        String result = target.processRegistrationForm(userRegisterRequestDto, resultMock, modelMock, 1);
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("redirect:register/otp"));
            verify(resultMock, atLeast(5)).addError((FieldError) any());
            verify(resultMock).hasErrors();
            verify(userServiceMock).checkUsernameExist("username1");
            verify(userServiceMock).checkEmailExist("email1");
            verify(userRegisterMapperMock).toUser(userRegisterRequestDto);
            verify(userMock).setVerified(false);
            verify(userMock).setDeleted(true);
            verify(sessionMock).setAttribute("rawUser-register", userMock);
            verify(userServiceMock).saveUser(userMock);
        });
    }

    //Sapient generated method id: ${showOtpWhenUserIsNull}, hash: 2A27C230F70F74F53BE08F2C46961BAE
    @Test()
    void showOtpWhenUserIsNull() throws MessagingException {
        /* Branches:
         * (user == null) : true
         */
        //Arrange Statement(s)
        target = new RegisterController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        doReturn(null).when(sessionMock).getAttribute("rawUser-register");
        Model modelMock = mock(Model.class);
        //Act Statement(s)
        String result = target.showOtp(modelMock);
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("redirect:/"));
            verify(sessionMock).getAttribute("rawUser-register");
        });
    }

    //Sapient generated method id: ${showOtpWhenSessionGetAttributeTimesEnterOtp_registerIsNull}, hash: 9594A2666D862DB87C75A4E1AF44B46C
    @Test()
    void showOtpWhenSessionGetAttributeTimesEnterOtp_registerIsNull() throws MessagingException {
        /* Branches:
         * (user == null) : false
         * (session.getAttribute("timesEnterOtp-register") == null) : true
         */
        //Arrange Statement(s)
        Model modelMock = mock(Model.class);
        Model modelMock2 = mock(Model.class);
        doReturn(modelMock2).when(modelMock).addAttribute("action", "/register/otp");
        target = new RegisterController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        User user = new User();
        user.setEmail("email1");
        doReturn(user).when(sessionMock).getAttribute("rawUser-register");
        doReturn("return_of_createOtp1").when(emailServiceMock).createOtp();
        doNothing().when(emailServiceMock).sendEmail("email1", "return_of_createOtp1");
        doNothing().when(sessionMock).setAttribute("otp-register", "return_of_createOtp1");
        doNothing().when(sessionMock).setMaxInactiveInterval(300);
        doReturn(null).when(sessionMock).getAttribute("timesEnterOtp-register");
        doNothing().when(sessionMock).setAttribute("timesEnterOtp-register", 5);
        //Act Statement(s)
        String result = target.showOtp(modelMock);
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("publics/verify-otp.html"));
            verify(modelMock).addAttribute("action", "/register/otp");
            verify(sessionMock).getAttribute("rawUser-register");
            verify(emailServiceMock).createOtp();
            verify(emailServiceMock).sendEmail("email1", "return_of_createOtp1");
            verify(sessionMock).setAttribute("otp-register", "return_of_createOtp1");
            verify(sessionMock).setMaxInactiveInterval(300);
            verify(sessionMock).getAttribute("timesEnterOtp-register");
            verify(sessionMock).setAttribute("timesEnterOtp-register", 5);
        });
    }

    //Sapient generated method id: ${verifyOtpWhenUserIsNull}, hash: 4D8CBFDAE49044891684EB85092A6F9B
    @Test()
    void verifyOtpWhenUserIsNull() {
        /* Branches:
         * (user == null) : true
         */
        //Arrange Statement(s)
        target = new RegisterController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        doReturn(null).when(sessionMock).getAttribute("rawUser-register");
        Model modelMock = mock(Model.class);
        RedirectAttributes redirectAttributesMock = mock(RedirectAttributes.class);
        //Act Statement(s)
        String result = target.verifyOtp(modelMock, "otp1", redirectAttributesMock);
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("redirect:/"));
            verify(sessionMock).getAttribute("rawUser-register");
        });
    }

    //Sapient generated method id: ${verifyOtpWhenCodeEqualsOtp}, hash: 4F2FA85A32F970CBDEBA61C2205485AD
    @Test()
    void verifyOtpWhenCodeEqualsOtp() {
        /* Branches:
         * (user == null) : false
         * (code != null) : true
         * (code.equals(otp)) : true
         */
        //Arrange Statement(s)
        RedirectAttributes redirectAttributesMock = mock(RedirectAttributes.class);
        RedirectAttributes redirectAttributesMock2 = mock(RedirectAttributes.class);
        doReturn(redirectAttributesMock2).when(redirectAttributesMock).addFlashAttribute("accountCreated", true);
        target = new RegisterController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        User userMock = mock(User.class);
        doReturn(userMock).when(sessionMock).getAttribute("rawUser-register");
        doNothing().when(userMock).setVerified(true);
        doNothing().when(userMock).setDeleted(false);
        doReturn("A").when(sessionMock).getAttribute("otp-register");
        User userMock2 = mock(User.class);
        doReturn(userMock2).when(userServiceMock).saveUser(userMock);
        doNothing().when(sessionMock).removeAttribute("rawUser-register");
        doNothing().when(sessionMock).removeAttribute("otp-register");
        Model modelMock = mock(Model.class);
        //Act Statement(s)
        String result = target.verifyOtp(modelMock, "A", redirectAttributesMock);
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("redirect:/login"));
            verify(redirectAttributesMock).addFlashAttribute("accountCreated", true);
            verify(sessionMock).getAttribute("rawUser-register");
            verify(userMock).setVerified(true);
            verify(userMock).setDeleted(false);
            verify(sessionMock).getAttribute("otp-register");
            verify(userServiceMock).saveUser(userMock);
            verify(sessionMock).removeAttribute("rawUser-register");
            verify(sessionMock).removeAttribute("otp-register");
        });
    }

    //Sapient generated method id: ${verifyOtpWhenCodeIsNull}, hash: ABB81E6D2A6941AD18FD8A404629188C
    @Test()
    void verifyOtpWhenCodeIsNull() {
        /* Branches:
         * (user == null) : false
         * (code != null) : false
         * (code == null) : true
         */
        //Arrange Statement(s)
        Model modelMock = mock(Model.class);
        Model modelMock2 = mock(Model.class);
        doReturn(modelMock2).when(modelMock).addAttribute("action", "/register/otp");
        Model modelMock3 = mock(Model.class);
        doReturn(modelMock3).when(modelMock).addAttribute("error", "OTP is expired");
        target = new RegisterController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        User userMock = mock(User.class);
        doReturn(userMock).when(sessionMock).getAttribute("rawUser-register");
        doReturn(null).when(sessionMock).getAttribute("otp-register");
        doNothing().when(userServiceMock).deleteUser(userMock);
        doNothing().when(sessionMock).removeAttribute("rawUser-register");
        doNothing().when(sessionMock).removeAttribute("otp-register");
        RedirectAttributes redirectAttributesMock = mock(RedirectAttributes.class);
        //Act Statement(s)
        String result = target.verifyOtp(modelMock, "otp1", redirectAttributesMock);
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("publics/verify-otp.html"));
            verify(modelMock).addAttribute("action", "/register/otp");
            verify(modelMock).addAttribute("error", "OTP is expired");
            verify(sessionMock).getAttribute("rawUser-register");
            verify(sessionMock).getAttribute("otp-register");
            verify(userServiceMock).deleteUser(userMock);
            verify(sessionMock).removeAttribute("rawUser-register");
            verify(sessionMock).removeAttribute("otp-register");
        });
    }

    //Sapient generated method id: ${verifyOtpWhenTimesEnterOtpGreaterThan0}, hash: BE5A3810F069C62CE4784C3A67CF2284
    @Test()
    void verifyOtpWhenTimesEnterOtpGreaterThan0() {
        /* Branches:
         * (user == null) : false
         * (code != null) : true
         * (code.equals(otp)) : false
         * (code == null) : false
         * (timesEnterOtp > 0) : true
         */
        //Arrange Statement(s)
        Model modelMock = mock(Model.class);
        Model modelMock2 = mock(Model.class);
        doReturn(modelMock2).when(modelMock).addAttribute("action", "/register/otp");
        Model modelMock3 = mock(Model.class);
        doReturn(modelMock3).when(modelMock).addAttribute("error", "OTP ch\u01B0a \u0111\u00FAng. B\u1EA1n c\u00F2n 2 l\u1EA7n th\u1EED");
        target = new RegisterController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        User userMock = mock(User.class);
        doReturn(userMock).when(sessionMock).getAttribute("rawUser-register");
        doReturn("A").when(sessionMock).getAttribute("otp-register");
        doReturn(3).when(sessionMock).getAttribute("timesEnterOtp-register");
        doNothing().when(sessionMock).setAttribute("timesEnterOtp-register", 2);
        RedirectAttributes redirectAttributesMock = mock(RedirectAttributes.class);
        //Act Statement(s)
        String result = target.verifyOtp(modelMock, "C", redirectAttributesMock);
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("publics/verify-otp.html"));
            verify(modelMock).addAttribute("action", "/register/otp");
            verify(modelMock).addAttribute("error", "OTP ch\u01B0a \u0111\u00FAng. B\u1EA1n c\u00F2n 2 l\u1EA7n th\u1EED");
            verify(sessionMock).getAttribute("rawUser-register");
            verify(sessionMock).getAttribute("otp-register");
            verify(sessionMock).getAttribute("timesEnterOtp-register");
            verify(sessionMock).setAttribute("timesEnterOtp-register", 2);
        });
    }

    //Sapient generated method id: ${verifyOtpWhenTimesEnterOtpNotGreaterThan0}, hash: 37CCB894471977A0ACC410308AD9155B
    @Test()
    void verifyOtpWhenTimesEnterOtpNotGreaterThan0() {
        /* Branches:
         * (user == null) : false
         * (code != null) : true
         * (code.equals(otp)) : false
         * (code == null) : false
         * (timesEnterOtp > 0) : false
         */
        //Arrange Statement(s)
        target = new RegisterController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        User userMock = mock(User.class);
        doReturn(userMock).when(sessionMock).getAttribute("rawUser-register");
        doReturn("A").when(sessionMock).getAttribute("otp-register");
        doReturn(0).when(sessionMock).getAttribute("timesEnterOtp-register");
        doNothing().when(sessionMock).setAttribute("timesEnterOtp-register", -1);
        doNothing().when(userServiceMock).deleteUser(userMock);
        doNothing().when(sessionMock).removeAttribute("rawUser-register");
        doNothing().when(sessionMock).removeAttribute("otp-register");
        doNothing().when(sessionMock).removeAttribute("timesEnterOtp-register");
        Model modelMock = mock(Model.class);
        RedirectAttributes redirectAttributesMock = mock(RedirectAttributes.class);
        //Act Statement(s)
        String result = target.verifyOtp(modelMock, "B", redirectAttributesMock);
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("redirect:/register"));
            verify(sessionMock).getAttribute("rawUser-register");
            verify(sessionMock).getAttribute("otp-register");
            verify(sessionMock).getAttribute("timesEnterOtp-register");
            verify(sessionMock).setAttribute("timesEnterOtp-register", -1);
            verify(userServiceMock).deleteUser(userMock);
            verify(sessionMock).removeAttribute("rawUser-register");
            verify(sessionMock).removeAttribute("otp-register");
            verify(sessionMock).removeAttribute("timesEnterOtp-register");
        });
    }
}
