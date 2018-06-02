package com.web.handler;

import com.common.enums.ExceptionCode;
import com.common.exception.BusiException;
import com.web.model.ResultModel;
import com.web.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: 当controller抛出异常时, 自动捕获异常并返回相应错误码及信息
 * @Date: 2017-12-16 20:11
 */
@ControllerAdvice
public class AutoExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = Exception.class) //该注解实现了对某一类异常的处理
    @ResponseBody //设置返回结果格式为json字符串
    public ResultModel<?> handleException(Exception e) {
        //先打印异常日志信息
        logger.error(e.getMessage(), e);
        if (e instanceof BusiException) {//若属于业务异常,则抛出相关编码信息
            BusiException busiException = (BusiException) e;
            return ResultUtil.error(busiException.getCode(), busiException.getMessage());
        } else {// 其他异常默认为未知错误
            return ResultUtil.error(ExceptionCode.UNKOW_ERROR.getCode(), ExceptionCode.UNKOW_ERROR.getMsg());
        }
    }
}
