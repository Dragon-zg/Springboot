package com.mybatis.model.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 用户实体对应表 user
 *
 * @author wangqiang
 * @date 2019/6/14 14:27
 **/
@Data
@ApiModel("mybatisUser")
public class MybatisUser {

    private Long id;

    private String name;

    private Integer age;

    private String email;
}
