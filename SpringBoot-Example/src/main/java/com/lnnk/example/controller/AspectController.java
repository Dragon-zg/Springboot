package com.lnnk.example.controller;

import com.lnnk.web.enums.ExceptionCode;
import com.lnnk.web.exception.CustomizedException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lnnk
 * @date 2019/7/17 9:56
 **/
@Api(tags = "AOP切面")
@RestController
@RequestMapping("/api/aspect")
@Log4j2
public class AspectController {


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
