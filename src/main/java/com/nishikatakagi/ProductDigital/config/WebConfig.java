package com.nishikatakagi.ProductDigital.config;

import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nishikatakagi.ProductDigital.common.Captcha;
import com.nishikatakagi.ProductDigital.filter.AdminFilter;
import com.nishikatakagi.ProductDigital.filter.BeforeLoginFilter;
import com.nishikatakagi.ProductDigital.filter.LoginFilter;
import com.nishikatakagi.ProductDigital.mapper.UserRegisterMapper;


@Configuration
public class WebConfig {

    @Bean
    public PhysicalNamingStrategy physicalNamingStrategy() {
        return new CustomPhysicalNamingStrategy();
    }
    @Bean
    public UserRegisterMapper userRegisterMapper() {
        return new UserRegisterMapper();
    }
    @Bean
    Captcha captcha() {
        return new Captcha();
    }

    @Bean
    public FilterRegistrationBean<LoginFilter> loginFilter() {
        //customer access
        FilterRegistrationBean<LoginFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LoginFilter());
        registrationBean.addUrlPatterns("/profile/*","/profile");
        registrationBean.addUrlPatterns("/changepassword");
        registrationBean.addUrlPatterns("/cart/*","/cart");
        registrationBean.addUrlPatterns("/order/*","/order");
        return registrationBean;
    }
    @Bean
    public FilterRegistrationBean<BeforeLoginFilter> beforeLoginFilter(){
        //just before login
        FilterRegistrationBean<BeforeLoginFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new BeforeLoginFilter());
        registrationBean.addUrlPatterns("/register/*","/register");
        registrationBean.addUrlPatterns("/login");
        registrationBean.addUrlPatterns("/forgotpassword/*","/forgotpassword");
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<AdminFilter> adminFilter() {
        //admin access
        FilterRegistrationBean<AdminFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AdminFilter());
        registrationBean.addUrlPatterns("/account/*","/account");
        registrationBean.addUrlPatterns("/publisher/*","/publisher");
        registrationBean.addUrlPatterns("/admin/*","/admin");
        return registrationBean;
    }


}
