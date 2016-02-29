package com.vastiny.javaweb.quartz.mvcweb.service.impl;

import com.vastiny.javaweb.quartz.mvcweb.common.constant.StatusConstant;
import com.vastiny.javaweb.quartz.mvcweb.common.utils.ScheduleUtils;
import com.vastiny.javaweb.quartz.mvcweb.entity.ScheduleJob;
import com.vastiny.javaweb.quartz.mvcweb.mapper.ScheduleJobMapper;
import com.vastiny.javaweb.quartz.mvcweb.service.base.BaseService;
import com.vastiny.javaweb.quartz.mvcweb.service.ScheduleJobService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangzhi
 * @since 2016/1/22
 */

@Service
public class ScheduleJobServiceImpl extends BaseService<ScheduleJob> implements ScheduleJobService {

    @Autowired
    private Scheduler scheduler;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private ScheduleJobMapper scheduleJobMapper;

    public List<ScheduleJob> getAll() {
        return this.findAll();
    }

    @Override
    public List<ScheduleJob> getExecutingJobList() {
        try {
            List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
            List<ScheduleJob> jobList = new ArrayList<>(executingJobs.size());
            for (JobExecutionContext executingJob : executingJobs) {
                ScheduleJob job = new ScheduleJob();
                JobDetail jobDetail = executingJob.getJobDetail();
                JobKey jobKey = jobDetail.getKey();
                Trigger trigger = executingJob.getTrigger();
                job.setJobName(jobKey.getName());
                job.setJobGroup(jobKey.getGroup());
                job.setJobTrigger(trigger.getKey().getName());
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                job.setStatus(triggerState.name());
                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    job.setCronExpression(cronExpression);
                }
                jobList.add(job);
            }
            return jobList;
        } catch (SchedulerException e) {
            // 处理
            return null;
        }

    }

    @Override
    public int createScheduleJob(ScheduleJob scheduleJob) {
        ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
        return this.insert(scheduleJob);
    }

    @Override
    public int updateScheduleJob(ScheduleJob scheduleJob) {
        ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
        return this.updateWithoutNullColumn(scheduleJob);
    }

    @Override
    public int deleteScheduleJob(Long scheduleJobId) {
        ScheduleJob scheduleJob = this.findByScheduleJobId(scheduleJobId);
        ScheduleUtils.deleteScheduleJob(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());
        return this.deleteByEntity(scheduleJob);
    }

    @Override
    public ScheduleJob findByScheduleJobId(Long scheduleJobId) {
        return scheduleJobMapper.selectById(scheduleJobId);
    }

    @Override
    public void runOne(Long scheduleJobId) {
        ScheduleJob scheduleJob = this.findByScheduleJobId(scheduleJobId);
        ScheduleUtils.runOnce(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());
    }

    @Override
    public int pause(Long scheduleJobId) {
        ScheduleJob scheduleJob = this.findByScheduleJobId(scheduleJobId);
        ScheduleUtils.pauseJob(scheduler, scheduleJob);

        scheduleJob.setStatus(
                StatusConstant.TaskStatus.PAUSED.getCode());
        return this.updateWithoutNullColumn(scheduleJob);
    }

    @Override
    public int resume(Long scheduleJobId) {
        ScheduleJob scheduleJob = this.findByScheduleJobId(scheduleJobId);
        ScheduleUtils.resumeJob(scheduler, scheduleJob);

        scheduleJob.setStatus(
                StatusConstant.TaskStatus.EXECUTING.getCode());
        return this.updateWithoutNullColumn(scheduleJob);

    }
}
