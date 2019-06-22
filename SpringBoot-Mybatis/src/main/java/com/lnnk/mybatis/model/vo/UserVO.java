package com.lnnk.mybatis.model.vo;

import com.lnnk.mybatis.model.converter.OutputConverter;
import com.lnnk.mybatis.model.entity.User;
import com.lnnk.mybatis.model.enums.GenderEnum;
import com.lnnk.mybatis.model.enums.UseType;
import lombok.Data;

/**
 * UserVO
 *
 * @author Lnnk
 * @date 2019-06-22 18:02
 **/
@Data
public class UserVO implements OutputConverter<UserVO, User> {
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
