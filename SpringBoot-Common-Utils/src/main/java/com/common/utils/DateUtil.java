package com.common.utils;

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
     *  yyyy-MM-dd HH:mm:ss
     */
    public static final String FORMAT_DATE_TIME_DEFAULT="yyyy-MM-dd HH:mm:ss";
    /**
     *  yyyy-MM-dd
     */
    public static final String FORMAT_DATE_FOR_DAY="yyyy-MM-dd";
    /**
     *  yyyy年MM月dd日 HH时mm分ss秒
     */
    public static final String FORMAT_DATE_TIME_CH="yyyy年MM月dd日 HH时mm分ss秒";
    /**
     *  yyyy年MM月dd日
     */
    public static final String FORMAT_DATE_CH="yyyy年MM月dd日";

    /**
     * 获取当前时间
     * @return
     */
    public static Date getNowDate() {
        return DateTime.now().toDate();
    }

    /**
     * 格式化指定日期
     * @param format
     * @param dateTime
     * @return
     */
    public static String formatDate(String format, Date date) {
        return new DateTime(date).toString(format);
    }

    /**
     * 字符串转化成日期
     * @param format
     * @param dateStr
     * @return
     */
    public static Date parseDate(String format, String dateStr) {
        DateTime dateTime = null;
        try {
            dateTime = DateTimeFormat.forPattern(format).parseDateTime(dateStr);
        } catch (Exception e) {
            logger.error("格式转化异常: ", e);
        }
        return dateTime.toDate();
    }
}
