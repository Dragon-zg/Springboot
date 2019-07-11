package com.lnnk.web.handler;

import com.lnnk.web.constant.SymbolConsts;
import com.lnnk.web.enums.ExceptionCode;
import com.lnnk.web.exception.CustomizedException;
import com.lnnk.web.model.support.ResponseBack;
import com.lnnk.web.util.ResponseUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.StringJoiner;

/**
 * 当controller抛出异常时, 自动捕获异常并返回相应错误码及信息
 *
 * @author Lnnk
 * @date: 2017-12-16 20:11
 */
@Log4j2
@RestControllerAdvice(annotations = {RestController.class, Controller.class})
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseBack handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringJoiner errorMsg = new StringJoiner(SymbolConsts.SYMBOL_SEMICOLON_BLANK);
        bindingResult.getFieldErrors().forEach(fieldError -> errorMsg.add(String.format("错误字段: %s，错误值: %s，原因：%s",
                fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage())));
        return ResponseUtils.error(ExceptionCode.PARAM_ERROR.getCode(), errorMsg.toString());
    }

    @ExceptionHandler(value = CustomizedException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseBack handleBusiException(CustomizedException e) {
        //若属于业务异常,则抛出相关编码信息
        log.info(e.getMessage());
        return ResponseUtils.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseBack handleException(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseUtils.error(ExceptionCode.UNKOW_ERROR);
    }
}
