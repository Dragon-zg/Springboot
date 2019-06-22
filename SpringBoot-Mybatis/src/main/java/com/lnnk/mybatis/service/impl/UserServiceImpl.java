package com.lnnk.mybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lnnk.mybatis.mapper.UserMapper;
import com.lnnk.mybatis.model.dto.UserDTO;
import com.lnnk.mybatis.model.entity.User;
import com.lnnk.mybatis.service.UserService;
import com.lnnk.web.enums.ExceptionCode;
import com.lnnk.web.exception.CustomizedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;

/**
 * UserServiceImpl
 *
 * @author Lnnk
 * @date 2019-06-16 20:11
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /**
     * 初始化
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void init() {
        User init1 = new User();
        init1.setName("init1");
        init1.setAge(10);
        init1.setEmail("init1@baomidou.com");
        init1.setCreateTime(new Date());

        User init2 = new User();
        init2.setName("init2");
        init2.setAge(20);
        init2.setEmail("init1@baomidou.com");
        init2.setCreateTime(new Date());
        saveBatch(Arrays.asList(init1, init2));
    }

    /**
     * 更新
     *
     * @param id      用户ID
     * @param userDTO DTO
     */
    @Override
    public void update(Long id, UserDTO userDTO) {
        User user = getById(id);
        if (null == user) {
            throw new CustomizedException(ExceptionCode.DATA_NOT_EXIST);
        }
        userDTO.convertTo(user);
        updateById(user);
    }

    /**
     * 详情
     *
     * @param id 用户ID
     * @return User
     */
    @Override
    public User detail(Long id) {
        User user = getById(id);
        if (null == user) {
            throw new CustomizedException(ExceptionCode.DATA_NOT_EXIST);
        }
        return user;
    }
}
