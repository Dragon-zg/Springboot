package com.lnnk.mybatis.model.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.lnnk.web.support.convert.IConvertEnum;

/**
 * 通用枚举注入演示，注意需要实现 IEnums 也需要扫描枚举包
 *
 * @author Lnnk
 * @date 2019-06-22 22:04
 */
public enum GenderEnum implements IEnum<Integer>, IConvertEnum<Integer> {
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

    GenderEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @JsonCreator
    public static GenderEnum get(Integer value) {
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
