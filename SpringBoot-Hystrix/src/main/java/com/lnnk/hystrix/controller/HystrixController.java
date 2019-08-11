package com.lnnk.hystrix.controller;

import com.lnnk.hystrix.model.entity.Person;
import com.lnnk.hystrix.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HystrixController
 *
 * @author wangqiang
 * @date 2019/8/11 16:45
 **/
@Api(tags = "熔断机制")
@RestController
@RequestMapping("/api/hystrix")
public class HystrixController {
    @Autowired
    PersonService personService;

    @ApiOperation(value = "semaphore")
    @GetMapping(value = "/semaphore")
    public Person semaphore() {
        return personService.semaphore("");
    }

    @ApiOperation(value = "thread")
    @GetMapping(value = "/thread")
    public Person thread() {
        return personService.thread("");
    }
}
