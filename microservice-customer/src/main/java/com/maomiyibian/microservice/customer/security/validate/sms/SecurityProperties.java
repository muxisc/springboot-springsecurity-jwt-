package com.maomiyibian.microservice.customer.security.validate.sms;

import com.maomiyibian.microservice.customer.security.properties.ValidateCodeProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author
 */
@Data
@Component
@ConfigurationProperties(prefix = "merryyou.security") //配置文件载入实体
public class SecurityProperties {

    private LoginType loginType = LoginType.JSON;

    /**
     * 验证码配置
     */
    private ValidateCodeProperties code = new ValidateCodeProperties();

    /**
     * 记住我的有效时间秒
     */
    private int rememberMeSeconds = 60 * 60 * 24 * 7;

}
