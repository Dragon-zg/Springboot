package com.lnnk.mybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lnnk.mybatis.mapper.UserMapper;
import com.lnnk.mybatis.model.entity.User;
import com.lnnk.mybatis.service.UserService;
import org.springframework.stereotype.Service;

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
    public void init() {
    }
}
