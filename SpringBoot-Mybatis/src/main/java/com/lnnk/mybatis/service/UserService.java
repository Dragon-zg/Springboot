package com.lnnk.mybatis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lnnk.mybatis.model.dto.UserDTO;
import com.lnnk.mybatis.model.entity.User;

/**
 * UserService
 *
 * @author Lnnk
 * @date 2019-06-16 20:12
 */
public interface UserService extends IService<User> {
    /**
     * 初始化
     */
    void init();

    /**
     * 更新
     *
     * @param id      用户ID
     * @param userDTO DTO
     */
    void update(Long id, UserDTO userDTO);

    /**
     * 详情
     *
     * @param id 用户ID
     * @return User
     */
    User detail(Long id);
}
