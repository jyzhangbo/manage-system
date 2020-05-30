package com.github.managesystem.config.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;
import org.nutz.lang.Lang;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 使用aop来处理请求返回和异常日志.
 *
 * @author zhangbo
 */
@Aspect
@Component
@Slf4j
public class LogHandler {

    /**
     * 定义请求拦截规则
     */
    @Pointcut("execution(* com.github.managesystem.controller..*.*(..))")
    public void controllerMethodPointcut() {
    }


    @Pointcut("execution(* com.github.managesystem.config.advice.GlobalExceptionAdvice.*(..))")
    public void advicePointcut() {
    }

    /**
     * 打印异常返回日志.
     *
     * @param obj
     */
    @AfterReturning(returning = "obj", pointcut = "advicePointcut()")
    public void doAfterReturning(Object obj) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        log.error(Lang.getIP(request) + "@" + request.getRequestURI() + "异常返回：{}", Json.toJson(obj, JsonFormat.tidy()));
    }

    /**
     * @param joinPoint
     * @return java.lang.Object
     * @throws Throwable controller拦截日志
     * @author Yangjy
     * @date 2017/12/12
     */
    @Around("controllerMethodPointcut()")
    public Object action1(ProceedingJoinPoint joinPoint) throws Throwable {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        if (joinPoint.getArgs().length >= 1) {
            String reqStr = Json.toJson(joinPoint.getArgs()[0], JsonFormat.tidy());
            log.info(Lang.getIP(request) + "@" + request.getRequestURI() + "请求参数：{}", reqStr.substring(0, reqStr.length() > 500 ? 500 : reqStr.length()));
        }

        //方法执行开始时间
        long start = System.currentTimeMillis();
        //方法执行
        Object result = joinPoint.proceed();
        //方法执行结束时间
        long end = System.currentTimeMillis();

        log.info(Lang.getIP(request) + "@" + request.getRequestURI() + "请求耗时：{}", (end - start) + "ms");
        String resultStr = Json.toJson(result, JsonFormat.tidy());
        log.info(Lang.getIP(request) + "@" + request.getRequestURI() + "正常返回：{}", resultStr.substring(0, resultStr.length() > 500 ? 500 : resultStr.length()));
        return result;

    }

}
