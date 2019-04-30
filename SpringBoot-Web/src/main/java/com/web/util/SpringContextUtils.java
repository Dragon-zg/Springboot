package com.web.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.util.Map;

/**
 *
 * @Author Dragon-zg
 * @Date 2018/8/15 13:20
 **/
@SuppressWarnings("unchecked")
public class SpringContextUtils {

    private static ApplicationContext getContext() {
        return ContextLoader.getCurrentWebApplicationContext();
    }

    public static <T> T getBean(String name) throws BeansException {
        ApplicationContext context = getContext();
        return (T) context.getBean(name);
    }

    public static <T> T getBean(Class<T> cls) {
        ApplicationContext context = getContext();
        return getBean(cls, context);
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
