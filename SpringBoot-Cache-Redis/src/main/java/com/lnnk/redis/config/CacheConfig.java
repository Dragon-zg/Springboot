package com.lnnk.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lnnk.web.constant.SymbolConsts;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.StringJoiner;

/**
 * 缓存配置-使用Lettuce客户端，自动注入配置的方式
 *
 * @author lnnk
 * @date 2019/6/26 14:03
 **/
@Configuration
public class CacheConfig extends CachingConfigurerSupport {

    private static String generate(Object target, Method method, Object... params) {
        StringJoiner joiner = new StringJoiner(SymbolConsts.SYMBOL_PERIOD_HALF);
        joiner.add(target.getClass().getSimpleName());
        joiner.add(method.getName());
        for (Object param : params) {
            joiner.add(param.toString());
        }
        return joiner.toString();
    }

    /**
     * 自定义缓存key的生成策略.
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return CacheConfig::generate;
    }

    /**
     * 缓存配置管理器
     */
    @Bean
    public CacheManager cacheManager(LettuceConnectionFactory factory) {
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        //解决查询缓存转换异常的问题
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        // 配置序列化（解决乱码的问题）
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                // redis中缓存超时的时间 0代表无限
                .entryTtl(Duration.ZERO)
                // 缓存统一使用cacheName
                //.prefixKeysWith("prefix::")
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
                // 不缓存null数据
                .disableCachingNullValues();

        RedisCacheManager cacheManager = RedisCacheManager.builder(factory)
                .cacheDefaults(config)
                .build();
        return cacheManager;
    }
}
