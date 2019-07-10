package com.lnnk.mybatis.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.lnnk.web.support.IConvertEnum;

/**
 * 使用状态枚举类
 *
 * @author Lnnk
 * @date 2019/6/11 15:33
 */
public enum UseType implements IConvertEnum<Integer> {
    /**
     * 启用
     */
    Enable(1, "启用"),
    /**
     * 禁用
     */
    Disable(2, "禁用");

    @EnumValue
    private final Integer value;

    private final String desc;


    UseType(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @JsonCreator
    public static UseType getUseType(Integer value) {
        for (UseType item : values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }

    @JsonValue
    @Override
    public Integer getValue() {
        return value;
    }
}