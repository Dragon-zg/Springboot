package com.lnnk.example.controller;

import com.lnnk.example.annotation.IpStint;
import com.lnnk.example.i18n.I18nUtil;
import com.lnnk.web.enums.ExceptionCode;
import com.lnnk.web.exception.CustomizedException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lnnk
 * @date 2019/1/10 16:20
 **/
@Api(tags = "测试功能接口")
@RestController
@RequestMapping("/api/test")
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
}
