package com.vastiny.javaweb.quartz.mvcweb.service.impl;

import com.vastiny.javaweb.quartz.mvcweb.common.constant.StatusConstant;
import com.vastiny.javaweb.quartz.mvcweb.common.utils.ScheduleUtils;
import com.vastiny.javaweb.quartz.mvcweb.entity.ScheduleJob;
import com.vastiny.javaweb.quartz.mvcweb.mapper.ScheduleJobMapper;
import com.vastiny.javaweb.quartz.mvcweb.service.base.BaseService;
import com.vastiny.javaweb.quartz.mvcweb.service.ScheduleJobService;
import org.quartz.*;
import org.quartz.utils.DBConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
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

//    private final static Logger LOG = LoggerFactory.getLogger(ScheduleJobService.class);

    public List<ScheduleJob> getAll() {
        try {
            // but since you are using the underlying data pool of quartz make sure that you close the connection so that it should get back to the pool.
            // http://sishuok.com/forum/blogPost/list/1186.html
            Connection conn = DBConnectionManager.getInstance().getConnection("myDS");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return super.findAll();
    }

    @Override
    public ScheduleJob getOne(Long scheduleJobId) {
        return super.find(scheduleJobId);
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
        return super.insert(scheduleJob);
    }

    @Override
    public int updateScheduleJob(ScheduleJob scheduleJob) {
        ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
        return super.updateWithoutNullColumn(scheduleJob);
    }

    @Override
    public int deleteScheduleJob(Long scheduleJobId) {
        ScheduleJob scheduleJob = super.find(scheduleJobId);
        ScheduleUtils.deleteScheduleJob(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());
        return super.deleteByEntity(scheduleJob);
    }

    @Override
    public void runOne(Long scheduleJobId) {
        ScheduleJob scheduleJob = super.find(scheduleJobId);
        ScheduleUtils.runOnce(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());
    }

    @Override
    public int pause(Long scheduleJobId) {
        ScheduleJob scheduleJob = scheduleJobMapper.selectById(scheduleJobId);
        ScheduleUtils.pauseJob(scheduler, scheduleJob);

        scheduleJob.setStatus(
                StatusConstant.TaskStatus.PAUSED.getCode());
        scheduleJobMapper.updateByPrimaryKeySelective(scheduleJob);
        int row = super.updateWithoutNullColumn(scheduleJob);
        return row;
    }

    @Override
    public int resume(Long scheduleJobId) {
        ScheduleJob scheduleJob = super.find(scheduleJobId);
        ScheduleUtils.resumeJob(scheduler, scheduleJob);

        scheduleJob.setStatus(
                StatusConstant.TaskStatus.EXECUTING.getCode());
        return super.updateWithoutNullColumn(scheduleJob);

    }
}
