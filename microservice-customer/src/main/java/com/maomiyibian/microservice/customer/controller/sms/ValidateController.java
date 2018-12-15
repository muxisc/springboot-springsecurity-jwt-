package com.maomiyibian.microservice.customer.controller.sms;

import com.maomiyibian.microservice.customer.security.validate.sms.SmsCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 微笑天使
 * @Date: 2018/12/15 16:19
 * @Version 1.0
 */
@RestController
public class ValidateController {

    @Autowired
    private SmsCodeProcessor smsValidateCodeProcessor;

    /**
     * 生成验证码
     * */
    @PostMapping("generateCode")
    public void generateCode(HttpServletRequest request) throws Exception{
        smsValidateCodeProcessor.create(request);
    }
}
