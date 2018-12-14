package com.maomiyibian.microservice.customer.security.validate.sms;



import com.maomiyibian.microservice.customer.security.properties.SecurityConstants;
import com.maomiyibian.microservice.customer.security.validate.vo.ValidateCode;
import com.maomiyibian.microservice.customer.security.validate.impl.AbstractValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2018/1/10.
 *
 * @author zlf
 * @since 1.0
 */
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    /**
     * 短信验证码发送器
     */
    @Autowired
    private SmsCodeSender smsCodeSender;

    //SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;

    @Override
    protected void send(String phoneNum, ValidateCode validateCode) throws Exception {
        smsCodeSender.send(phoneNum, validateCode.getCode());
    }
}
