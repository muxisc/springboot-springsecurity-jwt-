package com.maomiyibian.microservice.customer.security.validate.sms;


import com.maomiyibian.microservice.customer.security.properties.SecurityConstants;
import com.maomiyibian.microservice.customer.security.validate.vo.ValidateCode;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author 微笑天使
 * @since 1.0
 */
@Component("smsValidateCodeGenerator")
public class SmsCodeGenerator {

    public ValidateCode generate() {
        String code = RandomStringUtils.randomNumeric(SecurityConstants.LENGTH);
        return new ValidateCode(code, SecurityConstants.EXPIRE_TIME);
    }
}
