package org.group35workingproject.configuration;


import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class LogConfiguration {

    @Pointcut("execution (public * org.group35workingproject.controller.*.*(..))")
    public void logForController() {}


    @Pointcut("execution (public * org.group35workingproject.service.*.*(..))")
    public void serviceLog(){}

    @Before("logForController()")
    public void beforeUsingAnyController(JoinPoint point) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        log.info("""
                RECEIVED REQUEST:
                IP:{}
                HTTP METHOD : {}
                URL : {}
                """,
                request.getRemoteAddr(),
                request.getMethod(),
                request.getRequestURL().toString());
    }


    @Before("serviceLog()")
    public void doBeforeServiceLog(JoinPoint point) {
        log.info(" RUN SERVICE: \n SERVICE METHOD: {}.{}",
                point.getSignature().getDeclaringTypeName(), point.getSignature().getName() );
    }


    @AfterReturning(returning = "returnObject", pointcut = "logForController()")
    public void doAfterReturn(Object returnObject) {
        log.info("Return value: {} \n END OF REQUEST!", returnObject);
    }

    @AfterThrowing(throwing = "e", pointcut = "logForController()")
    public void throwException(JoinPoint point, Exception e) {
        log.error("Request throw an exception. Cause - {}.{}",
                Arrays.toString(point.getArgs()), e.getMessage());
    }


}
