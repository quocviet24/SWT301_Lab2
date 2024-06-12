package com.nishikatakagi.ProductDigital.service;

import java.util.List;

import com.nishikatakagi.ProductDigital.dto.UserRegisterRequestDto;
import com.nishikatakagi.ProductDigital.dto.UserSessionDto;
import com.nishikatakagi.ProductDigital.model.User;

public interface UserService {
    boolean checkEmailExist(String email);
    //ngoc
    List<User> findAllUser();
    boolean checkUsernameExist(String username);
    void deleteUserByAdmin(String username, UserSessionDto userSessionDto);
    void changePassword(User user,String password,User userSessionData);
    void saveUser(UserRegisterRequestDto user, User createdBy);
    List<User> filterAccount(List<Integer> roleId, List<Integer> isDeleted, String username);
    void activateAccount(User user,User userSessionData);

    Object changePassword(int id, String newPassword);

    User saveUser(User user);

    User findById(int id);

    //huy
    boolean checkPassword(User user, String rawPassword);
    User findByUsername(String username);
    void deleteUser(User user);

    //viet
    void updateUser(UserSessionDto userDto, User user);
    User findUserDBByUserSession(UserSessionDto userSessionDto);
    User findUserDBByUserEmail(String email);

}
