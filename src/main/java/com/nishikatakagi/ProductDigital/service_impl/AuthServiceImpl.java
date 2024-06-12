package com.nishikatakagi.ProductDigital.service_impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.nishikatakagi.ProductDigital.dto.UserLoginRequestDto;
import com.nishikatakagi.ProductDigital.model.User;
import com.nishikatakagi.ProductDigital.repository.UserRepository;
import com.nishikatakagi.ProductDigital.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Autowired
    SecurityServiceImpl security;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public BindingResult Login(UserLoginRequestDto userLoginRequestDto, BindingResult result) {
        // find user by username in database
        User user = userRepository.findUserByUsername(userLoginRequestDto.getUserName());

        // create error if user doesn't exit
        if (user == null) {
            result.addError(new FieldError("user", "userName", "Wrong username or password"));
            return result;
        }

        if (!user.isVerified()){
            result.addError(new FieldError("user", "userName", "User is not verified"));
        }
        if (user.isDeleted()){
            result.addError(new FieldError("user", "userName", "User is not active"));
        }

        // create error if password not correct
        if (!user.getPassword().equals(security.encode(userLoginRequestDto.getPassword()))) {
            result.addError(new FieldError("user", "password", "Wrong username or password"));
            return result;
        }
        return result;
    }
}
