package com.lnnk.jpa.model.enums.converter;

import com.lnnk.jpa.model.enums.DbValue;
import lombok.extern.log4j.Log4j2;

import javax.persistence.AttributeConverter;

/**
 * Abstract converter.
 *
 * @param <E> enum generic
 * @param <V> value generic
 * @author Lnnk
 * @date 12/6/18
 */
@Log4j2
public abstract class AbstractConverter<E extends DbValue<V>, V> implements AttributeConverter<E, V> {

    private final Class<E> clazz;

    protected AbstractConverter(Class<E> clazz) {
        this.clazz = clazz;
    }

    @Override
    public V convertToDatabaseColumn(E attribute) {
        return attribute == null ? null : attribute.getDbValue();
    }

    @Override
    public E convertToEntityAttribute(V dbData) {
        return dbData == null ? null : DbValue.valueToEnum(clazz, dbData);
    }
}
