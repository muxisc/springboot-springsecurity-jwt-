package com.maomiyibian.microservice.customer.security.validate.sms;

/**
 * 短信发送接口
 * @author zlf
 * @since 1.0
 */
public interface SmsCodeSender {

    /**
     * 发送短信验证码
     * @param mobile
     * @param code
     */
    void send(String mobile, String code);
}
