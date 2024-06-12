package com.nishikatakagi.ProductDigital.service;

import org.springframework.scheduling.annotation.Async;

import jakarta.mail.MessagingException;

public interface EmailService {
    @Async
    public void sendEmail(String to,String otp) throws MessagingException;
    public String createOtp();
}
