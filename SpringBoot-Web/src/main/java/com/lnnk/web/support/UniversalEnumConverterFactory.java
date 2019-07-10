package com.lnnk.web.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * 通用字符串转枚举工厂类
 *
 * @author lnnk
 * @date 2019/7/10 9:57
 **/
public class UniversalEnumConverterFactory implements ConverterFactory<String, IConvertEnum> {

    private static final Map<Class<?>, Converter> CONVERT_MAP = new ConcurrentHashMap<>();

    @Override
    public <T extends IConvertEnum> Converter<String, T> getConverter(Class<T> targetType) {
        Assert.notNull(targetType, "targetType must not be null");
        Assert.isTrue(targetType.isEnum(), "targetType must be an enum type");
        return Optional.ofNullable(CONVERT_MAP.get(targetType))
                .orElseGet(() -> CONVERT_MAP.putIfAbsent(targetType, new StringToEnumConvert(targetType)));
    }

    private class StringToEnumConvert<T extends IConvertEnum> implements Converter<String, T> {
        private final Class<T> enumType;

        public StringToEnumConvert(Class<T> enumType) {
            this.enumType = enumType;
        }

        @Override
        public T convert(String source) {
            Assert.notNull(source, "value must not be null");

            return Stream.of(enumType.getEnumConstants())
                    .filter(item -> String.valueOf(item.getValue()).equals(source))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("UniversalEnumConverterFactory error, enum " + enumType.getSimpleName() + " not have value: " + source));
        }
    }
}
