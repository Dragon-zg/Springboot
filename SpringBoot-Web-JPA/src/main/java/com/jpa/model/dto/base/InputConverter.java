package com.jpa.model.dto.base;

/**
 * Converter interface for input DTO.
 *
 * @author Dragon-zg
 */
public interface InputConverter<DOMAIN> {

    /**
     * Convert to entity.
     *
     * @return new entity with same value(not null)
     */
    @SuppressWarnings("unchecked")
    default DOMAIN convertTo() {

        return null;
    }
}

