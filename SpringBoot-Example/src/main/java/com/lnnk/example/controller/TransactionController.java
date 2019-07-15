package com.lnnk.example.controller;


import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lnnk
 * @date 2019/7/15 14:00
 **/
@Api(tags = "事务传播性演示接口")
@RestController
@RequestMapping("/api/transaction")
@Log4j2
public class TransactionController {


}
