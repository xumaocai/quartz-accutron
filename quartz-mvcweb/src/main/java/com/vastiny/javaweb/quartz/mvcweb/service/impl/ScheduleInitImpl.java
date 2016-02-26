package com.vastiny.javaweb.quartz.mvcweb.service.impl;

import com.vastiny.javaweb.quartz.mvcweb.common.json.GsonUtil;
import com.vastiny.javaweb.quartz.mvcweb.entity.ScheduleJob;
import com.vastiny.javaweb.quartz.mvcweb.service.ScheduleInit;
import com.vastiny.javaweb.quartz.mvcweb.service.ScheduleJobService;
import com.vastiny.javaweb.quartz.mvcweb.utils.ScheduleUtils;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

/**
 * @author yangzhi
 * @since 2016/2/25
 */
@Service
public class ScheduleInitImpl implements ScheduleInit {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    ScheduleJobService scheduleJobService;

    protected final static Logger LOG = LoggerFactory.getLogger(ScheduleJobServiceImpl.class);

    /**
     * 初始化任务， 使用 PostConstruct 自启动
     */
    @PostConstruct
    public void init() {

        // #1 来自数据库的任务
        /*
        List<ScheduleJob> scheduleJobList = scheduleJobService.getAll();
        for (ScheduleJob scheduleJob : scheduleJobList) {
            ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
        }

        LOG.info("==db jobs==");
        LOG.info(GsonUtil.toJson(scheduleJobList));
        */

        ScheduleJob scheduleJob = new ScheduleJob();
        scheduleJob.setScheduleJobId(12345L);
        scheduleJob.setJobName("jobName1");
        scheduleJob.setJobGroup("jobGroup1");
        scheduleJob.setAliasName("haha");
        scheduleJob.setCronExpression("0/5 * * * * ?");
        scheduleJob.setDescription("hahaha");
        scheduleJob.setIsSync(false);
        scheduleJob.setStatus("1");

        ScheduleUtils.createScheduleJob(scheduler, scheduleJob);


        // #2 来自jobs.xml 的定时任务


        try {
            scheduler.start();
            System.out.println("schedule is started...");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }


    }
}
