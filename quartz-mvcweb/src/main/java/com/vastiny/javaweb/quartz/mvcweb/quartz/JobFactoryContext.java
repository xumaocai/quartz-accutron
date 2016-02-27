package com.vastiny.javaweb.quartz.mvcweb.quartz;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * @author yangzhi
 * @since 2016/2/27
 */
public class JobFactoryContext implements ApplicationContextAware {

    String targetMethod = "execute";

    Map<String, Class> targetTaskMap;

    private static ApplicationContext context = null;

    public static ApplicationContext getApplicationContext() {
        return context;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public static Object getBean(String name) {
        return context.getBean(name);
    }

    public String getTargetMethod() {
        return targetMethod;
    }

    public void setTargetMethod(String targetMethod) {
        this.targetMethod = targetMethod;
    }

    public Map<String, Class> getTargetTaskMap() {
        return targetTaskMap;
    }

    public void setTargetTaskMap(Map<String, Class> targetTaskMap) {
        this.targetTaskMap = targetTaskMap;
    }
}
