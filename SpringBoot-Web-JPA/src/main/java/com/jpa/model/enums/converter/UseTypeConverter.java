package com.jpa.model.enums.converter;

import com.jpa.model.enums.UseType;

import javax.persistence.Converter;

/**
 * UseTypeConverter
 *
 * @author wangqiang
 * @date 2019/6/11 15:39
 **/
@Converter(autoApply = true)
public class UseTypeConverter extends AbstractConverter<UseType, Integer> {

    public UseTypeConverter() {
        super(UseType.class);
    }
}
