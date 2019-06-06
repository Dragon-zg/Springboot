package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

/**
 * @author Dragon-zg
 * @date 2018/8/28 19:44
 */
@SpringBootApplication
@EntityScan(basePackages = {"com.jpa.model.entity"})
@EnableSpringDataWebSupport
@EnableJpaRepositories
@EnableCaching
@PropertySource("classpath:env/${spring.profiles.active}/application.properties")
public class JpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }
}
