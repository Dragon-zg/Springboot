package com.lnnk.mybatis.model.base;

import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 所有实体的父类
 *
 * @author Lnnk
 * @date 2018-06-03 18:22
 */
@Getter
@Setter
@ApiModel("实体基础属性类")
public class BaseEntity implements Serializable {

    @ApiModelProperty("创建时间")
    protected LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    protected LocalDateTime updateTime;

    @TableLogic
    @ApiModelProperty("删除标识符")
    protected Integer deleteFlag;
}
