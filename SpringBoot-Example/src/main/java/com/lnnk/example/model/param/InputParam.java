package com.lnnk.example.model.param;

import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * @author Lnnk
 * @date 2019-07-03 22:56
 **/
@Data
public class InputParam implements Serializable {

    @NotNull(message = "活动ID不能为NULL", groups = {ParameterGroup1.class, ParameterGroup2.class})
    @Min(message = "活动ID要大于0", value = 1, groups = {
            ParameterGroup1.class, ParameterGroup2.class})
    private Long activityId;

    @NotEmpty(message = "名称不能为空，不去除空格校验", groups = {ParameterGroup1.class, ParameterGroup2.class})
    private String name;

    @NotNull(message = "年龄不能为null", groups = {ParameterGroup1.class})
    @Min(message = "年龄要大于0", value = 1, groups = {ParameterGroup1.class})
    @Max(message = "年龄要小于120", value = 120, groups = {ParameterGroup1.class})
    private Long age;

    @NotBlank(message = "地址不能为空,去除空格校验", groups = {ParameterGroup1.class})
    private String address;

    /**
     * 验证组1
     */
    public interface ParameterGroup1 {
    }

    /**
     * 验证组2
     */
    public interface ParameterGroup2 {
    }
}
