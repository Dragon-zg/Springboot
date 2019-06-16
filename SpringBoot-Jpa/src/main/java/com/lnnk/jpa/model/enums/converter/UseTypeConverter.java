package com.lnnk.jpa.model.enums.converter;

import com.lnnk.jpa.model.enums.UseType;

import javax.persistence.Converter;

/**
 * UseTypeConverter
 *
 * @author Lnnk
 * @date 2019/6/11 15:39
 **/
@Converter(autoApply = true)
public class UseTypeConverter extends AbstractConverter<UseType, Integer> {

    public UseTypeConverter() {
        super(UseType.class);
    }
}
