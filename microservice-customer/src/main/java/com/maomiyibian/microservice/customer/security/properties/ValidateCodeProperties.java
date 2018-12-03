package com.maomiyibian.microservice.customer.security.properties;

import lombok.Data;

/**
 * @author
 * @since 1.0
 */
@Data
public class ValidateCodeProperties {
    /**
     * 短信验证码配置
     */
    private SmsCodeProperties sms = new SmsCodeProperties();
}
