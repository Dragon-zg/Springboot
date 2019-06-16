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
}
