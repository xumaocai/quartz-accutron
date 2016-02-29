package com.vastiny.javaweb.quartz.mvcweb.quartz;

import com.vastiny.javaweb.quartz.mvcweb.entity.ScheduleJob;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 任务工厂类,非同步
 *
 * User: liyd
 * Date: 14-1-3
 * Time: 上午10:11
 */
@Component
@DisallowConcurrentExecution
public class JobFactory {

    /* 日志对象 */
    private static final Logger LOG = LoggerFactory.getLogger(JobFactory.class);

    protected void runTask(String jobName, JobExecutionContext context) {

        JobFactoryContext jobFactoryBean = (JobFactoryContext) JobFactoryContext.getBean("jobFactoryBean");

        Class currentTask = jobFactoryBean.getTargetTaskMap().get(jobName);
        String methodName = jobFactoryBean.getTargetMethod();

        try {
            context.getScheduler().getCurrentlyExecutingJobs();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }


        // 没有对应的 task 返回 null
        if (currentTask == null) {
            LOG.info("jobName 没有对应的 task");
            return;
        }


        try {
            // 生成方法的参数
            Class[] cArg = new Class[1];
            cArg[0] = JobExecutionContext.class;

            // 获取方法
            Method m = currentTask.getMethod(methodName, cArg);

            // 生成类实例
            Object o = currentTask.newInstance();

            // 方法调用
            m.invoke(o, context);

        } catch (InstantiationException e) {
            // 创建实例失败，实例化时失败
            e.printStackTrace();

        } catch (IllegalAccessException e) {
            // 创建实例失败，没有权限创建
            // 调用方法失败，没有权限执行
            e.printStackTrace();

        }
        catch (InvocationTargetException e) {
            // 调用方法失败
            e.printStackTrace();

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
}
