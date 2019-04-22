package com.web.config;

import com.common.enums.DateFormat;
import com.common.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 项目启动时初始化一些参数信息
 * @author Dragon-zg
 * @date 2019/1/10 15:04
 */
@Component
public class InitRunner implements CommandLineRunner {
    private final static Logger logger = LoggerFactory.getLogger(InitRunner.class);


    @Override
    public void run(String... args) throws Exception {
        //打印当前服务器时间
        logger.info("当前项目启动时间是: {}", DateUtil.getNowStr(DateFormat.CHINA_TIME));
    }

}
