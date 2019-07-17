package com.lnnk.example.controller;

import com.lnnk.example.annotation.IpStint;
import com.lnnk.example.i18n.I18nUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lnnk
 * @date 2019/1/10 16:20
 **/
@Api(tags = "测试功能")
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
}
