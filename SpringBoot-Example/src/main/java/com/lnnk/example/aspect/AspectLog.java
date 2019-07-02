package com.lnnk.example.aspect;

import cn.hutool.extra.servlet.ServletUtil;
import com.lnnk.web.constant.SymbolConsts;
import com.lnnk.web.util.JsonUtils;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * AOP日志切面编程
 *
 * @author lnnk
 * @date 2019/7/2 16:47
 **/
@Aspect
@Component
@Log4j2
public class AspectLog {

    /**
     * 切点
     */
    @Pointcut("execution(public * com.lnnk.example.controller.TestController.aspect(..))")
    public void indexLog() {
    }

    /**
     * 记录HTTP请求结束时的日志
     */
    @Before("indexLog()")
    public void doBefore(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (null != attributes) {
            HttpServletRequest request = attributes.getRequest();
            log.info("Before >>>>>>>>>> URL: {}, HTTP_METHOD: {}, IP: {}, PATH: {}, METHOD: {}, CLASS_METHOD: {}, ARGS: {}",
                    request.getRequestURL().toString(),
                    request.getMethod(),
                    ServletUtil.getClientIP(request),
                    request.getServletPath(),
                    request.getMethod(),
                    joinPoint.getSignature().getDeclaringTypeName() + SymbolConsts.SYMBOL_PERIOD_HALF + joinPoint.getSignature().getName(),
                    Arrays.toString(joinPoint.getArgs()));
        }
    }

    @AfterReturning(returning = "obj", pointcut = "indexLog()")
    public void doAfterReturning(Object obj) {
        //处理完请求，返回内容
        log.info("AfterReturning >>>>>>>>>> RESPONSE: {}", JsonUtils.toJson(obj));
    }

    @AfterThrowing(value = "indexLog()", throwing = "exception")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        String exMsg;
        if (exception instanceof NullPointerException) {
            exMsg = "发生了空指针异常!";
        } else {
            exMsg = "发生了未知异常!";
        }
        log.info("AfterThrowing >>>>>>>>>> CLASS_METHOD: {}, exMsg: {}",
                joinPoint.getSignature().getName(), exMsg);
    }

    @Around(value = "indexLog()")
    public Object doAroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        String name = "-";
        String result = "Y";
        try {
            name = pjp.getSignature().toShortString();
            return pjp.proceed();
        } catch (Throwable throwable) {
            result = "N";
            throw throwable;
        } finally {
            long endTime = System.currentTimeMillis();
            log.info("Around >>>>>>>>>> 方法名: {}, 是否成功: {}, 执行时间: {}ms",
                    name, result, endTime - startTime);
        }
    }
}
