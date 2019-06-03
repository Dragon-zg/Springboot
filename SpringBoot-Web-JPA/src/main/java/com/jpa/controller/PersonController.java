package com.jpa.controller;

import com.jpa.entity.onetoone.Person;
import com.jpa.service.PersonService;
import com.web.model.ResultModel;
import com.web.util.ResultUtils;
import com.web.util.ServletUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * @author Dragon-zg
 * @date 2019/4/24 10:40
 **/
@Api(value = "person", tags = "person")
@RestController
@RequestMapping("/api/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @ApiOperation(value = "初始化")
    @PostMapping(value = {"/init"})
    public ResultModel initPerson() {
        personService.initPerson();
        return ResultUtils.success();
    }

    @ApiOperation(value = "分页列表")
    @GetMapping(value = {""})
    public Page<Person> page(WebRequest request, Pageable pageable) {
        Map<String, Object> searchParam = ServletUtils.getParametersStartingWith(request, "search_");
        return personService.pagingList(searchParam, pageable);
    }

    @ApiOperation(value = "详情")
    @GetMapping(value = {"/{id}/detail"})
    public ResultModel<Person> detail(@PathVariable("id") Long id) {
        return ResultUtils.success(personService.detail(id));
    }

    @ApiOperation(value = "更新")
    @PutMapping(value = {"/{id}/update"})
    public ResultModel update(@PathVariable("id") Long id, @RequestBody Person person) {
        personService.update(id, person);
        return ResultUtils.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = {"/{id}/delete"})
    public ResultModel delete(@PathVariable("id") Long id) {
        personService.delete(id);
        return ResultUtils.success();
    }
}
