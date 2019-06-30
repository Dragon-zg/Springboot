package com.lnnk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author Lnnk
 * @date 2019/6/12 16:01
 */
@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.lnnk.mongo.repository")
public class MongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoApplication.class, args);
    }
}
