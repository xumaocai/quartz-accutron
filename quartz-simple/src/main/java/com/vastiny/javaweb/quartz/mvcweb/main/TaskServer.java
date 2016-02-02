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

        log.info("======================");
        log.info("User Life Cycle Activity service start...");
        log.info("======================");


    }
}


