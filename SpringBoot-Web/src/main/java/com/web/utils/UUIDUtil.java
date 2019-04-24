package com.web.utils;

import java.util.UUID;

/**
 * @Description UUID工具类
 * @Author Dragon-zg
 * @Date 2016-10-05 13:55
 */
public class UUIDUtil {
	/**
	 * @Description 获取UUID
	 * @Author Dragon-zg
	 * @Date 2018/6/2 20:24
	 * @Param []
	 * @return java.lang.String
	 */
	public static String getUUID() {
		String s = UUID.randomUUID().toString();
		// 去掉“-”符号
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
	}
}
