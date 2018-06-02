package com.jpa.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description: 项目启动时读取配置文件的配置信息
 * @Date: 2017-12-16 19:29
 */
@Component
@ConfigurationProperties(prefix = "girl") // 将配置文件girl前缀的配置写入此类中
public class GirlProperties {
    private String cupSize;
    private Integer age;

    public String getCupSize() {
        return cupSize;
    }

    public void setCupSize(String cupSize) {
        this.cupSize = cupSize;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
