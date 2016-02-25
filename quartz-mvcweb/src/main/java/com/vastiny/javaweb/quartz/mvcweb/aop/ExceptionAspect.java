package com.vastiny.javaweb.quartz.mvcweb.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author yangzhi
 * @since 2016/2/1
 */

@Component
@Aspect
public class ExceptionAspect extends AbstractAspect {
    @AfterThrowing(throwing = "e", pointcut = "execution(* com.vastiny.javaweb.quartz.mvcweb.service..*.*(..))")
    public void processServiceException(JoinPoint jp, Exception e){

        //记录日志
        LOG.error("-----------------ExceptionAspect start---------------");
        LOG.error(getLogHeader(jp) + " exception## " + e.getMessage(), e);
        LOG.error("-----------------ExceptionAspect end-----------------");
    }
}
