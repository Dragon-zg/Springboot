package com.web.exception;


import com.web.enums.ExceptionCode;

/**
 * 自定义业务异常
 *
 * @Author Dragon-zg
 * @Date: 2017-12-16 20:07
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
