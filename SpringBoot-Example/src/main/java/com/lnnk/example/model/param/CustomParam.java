package com.lnnk.example.model.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Lnnk
 * @date 2019/7/11 17:10
 **/
@Data
public class CustomParam implements Serializable {

    private String userName;

    private String password;

    private String email;
}
