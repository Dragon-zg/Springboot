package com.lnnk.web.util;

import com.lnnk.web.enums.DateFormat;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @description 时间工具类
 * @author Lnnk
 * @date 2018/7/31 9:03
 **/
public class DateUtils {

    /**
     * 系统所在时区
     */
    private final static ZoneId SYSTEM_ZONEID = ZoneId.systemDefault();

    /**
     * Date转换为LocalDateTime
     *
     * @return java.time.LocalDateTime
     * @author Lnnk
     * @date 2019/4/18 19:13
     * @params [date]
     */
    public final static LocalDateTime date2LocalDateTime(Date date) {
        LocalDateTime localDateTime = date.toInstant().atZone(SYSTEM_ZONEID).toLocalDateTime();
        return localDateTime;
    }

    /**
     * LocalDateTime转换为Date
     *
     * @return java.util.Date
     * @author Lnnk
     * @date 2019/4/18 19:14
     * @params [localDateTime]
     */
    public final static Date localDateTime2Date(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(SYSTEM_ZONEID).toInstant());
    }

    /**
     * 获取当前时间
     *
     * @return java.util.Date
     * @author Lnnk
     * @date 2019/4/18 19:09
     * @params []
     */
    public final static Date getNowDate() {
        return Date.from(Instant.now());
    }

    /**
     * 获取当前时间 Str类型
     *
     * @return java.lang.String
     * @author Lnnk
     * @date 2019/4/18 19:10
     * @params [dateFormat]
     */
    public final static String getNowStr(DateFormat dateFormat) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(dateFormat.getFormat()));
    }

    /**
     * 格式化指定日期
     *
     * @return java.lang.String
     * @author Lnnk
     * @date 2019/4/18 19:10
     * @params [date, dateFormat]
     */
    public final static String formatDate(Date date, DateFormat dateFormat) {
        return LocalDateTime.ofInstant(date.toInstant(), SYSTEM_ZONEID).format(DateTimeFormatter.ofPattern(dateFormat.getFormat()));
    }

    /**
     * 字符串转化成日期
     *
     * @return java.util.Date
     * @author Lnnk
     * @date 2019/4/18 19:10
     * @params [dateStr, dateFormat]
     */
    public final static Date parseDate(String dateStr, DateFormat dateFormat) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(dateFormat.getFormat()));
        return localDateTime2Date(localDateTime);
    }
}
