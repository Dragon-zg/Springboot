package com.lnnk.mybatis.model.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 通用枚举注入演示，注意需要实现 IEnums 也需要扫描枚举包
 *
 * @author Lnnk
 * @date 2019-06-22 22:04
 */
public enum GenderEnum implements IEnum<Integer> {
    /**
     * 男性
     */
    MALE(1, "男性"),
    /**
     * 女性
     */
    FEMALE(2, "女性");

    private Integer value;
    private String desc;

    GenderEnum(final Integer value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    @JsonCreator
    public static GenderEnum getUseType(Integer value) {
        for (GenderEnum item : values()) {
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