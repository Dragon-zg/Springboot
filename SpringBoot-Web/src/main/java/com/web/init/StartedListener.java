package com.web.init;

import com.web.enums.DateFormat;
import com.web.util.DateUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 项目启动时初始化
 *
 * @author Dragon-zg
 * @date 2019/1/10 15:04
 */
@Log4j2
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class StartedListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        //初始化参数
        //打印当前服务器时间
        log.info("当前项目启动时间是: {}", DateUtils.getNowStr(DateFormat.CHINA_TIME));
    }
}
