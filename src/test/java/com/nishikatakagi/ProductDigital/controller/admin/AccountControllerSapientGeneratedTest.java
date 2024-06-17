package com.nishikatakagi.ProductDigital.controller.admin;

import org.junit.jupiter.api.Timeout;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import com.nishikatakagi.ProductDigital.dto.UserRegisterRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.validation.FieldError;
import org.mockito.MockitoAnnotations;
import com.nishikatakagi.ProductDigital.dto.UserSessionDto;
import com.nishikatakagi.ProductDigital.model.User;
import org.springframework.ui.Model;
import com.nishikatakagi.ProductDigital.service.UserService;
import java.util.ArrayList;
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
import static org.mockito.Mockito.times;

@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
class AccountControllerSapientGeneratedTest {

    private final UserService userServiceMock = mock(UserService.class, "userService");

    private final HttpSession sessionMock = mock(HttpSession.class, "session");

    private AutoCloseable autoCloseableMocks;

    @InjectMocks()
    private AccountController target;

    @AfterEach()
    public void afterTest() throws Exception {
        if (autoCloseableMocks != null)
            autoCloseableMocks.close();
    }

    //Sapient generated method id: ${showPagePublisherWhenListAccountIsEmpty}, hash: DAC0FA4293633B8987B3AF1EBF9F8F37
    @Test()
    void showPagePublisherWhenListAccountIsEmpty() {
        /* Branches:
         * (pageNo < 0) : true
         * (listAccount.isEmpty()) : true
         */
         //Arrange Statement(s)
        Model modelMock = mock(Model.class);
        Model modelMock2 = mock(Model.class);
        doReturn(modelMock2).when(modelMock).addAttribute("error", "Kh\u00F4ng c\u00F3 t\u00E0i kho\u1EA3n n\u00E0o");
        Model modelMock3 = mock(Model.class);
        doReturn(modelMock3).when(modelMock).addAttribute("totalPages", -2147483648);
        Model modelMock4 = mock(Model.class);
        doReturn(modelMock4).when(modelMock).addAttribute("pageNo", 0);
        Model modelMock5 = mock(Model.class);
        doReturn(modelMock5).when(modelMock).addAttribute("action", "/account?");
        target = new AccountController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        Page<User> pageMock = mock(Page.class);
        doReturn(pageMock).when(userServiceMock).findAllUser(0, 1);
        doReturn(true).when(pageMock).isEmpty();
        doReturn(-2147483648).when(pageMock).getTotalPages();
        
        //Act Statement(s)
        String result = target.showPagePublisher(modelMock, -2147483648);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("pages/account/view-account.html"));
            verify(modelMock).addAttribute("error", "Kh\u00F4ng c\u00F3 t\u00E0i kho\u1EA3n n\u00E0o");
            verify(modelMock).addAttribute("totalPages", -2147483648);
            verify(modelMock).addAttribute("pageNo", 0);
            verify(modelMock).addAttribute("action", "/account?");
            verify(userServiceMock).findAllUser(0, 1);
            verify(pageMock).isEmpty();
            verify(pageMock).getTotalPages();
        });
    }

    //Sapient generated method id: ${showPagePublisherWhenListAccountNotIsEmpty}, hash: 51F3F614BBB410258FF10CED05DF7F3E
    @Test()
    void showPagePublisherWhenListAccountNotIsEmpty() {
        /* Branches:
         * (pageNo < 0) : true
         * (listAccount.isEmpty()) : false
         */
         //Arrange Statement(s)
        Model modelMock = mock(Model.class);
        Model modelMock2 = mock(Model.class);
        List<User> userList = new ArrayList<>();
        doReturn(modelMock2).when(modelMock).addAttribute("listAccount", userList);
        Model modelMock3 = mock(Model.class);
        doReturn(modelMock3).when(modelMock).addAttribute("totalPages", -2147483648);
        Model modelMock4 = mock(Model.class);
        doReturn(modelMock4).when(modelMock).addAttribute("pageNo", 0);
        Model modelMock5 = mock(Model.class);
        doReturn(modelMock5).when(modelMock).addAttribute("action", "/account?");
        target = new AccountController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        Page<User> pageMock = mock(Page.class);
        doReturn(pageMock).when(userServiceMock).findAllUser(0, 1);
        doReturn(false).when(pageMock).isEmpty();
        doReturn(userList).when(pageMock).getContent();
        doReturn(-2147483648).when(pageMock).getTotalPages();
        
        //Act Statement(s)
        String result = target.showPagePublisher(modelMock, -2147483648);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("pages/account/view-account.html"));
            verify(modelMock).addAttribute("listAccount", userList);
            verify(modelMock).addAttribute("totalPages", -2147483648);
            verify(modelMock).addAttribute("pageNo", 0);
            verify(modelMock).addAttribute("action", "/account?");
            verify(userServiceMock).findAllUser(0, 1);
            verify(pageMock).isEmpty();
            verify(pageMock).getContent();
            verify(pageMock).getTotalPages();
        });
    }

    //Sapient generated method id: ${deleteAccountWhenUserIsNull}, hash: 938F2BA93FEBCAD0B36EA91A17BA428F
    @Test()
    void deleteAccountWhenUserIsNull() {
        /* Branches:
         * (user == null) : true
         */
         //Arrange Statement(s)
        target = new AccountController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        doReturn(null).when(sessionMock).getAttribute("user_sess");
        RedirectAttributes redirectAttributesMock = mock(RedirectAttributes.class);
        
        //Act Statement(s)
        String result = target.deleteAccount("username1", redirectAttributesMock);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("redirect:/login"));
            verify(sessionMock).getAttribute("user_sess");
        });
    }

    //Sapient generated method id: ${deleteAccountWhenUserDeleteIsDeleted}, hash: 668B808D3C6464D2CE6988EB64C00D32
    @Test()
    void deleteAccountWhenUserDeleteIsDeleted() {
        /* Branches:
         * (user == null) : false
         * (userDelete.isDeleted()) : true
         */
         //Arrange Statement(s)
        RedirectAttributes modelMock = mock(RedirectAttributes.class);
        RedirectAttributes redirectAttributesMock = mock(RedirectAttributes.class);
        doReturn(redirectAttributesMock).when(modelMock).addFlashAttribute("error", "T\u00E0i kho\u1EA3n \u0111\u00E3 b\u1ECB kh\u00F3a");
        target = new AccountController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        UserSessionDto userSessionDtoMock = mock(UserSessionDto.class);
        doReturn(userSessionDtoMock).when(sessionMock).getAttribute("user_sess");
        User userMock = mock(User.class);
        doReturn(userMock).when(userServiceMock).findByUsername("username1");
        doReturn(true).when(userMock).isDeleted();
        
        //Act Statement(s)
        String result = target.deleteAccount("username1", modelMock);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("redirect:/account"));
            verify(modelMock).addFlashAttribute("error", "T\u00E0i kho\u1EA3n \u0111\u00E3 b\u1ECB kh\u00F3a");
            verify(sessionMock).getAttribute("user_sess");
            verify(userServiceMock).findByUsername("username1");
            verify(userMock).isDeleted();
        });
    }

    //Sapient generated method id: ${deleteAccountWhenUserDeleteGetRoleIdEquals1}, hash: 4EB51B3328E2510FF2D80C3F97056641
    @Test()
    void deleteAccountWhenUserDeleteGetRoleIdEquals1() {
        /* Branches:
         * (user == null) : false
         * (userDelete.isDeleted()) : false
         * (userDelete.getRoleId() == 1) : true
         */
         //Arrange Statement(s)
        RedirectAttributes modelMock = mock(RedirectAttributes.class);
        RedirectAttributes redirectAttributesMock = mock(RedirectAttributes.class);
        doReturn(redirectAttributesMock).when(modelMock).addFlashAttribute("error", "B\u1EA1n kh\u00F4ng th\u1EC3 kh\u00F3a t\u00E0i kho\u1EA3n n\u00E0y");
        target = new AccountController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        UserSessionDto userSessionDtoMock = mock(UserSessionDto.class);
        doReturn(userSessionDtoMock).when(sessionMock).getAttribute("user_sess");
        User userMock = mock(User.class);
        doReturn(userMock).when(userServiceMock).findByUsername("username1");
        doReturn(false).when(userMock).isDeleted();
        doReturn(1).when(userMock).getRoleId();
        
        //Act Statement(s)
        String result = target.deleteAccount("username1", modelMock);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("redirect:/account"));
            verify(modelMock).addFlashAttribute("error", "B\u1EA1n kh\u00F4ng th\u1EC3 kh\u00F3a t\u00E0i kho\u1EA3n n\u00E0y");
            verify(sessionMock).getAttribute("user_sess");
            verify(userServiceMock).findByUsername("username1");
            verify(userMock).isDeleted();
            verify(userMock).getRoleId();
        });
    }

    //Sapient generated method id: ${deleteAccountWhenUserDeleteGetRoleIdNotEquals1}, hash: B29DC03E068554D8C763EAA635274916
    @Test()
    void deleteAccountWhenUserDeleteGetRoleIdNotEquals1() {
        /* Branches:
         * (user == null) : false
         * (userDelete.isDeleted()) : false
         * (userDelete.getRoleId() == 1) : false
         */
         //Arrange Statement(s)
        target = new AccountController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        UserSessionDto userSessionDtoMock = mock(UserSessionDto.class);
        doReturn(userSessionDtoMock).when(sessionMock).getAttribute("user_sess");
        User userMock = mock(User.class);
        doReturn(userMock).when(userServiceMock).findByUsername("username1");
        doReturn(false).when(userMock).isDeleted();
        doReturn(2).when(userMock).getRoleId();
        doNothing().when(userServiceMock).deleteUserByAdmin("username1", userSessionDtoMock);
        RedirectAttributes redirectAttributesMock = mock(RedirectAttributes.class);
        
        //Act Statement(s)
        String result = target.deleteAccount("username1", redirectAttributesMock);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("redirect:/account"));
            verify(sessionMock).getAttribute("user_sess");
            verify(userServiceMock).findByUsername("username1");
            verify(userMock).isDeleted();
            verify(userMock).getRoleId();
            verify(userServiceMock).deleteUserByAdmin("username1", userSessionDtoMock);
        });
    }

    //Sapient generated method id: ${showCreateAccountPageTest}, hash: 5440D4003580ABDF5F2EF806F4B0D0DD
    @Test()
    void showCreateAccountPageTest() {
        //Arrange Statement(s)
        Model modelMock = mock(Model.class);
        Model modelMock2 = mock(Model.class);
        doReturn(modelMock2).when(modelMock).addAttribute(eq("user"), (UserRegisterRequestDto) any());
        target = new AccountController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        
        //Act Statement(s)
        String result = target.showCreateAccountPage(modelMock);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("pages/account/create-account.html"));
            verify(modelMock).addAttribute(eq("user"), (UserRegisterRequestDto) any());
        });
    }

    //Sapient generated method id: ${createAccountWhenUserSessionIsNull}, hash: 883861F6B7F17A5FDDE90403826427E2
    @Test()
    void createAccountWhenUserSessionIsNull() {
        /* Branches:
         * (userSession == null) : true
         */
         //Arrange Statement(s)
        target = new AccountController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        doReturn(null).when(sessionMock).getAttribute("user_sess");
        Model modelMock = mock(Model.class);
        UserRegisterRequestDto userRegisterRequestDtoMock = mock(UserRegisterRequestDto.class);
        BindingResult bindingResultMock = mock(BindingResult.class);
        
        //Act Statement(s)
        String result = target.createAccount(modelMock, userRegisterRequestDtoMock, bindingResultMock);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("redirect:/login"));
            verify(sessionMock).getAttribute("user_sess");
        });
    }

    //Sapient generated method id: ${createAccountWhenResultHasErrors}, hash: 566792C98EC1C054AF19B4E989F41930
    @Test()
    void createAccountWhenResultHasErrors() {
        /* Branches:
         * (userSession == null) : false
         * (userService.findByUsername(user.getUsername()) != null) : true
         * (userService.checkEmailExist(user.getEmail())) : true
         * (result.hasErrors()) : true
         */
         //Arrange Statement(s)
        Model modelMock = mock(Model.class);
        Model modelMock2 = mock(Model.class);
        UserRegisterRequestDto userRegisterRequestDto = new UserRegisterRequestDto();
        userRegisterRequestDto.setEmail("email1");
        userRegisterRequestDto.setUsername("username1");
        doReturn(modelMock2).when(modelMock).addAttribute("user", userRegisterRequestDto);
        BindingResult resultMock = mock(BindingResult.class);
        doNothing().when(resultMock).addError((FieldError) any());
        doReturn(true).when(resultMock).hasErrors();
        target = new AccountController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        UserSessionDto userSessionDtoMock = mock(UserSessionDto.class);
        doReturn(userSessionDtoMock).when(sessionMock).getAttribute("user_sess");
        User userMock = mock(User.class);
        doReturn(userMock).when(userServiceMock).findByUsername("username1");
        doReturn(true).when(userServiceMock).checkEmailExist("email1");
        
        //Act Statement(s)
        String result = target.createAccount(modelMock, userRegisterRequestDto, resultMock);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("pages/account/create-account.html"));
            verify(modelMock).addAttribute("user", userRegisterRequestDto);
            verify(resultMock, atLeast(2)).addError((FieldError) any());
            verify(resultMock).hasErrors();
            verify(sessionMock).getAttribute("user_sess");
            verify(userServiceMock).findByUsername("username1");
            verify(userServiceMock).checkEmailExist("email1");
        });
    }

    //Sapient generated method id: ${createAccountWhenResultNotHasErrors}, hash: BDFAB61D33C914CA0C0820215573BF53
    @Test()
    void createAccountWhenResultNotHasErrors() {
        /* Branches:
         * (userSession == null) : false
         * (userService.findByUsername(user.getUsername()) != null) : true
         * (userService.checkEmailExist(user.getEmail())) : true
         * (result.hasErrors()) : false
         */
         //Arrange Statement(s)
        BindingResult resultMock = mock(BindingResult.class);
        doNothing().when(resultMock).addError((FieldError) any());
        doReturn(false).when(resultMock).hasErrors();
        target = new AccountController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        UserSessionDto userSessionDtoMock = mock(UserSessionDto.class);
        doReturn(userSessionDtoMock).when(sessionMock).getAttribute("user_sess");
        User userMock = mock(User.class);
        doReturn(userMock).when(userServiceMock).findByUsername("username1");
        doReturn(true).when(userServiceMock).checkEmailExist("email1");
        User userMock2 = mock(User.class);
        doReturn(userMock2).when(userServiceMock).findUserDBByUserSession(userSessionDtoMock);
        UserRegisterRequestDto userRegisterRequestDto = new UserRegisterRequestDto();
        userRegisterRequestDto.setEmail("email1");
        userRegisterRequestDto.setUsername("username1");
        doNothing().when(userServiceMock).saveUser(userRegisterRequestDto, userMock2);
        Model modelMock = mock(Model.class);
        
        //Act Statement(s)
        String result = target.createAccount(modelMock, userRegisterRequestDto, resultMock);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("redirect:/account"));
            verify(resultMock, atLeast(2)).addError((FieldError) any());
            verify(resultMock).hasErrors();
            verify(sessionMock).getAttribute("user_sess");
            verify(userServiceMock).findByUsername("username1");
            verify(userServiceMock).checkEmailExist("email1");
            verify(userServiceMock).findUserDBByUserSession(userSessionDtoMock);
            verify(userServiceMock).saveUser(userRegisterRequestDto, userMock2);
        });
    }

    //Sapient generated method id: ${showDetailAccountWhenUserIsNull}, hash: DB4733CE879595B09A2D0A1043E19EF7
    @Test()
    void showDetailAccountWhenUserIsNull() {
        /* Branches:
         * (user == null) : true
         */
         //Arrange Statement(s)
        RedirectAttributes redirectAttributesMock = mock(RedirectAttributes.class);
        RedirectAttributes redirectAttributesMock2 = mock(RedirectAttributes.class);
        doReturn(redirectAttributesMock2).when(redirectAttributesMock).addFlashAttribute("error", "T\u00E0i kho\u1EA3n kh\u00F4ng t\u1ED3n t\u1EA1i");
        target = new AccountController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        doReturn(null).when(userServiceMock).findByUsername("username1");
        Model modelMock = mock(Model.class);
        
        //Act Statement(s)
        String result = target.showDetailAccount(modelMock, "username1", redirectAttributesMock);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("redirect:/account"));
            verify(redirectAttributesMock).addFlashAttribute("error", "T\u00E0i kho\u1EA3n kh\u00F4ng t\u1ED3n t\u1EA1i");
            verify(userServiceMock).findByUsername("username1");
        });
    }

    //Sapient generated method id: ${showDetailAccountWhenUserIsNotNull}, hash: E3FD524CE86C12DE8A0017DCA7CF5407
    @Test()
    void showDetailAccountWhenUserIsNotNull() {
        /* Branches:
         * (user == null) : false
         */
         //Arrange Statement(s)
        Model modelMock = mock(Model.class);
        Model modelMock2 = mock(Model.class);
        User userMock = mock(User.class);
        doReturn(modelMock2).when(modelMock).addAttribute("user", userMock);
        target = new AccountController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        doReturn(userMock).when(userServiceMock).findByUsername("username1");
        RedirectAttributes redirectAttributesMock = mock(RedirectAttributes.class);
        
        //Act Statement(s)
        String result = target.showDetailAccount(modelMock, "username1", redirectAttributesMock);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("pages/account/detailed-account.html"));
            verify(modelMock).addAttribute("user", userMock);
            verify(userServiceMock).findByUsername("username1");
        });
    }

    //Sapient generated method id: ${showChangePasswordPageWhenUserGetRoleIdEquals1}, hash: 1A4CAA4BCEBFA8E6DA7C375553557DEC
    @Test()
    void showChangePasswordPageWhenUserGetRoleIdEquals1() {
        /* Branches:
         * (user.getRoleId() == 1) : true
         */
         //Arrange Statement(s)
        RedirectAttributes redirectAttributesMock = mock(RedirectAttributes.class);
        RedirectAttributes redirectAttributesMock2 = mock(RedirectAttributes.class);
        doReturn(redirectAttributesMock2).when(redirectAttributesMock).addFlashAttribute("error", "B\u1EA1n kh\u00F4ng th\u1EC3 thay \u0111\u1ED5i m\u1EADt kh\u1EA9u c\u1EE7a t\u00E0i kho\u1EA3n n\u00E0y");
        target = new AccountController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        User user = new User();
        user.setRoleId(1);
        doReturn(user).when(userServiceMock).findByUsername("username1");
        Model modelMock = mock(Model.class);
        
        //Act Statement(s)
        String result = target.showChangePasswordPage(modelMock, "username1", redirectAttributesMock);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("redirect:/account"));
            verify(redirectAttributesMock).addFlashAttribute("error", "B\u1EA1n kh\u00F4ng th\u1EC3 thay \u0111\u1ED5i m\u1EADt kh\u1EA9u c\u1EE7a t\u00E0i kho\u1EA3n n\u00E0y");
            verify(userServiceMock).findByUsername("username1");
        });
    }

    //Sapient generated method id: ${showChangePasswordPageWhenUserGetRoleIdNotEquals1}, hash: 89B32E931D9417F909DD0A86230C8918
    @Test()
    void showChangePasswordPageWhenUserGetRoleIdNotEquals1() {
        /* Branches:
         * (user.getRoleId() == 1) : false
         */
         //Arrange Statement(s)
        Model modelMock = mock(Model.class);
        Model modelMock2 = mock(Model.class);
        User user = new User();
        user.setRoleId(2);
        doReturn(modelMock2).when(modelMock).addAttribute("user", user);
        target = new AccountController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        doReturn(user).when(userServiceMock).findByUsername("username1");
        RedirectAttributes redirectAttributesMock = mock(RedirectAttributes.class);
        
        //Act Statement(s)
        String result = target.showChangePasswordPage(modelMock, "username1", redirectAttributesMock);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("pages/account/change-password.html"));
            verify(modelMock).addAttribute("user", user);
            verify(userServiceMock).findByUsername("username1");
        });
    }

    //Sapient generated method id: ${changePasswordWhenUserSessionIsNull}, hash: 4246F2398BCBFC59E8B698F57C54F1E3
    @Test()
    void changePasswordWhenUserSessionIsNull() {
        /* Branches:
         * (userSession == null) : true
         */
         //Arrange Statement(s)
        target = new AccountController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        User userMock = mock(User.class);
        doReturn(userMock).when(userServiceMock).findByUsername("username1");
        doReturn(null).when(sessionMock).getAttribute("user_sess");
        
        //Act Statement(s)
        String result = target.changePassword("username1", "password1");
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("redirect:/login"));
            verify(userServiceMock).findByUsername("username1");
            verify(sessionMock).getAttribute("user_sess");
        });
    }

    //Sapient generated method id: ${changePasswordWhenUserSessionIsNotNull}, hash: 1573F6D51548E65E19344EDE8AD68401
    @Test()
    void changePasswordWhenUserSessionIsNotNull() {
        /* Branches:
         * (userSession == null) : false
         */
         //Arrange Statement(s)
        target = new AccountController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        User userMock = mock(User.class);
        UserSessionDto userSessionDto = new UserSessionDto();
        userSessionDto.setUsername("username1");
        doReturn(userSessionDto).when(sessionMock).getAttribute("user_sess");
        User userMock2 = mock(User.class);
        doReturn(userMock, userMock2).when(userServiceMock).findByUsername("username1");
        doNothing().when(userServiceMock).changePassword(userMock, "password1", userMock2);
        
        //Act Statement(s)
        String result = target.changePassword("username1", "password1");
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("redirect:/account"));
            verify(userServiceMock, times(2)).findByUsername("username1");
            verify(sessionMock).getAttribute("user_sess");
            verify(userServiceMock).changePassword(userMock, "password1", userMock2);
        });
    }

    //Sapient generated method id: ${activateAccountWhenUserSessionIsNull}, hash: D986527BC835D7F6A1824A746A9952C2
    @Test()
    void activateAccountWhenUserSessionIsNull() {
        /* Branches:
         * (userSession == null) : true
         */
         //Arrange Statement(s)
        target = new AccountController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        User userMock = mock(User.class);
        doReturn(userMock).when(userServiceMock).findByUsername("username1");
        doReturn(null).when(sessionMock).getAttribute("user_sess");
        RedirectAttributes redirectAttributesMock = mock(RedirectAttributes.class);
        
        //Act Statement(s)
        String result = target.activateAccount("username1", redirectAttributesMock);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("redirect:/login"));
            verify(userServiceMock).findByUsername("username1");
            verify(sessionMock).getAttribute("user_sess");
        });
    }

    //Sapient generated method id: ${activateAccountWhenUserGetRoleIdEquals1}, hash: C3F1FAA4DAA56649564E9CCD379EDA65
    @Test()
    void activateAccountWhenUserGetRoleIdEquals1() {
        /* Branches:
         * (userSession == null) : false
         * (user.getRoleId() == 1) : true
         */
         //Arrange Statement(s)
        RedirectAttributes modelMock = mock(RedirectAttributes.class);
        RedirectAttributes redirectAttributesMock = mock(RedirectAttributes.class);
        doReturn(redirectAttributesMock).when(modelMock).addFlashAttribute("error", "B\u1EA1n kh\u00F4ng th\u1EC3 k\u00EDch ho\u1EA1t t\u00E0i kho\u1EA3n n\u00E0y");
        target = new AccountController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        User user = new User();
        user.setRoleId(1);
        doReturn(user).when(userServiceMock).findByUsername("username1");
        UserSessionDto userSessionDtoMock = mock(UserSessionDto.class);
        doReturn(userSessionDtoMock).when(sessionMock).getAttribute("user_sess");
        
        //Act Statement(s)
        String result = target.activateAccount("username1", modelMock);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("redirect:/account"));
            verify(modelMock).addFlashAttribute("error", "B\u1EA1n kh\u00F4ng th\u1EC3 k\u00EDch ho\u1EA1t t\u00E0i kho\u1EA3n n\u00E0y");
            verify(userServiceMock).findByUsername("username1");
            verify(sessionMock).getAttribute("user_sess");
        });
    }

    //Sapient generated method id: ${activateAccountWhenUserGetRoleIdNotEquals1}, hash: 7C4778A25903622631AE15328E8E6673
    @Test()
    void activateAccountWhenUserGetRoleIdNotEquals1() {
        /* Branches:
         * (userSession == null) : false
         * (user.getRoleId() == 1) : false
         */
         //Arrange Statement(s)
        target = new AccountController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        User user = new User();
        user.setRoleId(2);
        UserSessionDto userSessionDto = new UserSessionDto();
        userSessionDto.setUsername("username1");
        doReturn(userSessionDto).when(sessionMock).getAttribute("user_sess");
        User userMock = mock(User.class);
        doReturn(user, userMock).when(userServiceMock).findByUsername("username1");
        doNothing().when(userServiceMock).activateAccount(user, userMock);
        RedirectAttributes redirectAttributesMock = mock(RedirectAttributes.class);
        
        //Act Statement(s)
        String result = target.activateAccount("username1", redirectAttributesMock);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("redirect:/account"));
            verify(userServiceMock, times(2)).findByUsername("username1");
            verify(sessionMock).getAttribute("user_sess");
            verify(userServiceMock).activateAccount(user, userMock);
        });
    }

    //Sapient generated method id: ${filterAccountWhenRoleIdIsNotEmptyAndIsDeletedIsNotNullAndIsDeletedIsNotEmptyAndUsernameIsNotNull}, hash: CCF6655196C7B3B50216E2750EB26742
    @Test()
    void filterAccountWhenRoleIdIsNotEmptyAndIsDeletedIsNotNullAndIsDeletedIsNotEmptyAndUsernameIsNotNull() {
        /* Branches:
         * (listAccount1.isEmpty()) : true
         * (roleId != null) : true
         * (for-each(roleId)) : true
         * (isDeleted != null) : true
         * (for-each(isDeleted)) : true
         * (username != null) : true
         */
         //Arrange Statement(s)
        Model modelMock = mock(Model.class);
        Model modelMock2 = mock(Model.class);
        doReturn(modelMock2).when(modelMock).addAttribute("error", "Kh\u00F4ng c\u00F3 t\u00E0i kho\u1EA3n n\u00E0o");
        Model modelMock3 = mock(Model.class);
        doReturn(modelMock3).when(modelMock).addAttribute("action", "/account/filter?role=2&is-deleted=2&username=A&");
        Model modelMock4 = mock(Model.class);
        doReturn(modelMock4).when(modelMock).addAttribute("totalPages", 1);
        Model modelMock5 = mock(Model.class);
        doReturn(modelMock5).when(modelMock).addAttribute("pageNo", 0);
        Model modelMock6 = mock(Model.class);
        List<User> userList = new ArrayList<>();
        doReturn(modelMock6).when(modelMock).addAttribute("listAccount", userList);
        Model modelMock7 = mock(Model.class);
        List<Integer> integerList = new ArrayList<>();
        integerList.add(2);
        doReturn(modelMock7).when(modelMock).addAttribute("role", integerList);
        Model modelMock8 = mock(Model.class);
        List<Integer> integerList2 = new ArrayList<>();
        integerList2.add(2);
        doReturn(modelMock8).when(modelMock).addAttribute("isDeleted", integerList2);
        Model modelMock9 = mock(Model.class);
        doReturn(modelMock9).when(modelMock).addAttribute("username", "A");
        target = new AccountController();
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        Page<User> pageMock = mock(Page.class);
        doReturn(pageMock).when(userServiceMock).filterAccount(integerList, integerList2, "A", 0, 1);
        doReturn(userList).when(pageMock).getContent();
        doReturn(1).when(pageMock).getTotalPages();
        
        //Act Statement(s)
        String result = target.filterAccount(integerList, integerList2, "A", 0, modelMock);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("pages/account/view-account.html"));
            verify(modelMock).addAttribute("error", "Kh\u00F4ng c\u00F3 t\u00E0i kho\u1EA3n n\u00E0o");
            verify(modelMock).addAttribute("action", "/account/filter?role=2&is-deleted=2&username=A&");
            verify(modelMock).addAttribute("totalPages", 1);
            verify(modelMock).addAttribute("pageNo", 0);
            verify(modelMock).addAttribute("listAccount", userList);
            verify(modelMock).addAttribute("role", integerList);
            verify(modelMock).addAttribute("isDeleted", integerList2);
            verify(modelMock).addAttribute("username", "A");
            verify(userServiceMock).filterAccount(integerList, integerList2, "A", 0, 1);
            verify(pageMock).getContent();
            verify(pageMock).getTotalPages();
        });
    }
}
