package com.lnnk.quartz.util;


import com.lnnk.quartz.model.enums.ExceptionCode;
import com.lnnk.quartz.model.support.ResponseBack;

/**
 * 相应结果工具类
 *
 * @author Lnnk
 * @date: 2017-12-16 20:41
 */
@SuppressWarnings("unchecked")
public class ResponseUtils {
    public static <T extends Object> ResponseBack<T> success(T object) {
        ResponseBack result = new ResponseBack();
        result.setCode(ExceptionCode.SUCCESS.getCode());
        result.setMsg(ExceptionCode.SUCCESS.getMsg());
        result.setData(object);
        return result;
    }

    public static ResponseBack success() {
        return success(null);
    }

    public static ResponseBack error(Integer code, String msg) {
        ResponseBack result = new ResponseBack();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static ResponseBack error(ExceptionCode exceptionCode) {
        ResponseBack result = new ResponseBack();
        result.setCode(exceptionCode.getCode());
        result.setMsg(exceptionCode.getMsg());
        return result;
    }
}
