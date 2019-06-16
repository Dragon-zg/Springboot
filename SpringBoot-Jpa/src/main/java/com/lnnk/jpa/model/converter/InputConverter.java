package com.lnnk.jpa.model.converter;

import org.springframework.util.Assert;

/**
 * Converter interface for input DTO.
 *
 * @author Lnnk
 */
public interface InputConverter<DOMAIN> {

    /**
     * Update a domain by dto.
     *
     * @param domain updated domain
     */
    default void convertTo(DOMAIN domain) {
        Assert.notNull(domain, "domain object must not be null");

        org.springframework.beans.BeanUtils.copyProperties(this, domain);
    }
}
