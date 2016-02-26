package com.vastiny.javaweb.quartz.mvcweb.quartz;

import com.vastiny.javaweb.quartz.mvcweb.entity.ScheduleJob;
import com.vastiny.javaweb.quartz.mvcweb.task.DataConversionTask;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 任务工厂类,非同步
 *
 * User: liyd
 * Date: 14-1-3
 * Time: 上午10:11
 */
@DisallowConcurrentExecution
public class JobDispatch implements Job {

    /* 日志对象 */
    private static final Logger LOG = LoggerFactory.getLogger(JobDispatch.class);

    public void execute(JobExecutionContext context) throws JobExecutionException {

        LOG.info("JobSyncFactory execute");

        ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get(ScheduleJob.JOB_PARAM_KEY);

        System.out.println("jobName:" + scheduleJob.getJobName() + "  " + scheduleJob);

        String className = "com.vastiny.javaweb.quartz.mvcweb.task.DataConversionTask";
        String methodName = "execute";

        try {
            // 获取指定类
            Class classA = Class.forName(className);

            // 生成方法的参数
            Class[] cArg = new Class[1];
            cArg[0] = JobExecutionContext.class;

            // 获取方法
            Method m = classA.getMethod(methodName, cArg);

            // 生成类实例
            Object o = classA.newInstance();

            // 方法调用
            m.invoke(o, context);

        } catch (ClassNotFoundException e) {
            // 未找到类 className
            e.printStackTrace();

        } catch (NoSuchMethodException e) {
            // 没有方法 methodName
            e.printStackTrace();

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

        }
    }
}
