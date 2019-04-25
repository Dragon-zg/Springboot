package com.jpa.controller;

import com.jpa.entity.onetoone.Person;
import com.jpa.service.PersonService;
import com.web.model.ResultModel;
import com.web.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

/**
 * @author Dragon-zg
 * @date 2019/4/24 10:40
 **/
@Api(value = "person", tags = "person")
@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @ApiOperation(value = "初始化公民数据")
    @GetMapping(value = {"/initPerson"})
    public void initPerson() {
        personService.initPerson();
    }

    @ApiOperation(value = "分页列表")
    @GetMapping(value = {""})
    public Page<Person> page(WebRequest request, Pageable pageable){
        return null;
    }

    @ApiOperation(value = "详情")
    @GetMapping(value = {"/{id}/detail"})
    public ResultModel<Person> detail(@PathVariable("id") Long id) {
        return ResultUtil.success(personService.detail(id));
    }
}
