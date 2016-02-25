package com.vastiny.javaweb.quartz.mvcweb.service.impl;

import com.vastiny.javaweb.quartz.mvcweb.service.ScheduleInit;
import com.vastiny.javaweb.quartz.mvcweb.service.ScheduleJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author yangzhi
 * @since 2016/2/25
 */
@Service
public class ScheduleInitImpl implements ScheduleInit {

    @Autowired
    ScheduleJobService scheduleJobService;

    /**
     * 任务初始化
     */
    public void init() {
        System.out.println("in TaskInit");
        scheduleJobService.initScheduleJob();
    }
}
