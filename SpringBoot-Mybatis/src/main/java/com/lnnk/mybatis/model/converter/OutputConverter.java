package com.lnnk.mybatis.model.converter;


import org.springframework.util.Assert;

/**
 * Converter interface for output DTO.
 *
 * <b>The implementation type must be equal to DTO type</b>
 *
 * @param <DTO>    the implementation class type
 * @param <DOMAIN> doamin type
 * @author Lnnk
 */
public interface OutputConverter<DTO extends OutputConverter<DTO, DOMAIN>, DOMAIN> {

    /**
     * Convert from entity.(shallow)
     *
     * @param domain entity data
     * @return converted dto data
     */
    @SuppressWarnings("unchecked")
    default <T extends DTO> T convertFrom(DOMAIN domain) {
        Assert.notNull(domain, "domain object must not be null");

        org.springframework.beans.BeanUtils.copyProperties(domain, this);
        return (T) this;
    }
}
