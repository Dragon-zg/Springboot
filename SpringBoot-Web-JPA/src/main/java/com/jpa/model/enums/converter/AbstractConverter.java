package com.jpa.model.enums.converter;

import com.jpa.model.enums.ValueEnum;
import lombok.extern.log4j.Log4j2;

import javax.persistence.AttributeConverter;

/**
 * Abstract converter.
 *
 * @param <E> enum generic
 * @param <V> value generic
 * @author johnniang
 * @date 12/6/18
 */
@Log4j2
public abstract class AbstractConverter<E extends ValueEnum<V>, V> implements AttributeConverter<E, V> {

    private final Class<E> clazz;

    protected AbstractConverter(Class<E> clazz) {
        this.clazz = clazz;
    }

    @Override
    public V convertToDatabaseColumn(E attribute) {
        log.debug("attribute: {}", attribute.getValue());
        return attribute == null ? null : attribute.getValue();
    }

    @Override
    public E convertToEntityAttribute(V dbData) {
        return dbData == null ? null : ValueEnum.valueToEnum(clazz, dbData);
    }
}
