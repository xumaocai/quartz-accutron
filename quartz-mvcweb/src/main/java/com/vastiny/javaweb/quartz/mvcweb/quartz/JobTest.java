package com.vastiny.javaweb.quartz.mvcweb.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 完全由反射机制获取Task 列表
 *
 * 完成度：未完成
 * 风险程度： ★★★☆
 *
 * @author yangzhi
 * @since 2016/2/27
 */
public class JobTest implements Job{
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {


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
