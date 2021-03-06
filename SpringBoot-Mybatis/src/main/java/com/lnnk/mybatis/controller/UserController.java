package com.lnnk.mybatis.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lnnk.mybatis.model.dto.UserDTO;
import com.lnnk.mybatis.model.dto.UserPageDTO;
import com.lnnk.mybatis.model.entity.User;
import com.lnnk.mybatis.model.vo.UserVO;
import com.lnnk.mybatis.page.PageUtils;
import com.lnnk.mybatis.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Collections;
import java.util.List;
import java.util.Map;
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
                              UserPageDTO userPageDTO) {
        //GET 方法不能使用json传输参数,故无法使用jackson进行序列化
        QueryWrapper<User> wrapper = Wrappers.<User>query()
                .eq(StringUtils.isNotEmpty(userPageDTO.getName()), "name", userPageDTO.getName())
                .ge(null != userPageDTO.getAge(), "age", userPageDTO.getAge())
                .eq(null != userPageDTO.getUseType(), "enable", userPageDTO.getUseType())
                .eq(null != userPageDTO.getGender(), "gender", userPageDTO.getGender());
        return userService.page(new Page<>(current, size), wrapper)
                .convert(user -> new UserVO().convertFrom(user));
    }

    @ApiOperation(value = "分页列表")
    @GetMapping(value = {"/page2"})
    public Page<User> page2(Page<User> page, WebRequest request) {
        Map<String, Object> params = PageUtils.getParametersStartingWith(request, "search_");
        QueryWrapper<User> queryWrapper = PageUtils.bySearchFilter(params);
        return userService.page(page, queryWrapper.orderByDesc("id"));

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
