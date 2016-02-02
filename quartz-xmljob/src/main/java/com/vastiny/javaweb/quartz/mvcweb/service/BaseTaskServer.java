package com.vastiny.javaweb.quartz.mvcweb.service;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yangzhi
 * @since 2016/1/22
 */
public class BaseTaskServer implements Runnable {

    @Autowired
    ScheduleJobService scheduleJobService;

    public void serve() {
        run();
    }

    @Override
    public void run() {
        scheduleJobService.initScheduleJob();
    }


}
