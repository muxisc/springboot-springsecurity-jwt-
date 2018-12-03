package com.maomiyibian.microservice.customer.security.mobile;

import lombok.Data;

/**
 *
 * @author
 * @since 1.0
 */
@Data
public class Result<T> {
    private Integer code;
    private String msg;

    private T data;
}
