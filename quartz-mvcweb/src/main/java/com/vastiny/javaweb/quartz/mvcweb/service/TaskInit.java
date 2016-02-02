package com.vastiny.javaweb.quartz.mvcweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author yangzhi
 * @since 2016/1/22
 */

@Component
public class TaskInit {

    @Autowired
    ScheduleJobService scheduleJobService;

    /**
     * 用 postConstruct 来利用spring 启动 任务
     */
    @PostConstruct
    public void init() {
        System.out.println("hahaha");
        scheduleJobService.initScheduleJob();
    }
}
