package com.web.enums;

/**
 * @Description: 异常编码枚举类
 * @Author Dragon-zg
 * @Date: 2017-12-16 20:34
 */
public enum ExceptionCode {
    /**
     * 错误的参数 100
     */
    PARAM_ERROR(100, "错误的参数!"),
    /**
     * 操作成功 200
     */
    SUCCESS(200, "操作成功!"),
    /**
     * 未知错误 999999
     */
    UNKOW_ERROR(999999, "未知错误!"),

    DATA_NOT_EXIST(300, "数据不存在!")

    ;

    private Integer code;
    private String msg;

    ExceptionCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
