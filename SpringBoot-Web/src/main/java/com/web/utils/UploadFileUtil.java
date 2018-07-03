package com.web.utils;

import com.web.enums.ExceptionCode;
import com.web.exception.BusiException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description: 上传文件工具类 <br/>
 */
@Component
public class UploadFileUtil {

    private final static Logger logger = LoggerFactory.getLogger(UploadFileUtil.class);

    /**
     * 上传图片 默认最大2M 默认{"image/gif", "image/png", "image/bmp", "image/jpg", "image/jpeg"}
     *
     * @param multipartFile
     * @param propertyId    附件类型配置信息ID
     * @return 上传文件对应attachments表ID
     * @throws Exception
     */
    public static String uploadImage(MultipartFile multipartFile, String propertyId) throws Exception {
        return UploadFileUtil.uploadFile(multipartFile, propertyId, new ValidFileRule() {
            @Override
            public void validFile(MultipartFile multipartFile) throws Exception {
                String fileName = multipartFile.getOriginalFilename();
                if (StringUtils.isBlank(fileName)) {
                    logger.warn("上传文件不存在!");
                    throw new BusiException(ExceptionCode.UPLOAD_FILE_UNEXIST);
                }
                //校验文件类型是否正确
                if (!ValidFileRule.IMAGE_TYPE.contains(multipartFile.getContentType())) {
                    logger.warn("上传文件类型不正确!");
                    throw new BusiException(ExceptionCode.UPLOAD_FORMAL_ERROR);
                }

                //校验文件大小是否过大
                if (multipartFile.getSize() > ValidFileRule.IMAGE_SIZE) {
                    logger.warn("文件大小超过限制!");
                    throw new BusiException(ExceptionCode.UPLOAD_SIZE_ERROR);
                }
            }
        });
    }

    /**
     * 上传压缩文件 默认最大5M 默认{"application/octet-stream","application/zip"}
     *
     * @param multipartFile
     * @param propertyId    附件类型配置信息ID
     * @return 上传文件对应attachments表ID
     * @throws Exception
     */
    public static String uploadCompress(MultipartFile multipartFile, String propertyId) throws Exception {
        return UploadFileUtil.uploadFile(multipartFile, propertyId, new ValidFileRule() {
            @Override
            public void validFile(MultipartFile multipartFile) throws Exception {
                String fileName = multipartFile.getOriginalFilename();
                if (StringUtils.isBlank(fileName)) {
                    logger.warn("上传文件不存在!");
                    throw new BusiException(ExceptionCode.UPLOAD_FILE_UNEXIST);
                }
                //校验文件类型是否正确
                if (!ValidFileRule.COMPRESS_TYPE.contains(multipartFile.getContentType())) {
                    logger.warn("上传文件类型不正确!");
                    throw new BusiException(ExceptionCode.UPLOAD_FORMAL_ERROR);
                }

                if (!fileName.matches(".*\\.zip") && !fileName.matches(".*\\.rar")) {
                    throw new BusiException(ExceptionCode.UPLOAD_FORMAL_ERROR);
                }

                //校验文件大小是否过大
                if (multipartFile.getSize() > ValidFileRule.COMPRESS_SIZE) {
                    logger.warn("文件大小超过限制!");
                    throw new BusiException(ExceptionCode.UPLOAD_SIZE_ERROR);
                }
            }
        });
    }

    /**
     * 上传Excel文件 默认{"application/vnd.ms-excel", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"}
     *
     * @param multipartFile
     * @param propertyId    附件类型配置信息ID
     * @return 上传文件对应attachments表ID
     * @throws Exception
     */
    public static String uploadExcel(MultipartFile multipartFile, String propertyId) throws Exception {
        return UploadFileUtil.uploadFile(multipartFile, propertyId, new ValidFileRule() {
            @Override
            public void validFile(MultipartFile multipartFile) throws Exception {
                String fileName = multipartFile.getOriginalFilename();
                if (StringUtils.isBlank(fileName)) {
                    logger.warn("上传文件不存在!");
                    throw new BusiException(ExceptionCode.UPLOAD_FILE_UNEXIST);
                }
                //校验文件类型是否正确
                if (!ValidFileRule.EXCEL_TYPE.contains(multipartFile.getContentType())) {
                    logger.warn("上传文件类型不正确!");
                    throw new BusiException(ExceptionCode.UPLOAD_FORMAL_ERROR);
                }
            }
        });
    }

    /**
     * 上传文件
     *
     * @param multipartFile
     * @param validFileRule
     * @return 上传文件对应attachments表ID
     * @throws Exception
     */
    private static String uploadFile(MultipartFile multipartFile, String propertyId, ValidFileRule validFileRule) throws Exception {
        //校验文件格式
        validFileRule.validFile(multipartFile);
        //TODO 上传文件
        return null;
    }
}
