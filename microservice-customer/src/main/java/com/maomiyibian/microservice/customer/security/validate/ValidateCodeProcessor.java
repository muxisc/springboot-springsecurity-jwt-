package com.maomiyibian.microservice.customer.security.validate;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码处理器，封装不通的验证码处理逻辑
 * @author
 */
public interface ValidateCodeProcessor {

    /**
     * 创建校验码
     *
     * @param request
     * @throws Exception
     */
    void create(ServletWebRequest request) throws Exception;

    /**
     * 校验验证码
     *
     * @param servletWebRequest
     * @throws Exception
     */
    void validate(ServletWebRequest servletWebRequest);

}
