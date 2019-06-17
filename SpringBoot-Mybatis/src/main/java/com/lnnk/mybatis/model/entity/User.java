package com.lnnk.mybatis.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.lnnk.mybatis.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("mybatisUser")
public class User extends BaseEntity {

    @TableId(type = IdType.ID_WORKER)
    @ApiModelProperty("雪花算法 主键ID")
    private Long id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("邮箱")
    private String email;

}
