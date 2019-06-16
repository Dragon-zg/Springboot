package com.lnnk.web.init;

import com.lnnk.web.constant.PropertiesKeyConsts;
import com.lnnk.web.enums.DateFormat;
import com.lnnk.web.util.DateUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 项目启动时初始化
 *
 * @author Lnnk
 * @date 2019/1/10 15:04
 */
@Log4j2
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class StartedListener implements CommandLineRunner {

    private final Environment environment;

    public StartedListener(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void run(String... args) {
        //打印当前服务器信息
        log.info("当前项目应用名称是: {}, 启动时间是: {}",
                environment.getProperty(PropertiesKeyConsts.APP_NAME, "web app"),
                DateUtils.getNowStr(DateFormat.CHINA_TIME));
    }
}
