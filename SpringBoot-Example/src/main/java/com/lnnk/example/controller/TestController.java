package com.lnnk.example.controller;

import com.lnnk.example.annotation.IpStint;
import com.lnnk.example.i18n.I18nUtil;
import com.lnnk.example.model.param.CustomParam;
import com.lnnk.example.model.param.InputParam;
import com.lnnk.example.support.validator.CustomValidator;
import com.lnnk.web.enums.ExceptionCode;
import com.lnnk.web.exception.CustomizedException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lnnk
 * @date 2019/1/10 16:20
 **/
@Api(tags = "测试功能接口")
@RestController
@RequestMapping("/api/test")
@Log4j2
public class TestController {

    @ApiOperation("多语言Demo测试")
    @GetMapping("/i18n")
    public String getI18n() {
        // cookie key:language value:zh/en
        String code = "唯一code";
        return I18nUtil.getMessage(code);
    }

    @IpStint
    @ApiOperation("IP拦截Demo测试")
    @GetMapping("/ipStint")
    public void ipStint() {
    }

    @ApiOperation("切面Demo测试")
    @GetMapping("/aspect")
    public void aspect(@RequestParam(value = "type", required = false) String type) {
        if (StringUtils.equals("1", type)) {
            throw new NullPointerException();
        } else if (StringUtils.equals("2", type)) {
            throw new CustomizedException(ExceptionCode.PARAM_ERROR);
        }
    }

    @ApiOperation("参数校验Demo测试")
    @PostMapping("/validated")
    public void validatedTest(@Validated(InputParam.ParameterGroup1.class) @RequestBody InputParam inputParam) {
        log.info("参数校验Demo测试. inputParam: {}", inputParam);
    }

    @InitBinder("customParam")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new CustomValidator());
    }

    @ApiOperation("自定义参数校验Demo测试")
    @PostMapping("/customValidated")
    public void validatedTest(@Validated @RequestBody CustomParam customParam) {
        log.info("自定义参数校验Demo测试. customParam : {}", customParam);
    }
}
