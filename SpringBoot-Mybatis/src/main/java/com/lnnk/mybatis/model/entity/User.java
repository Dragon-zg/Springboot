package com.lnnk.mybatis.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lnnk.mybatis.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户实体对应表 user
 *
 * @author Lnnk
 * @date 2019/6/14 14:27
 **/
@Data
@ApiModel("mybatisUser")
@TableName("mybatis_user")
public class User extends BaseEntity {

    @TableId
    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("邮箱")
    private String email;

}
