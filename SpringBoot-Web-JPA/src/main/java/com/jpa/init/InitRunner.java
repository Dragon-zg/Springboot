package com.jpa.init;

import com.web.enums.DateFormat;
import com.web.utils.DateUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 项目启动时初始化一些参数信息
 * @author Dragon-zg
 * @date 2019/1/10 15:04
 */
@Log4j2
@Component
public class InitRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        //打印当前服务器时间
        log.info("当前项目启动时间是: {}", DateUtil.getNowStr(DateFormat.CHINA_TIME));
    }

}
