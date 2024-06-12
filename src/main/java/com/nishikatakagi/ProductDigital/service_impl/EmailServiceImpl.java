package com.nishikatakagi.ProductDigital.service_impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.nishikatakagi.ProductDigital.service.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
@EnableAsync
@Service
public class EmailServiceImpl implements EmailService{
    @Autowired
    public JavaMailSender mailSender;
    @Autowired
    public TemplateEngine templateEngine;
    @Override
    public String createOtp(){
        String code = "";
        for (int i = 0; i < 6; i++) {
            code += (int) (Math.random() * 10);
        }
        return code;
    }
    @Async
    public void sendEmail(String email, String otp)  throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(email);
        helper.setSubject("Sending verification code");

        // Prepare the Thymeleaf context and template
        Context context = new Context();
        context.setVariable("code", otp);

        // Correctly reference the template with subdirectory
        String htmlMsg = templateEngine.process("emails/send-otp", context);
        helper.setText(htmlMsg, true);
        mailSender.send(message);
    }
    

}
