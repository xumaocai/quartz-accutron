package com.vastiny.javaweb.quartz.mvcweb.quartz;

import com.vastiny.javaweb.quartz.mvcweb.entity.ScheduleJob;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 同步的任务工厂类
 *
 * Created by liyd on 12/19/14.
 */
public class JobDispatchConcurrent implements Job {

    /* 日志对象 */
    private static final Logger LOG = LoggerFactory.getLogger(JobDispatchConcurrent.class);

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        LOG.info("JobSyncFactory execute");

        JobDataMap mergedJobDataMap = jobExecutionContext.getMergedJobDataMap();
        ScheduleJob scheduleJob = (ScheduleJob) mergedJobDataMap.get(ScheduleJob.JOB_PARAM_KEY);

        System.out.println("jobName:" + scheduleJob.getJobName() + "  " + scheduleJob);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
