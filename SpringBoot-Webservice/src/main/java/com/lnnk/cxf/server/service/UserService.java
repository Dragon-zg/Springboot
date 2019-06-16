package com.lnnk.cxf.server.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 *
 * @date: 2018-06-03 15:05
 * @author: Lnnk
 */
@WebService(targetNamespace = "http://service.server.cxf.com")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface UserService {

    /**
     * Demo
     *
     * @param name
     * @return
     */
    @WebMethod
    @WebResult(name = "sayHelloResponse")
    String sayHello(@WebParam(name = "userName") String name);
}
