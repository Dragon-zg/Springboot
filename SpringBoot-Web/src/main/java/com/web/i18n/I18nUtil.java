package com.web.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Description i18N工具类
 * @Author Dragon-zg
 * @Date 2018/7/7 16:51
 **/
@Component
public class I18nUtil {

    @Autowired
    private I18nMessageResource i18NMessageResource;

    private static I18nUtil i18nUtil;

    @PostConstruct
    public void init() {
        i18nUtil = this;
        i18nUtil.i18NMessageResource = this.i18NMessageResource;
    }

    /**
     * @Description 系统多语言翻译
     * @Author Dragon-zg
     * @Date 2018/7/20 13:47
     * @Param [code]
     * @return java.lang.String
     */
    public static String getMessage(String code){
        return i18nUtil.i18NMessageResource.getMessage(code, null, LocaleContextHolder.getLocale());
    }

    /**
     * @Description 系统多语言翻译,加参数
     * @Author Dragon-zg
     * @Date 2018/7/20 13:47
     * @Param [code, args]
     * @return java.lang.String
     */
    public static String getMessage(String code, Object... args){
        return i18nUtil.i18NMessageResource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
