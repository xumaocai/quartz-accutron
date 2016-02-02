package com.vastiny.javaweb.quartz.mvcweb.service.impl;

import com.vastiny.javaweb.quartz.mvcweb.service.ScheduleJobService;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author yangzhi
 * @since 2016/1/22
 */

@Service
public class ScheduleJobServiceImpl implements ScheduleJobService {

    protected final static Logger log = LoggerFactory.getLogger(ScheduleJobServiceImpl.class);

    @Override
    public void initScheduleJob() {
        log.info("======init======");

        try {
            SkeletonExample();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public static void SkeletonExample() throws SchedulerException {

        // Grab the Scheduler instance from the Factory
        log.info("===start===");
    }
}
