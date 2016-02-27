package com.vastiny.javaweb.quartz.mvcweb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.vastiny.javaweb.quartz.mvcweb.common.utils.SQLStringUtils;
import com.vastiny.javaweb.quartz.mvcweb.entity.PageRequest;
import com.vastiny.javaweb.quartz.mvcweb.entity.PageResponse;
import com.vastiny.javaweb.quartz.mvcweb.entity.ScheduleJob;
import com.vastiny.javaweb.quartz.mvcweb.mapper.ScheduleJobMapper;
import com.vastiny.javaweb.quartz.mvcweb.service.base.BaseService;
import com.vastiny.javaweb.quartz.mvcweb.service.ScheduleJobService;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yangzhi
 * @since 2016/1/22
 */

@Service
public class ScheduleJobServiceImpl extends BaseService<ScheduleJob> implements ScheduleJobService {

    protected final static Logger LOG = LoggerFactory.getLogger(ScheduleJobServiceImpl.class);

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
            List<ScheduleJob> jobList = new ArrayList<ScheduleJob>(executingJobs.size());
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

}
