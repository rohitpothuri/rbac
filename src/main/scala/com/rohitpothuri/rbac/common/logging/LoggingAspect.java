package com.rohitpothuri.rbac.common.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.rohitpothuri.rbac..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Before method execution: " + joinPoint.getSignature());
    }

    @AfterReturning(pointcut = "execution(* com.rohitpothuri.rbac..*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("After method execution: " + joinPoint.getSignature());
        logger.info("Returned value: " + result);
    }

    @AfterThrowing(pointcut = "execution(* com.rohitpothuri.rbac..*(..))", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        logger.error("Exception thrown from method: " + joinPoint.getSignature());
        logger.error("Exception: " + exception.getMessage());
    }
}

