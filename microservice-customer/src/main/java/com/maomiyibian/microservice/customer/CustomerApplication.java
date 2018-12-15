package com.maomiyibian.microservice.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * TODO: 服务消费者
 *
 * @author junyunxiao
 * @date 2018-9-10 10:06
 */
@SpringBootApplication(scanBasePackages = "com.maomiyibian.microservice.customer")
public class CustomerApplication {
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }
}
