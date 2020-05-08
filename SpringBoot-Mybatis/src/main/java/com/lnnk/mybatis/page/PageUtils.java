package com.lnnk.mybatis.page;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.lnnk.web.enums.ExceptionCode;
import com.lnnk.web.exception.CustomizedException;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.web.context.request.WebRequest;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author: Lnnk
 **/
public class PageUtils {

    private static final Set<String> trueValues = Sets.newHashSet("true", "on", "yes");
    private static final Set<String> falseValues = Sets.newHashSet("false", "off", "no");

    /**
     * 取得带相同前缀的Request Parameters, copy from spring WebUtils.
     *
     * @return Parameter名已去除前缀.
     */
    public static Map<String, Object> getParametersStartingWith(WebRequest request, String prefix) {
        Validate.notNull(request, "Request must not be null");
        Iterator<String> paramNames = request.getParameterNames();
        Map<String, Object> params = Maps.newTreeMap();
        if (prefix == null) {
            prefix = "";
        }
        while (paramNames.hasNext()) {
            String paramName = paramNames.next();
            if ("".equals(prefix) || paramName.startsWith(prefix)) {
                String unPrefixed = paramName.substring(prefix.length());
                String[] values = request.getParameterValues(paramName);
                if (values != null && values.length == 1) {
                    Boolean boolValue = convert(values[0]);
                    if (boolValue == null) {
                        params.put(unPrefixed, values[0]);
                    } else if (boolValue) {
                        params.put(unPrefixed, true);
                    } else {
                        params.put(unPrefixed, false);
                    }
                } else {
                    params.put(unPrefixed, values);
                }
            }
        }
        return params;
    }


    public static Boolean convert(String source) {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(source)) {
            source = source.trim().toLowerCase();
            if (trueValues.contains(source)) {
                return Boolean.TRUE;
            } else if (falseValues.contains(source)) {
                return Boolean.FALSE;
            }
        }
        return null;
    }

    public static <T> QueryWrapper<T> bySearchFilter(Map<String, Object> params) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        Map<String, Object[]> betweens = null;
        String key;
        Object value;
        String column;
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
            String[] keys = key.split("_");
            if (keys.length != 2 && keys.length != 3) {
                continue;
            }
            column = StrUtil.toUnderlineCase(keys[1]);
            if (StringUtils.isBlank(column)) {
                continue;
            }
            Operator operator = Operator.valueOf(keys[0].toUpperCase());
            switch (operator) {
                case EQ: {
                    queryWrapper.eq(column, value);
                    break;
                }
                case LIKE: {
                    queryWrapper.like(column, value);
                    break;
                }
                case LLIKE: {
                    queryWrapper.likeLeft(column, value);
                    break;
                }
                case RLIKE: {
                    queryWrapper.likeRight(column, value);
                    break;
                }
                case GT: {
                    queryWrapper.gt(column, value);
                    break;
                }
                case LT: {
                    queryWrapper.lt(column, value);
                    break;
                }
                case GE: {
                    queryWrapper.ge(column, value);
                    break;
                }
                case LE: {
                    queryWrapper.le(column, value);
                    break;
                }
                case IN: {
                    queryWrapper.in(column, value);
                    break;
                }
                case NOTIN: {
                    queryWrapper.notIn(column, value);
                    break;
                }
                case ISNULL: {
                    queryWrapper.isNull(column);
                    break;
                }
                case ISNOTNULL: {
                    queryWrapper.isNotNull(column);
                    break;
                }
                case BETWEEN: {
                    if (betweens == null) {
                        betweens = Maps.newHashMap();
                    }
                    BetweenType betweenType = BetweenType.valueOf(keys[2].toUpperCase());
                    Object[] fieldArray = betweens.get(column);
                    if (fieldArray == null) {
                        fieldArray = new Object[]{null, null};
                    }
                    switch (betweenType) {
                        case LOW: {
                            fieldArray[0] = value;
                            break;
                        }
                        case HIGH: {
                            fieldArray[1] = value;
                            break;
                        }
                        default: {
                            throw new CustomizedException(ExceptionCode.PARAM_ERROR);
                        }
                    }
                    betweens.put(column, fieldArray);
                    break;
                }
                default: {
                    break;
                }
            }
        }
        if (MapUtils.isNotEmpty(betweens)) {
            for (Map.Entry<String, Object[]> entry : betweens.entrySet()) {
                if (entry.getValue().length == 2) {
                    queryWrapper.between(entry.getKey(), entry.getValue()[0], entry.getValue()[1]);
                }
            }
        }
        return queryWrapper;
    }

}
