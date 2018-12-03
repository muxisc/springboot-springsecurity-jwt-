package com.maomiyibian.microservice.customer.exception;

import com.maomiyibian.microservice.customer.security.mobile.ResultEnum;
import lombok.Data;
import org.springframework.security.core.AuthenticationException;

/**
 * @author weili
 */
@Data
public class
ValidateCodeException extends AuthenticationException {
    private static final long serialVersionUID = -7525757620869234981L;

    private Integer code;

    public ValidateCodeException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public ValidateCodeException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
