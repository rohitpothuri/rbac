package com.rohitpothuri.rbac.common.logging

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.stereotype.Component
import org.slf4j.{LoggerFactory,Logger}


@Aspect
@Component
class LoggingAspect {
  private final val logger = LoggerFactory.getLogger(classOf[LoggingAspect])

  @Before("execution(* com.rohitpothuri.rbac..*(..))")
  def logBefore(joinPoint: JoinPoint): Unit = {
  /*logger = LoggerFactory.getLogger(joinPoint.getTarget.getClass)*/
    logger.info("Before method execution: " + joinPoint.getSignature)
  }

  @AfterReturning(pointcut = "execution(* com.rohitpothuri.rbac..*(..))", returning = "result")
  def logAfterReturning(joinPoint: JoinPoint, result: AnyRef): Unit = {
  /*logger = LoggerFactory.getLogger(joinPoint.getTarget.getClass)*/
    logger.info("After method execution: " + joinPoint.getSignature)
    logger.debug("Returned value: " + result)
  }

  @AfterThrowing(pointcut = "execution(* com.rohitpothuri.rbac..*(..))", throwing = "exception") def logAfterThrowing(joinPoint: JoinPoint, exception: Throwable): Unit = {
    /*logger = LoggerFactory.getLogger(joinPoint.getTarget.getClass)*/
    logger.error("Exception thrown from method: " + joinPoint.getSignature)
    logger.error("Exception: " + exception.getMessage)
  }
}

