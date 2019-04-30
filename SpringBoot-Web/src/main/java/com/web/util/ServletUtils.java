package com.web.util;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.net.HttpHeaders;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.Validate;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Http与Servlet工具类.
 * @author Dragon-zg
 * @date 2019/4/30 16:29
 **/
@Log4j2
public class ServletUtils {
    /**
     * 常用数值定义
     */
    public static final long ONE_YEAR_SECONDS = 60 * 60 * 24 * 365;
    /**
     * true集合
     */
    private static final Set<String> TRUE_VALUES = Sets.newHashSet("true", "on", "yes");
    /**
     * false集合
     */
    private static final Set<String> FALSE_VALUES = Sets.newHashSet("false", "off", "no");

    /**
     * 设置客户端缓存过期时间 的Header.
     */
    public static void setExpiresHeader(HttpServletResponse response, long expiresSeconds) {
        // Http 1.0 header, set a fix expires date.
        response.setDateHeader(HttpHeaders.EXPIRES, System.currentTimeMillis() + (expiresSeconds * 1000));
        // Http 1.1 header, set a time after now.
        response.setHeader(HttpHeaders.CACHE_CONTROL, "max-age=" + expiresSeconds);
    }

    /**
     * 设置禁止客户端缓存的Header.
     */
    public static void setNoCacheHeader(HttpServletResponse response) {
        // Http 1.0 header
        response.setDateHeader(HttpHeaders.EXPIRES, 1L);
        response.addHeader(HttpHeaders.PRAGMA, "no-cache");
        // Http 1.1 header
        response.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, max-age=0");
    }

    /**
     * 取得带相同前缀的Request Parameters, copy from spring WebUtils.
     *
     * @return Parameter名已去除前缀.
     */
    public static Map<String, Object> getParametersStartingWith(WebRequest request, String prefix) {
        log.debug("prefix = " + prefix);
        Validate.notNull(request, "Request must not be null");
        Iterator<String> paramNames = request.getParameterNames();
        Map<String, Object> params = Maps.newTreeMap();
        if (prefix == null) {
            prefix = "";
        }
        while ((paramNames != null) && paramNames.hasNext()) {
            String paramName = paramNames.next();
            log.debug("paramName = " + paramName);
            if ("".equals(prefix) || paramName.startsWith(prefix)) {
                String unprefixed = paramName.substring(prefix.length());
                String[] values = request.getParameterValues(paramName);
                if (values != null && values.length == 1) {
                    Boolean boolValue = convert(values[0]);
                    if(boolValue == null) {
                        params.put(unprefixed, values[0]);
                    } else if(boolValue) {
                        params.put(unprefixed, true);
                    } else {
                        params.put(unprefixed, false);
                    }
                } else {
                    params.put(unprefixed, values);
                }
            }
        }
        return params;
    }

    private static Boolean convert(String source) {
        if(org.apache.commons.lang3.StringUtils.isNotBlank(source)) {
            source = source.trim().toLowerCase();
            if(TRUE_VALUES.contains(source)) {
                return Boolean.TRUE;
            } else if(FALSE_VALUES.contains(source)) {
                return Boolean.FALSE;
            }
        }
        return null;
    }

    /**
     * 组合Parameters生成Query String的Parameter部分, 并在paramter name上加上prefix.
     * @see #getParametersStartingWith
     */
    public static String encodeParameterStringWithPrefix(Map<String, Object> params, String prefix) {
        if ((params == null) || (params.isEmpty())) {
            return "";
        }

        if (prefix == null) {
            prefix = "";
        }

        StringBuilder queryStringBuilder = new StringBuilder();
        Iterator<Map.Entry<String, Object>> it = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            queryStringBuilder.append(prefix).append(entry.getKey()).append('=').append(entry.getValue());
            if (it.hasNext()) {
                queryStringBuilder.append('&');
            }
        }
        return queryStringBuilder.toString();
    }
}
