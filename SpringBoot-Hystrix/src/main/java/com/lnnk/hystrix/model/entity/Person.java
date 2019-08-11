package com.lnnk.hystrix.model.entity;

import lombok.Data;

/**
 * @author Lnnk
 * @date 2019/8/11 16:38
 **/
@Data
public class Person {
    private Long id;

    private String name;

    private Integer age;

    private String address;
}
