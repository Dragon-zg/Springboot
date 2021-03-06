package com.lnnk.web.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * JSON 工具类
 * @author Lnnk
 * @date 2018/8/15 13:18
 **/
@Log4j2
public class JsonUtils {

    private static ObjectMapper objectMapper;

    private static ObjectMapper objectMapper() {
        if(objectMapper != null) {
            return objectMapper;
        }
        try {
            objectMapper = SpringContextUtils.getBean(ObjectMapper.class);
        } catch (Exception e) {
            final Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
            builder.featuresToDisable(
                    com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS,
                    com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
            );
            builder.serializationInclusion(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL);
            objectMapper = builder.build();
        }
        return objectMapper;
    }

    public static String toJson(Object value) {
        try {
            return objectMapper().writeValueAsString(value);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public static String toJson(Object value, Class<?> jsonView) {
        try {
            objectMapper = objectMapper();
            objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
            String json = objectMapper.writerWithView(jsonView).writeValueAsString(value);
            objectMapper.enable(MapperFeature.DEFAULT_VIEW_INCLUSION);
            return json;
        } catch (Exception e) {
            log.warn(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static <T> T fromJson(String json, Class<T> valueType) {
        try {
            return objectMapper().readValue(json, valueType);
        } catch (Exception e) {
            log.warn(e.getMessage() + ", json : " + json);
            throw new RuntimeException(e);
        }

    }

    public static <T> T fromJson(String json, TypeReference<T> typeReference) {
        try {
            return objectMapper().readValue(json, typeReference);
        } catch (Exception e) {
            log.warn(e.getMessage() + ", json : " + json);
            throw new RuntimeException(e);
        }

    }

    public static <T> T fromJson(String json, JavaType javaType) {
        try {
            return objectMapper().readValue(json, javaType);
        } catch (Exception e) {
            log.warn(e.getMessage() + ", json : " + json);
            throw new RuntimeException(e);
        }
    }
}
