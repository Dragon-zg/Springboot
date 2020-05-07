package com.lnnk.web.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 获取spring上下文,bean实例工具类
 *
 * @author Lnnk
 **/
@SuppressWarnings("unchecked")
@Component
public class SpringContextUtils implements ApplicationContextAware {
    // Spring应用上下文环境
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtils.applicationContext = applicationContext;
    }

    public static <T> T getBean(String name) throws BeansException {
        return (T) applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> cls) {
        return getBean(cls, applicationContext);
    }

    public static <T> T getBean(Class<T> cls, ApplicationContext ctx) {
        Map<String, T> map = ctx.getBeansOfType(cls);
        if (map.size() == 0) {
            return null;
        } else if (map.size() > 1) {
            new Exception("bean is not unique.").printStackTrace();
        }
        return map.values().iterator().next();
    }
}
