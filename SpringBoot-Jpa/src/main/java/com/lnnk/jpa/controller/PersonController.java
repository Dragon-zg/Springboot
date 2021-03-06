package com.lnnk.jpa.controller;

import com.lnnk.jpa.model.dto.PersonDTO;
import com.lnnk.jpa.model.entity.onetoone.Person;
import com.lnnk.jpa.model.vo.PersonVO;
import com.lnnk.jpa.service.PersonService;
import com.lnnk.web.util.ServletUtils;
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
 * @author Lnnk
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
    public void initPerson() {
        personService.initPerson();
    }

    @ApiOperation(value = "详情")
    @GetMapping(value = {"/{id}/detail"})
    public PersonVO detail(@PathVariable("id") Long id) {
        Person person = personService.detail(id);
        return new PersonVO().convertFrom(person);
    }

    @ApiOperation(value = "更新")
    @PutMapping(value = {"/{id}/update"})
    public void update(@PathVariable("id") Long id, @RequestBody PersonDTO personDto) {
        personService.update(id, personDto);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = {"/{id}/delete"})
    public void delete(@PathVariable("id") Long id) {
        personService.delete(id);
    }

    private List<PersonVO> convertTo(List<Person> personList) {
        if (CollectionUtils.isEmpty(personList)) {
            return Collections.emptyList();
        }

        return personList.stream().map(person -> (PersonVO) new PersonVO().convertFrom(person))
                .collect(Collectors.toList());
    }
}
