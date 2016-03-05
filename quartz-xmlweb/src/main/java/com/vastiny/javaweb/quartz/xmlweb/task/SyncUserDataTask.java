package com.vastiny.javaweb.quartz.xmlweb.task;

import com.vastiny.javaweb.quartz.xmlweb.entity.ScheduleJob;
import org.quartz.JobExecutionContext;

/**
 * @author yangzhi
 * @since 2016/2/27
 */
public class SyncUserDataTask extends BaseTask {

    @Override
    public void execute() {

        if (LOG.isInfoEnabled()) {
            LOG.info("同步数据开始执行");
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
