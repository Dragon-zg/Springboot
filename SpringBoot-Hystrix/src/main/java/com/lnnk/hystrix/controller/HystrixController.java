package com.lnnk.hystrix.controller;

import com.lnnk.hystrix.model.entity.Person;
import com.lnnk.hystrix.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    private final PersonService personService;

    public HystrixController(PersonService personService) {
        this.personService = personService;
    }

    @ApiOperation(value = "隔离策略: SEMAPHORE")
    @GetMapping(value = "/semaphore")
    public Person semaphore(@RequestParam(required = false) String arg) {
        return personService.semaphore(arg);
    }

    @ApiOperation(value = "隔离策略: THREAD")
    @GetMapping(value = "/thread")
    public Person thread(@RequestParam(required = false) String arg) {
        return personService.thread(arg);
    }
}
