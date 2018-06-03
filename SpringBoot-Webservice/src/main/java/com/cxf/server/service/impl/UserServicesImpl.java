package com.cxf.server.service.impl;

import com.cxf.server.service.UserService;
import org.springframework.stereotype.Component;

import javax.jws.WebService;

/**
 * @Description:
 * @Date: 2018-06-03 15:09
 * @Author: Dragon-zg
 */
@WebService(serviceName = "userServices",//服务名
        portName = "userPort",
        endpointInterface = "com.cxf.server.service.UserService",
        targetNamespace = "http://service.server.cxf.com")//报名倒叙，并且和接口定义保持一致
@Component
public class UserServicesImpl implements UserService {
    @Override
    public String sayHello(String name) {
        return "hello , "+ name;
    }
}
