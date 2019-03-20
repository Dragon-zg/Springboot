package com.common.utils;

import com.common.enums.DateFormat;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @Description 时间工具类
 * @Author Dragon-zg
 * @Date 2018/7/31 9:03
 **/
public class DateUtil {

    private final static Logger logger = LoggerFactory.getLogger(DateUtil.class);

    /**
     * 获取当前时间
     * @return
     */
    public static Date getNowDate() {
        return DateTime.now().toDate();
    }

    /**
     * 获取当前时间 Str类型
     * @return java.lang.String
     */  
    public static String getNowStr(DateFormat dateFormat) {
        return DateTime.now().toString(dateFormat.getFormat());
    }

    /**
     * 格式化指定日期
     * @param dateFormat
     * @param dateFormat
     * @param date
     * @return
     */
    public static String formatDate(DateFormat dateFormat, Date date) {
        return new DateTime(date).toString(dateFormat.getFormat());
    }

    /**
     * 字符串转化成日期
     * @param dateFormat
     * @param dateStr
     * @return
     */
    public static Date parseDate(DateFormat dateFormat, String dateStr) {
        DateTime dateTime = DateTimeFormat.forPattern(dateFormat.getFormat()).parseDateTime(dateStr);
        return dateTime.toDate();
    }
}
