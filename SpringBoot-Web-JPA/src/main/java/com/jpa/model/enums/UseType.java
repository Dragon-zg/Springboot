package com.jpa.model.enums;

/**
 * 使用状态枚举类
 *
 * @author wangqiang
 * @date 2019/6/11 15:33
 */
public enum UseType implements ValueEnum<Integer> {
    /** 启用 */
    Enable("启用",1),
    /** 禁用 */
    Disable("禁用", 2)
    ;

    private final String desc;
    private final Integer value;

    UseType(String desc, Integer value) {
        this.desc = desc;
        this.value = value;
    }

    /**
     * Gets enum value.
     *
     * @return enum value
     */
    @Override
    public Integer getValue() {
        return value;
    }
}
