package com.maomiyibian.microservice.customer.security;

import com.maomiyibian.microservice.customer.security.handler.AuthenticationSuccessHandler;
import com.maomiyibian.microservice.customer.security.handler.CustomAuthenticationFailHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * TODO: Spring Security 核心配置类
 *
 * @author junyunxiao
 * @version 1.0
 * @date 2018/11/28 15:44
 */
@Configuration
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private CustomAuthenticationFailHandler customAuthenticationFailHandler;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                //制定授权地址
                .loginPage("/user/unAuthorized")
                .loginProcessingUrl("/user/login")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(customAuthenticationFailHandler)
        //http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/user/unAuthorized","/user/login").permitAll()
                .anyRequest()
                .authenticated()
                .and().csrf().disable();
    }
}
