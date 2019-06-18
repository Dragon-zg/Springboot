package com.lnnk.mybatis.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.lnnk.mybatis.model.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 用户实体对应表 mybatis_user
 *
 * @author Lnnk
 * @date 2019/6/14 14:27
 **/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {
    /**
     * 雪花算法 主键ID
     */
    @TableId(type = IdType.ID_WORKER)
    private Long id;

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

}
