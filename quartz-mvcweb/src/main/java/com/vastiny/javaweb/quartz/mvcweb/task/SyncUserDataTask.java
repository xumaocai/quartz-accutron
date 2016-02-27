package com.vastiny.javaweb.quartz.mvcweb.task;

import com.vastiny.javaweb.quartz.mvcweb.entity.ScheduleJob;
import org.quartz.JobExecutionContext;

/**
 * @author yangzhi
 * @since 2016/2/27
 */
public class SyncUserDataTask extends BaseTask {

    @Override
    public void execute(JobExecutionContext context) {

        ScheduleJob scheduleJob = (ScheduleJob)context.getMergedJobDataMap().get("scheduleJob");

        if (LOG.isInfoEnabled()) {
            LOG.info(scheduleJob.getDescription());
            LOG.info("同步数据开始执行");
        }
    }
}
