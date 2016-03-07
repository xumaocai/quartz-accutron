package com.vastiny.javaweb.quartz.xmlweb.service.impl;

import com.vastiny.javaweb.quartz.xmlweb.common.SchedulerUtil;
import com.vastiny.javaweb.quartz.xmlweb.entity.ScheduleJob;
import com.vastiny.javaweb.quartz.xmlweb.service.ScheduleJobService;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author yangzhi
 * @since 2016/1/22
 */

@Service
public class ScheduleJobServiceImpl implements ScheduleJobService {

    @Autowired
    private Scheduler scheduler;

    private final static Logger LOG = LoggerFactory.getLogger(ScheduleJobService.class);

    @Override
    public List<ScheduleJob> getAllScheduleJobList() {
        try {
            return SchedulerUtil.getAllScheduleJobList(scheduler);
        } catch (SchedulerException e) {
            e.printStackTrace();
            LOG.error("getAllScheduleJobList Fail");
            return null;
        }
    }

    @Override
    public List<ScheduleJob> getExecutingJobList() {
        try {
            return SchedulerUtil.getExecutingJobList(scheduler);
        } catch (SchedulerException e) {
            e.printStackTrace();
            LOG.error("getExecutingJobList Fail");
            return null;
        }
    }

    @Override
    public void pauseJob(TriggerKey triggerKey) {
        ScheduleJob scheduleJob = this.getScheduleJob(triggerKey);
        try {
            SchedulerUtil.pauseJob(scheduler, scheduleJob);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void resumeJob(TriggerKey triggerKey) {
        ScheduleJob scheduleJob = this.getScheduleJob(triggerKey);
        try {
            SchedulerUtil.resumeJob(scheduler, scheduleJob);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean deleteJob(TriggerKey triggerKey) {
        ScheduleJob scheduleJob = this.getScheduleJob(triggerKey);
        try {
            return SchedulerUtil.deleteJob(scheduler, scheduleJob);
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public void runOnceNow(TriggerKey triggerKey) {
        ScheduleJob scheduleJob = this.getScheduleJob(triggerKey);

        try {
            SchedulerUtil.runOnceNow(scheduler, scheduleJob);
        } catch (SchedulerException e) {
            e.printStackTrace();
            LOG.error("runOnceNow Fail!");
        }
    }

    private ScheduleJob getScheduleJob(TriggerKey triggerKey) {
        ScheduleJob scheduleJob = null;
        try {
            scheduleJob = SchedulerUtil.getScheduleJob(scheduler, triggerKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
            LOG.error("getScheduleJob Fail!");
        }
        return scheduleJob;
    }

    @Override
    public Map<String, String> getInfo() {
        try {
            return SchedulerUtil.getInfo(scheduler);
        } catch (SchedulerException e) {
            e.printStackTrace();
            LOG.error("getInfo Fail!");
            return null;
        }
    }
}
