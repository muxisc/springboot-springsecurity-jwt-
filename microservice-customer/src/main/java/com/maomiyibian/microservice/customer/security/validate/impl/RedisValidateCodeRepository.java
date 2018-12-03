package com.maomiyibian.microservice.customer.security.validate.impl;


import com.maomiyibian.microservice.customer.security.validate.ValidateCode;
import com.maomiyibian.microservice.customer.security.validate.ValidateCodeRepository;
import com.maomiyibian.microservice.customer.security.validate.ValidateCodeType;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author: 微笑天使
 * @Date: 2018/11/30 14:53
 * @Version 1.0
 */
@Component
public class RedisValidateCodeRepository implements ValidateCodeRepository {
    @Override
    public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType) {

    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType) {
        return null;
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType codeType) {

    }
}
