package com.web.utils;

import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

/**
 * @Description: 上传文件校验文件规则类 <br/>
 * application.properties中设置上传文件的最大值
 */
public interface ValidFileRule {

    static final long IMAGE_SIZE = 2 * 1024 * 1024;//上传图片最多2M

    static final long COMPRESS_SIZE = 5 * 1024 * 1024;//上传压缩文件最多5M

    static final List<String> IMAGE_TYPE = Arrays.asList("image/gif", "image/png", "image/bmp", "image/jpg", "image/jpeg");//上传图片格式

    static final List<String> COMPRESS_TYPE = Arrays.asList("application/octet-stream", "application/zip");//上传文件格式

    static final List<String> EXCEL_TYPE = Arrays.asList("application/vnd.ms-excel", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");//上传文件格式

    /**
     * 校验规则
     *
     * @param multipartFile
     * @throws Exception
     */
    void validFile(MultipartFile multipartFile) throws Exception;
}
