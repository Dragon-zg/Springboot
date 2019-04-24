package com.jpa.controller;

import com.web.i18n.I18nUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dragon-zg
 * @date 2019/1/10 16:20
 **/
@Api(tags = "多语言国际化")
@RestController
@RequestMapping("/api/i18n")
public class I18nController {

    @ApiOperation("多语言Demo测试")
    @GetMapping("")
    public String getI18n() {
        // cookie key:language value:zh/en
        String code = "唯一code";
        return I18nUtil.getMessage(code);
    }
}
