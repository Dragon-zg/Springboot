package com.lnnk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Lnnk
 * @date 2019/6/12 16:01
 */
@EnableCaching
@EnableTransactionManagement
@EntityScan(basePackages = {"com.lnnk.caffeine.entity"})
@EnableJpaRepositories(basePackages = "com.lnnk.caffeine.repository")
@SpringBootApplication
public class CaffeineApplication {

    public static void main(String[] args) {
        SpringApplication.run(CaffeineApplication.class, args);
    }
}
