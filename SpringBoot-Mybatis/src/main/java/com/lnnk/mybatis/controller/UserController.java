package com.lnnk.mybatis.controller;

import com.lnnk.mybatis.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController
 *
 * @author Lnnk
 * @date 2019-06-16 13:56
 **/
@Api(value = "user", tags = "user")
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
}
