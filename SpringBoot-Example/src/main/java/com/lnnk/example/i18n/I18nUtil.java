package com.lnnk.example.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @description i18N工具类
 * @author Lnnk
 * @date 2018/7/7 16:51
 **/
@Component
public class I18nUtil {

    @Autowired
    private I18nMessageResource i18nMessageResource;

    private static I18nUtil i18nUtil;

    @PostConstruct
    public void init() {
        i18nUtil = this;
        i18nUtil.i18nMessageResource = this.i18nMessageResource;
    }

    /**
     * @description 系统多语言翻译
     * @author Lnnk
     * @date 2018/7/20 13:47
     * @Param [code]
     * @return java.lang.String
     */
    public static String getMessage(String code){
        return i18nUtil.i18nMessageResource.getMessage(code, null, LocaleContextHolder.getLocale());
    }

    /**
     * @description 系统多语言翻译,加参数
     * @author Lnnk
     * @date 2018/7/20 13:47
     * @Param [code, args]
     * @return java.lang.String
     */
    public static String getMessage(String code, Object... args){
        return i18nUtil.i18nMessageResource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
