package com.lnnk.mybatis.model.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

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

    /**
     * 创建时间,插入时默认填充字段
     */
    @TableField(fill = FieldFill.INSERT)
    protected Date createTime;

    /**
     * 更新时间，插入更新时默认填充字段
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    protected Date updateTime;

    /**
     * 删除标识符 0.未删除 1.已删除
     */
    @TableLogic
    protected Integer deleteFlag;
}
