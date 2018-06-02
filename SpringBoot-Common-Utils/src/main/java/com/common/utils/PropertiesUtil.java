package com.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Description 读取Properties文件工具类
 * @Author Dragon-zg
 * @Date 2016-10-05 14:01
 */
public class PropertiesUtil {

	/**
	 * 读取资源文件
	 *
	 * @param fileName
	 *            资源文件名
	 * @return 资源文件
	 */
	public static Properties loadProperties(String fileName) {
		InputStream inputStream = null;
		Properties p = new Properties();
		try {
			inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
			p.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return p;
	}

	/**
	 * 根据key值读取配置的值
	 *
	 * @param fileName
	 *            资源文件名
	 * @param attrName
	 *            key值
	 * @return key 键对应的值
	 */
	public static String getPropertiesValue(String fileName, String attrName) {
		final Properties pps = loadProperties(fileName);
		final String attrValue = pps.getProperty(attrName);
		return null != attrValue ? attrValue : "";
	}

	/**
	 * 读取properties的全部信息
	 *
	 * @param fileName
	 *            资源文件名
	 * @throws IOException
	 *             关闭资源文件，或者加载配置文件错误
	 */
	public Map<String, String> readAllProperties(String fileName) throws IOException {
		// 保存所有的键值
		final Properties props = loadProperties(fileName);
		Map<String, String> map = new HashMap<String, String>();
		Enumeration en = props.propertyNames();
		while (en.hasMoreElements()) {
			String key = (String) en.nextElement();
			String Property = props.getProperty(key);
			map.put(key, Property);
		}
		return map;
	}

}
