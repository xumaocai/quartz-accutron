package com.vastiny.javaweb.quartz.mvcweb.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yangzhi
 * @since 2016/1/22
 */
public class TaskServer {


    protected final static Logger log = LoggerFactory.getLogger(TaskServer.class);

    public static void main(String[] args) {

        // init spring context
        ApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");

        // #1 启用 quartz 的方法，同时启动 thrift 也可以
        // BaseTaskServer baseTaskServer = (BaseTaskServer)context.getBean("baseTaskServer");
        // baseTaskServer.serve();

        // #2 启动 quartz 的第二种方法，使用 postConstruct 注解，在 BaseTaskInit 中

        log.info("======================");
        log.info("User Life Cycle Activity service start...");
        log.info("======================");


    }
}


