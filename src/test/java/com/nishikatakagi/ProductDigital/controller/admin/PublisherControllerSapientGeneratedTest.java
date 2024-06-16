package com.nishikatakagi.ProductDigital.controller.admin;

import org.junit.jupiter.api.Timeout;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import java.io.IOException;
import java.util.List;
import java.io.ByteArrayInputStream;
import org.mockito.MockitoAnnotations;
import com.nishikatakagi.ProductDigital.dto.UserSessionDto;
import com.nishikatakagi.ProductDigital.model.User;
import com.nishikatakagi.ProductDigital.service.PublisherService;
import org.springframework.ui.Model;
import com.nishikatakagi.ProductDigital.model.Publisher;
import com.nishikatakagi.ProductDigital.service.UserService;
import java.nio.file.StandardCopyOption;
import java.nio.file.CopyOption;
import java.util.ArrayList;
import org.springframework.validation.BindingResult;
import java.io.InputStream;
import com.nishikatakagi.ProductDigital.dto.PublisherDto;
import jakarta.servlet.http.HttpSession;
import java.nio.file.attribute.FileAttribute;
import org.mockito.stubbing.Answer;
import org.springframework.validation.FieldError;
import java.nio.file.Files;
import java.nio.file.Path;
import org.mockito.MockedStatic;
import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayOutputStream;
import static org.mockito.Mockito.doNothing;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.doReturn;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.atLeast;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.mockito.Mockito.mockStatic;

@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
class PublisherControllerSapientGeneratedTest {

    private final PublisherService publisherServiceMock = mock(PublisherService.class, "publisherService");

    private final UserService userServiceMock = mock(UserService.class, "userService");

    private final HttpSession sessionMock = mock(HttpSession.class, "session");

    private AutoCloseable autoCloseableMocks;

    @InjectMocks()
    private PublisherController target;

    @AfterEach()
    public void afterTest() throws Exception {
        if (autoCloseableMocks != null)
            autoCloseableMocks.close();
    }

