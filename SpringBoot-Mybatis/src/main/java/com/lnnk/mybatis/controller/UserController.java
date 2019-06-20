package com.lnnk.mybatis.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lnnk.mybatis.model.entity.User;
import com.lnnk.mybatis.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @ApiOperation(value = "分页列表")
    @GetMapping(value = {"/page"})
    public IPage<User> page(IPage<User> page) {
        return userService.page(page);
    }

    @ApiOperation(value = "列表")
    @PostMapping(value = {"/list"})
    public List<User> list() {
        return userService.list();
    }
}
