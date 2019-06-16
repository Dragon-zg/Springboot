package com.lnnk.web.exception;


import com.lnnk.web.enums.ExceptionCode;

/**
 * 自定义业务异常
 *
 * @author Lnnk
 * @date: 2017-12-16 20:07
 */
public class CustomizedException extends RuntimeException {
    private Integer code;

    public CustomizedException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMsg());
        this.code = exceptionCode.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
