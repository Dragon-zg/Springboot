package com.lnnk.example.aspect;

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

    @Pointcut("execution(public * com.lnnk.example.controller.TestController.aspect(..))")
    public void index_log() {
    }

    /**
     * 记录HTTP请求结束时的日志
     */
    @Before("index_log()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        log.info(">>>>>>>>>>Before");
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("PATH : " + request.getServletPath());
        log.info("METHOD : " + request.getMethod());
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "obj", pointcut = "index_log()")
    public void doAfterReturning(Object obj) throws Throwable {
        //处理完请求，返回内容
        log.info("AfterReturning >>>>>>>>>> RESPONSE: {}", JsonUtils.toJson(obj));
    }

    @AfterThrowing(value = "index_log()", throwing = "exception")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        //目标方法名：
        log.info(">>>>>>>>>>AfterThrowing");
        log.info(joinPoint.getSignature().getName());
        if (exception instanceof NullPointerException) {
            log.info("发生了空指针异常!!!!!");
        } else {
            log.info("发生了未知异常!!!!!");
        }
    }

    @Around(value = "index_log()")
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
            log.info("Around >>>>>>>>>> 方法名: {} 是否成功: {} 执行时间: {}ms", name, result, endTime - startTime);
        }
    }
}
