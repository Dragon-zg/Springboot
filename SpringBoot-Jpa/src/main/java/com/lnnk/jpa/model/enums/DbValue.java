package com.lnnk.jpa.model.enums;

import org.springframework.util.Assert;

import java.util.stream.Stream;

/**
 * Interface for value enum.
 *
 * @param <T> value type
 * @author Lnnk
 */
public interface DbValue<T> {

    /**
     * Converts value to corresponding enum.
     *
     * @param enumType enum type
     * @param value    database value
     * @param <V>      value generic
     * @param <E>      enum generic
     * @return corresponding enum
     */
    static <V, E extends DbValue<V>> E valueToEnum(final Class<E> enumType, final V value) {
        Assert.notNull(enumType, "enum type must not be null");
        Assert.notNull(value, "value must not be null");
        Assert.isTrue(enumType.isEnum(), "type must be an enum type");

        return Stream.of(enumType.getEnumConstants())
                .filter(item -> item.getDbValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("unknown database value: " + value));
    }

    /**
     * Gets enum value.
     *
     * @return enum value
     */
    T getDbValue();

}
