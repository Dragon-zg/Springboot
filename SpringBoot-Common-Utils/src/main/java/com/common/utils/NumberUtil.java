package com.common.utils;


import com.common.constants.SymbolConstant;

/**
 * 数字工具类
 *
 * @author Dragon-zg
 * @date 2019/1/5 14:26
 **/
public class NumberUtil {

    /**
     * 获取数字有多少位小数
     *
     * @param [number]
     * @return int
     * @author Dragon-zg
     * @date 2019/1/5 14:29
     */
    public static int getNumberPosition(Number number) {
        String str = String.valueOf(number);
        int index = str.indexOf(SymbolConstant.SYMBOL_POINT);
        int position = 0;
        if (index != -1) {
            position = (str.length() - (index + 1));
        }
        return position;
    }
}
