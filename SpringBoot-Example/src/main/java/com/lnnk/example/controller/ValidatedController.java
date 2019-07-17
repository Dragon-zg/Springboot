package com.lnnk.example.controller;

import com.lnnk.example.model.param.CustomParam;
import com.lnnk.example.model.param.InputParam;
import com.lnnk.example.support.validator.CustomValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lnnk
 * @date 2019/7/17 9:55
 **/
@Api(tags = "参数校验")
@RestController
@RequestMapping("/api/validated")
@Log4j2
public class ValidatedController {

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
