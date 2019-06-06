package com.jpa.model.dto.base;


/**
 * Converter interface for output DTO.
 *
 * <b>The implementation type must be equal to DTO type</b>
 *
 * @param <DTO>    the implementation class type
 * @param <DOMAIN> doamin type
 * @author Dragon-zg
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


        return (T) this;
    }
}
