package com.lnnk.web.support;

/**
 * support convert for String to enum
 *
 * @author lnnk
 * @date 2019/7/10 14:28
 */
public interface IConvertEnum<T> {
    /**
     * Gets enum value.
     *
     * @return enum value
     */
    T getValue();
}
