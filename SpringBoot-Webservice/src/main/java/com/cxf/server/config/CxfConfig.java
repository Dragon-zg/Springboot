package com.cxf.server.config;

import com.cxf.server.interceptor.ArtifactOutInterceptor;
import com.cxf.server.interceptor.PostInvokeInterceptor;
import com.cxf.server.interceptor.PreInvokeInterceptor;
import com.cxf.server.service.UserService;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 * CXF 配置类
 * @date: 2018-06-03 14:45
 * @author: Dragon-zg
 */
@Configuration
@SuppressWarnings("unchecked")
public class CxfConfig {

    private final Bus bus;

    private final UserService userService;

    public CxfConfig(Bus bus, UserService userService) {
        this.bus = bus;
        this.userService = userService;
    }

    /**
     * @description 设置cxfServlet根路径
     * @author Dragon-zg
     * @date 2018/6/3 15:59
     * @return org.springframework.boot.web.servlet.ServletRegistrationBean
     */
    @Bean(name = "cxfServlet")
    public ServletRegistrationBean cxfServletRegistrationBean() {
        CXFServlet servlet = new CXFServlet();
        ServletRegistrationBean bean = new ServletRegistrationBean(servlet,"/cxf/*");
        bean.setLoadOnStartup(1);
        return bean;
    }

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, userService);
        //接口发布在 /app 目录下
        endpoint.publish("/app");
        //方法前置拦截器
        endpoint.getInInterceptors().add(new PreInvokeInterceptor());
        //方法后置拦截器
        endpoint.getInInterceptors().add(new PostInvokeInterceptor());
        //在流关闭之前拦截器
        endpoint.getOutInterceptors().add(new ArtifactOutInterceptor());
        return endpoint;
    }
}
