package com.lnnk.jpa.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 使用状态枚举类
 *
 * @author Lnnk
 * @date 2019/6/11 15:33
 */
public enum UseType implements ValueEnum<Integer> {
    /**
     * 启用
     */
    Enable("启用", 1),
    /**
     * 禁用
     */
    Disable("禁用", 2);

    private final String desc;
    private final Integer value;

    UseType(String desc, Integer value) {
        this.desc = desc;
        this.value = value;
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

    /**
     * Gets enum value.
     *
     * @return enum value
     */
    @JsonValue
    @Override
    public Integer getValue() {
        return value;
    }
}