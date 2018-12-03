package com.maomiyibian.microservice.customer.security.validate.sms;


import com.maomiyibian.microservice.customer.security.validate.ValidateCode;
import com.maomiyibian.microservice.customer.security.validate.ValidateCodeGenerator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created on 2018/1/10.
 *
 * @author zlf
 * @since 1.0
 */
@Component("smsValidateCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
    }
}
