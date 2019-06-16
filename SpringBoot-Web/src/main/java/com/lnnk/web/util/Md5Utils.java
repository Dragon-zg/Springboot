package com.lnnk.web.util;


import lombok.extern.log4j.Log4j2;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @description MD5加密工具类
 * @author Lnnk
 * @date 2018/6/2 20:16
 * @Param
 * @return
 */
@Log4j2
public class Md5Utils {

    public Md5Utils() {
    }

    public static final String md5(String s) {
        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        MessageDigest mdInst = null;
        String result = null;
        try {
            byte[] inputBytes = s.getBytes("UTF-8");
            mdInst = MessageDigest.getInstance("md5");
            mdInst.update(inputBytes);
            byte[] md = mdInst.digest();
            int j = md.length;

            char[] str = new char[j * 2];
            int k = 0;

            for (int i = 0; i < j; ++i) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 15];
                str[k++] = hexDigits[byte0 & 15];
            }

            result = new String(str);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return result;
    }

    public synchronized static final String getMD5Str(String str) { //md5加密
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes());
        } catch (NoSuchAlgorithmException e) {
            log.error("md5 error:" + e.getMessage(), e);
        }
        byte[] byteArray = messageDigest.digest();
        StringBuffer md5StrBuff = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            } else {
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        }
        return md5StrBuff.toString();
    }

    public static void main(String[] args) {
        System.out.println(getMD5Str("123456"));
    }

}
