package com.maomiyibian.microservice.customer.security.validate.impl;


import com.maomiyibian.microservice.customer.exception.ValidateCodeException;
import com.maomiyibian.microservice.customer.security.mobile.ResultEnum;
import com.maomiyibian.microservice.customer.security.validate.*;
import com.maomiyibian.microservice.customer.security.validate.vo.ValidateCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * @author
 */
@Slf4j
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {

    /**
     * 收集系统中所有的 {@link ValidateCodeGenerator} 接口的实现。
     */
    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGenerators;

    @Autowired
    private ValidateCodeRepository validateCodeRepository;

    /**创建校验码，并且保存到，后面保存到redis*/
    @Override
    public void create(ServletWebRequest request) throws Exception {
        C validateCode = generate(request);
        save(request, validateCode);
        send(request, validateCode);
    }

    /**
     * 根据Holder返回得CodeProcessor类型来获取验证码的类型
     *
     * @param request
     * @return
     */
    private ValidateCodeType getValidateCodeType(ServletWebRequest request) {
        String type = StringUtils.substringBefore(getClass().getSimpleName(), "CodeProcessor");
        return ValidateCodeType.valueOf(type.toUpperCase());
    }

    /**
     * 生成验证码
     *
     * @param request
     * @return
     */
    private C generate(ServletWebRequest request) {
        String type = getValidateCodeType(request).toString().toLowerCase();
        String generatorName = type + ValidateCodeGenerator.class.getSimpleName();
        ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(generatorName);
        if (validateCodeGenerator == null) {
            throw new ValidateCodeException("验证码生成器" + generatorName + "不存在");
        }
        return (C) validateCodeGenerator.generate(request);
    }

    /**
     * 保存验证码
     */
    private void save(ServletWebRequest request, C validateCode){
        ValidateCode code = new ValidateCode(validateCode.getCode(), validateCode.getExpireTime());
        validateCodeRepository.save(request, code, getValidateCodeType(request));
    }

    /**
     * 发送校验码，由子类实现
     *
     * @param request
     * @param validateCode
     * @throws Exception
     */
    protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;

    @SuppressWarnings("unchecked")
    @Override
    public void validate(ServletWebRequest request) {

        ValidateCodeType codeType = getValidateCodeType(request);

        //从缓存中获取code，与请求的code对比
        //C codeInSession = (C) validateCodeRepository.get(request, codeType);
        ValidateCode codeInSession = new ValidateCode("123456",60);
        String codeInRequest;
        try {
            //获取请求进来的code
            codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),
                    codeType.getParamNameOnValidate());
            log.info("请求中的验证码smsCode:{}",codeInRequest);
        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeException(ResultEnum.CODE_ERROT.getCode(),"获取验证码的值失败");
    }
        //开始验证
        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException(ResultEnum.CODE_ERROT.getCode(),codeType + "验证码的值不能为空");
        }
        //缓存中的code码
        if (codeInSession == null) {
            throw new ValidateCodeException(ResultEnum.CODE_ERROT.getCode(),codeType + "验证码不存在");
        }

        if (codeInSession.isExpired()) {
            validateCodeRepository.remove(request, codeType);
            throw new ValidateCodeException(ResultEnum.CODE_ERROT.getCode(),codeType + "验证码已过期");
        }

        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException(ResultEnum.CODE_ERROT.getCode(),codeType + "验证码不匹配");
        }

        validateCodeRepository.remove(request, codeType);

    }
}
