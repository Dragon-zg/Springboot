package com.web.util;

import java.util.UUID;

/**
 * @description UUID工具类
 * @author Dragon-zg
 * @date 2016-10-05 13:55
 */
public class UUIDUtils {
    /**
     * @return java.lang.String
     * @description 获取UUID
     * @author Dragon-zg
     * @date 2018/6/2 20:24
     */
    public static String getUUID() {
        String s = UUID.randomUUID().toString();
        // 去掉“-”符号
        StringBuffer uuid = new StringBuffer();
        uuid.append(s.substring(0, 8))
                .append(s.substring(9, 13))
                .append(s.substring(14, 18))
                .append(s.substring(19, 23))
                .append(s.substring(24));
        return uuid.toString();
    }
}
