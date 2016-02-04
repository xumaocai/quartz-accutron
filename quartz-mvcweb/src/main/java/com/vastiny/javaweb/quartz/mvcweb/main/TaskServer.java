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
        // 但是注意的是，如果使用 spring-web， 需要在 WEB-INF 中添加 quartz Factory 对应的启动类

        log.info("======================");
        log.info("   service start...");
        log.info("======================");

//        String a = "a";
//        String b = a + "b";
//        String c = "ab";
//        String d = new String("ab");
//        String e = getA() + "b";
//        System.out.println(b.toString() == "ab");
//        System.out.println( "ab"== e);




    }

//    private static String getA(){ return "a";}
}


