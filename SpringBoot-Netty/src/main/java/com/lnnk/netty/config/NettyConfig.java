package com.lnnk.netty.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * Netty服务器配置信息
 *
 * @author Lnnk
 * @date 2021/2/28 18:16
 **/
@Component
@ConfigurationProperties(prefix = "app.netty")
public class NettyConfig {
    /**
     * IP
     */
    private String inetHost;
    /**
     * 端口
     */
    private int inetPort;

    public String getInetHost() {
        return inetHost;
    }

    public void setInetHost(String inetHost) {
        this.inetHost = inetHost;
    }

    public int getInetPort() {
        return inetPort;
    }

    public void setInetPort(int inetPort) {
        this.inetPort = inetPort;
    }
}
