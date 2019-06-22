package com.lnnk.mybatis.model.dto;

import com.lnnk.mybatis.model.converter.InputConverter;
import com.lnnk.mybatis.model.entity.User;
import com.lnnk.mybatis.model.enums.GenderEnum;
import com.lnnk.mybatis.model.enums.UseType;
import lombok.Data;

/**
 * UserDTO
 *
 * @author Lnnk
 * @date 2019-06-22 18:09
 **/
@Data
public class UserDTO implements InputConverter<User> {
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
    private UseType useType;
    /**
     * 性别
     */
    private GenderEnum gender;
}
