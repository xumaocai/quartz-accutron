package com.vastiny.javaweb.quartz.mvcweb.aop;

import com.vastiny.javaweb.quartz.mvcweb.common.json.GsonUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author yangzhi
 * @since 2016/2/1
 */

@Component
@Aspect
public class LogAspect extends AbstractAspect {

    /**
     * 打印 ulc 所有的服务执行情况
     * @param jp ProceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.vastiny.javaweb.quartz.mvcweb.service..*.*(..))")
    public Object ulcProcessMethodLog(ProceedingJoinPoint jp) throws Throwable {

        String argsJson = GsonUtil.toJson(jp.getArgs());
        LOG.info(String.format("%s Start## arguments: %s", getLogHeader(jp), argsJson));

        // 记录方法开始时间
        recordthreadTime();

        // 执行目标方法并得到其返回值
        Object rtn = jp.proceed(jp.getArgs());

        // 记录方法结束日志，以及方法的返回结果
        LOG.info(String.format("%s Over## Finished after: %sms. Args: %s. Result: %s ",
                getLogHeader(jp), getConsumeMilliTimestamp(), argsJson, getPrintResult(rtn)));

        return rtn;
    }

}
