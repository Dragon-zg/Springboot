package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 可用apache-CXF或wsdl2java根据wsdl逆向生成相关代码
 * https://www.cnblogs.com/ChrisMurphy/p/5224160.html
 * @author Dragon-zg
 * @date 2018/8/16 13:25
 */
@SpringBootApplication
public class WebServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebServiceApplication.class, args);
    }
}
