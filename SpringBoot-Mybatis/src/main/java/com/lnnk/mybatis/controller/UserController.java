package com.lnnk.mybatis.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    public IPage<UserVO> page(@RequestParam(value = "current", defaultValue = "1") Integer current,
                              @RequestParam(value = "size", defaultValue = "10") Integer size,
                              UserDTO userDTO) {
        QueryWrapper<User> wrapper = Wrappers.<User>query().ge(null != userDTO.getAge(), "age", userDTO.getAge());
        return userService.page(new Page<>(current, size), wrapper).convert(user -> new UserVO().convertFrom(user));
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
