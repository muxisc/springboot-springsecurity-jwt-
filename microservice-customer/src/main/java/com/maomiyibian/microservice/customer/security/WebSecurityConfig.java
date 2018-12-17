package com.maomiyibian.microservice.customer.security;

import com.maomiyibian.microservice.customer.security.deal.CustomAuthenticationProvider;
import com.maomiyibian.microservice.customer.security.filter.JWTAuthenticationFilter;
import com.maomiyibian.microservice.customer.security.filter.JWTLoginFilter;
import com.maomiyibian.microservice.customer.security.handler.AuthenticationSuccessHandler;
import com.maomiyibian.microservice.customer.security.handler.CustomAuthenticationFailHandler;
import com.maomiyibian.microservice.customer.security.mobile.SmsCodeAuthenticationSecurityConfig;
import com.maomiyibian.microservice.customer.security.validate.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @author junyunxiao
 * @version 1.0
 * @date 2018/11/28 15:44
 */
@Configuration
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private UserDetailsService userDetailServiceImpl;   ;

   @Autowired
    private SpringSocialConfigurer merryyouSpringSocialConfigurer;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private CustomAuthenticationFailHandler customAuthenticationFailHandler;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                //登录地址,路由前端控制，理应不作配置,这里配置拦截不存在的请求
                .loginPage("/user/toLoginPage")
                //采用JWT登录拦截，路由是/login
                /*.loginProcessingUrl("/user/login")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(customAuthenticationFailHandler)*/
                //http.httpBasic()
                .and()
                //验证码拦截
                .apply(validateCodeSecurityConfig)
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                //社交登录
                .apply(merryyouSpringSocialConfigurer)
                //采用自定义登录拦截
                /*.and()
                .authorizeRequests()
                .antMatchers("/user/unAuthorized","/user/login").permitAll()
                .anyRequest()
                .authenticated()*/
                .and()
                .addFilter(new JWTLoginFilter(authenticationManager()))
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
// 使用自定义身份验证组件
        auth.authenticationProvider(new CustomAuthenticationProvider(userDetailServiceImpl, bCryptPasswordEncoder));
    }

}
