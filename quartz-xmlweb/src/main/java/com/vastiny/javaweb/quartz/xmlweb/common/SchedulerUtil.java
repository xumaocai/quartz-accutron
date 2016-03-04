package com.vastiny.javaweb.quartz.xmlweb.common;

import com.vastiny.javaweb.quartz.xmlweb.entity.ScheduleJob;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author yangzhi
 * @since 2016/3/3
 */
public class SchedulerUtil {

    public static JobKey getJobKey(ScheduleJob scheduleJob) {
        return new JobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
    }

//    public static TriggerKey getTriggerKey(ScheduleJob scheduleJob) {
//
//    }

    public static void resumeJob(Scheduler scheduler, ScheduleJob scheduleJob) throws SchedulerException {
        JobKey jobkey = getJobKey(scheduleJob);
        scheduler.resumeJob(jobkey);
    }

    public static void pauseJob(Scheduler scheduler, ScheduleJob scheduleJob) throws SchedulerException {
        JobKey jobkey = getJobKey(scheduleJob);
        scheduler.pauseJob(jobkey);
    }

    public static void deleteJob(Scheduler scheduler, ScheduleJob scheduleJob) throws SchedulerException {
        JobKey jobKey = getJobKey(scheduleJob);
        scheduler.deleteJob(jobKey);
    }

    /**
     * 立即执行
     * @param scheduler
     * @param scheduleJob
     * @throws SchedulerException
     */
    public static void runJobNow(Scheduler scheduler, ScheduleJob scheduleJob) throws SchedulerException {
        JobKey jobKey = getJobKey(scheduleJob);
        scheduler.triggerJob(jobKey);
    }

    public static void changeCronExpression(Scheduler scheduler, ScheduleJob scheduleJob) throws SchedulerException {


    }

    /**
     * 多个 trigger 对应一个 job
     *
     * @param scheduler
     * @return
     * @throws SchedulerException
     */
    public static List<ScheduleJob> getAllScheduleJob(Scheduler scheduler) throws SchedulerException {
        Set<JobKey> jobKeySet = scheduler.getJobKeys( GroupMatcher.anyJobGroup());
        List<ScheduleJob> scheduleJobList = new ArrayList<>();
        for (JobKey jobKey : jobKeySet) {
            List<Trigger> triggerList = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
        }

        return scheduleJobList;
    }
}
