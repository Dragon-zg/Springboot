package com.web.handler;

import com.web.enums.ExceptionCode;
import com.web.exception.CustomizedException;
import com.web.model.ResultModel;
import com.web.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 当controller抛出异常时, 自动捕获异常并返回相应错误码及信息
 *
 * @author Dragon-zg
 * @Date: 2017-12-16 20:11
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class})
public class AutoExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResultModel<?> handleException(Exception e) {
        logger.error(e.getMessage(), e);
        return ResultUtil.error(ExceptionCode.UNKOW_ERROR);
    }

    @ExceptionHandler(value = CustomizedException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultModel<?> handleBusiException(CustomizedException e) {
        //若属于业务异常,则抛出相关编码信息
        logger.debug(e.getMessage(), e);
        return ResultUtil.error(e.getCode(), e.getMessage());
    }
}
