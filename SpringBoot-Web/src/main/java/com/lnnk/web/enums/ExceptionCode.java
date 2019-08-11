package com.lnnk.web.enums;

/**
 * @description: 异常编码枚举类
 * @author Lnnk
 * @date: 2017-12-16 20:34
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
    /**
     * 数据不存在! 300
     */
    DATA_NOT_EXIST(300, "数据不存在!"),
    /**
     * 熔断降级! 400
     */
    MELT_DOWNGRADE(400, "熔断降级!")

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
