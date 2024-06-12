package com.nishikatakagi.ProductDigital.service_impl;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nishikatakagi.ProductDigital.dto.UserRegisterRequestDto;
import com.nishikatakagi.ProductDigital.dto.UserSessionDto;
import com.nishikatakagi.ProductDigital.model.User;
import com.nishikatakagi.ProductDigital.repository.UserRepository;
import com.nishikatakagi.ProductDigital.service.SecurityService;
import com.nishikatakagi.ProductDigital.service.UserService;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    SecurityService security;
    @Autowired
    HttpSession session;

    public UserServiceImpl() {
    }

    @Override
    public boolean checkEmailExist(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean checkUsernameExist(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Object changePassword(int id, String newPassword) {
        // Kiểm tra xem người dùng có tồn tại không
        User user = userRepository.findById(id);
        if (user == null) {
            return null; // Người dùng không tồn tại
        } else {

            // Cập nhật mật khẩu mới cho người dùng
            user.setPassword(security.encode(newPassword));
            userRepository.save(user);
        }

        return user;
    }

    @Override
    public User saveUser(User user) {
        return userRepository
                .save(user);
    }

    @Override
    public void updateUser(UserSessionDto userDto, User user) {
        // Update user fields
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhone(userDto.getPhone());
        user.setUpdatedBy(user);
        // lấy thời gian hiện tại
        Date currentTime = Date.valueOf(LocalDateTime.now().toLocalDate());
        user.setLastUpdated(currentTime);
        // Save updated user to the database
        userRepository.save(user);
        // Update session with new DTO information

    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean checkPassword(User user, String rawPassword) {
        return false;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User findUserDBByUserSession(UserSessionDto userSessionDto) {
        String userName = userSessionDto.getUsername();
        User user = userRepository.findUserByUsername(userName);
        if (user != null) {
            return user;
        } else
            return null;

    }

    @Override
    public User findUserDBByUserEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUserByAdmin(String username, UserSessionDto userSessionDto) {
        User user = userRepository.findUserByUsername(username);
        User deletedBy = userRepository.findUserByUsername(userSessionDto.getUsername());
        user.setDeleted(true);
        user.setDeletedDate(Date.valueOf(LocalDateTime.now().toLocalDate()));
        user.setDeletedBy(deletedBy);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public void changePassword(User user, String password, User userSessionData) {
        user.setPassword(security.encode(password));
        user.setUpdatedBy(userSessionData);
        Date currentTime = Date.valueOf(LocalDateTime.now().toLocalDate());
        user.setLastUpdated(currentTime);
        userRepository.save(user);
    }

    @Override
    public void saveUser(UserRegisterRequestDto user, User createdBy) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(security.encode(user.getPassword()));
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstname());
        newUser.setLastName(user.getLastname());
        newUser.setPhone(user.getPhone());
        newUser.setRoleId(2);
        newUser.setDeleted(false);
        newUser.setVerified(true);
        newUser.setCreatedBy(createdBy);
        newUser.setCreatedDate(Date.valueOf(LocalDateTime.now().toLocalDate()));
        userRepository.save(newUser);
    }

    @Override
    public List<User> filterAccount(List<Integer> roleId, List<Integer> isDeleted, String username) {
        List<User> listAccount = userRepository.findAll();
        List<User> listAccountResult = new ArrayList<>();
        if (username == null) {
            username = "";
        }
        for (User user : listAccount) {
            if (user.getUsername().contains(username)) {
                if (roleId != null && isDeleted != null) {
                    if (roleId.contains(user.getRoleId()) && isDeleted.contains(user.isDeleted() ? 1 : 0)) {
                        listAccountResult.add(user);
                    }
                } else if (roleId != null) {
                    if (roleId.contains(user.getRoleId())) {
                        listAccountResult.add(user);
                    }
                } else if (isDeleted != null) {
                    if (isDeleted.contains(user.isDeleted() ? 1 : 0)) {
                        listAccountResult.add(user);
                    }
                }else{
                    listAccountResult.add(user);
                
                }
            }
        }
        return listAccountResult;
    }

    @Override
    public void activateAccount(User user, User userSessionData) {
        user.setDeleted(false);
        user.setUpdatedBy(userSessionData);
        Date currentTime = Date.valueOf(LocalDateTime.now().toLocalDate());
        user.setLastUpdated(currentTime);
        userRepository.save(user);
    }

}
