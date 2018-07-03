package com.web.enums;

/**
 * @Description: 异常编码枚举类
 * @Date: 2017-12-16 20:34
 */
public enum ExceptionCode {
    SUCCESS(200, "操作成功!"),
    UNKOW_ERROR(999999, "未知错误!"),

    //301 ~ 399 上传异常定义
    UPLOAD_FILE_UNEXIST(301, "上传文件不存在!"),
    UPLOAD_FORMAL_ERROR(301, "上传文件类型不正确!"),
    UPLOAD_SIZE_ERROR(301, "上传文件大小超过限制!"),

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
