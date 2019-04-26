package com.jpa.controller;

import com.web.annotation.IpStint;
import com.web.i18n.I18nUtil;
import com.web.model.ResultModel;
import com.web.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dragon-zg
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
    public ResultModel<String> ipStint() {
        return ResultUtil.success();
    }
}