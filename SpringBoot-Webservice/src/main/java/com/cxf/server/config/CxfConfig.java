package com.cxf.server.config;

import com.cxf.server.service.UserService;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.xml.ws.Endpoint;

/**
 * @Description: CXF 配置类
 * @Date: 2018-06-03 14:45
 * @Author: Qiang.Wang
 */
@Component
public class CxfConfig {
    @Autowired
    private Bus bus;

    @Autowired
    private UserService userService;

    /** 
     * @Description 设置cxfServlet根路径
     * @Author Dragon-zg 
     * @Date 2018/6/3 15:59
     * @Param []  
     * @return org.springframework.boot.web.servlet.ServletRegistrationBean  
     */  
    @Bean
    public ServletRegistrationBean dispatcherServlet() {
        return new ServletRegistrationBean(new CXFServlet(), "/*");
    }

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, userService);
        endpoint.publish("/cxf");//接口发布在 /cxf 目录下
        return endpoint;
    }
}
