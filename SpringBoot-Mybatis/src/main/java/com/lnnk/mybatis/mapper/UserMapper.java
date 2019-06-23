package com.lnnk.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lnnk.mybatis.model.entity.User;
import org.springframework.stereotype.Repository;

/**
 * UserMapper
 *
 * @author Lnnk
 * @date 2019/6/14 14:28
 **/
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 自定义根据ID查询用户信息
     *
     * @param id 用户ID
     * @return com.lnnk.mybatis.model.entity.User
     */
    User queryById(Long id);
}
