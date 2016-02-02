package com.vastiny.javaweb.quartz.mvcweb.main;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yangzhi
 * @since 2016/1/22
 */
public class SimpleServer {

    private final static Logger log = LoggerFactory.getLogger(SimpleServer.class);

    public static void main(String[] args) throws SchedulerException {

        log.info("start===============");

//        Example.SkeletonExample();
        Example.SimpleExample();

        log.info("===============");


    }
}
