package com.vastiny.javaweb.quartz.xmlweb.service.impl;

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

    public List<ScheduleJob> getAll() throws SchedulerException {

        int JobCount = scheduler.getMetaData().getNumberOfJobsExecuted();


        JobKey jobKey = new JobKey("job_work_name", "job_work");
        TriggerKey triggerKey = new TriggerKey("name");
        Trigger trigger = scheduler.getTrigger(triggerKey);
        scheduler.pauseAll();
        scheduler.start();
        scheduler.shutdown(true);
        scheduler.resumeAll();
        scheduler.checkExists(triggerKey);
        scheduler.checkExists(jobKey);

        Set<JobKey> jobKeySet = scheduler.getJobKeys( GroupMatcher.anyJobGroup());

        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        List<Trigger> triggerList = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
//        TriggerKey triggerKey = scheduler.getTriggersOfJob(jobKey).get(0).getKey();


        List<JobExecutionContext> jobExecutionContextList = scheduler.getCurrentlyExecutingJobs();

        List<String> groupName = scheduler.getJobGroupNames();
        Set<JobKey> jobKeySet1 = scheduler.getJobKeys( GroupMatcher.groupEquals(groupName.get(0)));

        return new ArrayList<>();
    }

}
