package com.vastiny.javaweb.quartz.mvcweb.quartz;

import com.vastiny.javaweb.quartz.mvcweb.common.json.GsonUtil;
import com.vastiny.javaweb.quartz.mvcweb.entity.ScheduleJob;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 任务工厂类,非同步
 *
 * User: liyd
 * Date: 14-1-3
 * Time: 上午10:11
 */
@DisallowConcurrentExecution
public class JobDispatch implements Job {

    /* 日志对象 */
    private static final Logger LOG = LoggerFactory.getLogger(JobDispatch.class);

    public void execute(JobExecutionContext context) throws JobExecutionException {

        LOG.info("JobSyncFactory execute");

        ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get(ScheduleJob.JOB_PARAM_KEY);

        System.out.println("jobName:" + scheduleJob.getJobName() + "  " + GsonUtil.toJson(scheduleJob));



        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
