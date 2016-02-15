package com.vastiny.javaweb.quartz.mvcweb.service.impl;

import com.vastiny.javaweb.quartz.mvcweb.entity.ScheduleJob;
import com.vastiny.javaweb.quartz.mvcweb.service.ScheduleJobService;
import com.vastiny.javaweb.quartz.mvcweb.utils.ScheduleUtils;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yangzhi
 * @since 2016/1/22
 */

@Service
public class ScheduleJobServiceImpl implements ScheduleJobService {

    @Autowired
    private Scheduler scheduler;

    protected final static Logger LOG = LoggerFactory.getLogger(ScheduleJobServiceImpl.class);

    @Override
    public void initScheduleJob() {
        LOG.info("======init Schedule Job======");

        for (int i = 0; i < 5; i++) {

            ScheduleJob job = new ScheduleJob();
            job.setScheduleJobId((long) (10001 + i));
            job.setJobName("data_import" + i);
            job.setJobGroup("dataWork");
            job.setStatus("1");
            job.setCronExpression("0/5 * * * * ?");
            job.setDescription("数据导入任务");


            ScheduleUtils.createScheduleJob(scheduler, job);
        }

        try {
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        // #1 来自数据库的任务


        // #2 来自jobs.xml 的定时任务

    }

    @Override
    public Long insert(ScheduleJob scheduleJob) {
        return null;
    }

    @Override
    public boolean delete(Long scheduleJobId) {
        return false;
    }

    @Override
    public ScheduleJob get(Long scheduleJobId) {
        return null;
    }

    @Override
    public boolean update(Long scheduleJobId, ScheduleJob scheduleJob) {
        return false;
    }

}
