package com.lnnk.mybatis.model.dto;

import com.lnnk.mybatis.model.converter.InputConverter;
import com.lnnk.mybatis.model.entity.User;
import lombok.Data;

/**
 * UserPageDTO
 *
 * @author Lnnk
 * @date 2019-06-22 23:39
 **/
@Data
public class UserPageDTO implements InputConverter<User> {
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 使用状态
     */
    private Integer useType;
    /**
     * 性别
     */
    private Integer gender;
}
