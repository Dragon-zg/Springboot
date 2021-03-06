package com.lnnk;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.lnnk.jpa.repository.base.impl.BaseRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Lnnk
 * @date 2018/8/28 19:44
 */
@SpringBootApplication
@EnableSpringDataWebSupport
@EnableTransactionManagement
@EnableCaching
@EntityScan(basePackages = {"com.lnnk.jpa.model.entity"})
@EnableJpaRepositories(basePackages = "com.lnnk.jpa.repository", repositoryBaseClass = BaseRepositoryImpl.class)
@PropertySource("classpath:env/${spring.profiles.active}/application.properties")
public class JpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    @Bean
    public Hibernate5Module hibernate5Module() {
        return new Hibernate5Module();
    }
}
