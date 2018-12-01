package com.maomiyibian.microservice.provider;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * TODO: 服务提供者
 *
 * @author junyunxiao
 * @date 2018-9-10 9:57
 */
@SpringBootApplication
public class ProviderApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ProviderApplication.class).web(WebApplicationType.NONE).run(args);
    }
}
