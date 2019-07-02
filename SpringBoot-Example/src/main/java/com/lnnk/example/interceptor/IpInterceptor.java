package com.lnnk.example.interceptor;

import cn.hutool.extra.servlet.ServletUtil;
import com.google.common.collect.Lists;
import com.lnnk.example.annotation.IpStint;
import com.lnnk.web.constant.PropertiesKeyConsts;
import com.lnnk.web.constant.SymbolConsts;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Lnnk
 * @date 2018/10/25 14:25
 **/
@Log4j2
public class IpInterceptor implements HandlerInterceptor {

    public IpInterceptor(Environment environment) {
        this.environment = environment;
    }

    private final Environment environment;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod) {
            IpStint ipFilter = ((HandlerMethod) handler).getMethodAnnotation(IpStint.class);
            if (ipFilter == null) {
                return true;
            }

            //获取请求主机IP地址
            String ipAddress = ServletUtil.getClientIP(request);
            //黑名单
            List<String> denyIpList = null;
            //白名单
            List<String> allowIpList = null;

            if (ArrayUtils.isEmpty(ipFilter.denyIp())) {
                //读取默认配置
                String denyIps = environment.getProperty(PropertiesKeyConsts.IP_INTERCEPTION_DEFAULT_DENY);
                if (log.isDebugEnabled()) {
                    log.debug("denyIps: {}", denyIps);
                }
                if (StringUtils.isNotBlank(denyIps)) {
                    denyIpList = Lists.newArrayList(denyIps.trim().split(SymbolConsts.SYMBOL_COMMA_HALF));
                }
            } else {
                denyIpList = Lists.newArrayList(ipFilter.denyIp());
            }

            if (ArrayUtils.isEmpty(ipFilter.allowIp())) {
                //读取默认配置
                String allowIps = environment.getProperty(PropertiesKeyConsts.IP_INTERCEPTION_DEFAULT_ALLOW);
                if (log.isDebugEnabled()) {
                    log.debug("allowIps: {}", allowIps);
                }
                if (StringUtils.isNotBlank(allowIps)) {
                    allowIpList = Lists.newArrayList(allowIps.trim().split(SymbolConsts.SYMBOL_COMMA_HALF));
                }
            } else {
                allowIpList = Lists.newArrayList(ipFilter.allowIp());
            }

            //设置了黑名单,黑名单内ip不给通过
            if (CollectionUtils.isNotEmpty(denyIpList)) {
                if (denyIpList.contains(ipAddress)) {
                    log.warn("denyIpList: {} ,IP: {} 被拦截!", denyIpList, ipAddress);
                    response.setStatus(500);
                    return false;
                }
            }

            //设置了白名单,  只有白名单内的ip给通过
            if (CollectionUtils.isNotEmpty(allowIpList)) {
                if (allowIpList.contains(ipAddress)) {
                    log.debug("allowIpList: {} ,IP: {} 被放行!", allowIpList, ipAddress);
                    return true;
                } else {
                    log.warn("allowIpList: {} ,IP: {} 没有放行权利!", allowIpList, ipAddress);
                    response.setStatus(500);
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
