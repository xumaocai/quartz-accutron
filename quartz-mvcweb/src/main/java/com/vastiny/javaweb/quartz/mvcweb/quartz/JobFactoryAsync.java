package com.vastiny.javaweb.quartz.mvcweb.quartz;

import com.vastiny.javaweb.quartz.mvcweb.entity.ScheduleJob;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 任务工厂类,非同步
 *
 * User: liyd
 * Date: 14-1-3
 * Time: 上午10:11
 */
@Component
@DisallowConcurrentExecution
public class JobFactoryAsync extends JobFactory implements Job {

    /* 日志对象 */
    private static final Logger LOG = LoggerFactory.getLogger(JobFactoryAsync.class);

    public void execute(JobExecutionContext context) throws JobExecutionException {

        ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get(ScheduleJob.JOB_PARAM_KEY);

        LOG.info("=======");
        LOG.info("JobSyncFactory Execute");
        LOG.info("JobName:" + scheduleJob.getJobName());
        // LOG.info("JobDetail:" + GsonUtil.toJson(scheduleJob));

        String jobName = scheduleJob.getJobName();
        // 执行 jobName 与 taskName 相同的任务
        runTask(jobName, context);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
