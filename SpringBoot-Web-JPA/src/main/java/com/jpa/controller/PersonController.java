package com.jpa.controller;

import com.jpa.model.dto.PersonDTO;
import com.jpa.model.entity.unidirectional.onetoone.Person;
import com.jpa.model.vo.PersonVO;
import com.jpa.service.PersonService;
import com.web.model.ResultModel;
import com.web.util.ResultUtils;
import com.web.util.ServletUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @ApiOperation(value = "分页列表")
    @GetMapping(value = {"/page"})
    public Page<PersonVO> page(WebRequest request, Pageable pageable) {
        Map<String, Object> searchParam = ServletUtils.getParametersStartingWith(request, "search_");
        Page<Person> personPage = personService.pagingList(searchParam, pageable);
        return personPage.map(person -> new PersonVO().convertFrom(person));
    }

    @ApiOperation(value = "列表")
    @GetMapping(value = {"/list"})
    public List<PersonVO> list() {
        return convertTo(personService.listAll());
    }

    @ApiOperation(value = "初始化")
    @PostMapping(value = {"/init"})
    public ResultModel initPerson() {
        personService.initPerson();
        return ResultUtils.success();
    }

    @ApiOperation(value = "详情")
    @GetMapping(value = {"/{id}/detail"})
    public ResultModel<PersonVO> detail(@PathVariable("id") Long id) {
        Person person = personService.detail(id);
        return ResultUtils.success(new PersonVO().convertFrom(person));
    }

    @ApiOperation(value = "更新")
    @PutMapping(value = {"/{id}/update"})
    public ResultModel update(@PathVariable("id") Long id, @RequestBody PersonDTO personDto) {
        personService.update(id, personDto);
        return ResultUtils.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = {"/{id}/delete"})
    public ResultModel delete(@PathVariable("id") Long id) {
        personService.delete(id);
        return ResultUtils.success();
    }

    private List<PersonVO> convertTo(List<Person> personList) {
        if (CollectionUtils.isEmpty(personList)) {
            return Collections.emptyList();
        }

        return personList.stream().map(person -> (PersonVO) new PersonVO().convertFrom(person))
                .collect(Collectors.toList());
    }
}
