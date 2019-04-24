package com.web.i18n;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import java.text.MessageFormat;
import java.util.Locale;

/**
 * @Description 多语言环境自定义MessageResource
 * @Author Dragon-zg
 * @Date 2018/7/4 14:16
 **/
public class I18nMessageResource extends AbstractMessageSource implements ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = (resourceLoader != null ? resourceLoader : new DefaultResourceLoader());
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        String message;
        //英文
        if (StringUtils.equals(locale.getLanguage(), Locale.ENGLISH.getLanguage())) {
            message = "这是英文";
        } else {//默认中文
            message = "这是中文";
        }
        MessageFormat messageFormat = new MessageFormat(message, locale);
        return messageFormat;
    }
}
