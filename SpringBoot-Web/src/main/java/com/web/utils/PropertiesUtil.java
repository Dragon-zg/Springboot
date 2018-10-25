package com.web.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 获取properties文件信息
 *
 * @Author Dragon-zg
 * @Date 2018/8/14 9:11
 **/
@Component
public class PropertiesUtil {

    private final static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    private static PropertiesUtil propertiesUtil;

    @Autowired
    private Environment environment;

    @PostConstruct
    private void init() {
        propertiesUtil = this;
        propertiesUtil.environment = this.environment;
    }

    public static String getPropertiesValue(String propertiesPrefix) {
        String propertiesValue = propertiesUtil.environment.getProperty(propertiesPrefix);
        logger.debug("{}: {}", propertiesPrefix, propertiesValue);
        return propertiesValue;
    }
}
