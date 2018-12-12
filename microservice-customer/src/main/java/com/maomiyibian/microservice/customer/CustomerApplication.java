package com.maomiyibian.microservice.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * TODO: 服务消费者
 *
 * @author junyunxiao
 * @date 2018-9-10 10:06
 */
@SpringBootApplication(scanBasePackages = "com.maomiyibian.microservice.customer")
public class CustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }
}
