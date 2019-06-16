package com.lnnk.mybatis.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

/**
 * MybatisPlus配置类
 *
 * @author Lnnk
 * @date 2019-06-16 20:28
 **/
@Configurable
public class MybatisPlusConfig {

    /**
     * 分页插件物理分页
     */
    @Bean
    public PaginationInterceptor getPaginationInterceptor() {
        return new PaginationInterceptor();
    }
}
