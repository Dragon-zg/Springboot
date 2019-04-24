package com.web.enums;

/**
 * 时间格式枚举类
 *
 * @author Dragon-zg
 * @date 2018/8/10 16:40
 */
public enum DateFormat {
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    DEFAULT_TIME("yyyy-MM-dd HH:mm:ss"),
    /**
     * yyyy-MM-dd
     */
    DEFAULT_DAY("yyyy-MM-dd"),
    /**
     * yyyy年MM月dd日 HH时mm分ss秒
     */
    CHINA_TIME("yyyy年MM月dd日 HH时mm分ss秒"),
    /**
     * yyyy年MM月dd日
     */
    CHINA_DAY("yyyy年MM月dd日")
    
    ;
    private String format;

    DateFormat(String format) {
        this.format = format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}
