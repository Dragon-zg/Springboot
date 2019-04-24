package com.web.enums;

/**
 * @Description: 异常编码枚举类
 * @Author Dragon-zg
 * @Date: 2017-12-16 20:34
 */
public enum ExceptionCode {
    /**
     * 操作成功 200
     */
    SUCCESS(200, "操作成功!"),
    /**
     * 未知错误 999999
     */
    UNKOW_ERROR(999999, "未知错误!"),
    /**
     * 上传文件不存在 301
     */
    UPLOAD_FILE_UNEXIST(301, "上传文件不存在!"),
    /**
     * 上传文件类型不正确 302
     */
    UPLOAD_FORMAL_ERROR(302, "上传文件类型不正确!"),
    /**
     * 上传文件大小超过限制 303
     */
    UPLOAD_SIZE_ERROR(303, "上传文件大小超过限制!"),

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