    //Sapient generated method id: ${ShowPagePublisherTest}, hash: 54895A25340B21441721B89CFA30EA65
    @Test()
    void ShowPagePublisherTest() {
        //Arrange Statement(s)
        Model modelMock = mock(Model.class);
        Model modelMock2 = mock(Model.class);
        List<Publisher> publisherList = new ArrayList<>();
        doReturn(modelMock2).when(modelMock).addAttribute("listPublisher", publisherList);
        target = new PublisherController(publisherServiceMock);
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        doReturn(publisherList).when(publisherServiceMock).getAllPublisher();
        
        //Act Statement(s)
        String result = target.ShowPagePublisher(modelMock);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("pages/publisher/publisher.html"));
            verify(modelMock).addAttribute("listPublisher", publisherList);
            verify(publisherServiceMock).getAllPublisher();
        });
    }

    //Sapient generated method id: ${ShowPageUpdatePublisherWhenPublisherNotGetIsDeleted}, hash: 85F8BCC9D6206EC336C2725194400B6B
    @Disabled()
    @Test()
    void ShowPageUpdatePublisherWhenPublisherNotGetIsDeleted() {
        /* Branches:
         * (publisher.getIsDeleted()) : true
         * (!publisher.getIsDeleted()) : true
         *
         * TODO: Help needed! Please adjust the input/test parameter values manually to satisfy the requirements of the given test scenario.
         *  The test code, including the assertion statements, has been successfully generated.
         */
         //Arrange Statement(s)
        Model modelMock = mock(Model.class);
        Model modelMock2 = mock(Model.class);
        Publisher publisher = new Publisher();
        publisher.setIsDeleted(false);
        publisher.setName("name1");
        doReturn(modelMock2).when(modelMock).addAttribute("publisher", publisher);
        Model modelMock3 = mock(Model.class);
        doReturn(modelMock3).when(modelMock).addAttribute(eq("publisherDto"), (PublisherDto) any());
        target = new PublisherController(publisherServiceMock);
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        doReturn(publisher).when(publisherServiceMock).findPublisherById(0);
        
        //Act Statement(s)
        String result = target.ShowPageUpdatePublisher(modelMock, 0);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("pages/publisher/updatePublisher.html"));
            verify(modelMock).addAttribute("publisher", publisher);
            verify(modelMock).addAttribute(eq("publisherDto"), (PublisherDto) any());
            verify(publisherServiceMock).findPublisherById(0);
        });
    }

    //Sapient generated method id: ${updatePublisherWhenResultHasErrors}, hash: 9CFE2ED01CDB32124E8AC50B27270A17
    @Test()
    void updatePublisherWhenResultHasErrors() {
        /* Branches:
         * (result.hasErrors()) : true
         */
         //Arrange Statement(s)
        Model modelMock = mock(Model.class);
        Model modelMock2 = mock(Model.class);
        Publisher publisherMock = mock(Publisher.class);
        doReturn(modelMock2).when(modelMock).addAttribute("publisher", publisherMock);
        BindingResult resultMock = mock(BindingResult.class);
        doReturn(true).when(resultMock).hasErrors();
        target = new PublisherController(publisherServiceMock);
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        doReturn(publisherMock).when(publisherServiceMock).findPublisherById(0);
        PublisherDto publisherDtoMock = mock(PublisherDto.class);
        
        //Act Statement(s)
        String result = target.updatePublisher(modelMock, 0, publisherDtoMock, resultMock);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("pages/publisher/updatePublisher.html"));
            verify(modelMock).addAttribute("publisher", publisherMock);
            verify(resultMock).hasErrors();
            verify(publisherServiceMock).findPublisherById(0);
        });
    }

    //Sapient generated method id: ${updatePublisherWhenCaughtExceptionThrowsRuntimeException}, hash: 9181568EEB657F13650C76F90FBE54E3
    @Disabled()
    @Test()
    void updatePublisherWhenCaughtExceptionThrowsRuntimeException() throws IOException {
        /* Branches:
         * (result.hasErrors()) : false
         * (!publisherDto.getImage().isEmpty()) : true
         * (Files.exists(oldImagePath)) : true
         * (catch-exception (Exception)) : true
         *
         * TODO: Help needed! This method is not unit testable!
         *  Potential harmful system call (Files.delete) detected; Learn more: https://github.com/Sapient-AI/docs#disabled-generated-tests
         *  Suggestions:
         *  This method should be avoided from unit testing. This can be covered during integration testing.
         *  The test code, including the assertion statements, has been successfully generated.
         */
         //Arrange Statement(s)
        Model modelMock = mock(Model.class);
        Model modelMock2 = mock(Model.class);
        MultipartFile multipartFileMock = mock(MultipartFile.class);
        BindingResult resultMock = mock(BindingResult.class);
        try (MockedStatic<Files> files = mockStatic(Files.class)) {
            Publisher publisher = new Publisher();
            publisher.setImage("A");
            doReturn(modelMock2).when(modelMock).addAttribute("publisher", publisher);
            doReturn(false).when(multipartFileMock).isEmpty();
            doReturn(false).when(resultMock).hasErrors();
            files.when(() -> Files.delete((Path) any())).thenAnswer((Answer<Void>) invocation -> null);
            target = new PublisherController(publisherServiceMock);
            autoCloseableMocks = MockitoAnnotations.openMocks(this);
            doReturn(publisher).when(publisherServiceMock).findPublisherById(0);
            PublisherDto publisherDto = new PublisherDto();
            publisherDto.setImage(multipartFileMock);
            //Act Statement(s)
            final RuntimeException result = assertThrows(RuntimeException.class, () -> {
                target.updatePublisher(modelMock, 0, publisherDto, resultMock);
            });
            Exception exception = new Exception();
            RuntimeException runtimeException = new RuntimeException(exception);
            //Assert statement(s)
            assertAll("result", () -> {
                assertThat(result, is(notNullValue()));
                assertThat(result.getCause(), is(instanceOf(runtimeException.getClass())));
                verify(modelMock).addAttribute("publisher", publisher);
                verify(multipartFileMock).isEmpty();
                verify(resultMock).hasErrors();
                files.verify(() -> Files.delete((Path) any()));
                verify(publisherServiceMock).findPublisherById(0);
            });
        }
    }

    //Sapient generated method id: ${updatePublisherWhenDefaultBranchThrowsThrowable}, hash: 74BE8F0BDF20360B44DBDBF38E0AC87D
    @Disabled()
    @Test()
    void updatePublisherWhenDefaultBranchThrowsThrowable() throws IOException {
        /* Branches:
         * (result.hasErrors()) : false
         * (!publisherDto.getImage().isEmpty()) : true
         * (Files.exists(oldImagePath)) : true
         * (branch expression (line 97)) : true
         *
         * TODO: Help needed! This method is not unit testable!
         *  Potential harmful system call (Files.delete) detected; Learn more: https://github.com/Sapient-AI/docs#disabled-generated-tests
         *  Suggestions:
         *  This method should be avoided from unit testing. This can be covered during integration testing.
         *  The test code, including the assertion statements, has been successfully generated.
         */
         //Arrange Statement(s)
        Model modelMock = mock(Model.class);
        Model modelMock2 = mock(Model.class);
        MultipartFile multipartFileMock = mock(MultipartFile.class);
        BindingResult resultMock = mock(BindingResult.class);
        try (MockedStatic<Files> files = mockStatic(Files.class)) {
            Publisher publisher = new Publisher();
            publisher.setImage("A");
            doReturn(modelMock2).when(modelMock).addAttribute("publisher", publisher);
            doReturn(false).when(multipartFileMock).isEmpty();
            doReturn("B").when(multipartFileMock).getOriginalFilename();
            doReturn(null).when(multipartFileMock).getInputStream();
            doReturn(false).when(resultMock).hasErrors();
            files.when(() -> Files.delete((Path) any())).thenAnswer((Answer<Void>) invocation -> null);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            InputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            CopyOption[] copyOptionArray = new CopyOption[] { StandardCopyOption.REPLACE_EXISTING };
            files.when(() -> Files.copy(eq(inputStream), (Path) any(), eq(copyOptionArray))).thenReturn(0L);
            target = new PublisherController(publisherServiceMock);
            autoCloseableMocks = MockitoAnnotations.openMocks(this);
            doReturn(publisher).when(publisherServiceMock).findPublisherById(0);
            PublisherDto publisherDto = new PublisherDto();
            publisherDto.setImage(multipartFileMock);
            //Act Statement(s)
            final Throwable result = assertThrows(Throwable.class, () -> {
                target.updatePublisher(modelMock, 0, publisherDto, resultMock);
            });
            //Assert statement(s)
            assertAll("result", () -> {
                assertThat(result, is(notNullValue()));
                verify(modelMock).addAttribute("publisher", publisher);
                verify(multipartFileMock).isEmpty();
                verify(multipartFileMock).getOriginalFilename();
                verify(multipartFileMock).getInputStream();
                verify(resultMock).hasErrors();
                files.verify(() -> Files.delete((Path) any()));
                files.verify(() -> Files.copy(eq(inputStream), (Path) any(), eq(copyOptionArray)));
                verify(publisherServiceMock).findPublisherById(0);
            });
        }
    }

    //Sapient generated method id: ${updatePublisherWhenFilesExistsOldImagePathAndDefaultBranchAndPublisherDtoGetActiveEqualsEditInActiveAndPublisherDtoGetA}, hash: D74252A81E17588D936E0B89E8ED181C
    @Disabled()
    @Test()
    void updatePublisherWhenFilesExistsOldImagePathAndDefaultBranchAndPublisherDtoGetActiveEqualsEditInActiveAndPublisherDtoGetA() throws IOException {
        /* Branches:
         * (result.hasErrors()) : false
         * (!publisherDto.getImage().isEmpty()) : true
         * (Files.exists(oldImagePath)) : true
         * (branch expression (line 100)) : false
         * (publisherDto.getActive().equals("editInActive")) : true
         * (publisherDto.getActive().equals("active")) : true
         *
         * TODO: Help needed! This method is not unit testable!
         *  Potential harmful system call (Files.delete) detected; Learn more: https://github.com/Sapient-AI/docs#disabled-generated-tests
         *  Suggestions:
         *  This method should be avoided from unit testing. This can be covered during integration testing.
         *  The test code, including the assertion statements, has been successfully generated.
         */
         //Arrange Statement(s)
        Model modelMock = mock(Model.class);
        Model modelMock2 = mock(Model.class);
        Publisher publisherMock = mock(Publisher.class);
        doReturn(modelMock2).when(modelMock).addAttribute("publisher", publisherMock);
        doReturn("return_of_getImage1").when(publisherMock).getImage();
        doNothing().when(publisherMock).setImage("string5");
        doNothing().when(publisherMock).setName("name1");
        doNothing().when(publisherMock).setIsDeleted(true);
        MultipartFile multipartFileMock = mock(MultipartFile.class);
        doReturn(false).when(multipartFileMock).isEmpty();
        doReturn("return_of_getOriginalFilename1").when(multipartFileMock).getOriginalFilename();
        doReturn(null).when(multipartFileMock).getInputStream();
        BindingResult resultMock = mock(BindingResult.class);
        doReturn(false).when(resultMock).hasErrors();
        target = new PublisherController(publisherServiceMock);
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        doReturn(publisherMock).when(publisherServiceMock).findPublisherById(0);
        doNothing().when(publisherServiceMock).activePublisher(publisherMock);
        doNothing().when(publisherServiceMock).savePublisher(publisherMock);
        PublisherDto publisherDto = new PublisherDto();
        publisherDto.setImage(multipartFileMock);
        publisherDto.setName("name1");
        publisherDto.setActive("active1");
        
        //Act Statement(s)
        String result = target.updatePublisher(modelMock, 0, publisherDto, resultMock);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("redirect:/publisher"));
            verify(modelMock).addAttribute("publisher", publisherMock);
            verify(publisherMock).getImage();
            verify(publisherMock).setImage("string5");
            verify(publisherMock).setName("name1");
            verify(publisherMock).setIsDeleted(true);
            verify(multipartFileMock).isEmpty();
            verify(multipartFileMock).getOriginalFilename();
            verify(multipartFileMock).getInputStream();
            verify(resultMock).hasErrors();
            verify(publisherServiceMock).findPublisherById(0);
            verify(publisherServiceMock).activePublisher(publisherMock);
            verify(publisherServiceMock).savePublisher(publisherMock);
        });
    }

    //Sapient generated method id: ${updatePublisherWhenDefaultBranchAndDefaultBranchAndPublisherDtoGetActiveEqualsEditInActiveAndPublisherDtoGetActiveEqual}, hash: 15BF24F7B1AE4FB5640F8436DEA71913
    @Disabled()
    @Test()
    void updatePublisherWhenDefaultBranchAndDefaultBranchAndPublisherDtoGetActiveEqualsEditInActiveAndPublisherDtoGetActiveEqual() throws IOException {
        /* Branches:
         * (result.hasErrors()) : false
         * (!publisherDto.getImage().isEmpty()) : true
         * (Files.exists(oldImagePath)) : true
         * (branch expression (line 100)) : false
         * (branch expression (line 97)) : true
         * (publisherDto.getActive().equals("editInActive")) : true
         * (publisherDto.getActive().equals("active")) : true
         *
         * TODO: Help needed! This method is not unit testable!
         *  Potential harmful system call (Files.delete) detected; Learn more: https://github.com/Sapient-AI/docs#disabled-generated-tests
         *  Suggestions:
         *  This method should be avoided from unit testing. This can be covered during integration testing.
         *  The test code, including the assertion statements, has been successfully generated.
         */
         //Arrange Statement(s)
        Model modelMock = mock(Model.class);
        Model modelMock2 = mock(Model.class);
        Publisher publisherMock = mock(Publisher.class);
        MultipartFile multipartFileMock = mock(MultipartFile.class);
        BindingResult resultMock = mock(BindingResult.class);
        Path pathMock = mock(Path.class);
        Path pathMock2 = mock(Path.class);
        try (MockedStatic<Files> files = mockStatic(Files.class)) {
            doReturn(modelMock2).when(modelMock).addAttribute("publisher", publisherMock);
            doReturn("return_of_getImage1").when(publisherMock).getImage();
            doNothing().when(publisherMock).setImage("string5");
            doNothing().when(publisherMock).setName("name1");
            doNothing().when(publisherMock).setIsDeleted(true);
            doReturn(false).when(multipartFileMock).isEmpty();
            doReturn("return_of_getOriginalFilename1").when(multipartFileMock).getOriginalFilename();
            doReturn(null).when(multipartFileMock).getInputStream();
            doReturn(false).when(resultMock).hasErrors();
            files.when(() -> Files.delete(pathMock)).thenAnswer((Answer<Void>) invocation -> null);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            InputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            CopyOption[] copyOptionArray = new CopyOption[] { StandardCopyOption.REPLACE_EXISTING };
            files.when(() -> Files.copy(inputStream, pathMock2, copyOptionArray)).thenReturn(0L);
            target = new PublisherController(publisherServiceMock);
            autoCloseableMocks = MockitoAnnotations.openMocks(this);
            doReturn(publisherMock).when(publisherServiceMock).findPublisherById(0);
            doNothing().when(publisherServiceMock).activePublisher(publisherMock);
            doNothing().when(publisherServiceMock).savePublisher(publisherMock);
            PublisherDto publisherDto = new PublisherDto();
            publisherDto.setImage(multipartFileMock);
            publisherDto.setName("name1");
            publisherDto.setActive("active1");
            //Act Statement(s)
            String result = target.updatePublisher(modelMock, 0, publisherDto, resultMock);
            //Assert statement(s)
            assertAll("result", () -> {
                assertThat(result, equalTo("redirect:/publisher"));
                verify(modelMock).addAttribute("publisher", publisherMock);
                verify(publisherMock).getImage();
                verify(publisherMock).setImage("string5");
                verify(publisherMock).setName("name1");
                verify(publisherMock).setIsDeleted(true);
                verify(multipartFileMock).isEmpty();
                verify(multipartFileMock).getOriginalFilename();
                verify(multipartFileMock).getInputStream();
                verify(resultMock).hasErrors();
                files.verify(() -> Files.delete(pathMock), atLeast(1));
                files.verify(() -> Files.copy(inputStream, pathMock2, copyOptionArray), atLeast(1));
                verify(publisherServiceMock).findPublisherById(0);
                verify(publisherServiceMock).activePublisher(publisherMock);
                verify(publisherServiceMock).savePublisher(publisherMock);
            });
        }
    }

    //Sapient generated method id: ${showPageAddPublisherTest}, hash: 28D05638236E0F7301FE62E6DC5EFBF6
    @Test()
    void showPageAddPublisherTest() {
        //Arrange Statement(s)
        Model modelMock = mock(Model.class);
        Model modelMock2 = mock(Model.class);
        doReturn(modelMock2).when(modelMock).addAttribute(eq("publisherDto"), (PublisherDto) any());
        target = new PublisherController(publisherServiceMock);
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        
        //Act Statement(s)
        String result = target.showPageAddPublisher(modelMock);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("pages/publisher/addPublisher.html"));
            verify(modelMock).addAttribute(eq("publisherDto"), (PublisherDto) any());
        });
    }

    //Sapient generated method id: ${addPublisherWhenResultHasErrors}, hash: 5B80E978B80AC8C55E7A7C5BB1351722
    @Test()
    void addPublisherWhenResultHasErrors() {
        /* Branches:
         * (publisherDto.getImage().isEmpty()) : true
         * (result.hasErrors()) : true
         */
         //Arrange Statement(s)
        MultipartFile multipartFileMock = mock(MultipartFile.class);
        doReturn(true).when(multipartFileMock).isEmpty();
        BindingResult resultMock = mock(BindingResult.class);
        doNothing().when(resultMock).addError((FieldError) any());
        doReturn(true).when(resultMock).hasErrors();
        target = new PublisherController(publisherServiceMock);
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        PublisherDto publisherDto = new PublisherDto();
        publisherDto.setImage(multipartFileMock);
        
        //Act Statement(s)
        String result = target.addPublisher(publisherDto, resultMock);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("pages/publisher/addPublisher.html"));
            verify(multipartFileMock).isEmpty();
            verify(resultMock).addError((FieldError) any());
            verify(resultMock).hasErrors();
        });
    }

    //Sapient generated method id: ${addPublisherWhenDefaultBranchThrowsThrowable}, hash: 21E6368CAB6A3B8ACD264BA20536EFA7
    @Disabled()
    @Test()
    void addPublisherWhenDefaultBranchThrowsThrowable() throws IOException {
        /* Branches:
         * (publisherDto.getImage().isEmpty()) : true
         * (result.hasErrors()) : false
         * (!Files.exists(uploadPath)) : true
         * (branch expression (line 151)) : true
         *
         * TODO: Help needed! Please adjust the input/test parameter values manually to satisfy the requirements of the given test scenario.
         *  The test code, including the assertion statements, has been successfully generated.
         */
         //Arrange Statement(s)
        MultipartFile multipartFileMock = mock(MultipartFile.class);
        BindingResult resultMock = mock(BindingResult.class);
        Path pathMock = mock(Path.class);
        try (MockedStatic<Files> files = mockStatic(Files.class)) {
            doReturn(true).when(multipartFileMock).isEmpty();
            doReturn("A").when(multipartFileMock).getOriginalFilename();
            doReturn(null).when(multipartFileMock).getInputStream();
            doNothing().when(resultMock).addError((FieldError) any());
            doReturn(false).when(resultMock).hasErrors();
            FileAttribute[] fileAttributeArray = new FileAttribute[] {};
            files.when(() -> Files.createDirectories((Path) any(), eq(fileAttributeArray))).thenReturn(pathMock);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            InputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            CopyOption[] copyOptionArray = new CopyOption[] { StandardCopyOption.REPLACE_EXISTING };
            files.when(() -> Files.copy(eq(inputStream), (Path) any(), eq(copyOptionArray))).thenReturn(0L);
            target = new PublisherController(publisherServiceMock);
            autoCloseableMocks = MockitoAnnotations.openMocks(this);
            PublisherDto publisherDto = new PublisherDto();
            publisherDto.setImage(multipartFileMock);
            //Act Statement(s)
            final Throwable result = assertThrows(Throwable.class, () -> {
                target.addPublisher(publisherDto, resultMock);
            });
            //Assert statement(s)
            assertAll("result", () -> {
                assertThat(result, is(notNullValue()));
                verify(multipartFileMock).isEmpty();
                verify(multipartFileMock).getOriginalFilename();
                verify(multipartFileMock).getInputStream();
                verify(resultMock).addError((FieldError) any());
                verify(resultMock).hasErrors();
                files.verify(() -> Files.createDirectories((Path) any(), eq(fileAttributeArray)));
                files.verify(() -> Files.copy(eq(inputStream), (Path) any(), eq(copyOptionArray)));
            });
        }
    }

    //Sapient generated method id: ${addPublisherWhenDefaultBranch}, hash: 03ABFDF42F532A51F0B0111B73B00C22
    @Disabled()
    @Test()
    void addPublisherWhenDefaultBranch() throws IOException {
        /* Branches:
         * (publisherDto.getImage().isEmpty()) : true
         * (result.hasErrors()) : false
         * (!Files.exists(uploadPath)) : true
         * (branch expression (line 154)) : false
         *
         * TODO: Help needed! Please adjust the input/test parameter values manually to satisfy the requirements of the given test scenario.
         *  The test code, including the assertion statements, has been successfully generated.
         */
         //Arrange Statement(s)
        MultipartFile multipartFileMock = mock(MultipartFile.class);
        doReturn(true).when(multipartFileMock).isEmpty();
        doReturn("A").when(multipartFileMock).getOriginalFilename();
        doReturn(null).when(multipartFileMock).getInputStream();
        BindingResult resultMock = mock(BindingResult.class);
        doNothing().when(resultMock).addError((FieldError) any());
        doReturn(false).when(resultMock).hasErrors();
        target = new PublisherController(publisherServiceMock);
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        UserSessionDto userSessionDtoMock = mock(UserSessionDto.class);
        doReturn(userSessionDtoMock).when(sessionMock).getAttribute("user_sess");
        User userMock = mock(User.class);
        doReturn(userMock).when(userServiceMock).findUserDBByUserSession(userSessionDtoMock);
        doNothing().when(publisherServiceMock).savePublisher((Publisher) any());
        PublisherDto publisherDto = new PublisherDto();
        publisherDto.setImage(multipartFileMock);
        publisherDto.setName("name1");
        
        //Act Statement(s)
        String result = target.addPublisher(publisherDto, resultMock);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("redirect:/publisher"));
            verify(multipartFileMock).isEmpty();
            verify(multipartFileMock).getOriginalFilename();
            verify(multipartFileMock).getInputStream();
            verify(resultMock).addError((FieldError) any());
            verify(resultMock).hasErrors();
            verify(sessionMock).getAttribute("user_sess");
            verify(userServiceMock).findUserDBByUserSession(userSessionDtoMock);
            verify(publisherServiceMock).savePublisher((Publisher) any());
        });
    }

    //Sapient generated method id: ${addPublisherWhenDefaultBranchAndDefaultBranch}, hash: 2F728A2AE5AB20B299E8EF3538A15ECE
    @Disabled()
    @Test()
    void addPublisherWhenDefaultBranchAndDefaultBranch() throws IOException {
        /* Branches:
         * (publisherDto.getImage().isEmpty()) : true
         * (result.hasErrors()) : false
         * (!Files.exists(uploadPath)) : true
         * (branch expression (line 154)) : false
         * (branch expression (line 151)) : true
         *
         * TODO: Help needed! Please adjust the input/test parameter values manually to satisfy the requirements of the given test scenario.
         *  The test code, including the assertion statements, has been successfully generated.
         */
         //Arrange Statement(s)
        MultipartFile multipartFileMock = mock(MultipartFile.class);
        BindingResult resultMock = mock(BindingResult.class);
        Path pathMock = mock(Path.class);
        UserSessionDto userSessionDtoMock = mock(UserSessionDto.class);
        User userMock = mock(User.class);
        try (MockedStatic<Files> files = mockStatic(Files.class)) {
            doReturn(true).when(multipartFileMock).isEmpty();
            doReturn("A").when(multipartFileMock).getOriginalFilename();
            doReturn(null).when(multipartFileMock).getInputStream();
            doNothing().when(resultMock).addError((FieldError) any());
            doReturn(false).when(resultMock).hasErrors();
            FileAttribute[] fileAttributeArray = new FileAttribute[] {};
            files.when(() -> Files.createDirectories((Path) any(), eq(fileAttributeArray))).thenReturn(pathMock);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            InputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            CopyOption[] copyOptionArray = new CopyOption[] { StandardCopyOption.REPLACE_EXISTING };
            files.when(() -> Files.copy(eq(inputStream), (Path) any(), eq(copyOptionArray))).thenReturn(0L);
            target = new PublisherController(publisherServiceMock);
            autoCloseableMocks = MockitoAnnotations.openMocks(this);
            doReturn(userSessionDtoMock).when(sessionMock).getAttribute("user_sess");
            doReturn(userMock).when(userServiceMock).findUserDBByUserSession(userSessionDtoMock);
            doNothing().when(publisherServiceMock).savePublisher((Publisher) any());
            PublisherDto publisherDto = new PublisherDto();
            publisherDto.setImage(multipartFileMock);
            publisherDto.setName("name1");
            //Act Statement(s)
            String result = target.addPublisher(publisherDto, resultMock);
            //Assert statement(s)
            assertAll("result", () -> {
                assertThat(result, equalTo("redirect:/publisher"));
                verify(multipartFileMock).isEmpty();
                verify(multipartFileMock).getOriginalFilename();
                verify(multipartFileMock).getInputStream();
                verify(resultMock).addError((FieldError) any());
                verify(resultMock).hasErrors();
                files.verify(() -> Files.createDirectories((Path) any(), eq(fileAttributeArray)));
                files.verify(() -> Files.copy(eq(inputStream), (Path) any(), eq(copyOptionArray)));
                verify(sessionMock).getAttribute("user_sess");
                verify(userServiceMock).findUserDBByUserSession(userSessionDtoMock);
                verify(publisherServiceMock).savePublisher((Publisher) any());
            });
        }
    }

    //Sapient generated method id: ${addPublisherWhenCaughtExceptionThrowsRuntimeException}, hash: 1E73B63F206BB5A7117CB94FAD36E694
    @Disabled()
    @Test()
    void addPublisherWhenCaughtExceptionThrowsRuntimeException() throws IOException {
        /* Branches:
         * (publisherDto.getImage().isEmpty()) : true
         * (result.hasErrors()) : false
         * (!Files.exists(uploadPath)) : true
         * (branch expression (line 154)) : false
         * (catch-exception (Exception)) : true
         *
         * TODO: Help needed! Please adjust the input/test parameter values manually to satisfy the requirements of the given test scenario.
         *  The test code, including the assertion statements, has been successfully generated.
         */
         //Arrange Statement(s)
        MultipartFile multipartFileMock = mock(MultipartFile.class);
        BindingResult resultMock = mock(BindingResult.class);
        Path pathMock = mock(Path.class);
        try (MockedStatic<Files> files = mockStatic(Files.class)) {
            doReturn(true).when(multipartFileMock).isEmpty();
            doReturn("A").when(multipartFileMock).getOriginalFilename();
            doReturn(null).when(multipartFileMock).getInputStream();
            doNothing().when(resultMock).addError((FieldError) any());
            doReturn(false).when(resultMock).hasErrors();
            FileAttribute[] fileAttributeArray = new FileAttribute[] {};
            files.when(() -> Files.createDirectories((Path) any(), eq(fileAttributeArray))).thenReturn(pathMock);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            InputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            CopyOption[] copyOptionArray = new CopyOption[] { StandardCopyOption.REPLACE_EXISTING };
            files.when(() -> Files.copy(eq(inputStream), (Path) any(), eq(copyOptionArray))).thenReturn(0L);
            target = new PublisherController(publisherServiceMock);
            autoCloseableMocks = MockitoAnnotations.openMocks(this);
            PublisherDto publisherDto = new PublisherDto();
            publisherDto.setImage(multipartFileMock);
            //Act Statement(s)
            final RuntimeException result = assertThrows(RuntimeException.class, () -> {
                target.addPublisher(publisherDto, resultMock);
            });
            Exception exception = new Exception();
            //Assert statement(s)
            assertAll("result", () -> {
                assertThat(result, is(notNullValue()));
                assertThat(result.getCause(), is(instanceOf(exception.getClass())));
                verify(multipartFileMock).isEmpty();
                verify(multipartFileMock).getOriginalFilename();
                verify(multipartFileMock).getInputStream();
                verify(resultMock).addError((FieldError) any());
                verify(resultMock).hasErrors();
                files.verify(() -> Files.createDirectories((Path) any(), eq(fileAttributeArray)));
                files.verify(() -> Files.copy(eq(inputStream), (Path) any(), eq(copyOptionArray)));
            });
        }
    }

    //Sapient generated method id: ${inactivePublisherTest}, hash: 35F7E4950399E9EE1B64926E41B449DB
    @Test()
    void inactivePublisherTest() {
        //Arrange Statement(s)
        target = new PublisherController(publisherServiceMock);
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        UserSessionDto userSessionDtoMock = mock(UserSessionDto.class);
        doReturn(userSessionDtoMock).when(sessionMock).getAttribute("user_sess");
        User userMock = mock(User.class);
        doReturn(userMock).when(userServiceMock).findUserDBByUserSession(userSessionDtoMock);
        Publisher publisherMock = mock(Publisher.class);
        doReturn(publisherMock).when(publisherServiceMock).findPublisherById(0);
        doNothing().when(publisherServiceMock).inactivePublisher(publisherMock, userMock);
        Model modelMock = mock(Model.class);
        
        //Act Statement(s)
        String result = target.inactivePublisher(modelMock, 0);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("redirect:/publisher"));
            verify(sessionMock).getAttribute("user_sess");
            verify(userServiceMock).findUserDBByUserSession(userSessionDtoMock);
            verify(publisherServiceMock).findPublisherById(0);
            verify(publisherServiceMock).inactivePublisher(publisherMock, userMock);
        });
    }

    //Sapient generated method id: ${activePublisherTest}, hash: AFE38FDEC235ADFADDC6261966322D47
    @Test()
    void activePublisherTest() {
        //Arrange Statement(s)
        target = new PublisherController(publisherServiceMock);
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        UserSessionDto userSessionDtoMock = mock(UserSessionDto.class);
        doReturn(userSessionDtoMock).when(sessionMock).getAttribute("user_sess");
        User userMock = mock(User.class);
        doReturn(userMock).when(userServiceMock).findUserDBByUserSession(userSessionDtoMock);
        Publisher publisherMock = mock(Publisher.class);
        doReturn(publisherMock).when(publisherServiceMock).findPublisherById(0);
        doNothing().when(publisherServiceMock).activePublisher(publisherMock);
        Model modelMock = mock(Model.class);
        
        //Act Statement(s)
        String result = target.activePublisher(modelMock, 0);
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("redirect:/publisher"));
            verify(sessionMock).getAttribute("user_sess");
            verify(userServiceMock).findUserDBByUserSession(userSessionDtoMock);
            verify(publisherServiceMock).findPublisherById(0);
            verify(publisherServiceMock).activePublisher(publisherMock);
        });
    }

    //Sapient generated method id: ${ShowPagePublisherFilterWhenSwitchStatusCaseActive}, hash: 55523D36512CD94196A02C9C30424907
    @Test()
    void ShowPagePublisherFilterWhenSwitchStatusCaseActive() {
        /* Branches:
         * (switch(status) = "active") : true
         */
         //Arrange Statement(s)
        Model modelMock = mock(Model.class);
        Model modelMock2 = mock(Model.class);
        doReturn(modelMock2).when(modelMock).addAttribute("status", "active");
        Model modelMock3 = mock(Model.class);
        List<Publisher> publisherList = new ArrayList<>();
        doReturn(modelMock3).when(modelMock).addAttribute("listPublisher", publisherList);
        target = new PublisherController(publisherServiceMock);
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        doReturn(publisherList).when(publisherServiceMock).getAllPublisherActive();
        
        //Act Statement(s)
        String result = target.ShowPagePublisherFilter(modelMock, "active");
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("pages/publisher/publisher.html"));
            verify(modelMock).addAttribute("status", "active");
            verify(modelMock).addAttribute("listPublisher", publisherList);
            verify(publisherServiceMock).getAllPublisherActive();
        });
    }

    //Sapient generated method id: ${ShowPagePublisherFilterWhenSwitchStatusCaseDefault}, hash: 827F03AFABD91EA769A579F10E5BA467
    @Test()
    void ShowPagePublisherFilterWhenSwitchStatusCaseDefault() {
        /* Branches:
         * (switch(status) = "default") : true
         */
         //Arrange Statement(s)
        Model modelMock = mock(Model.class);
        Model modelMock2 = mock(Model.class);
        doReturn(modelMock2).when(modelMock).addAttribute("status", "default");
        Model modelMock3 = mock(Model.class);
        List<Publisher> publisherList = new ArrayList<>();
        doReturn(modelMock3).when(modelMock).addAttribute("listPublisher", publisherList);
        target = new PublisherController(publisherServiceMock);
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        doReturn(publisherList).when(publisherServiceMock).getAllPublisher();
        
        //Act Statement(s)
        String result = target.ShowPagePublisherFilter(modelMock, "default");
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("pages/publisher/publisher.html"));
            verify(modelMock).addAttribute("status", "default");
            verify(modelMock).addAttribute("listPublisher", publisherList);
            verify(publisherServiceMock).getAllPublisher();
        });
    }

    //Sapient generated method id: ${ShowPagePublisherFilterWhenSwitchStatusCaseInactive}, hash: 1DA721EA1114B19EEACF38495FB117CA
    @Test()
    void ShowPagePublisherFilterWhenSwitchStatusCaseInactive() {
        /* Branches:
         * (switch(status) = "inactive") : true
         */
         //Arrange Statement(s)
        Model modelMock = mock(Model.class);
        Model modelMock2 = mock(Model.class);
        doReturn(modelMock2).when(modelMock).addAttribute("status", "inactive");
        Model modelMock3 = mock(Model.class);
        List<Publisher> publisherList = new ArrayList<>();
        doReturn(modelMock3).when(modelMock).addAttribute("listPublisher", publisherList);
        target = new PublisherController(publisherServiceMock);
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        doReturn(publisherList).when(publisherServiceMock).getAllPublisherDeactive();
        
        //Act Statement(s)
        String result = target.ShowPagePublisherFilter(modelMock, "inactive");
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("pages/publisher/publisher.html"));
            verify(modelMock).addAttribute("status", "inactive");
            verify(modelMock).addAttribute("listPublisher", publisherList);
            verify(publisherServiceMock).getAllPublisherDeactive();
        });
    }

    //Sapient generated method id: ${ShowPagePublisherFilterWhenSwitchStatusCaseDefault2}, hash: F7BBD5908882D8F01F8134F529F7F280
    @Test()
    void ShowPagePublisherFilterWhenSwitchStatusCaseDefault2() {
        /* Branches:
         * (switch(status) = default) : true
         */
         //Arrange Statement(s)
        Model modelMock = mock(Model.class);
        Model modelMock2 = mock(Model.class);
        doReturn(modelMock2).when(modelMock).addAttribute("status", "A");
        Model modelMock3 = mock(Model.class);
        List<Publisher> publisherList = new ArrayList<>();
        doReturn(modelMock3).when(modelMock).addAttribute("listPublisher", publisherList);
        target = new PublisherController(publisherServiceMock);
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        doReturn(publisherList).when(publisherServiceMock).getAllPublisher();
        
        //Act Statement(s)
        String result = target.ShowPagePublisherFilter(modelMock, "A");
        
        //Assert statement(s)
        assertAll("result", () -> {
            assertThat(result, equalTo("pages/publisher/publisher.html"));
            verify(modelMock).addAttribute("status", "A");
            verify(modelMock).addAttribute("listPublisher", publisherList);
            verify(publisherServiceMock).getAllPublisher();
        });
    }
}
