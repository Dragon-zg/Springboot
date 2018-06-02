package com.common.exception;


import com.common.enums.ExceptionCode;

/**
 * @Description: 自定义业务异常
 * @Date: 2017-12-16 20:07
 */
public class BusiException extends RuntimeException {
    private Integer code;

    public BusiException(ExceptionCode exceptionCode) {
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
