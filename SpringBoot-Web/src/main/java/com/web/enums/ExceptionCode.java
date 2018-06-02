package com.web.enums;

/**
 * @Description: 异常编码枚举类
 * @Date: 2017-12-16 20:34
 */
public enum ExceptionCode {
    UNKOW_ERROR(999999, "未知错误!"),
    SUCCESS(0, "操作成功!"),
    NOT_EXIST_GIRL(1000,"不存在该女生!"),
    SMALL_GIRL(1001,"女生年纪过小,不允许查询!")

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
