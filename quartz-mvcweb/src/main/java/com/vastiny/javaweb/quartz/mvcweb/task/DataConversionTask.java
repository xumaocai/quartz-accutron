package com.vastiny.javaweb.quartz.mvcweb.task;

import com.vastiny.javaweb.quartz.mvcweb.entity.ScheduleJob;
import org.quartz.JobExecutionContext;

/**
 * @author yangzhi
 * @since 2016/1/22
 */

public class DataConversionTask extends BaseTask {


    @Override
    public void execute(JobExecutionContext context) {

        ScheduleJob scheduleJob = (ScheduleJob)context.getMergedJobDataMap().get("scheduleJob");


        if (LOG.isInfoEnabled()) {
            LOG.info(scheduleJob.getDescription());
            LOG.info("数据转换任务线程开始执行");
        }
    }
}
