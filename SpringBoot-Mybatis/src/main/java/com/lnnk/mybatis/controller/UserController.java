package com.lnnk.mybatis.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lnnk.mybatis.model.dto.UserDTO;
import com.lnnk.mybatis.model.entity.User;
import com.lnnk.mybatis.model.vo.UserVO;
import com.lnnk.mybatis.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    @GetMapping(value = {"/list"})
    public List<UserVO> list() {
        return convertTo(userService.list());
    }

    @ApiOperation(value = "初始化")
    @PostMapping(value = {"/init"})
    public void init() {
        userService.init();
    }

    @ApiOperation(value = "详情")
    @GetMapping(value = {"/{id}/detail"})
    public UserVO detail(@PathVariable("id") Long id) {
        User teacher = userService.detail(id);
        return new UserVO().convertFrom(teacher);
    }

    @ApiOperation(value = "更新")
    @PutMapping(value = {"/{id}/update"})
    public void update(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
        userService.update(id, userDTO);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = {"/{id}/delete"})
    public void delete(@PathVariable("id") Long id) {
        userService.removeById(id);
    }

    private List<UserVO> convertTo(List<User> userList) {
        if (CollectionUtils.isEmpty(userList)) {
            return Collections.emptyList();
        }

        return userList.stream().map(user -> (UserVO) new UserVO().convertFrom(user))
                .collect(Collectors.toList());
    }
}
