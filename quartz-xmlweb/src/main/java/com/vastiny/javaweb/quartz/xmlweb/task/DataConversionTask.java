package com.vastiny.javaweb.quartz.xmlweb.task;


import com.vastiny.javaweb.quartz.xmlweb.entity.ScheduleJob;
import org.quartz.JobExecutionContext;

/**
 * @author yangzhi
 * @since 2016/1/22
 */

public class DataConversionTask extends BaseTask {


    @Override
    public void execute() {


        if (LOG.isInfoEnabled()) {
            LOG.info("数据转换任务线程开始执行");
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
