package com.web.model;

/**
 * @Description: 统一返回接口格式结果集
 * @Date: 2017-12-16 19:56
 */
public class ResultModel<T> {
    private Integer code;

    private String msg;

    private T data;

    public ResultModel() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
