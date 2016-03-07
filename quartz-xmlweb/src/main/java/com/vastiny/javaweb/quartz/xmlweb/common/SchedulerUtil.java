package com.vastiny.javaweb.quartz.xmlweb.common;

import com.vastiny.javaweb.quartz.xmlweb.entity.ScheduleJob;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;

import java.util.*;

/**
 * @author yangzhi
 * @since 2016/3/3
 */

        /*

        scheduler.pauseAll();
        scheduler.start();
        scheduler.shutdown(true);
        scheduler.resumeAll();
        scheduler.checkExists(triggerKey);
        scheduler.checkExists(jobKey);

        Set<JobKey> jobKeySet = scheduler.getJobKeys( GroupMatcher.anyJobGroup());

        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        List<Trigger> triggerList = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
        List<String> groupName = scheduler.getJobGroupNames();
        Set<JobKey> jobKeySet1 = scheduler.getJobKeys( GroupMatcher.groupEquals(groupName.get(0)));
*/
public class SchedulerUtil {

    public static JobKey getJobKey(ScheduleJob scheduleJob) {
        return new JobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
    }

    public static boolean checkValid(Scheduler scheduler, Trigger trigger) throws SchedulerException {
        boolean jobValid = scheduler.checkExists(trigger.getKey());
        boolean triggerValid = scheduler.checkExists(trigger.getJobKey());
        return jobValid && triggerValid;
    }

    public static void resumeJob(Scheduler scheduler, ScheduleJob scheduleJob) throws SchedulerException {
        JobKey jobkey = getJobKey(scheduleJob);
        scheduler.resumeJob(jobkey);
    }

    public static void pauseJob(Scheduler scheduler, ScheduleJob scheduleJob) throws SchedulerException {
        JobKey jobkey = getJobKey(scheduleJob);
        scheduler.pauseJob(jobkey);
    }

    public static boolean deleteJob(Scheduler scheduler, ScheduleJob scheduleJob) throws SchedulerException {
        JobKey jobKey = getJobKey(scheduleJob);

        // 暂停所有与 job 关联的 触发器,否则会出现新添加的任务自动触发以前的触发器
        // 在 getExecutingJobList 中会找到这个 trigger 但是找不到 job,会报异常
        // 删除会让正在运行的任务执行完
        List<? extends Trigger> triggerList = getTriggerList(scheduler, jobKey);
        for (Trigger trigger : triggerList) {
            scheduler.unscheduleJob(trigger.getKey());
        }
        return scheduler.deleteJob(jobKey);
    }

    /**
     * 立即执行
     * @param scheduler
     * @param scheduleJob
     * @throws SchedulerException
     */
    public static void runOnceNow(Scheduler scheduler, ScheduleJob scheduleJob) throws SchedulerException {
        JobKey jobKey = getJobKey(scheduleJob);
        scheduler.triggerJob(jobKey);
    }

    public static Map<String, String> getInfo(Scheduler scheduler) throws SchedulerException {
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("currentTime", new Date().toString());
        infoMap.put("jobCount", scheduler.getMetaData().getNumberOfJobsExecuted() + "");

        return infoMap;
    }

    public static void changeCronExpression(Scheduler scheduler, ScheduleJob scheduleJob) throws SchedulerException {


    }

    public static List<String> getTriggerGroupList(Scheduler scheduler) throws SchedulerException {
        return scheduler.getTriggerGroupNames();
    }

    public static Set<TriggerKey> getTriggerKeyList(Scheduler scheduler, String triggerGroupName) throws SchedulerException {
        return scheduler.getTriggerKeys(
                GroupMatcher.triggerGroupEquals(triggerGroupName));
    }

    public static List<? extends Trigger> getTriggerList(Scheduler scheduler, JobKey jobKey) throws SchedulerException {
        return scheduler.getTriggersOfJob(jobKey);
    }

    public static List<TriggerKey> getAllTriggerKeyList(Scheduler scheduler) throws SchedulerException {
        List<String> triggerGroupList = getTriggerGroupList(scheduler);

        List<TriggerKey> triggerKeyList = new ArrayList<>();
        for (String triggerGroupName : triggerGroupList) {
            Set<TriggerKey> triggerKeySet = getTriggerKeyList(scheduler, triggerGroupName);
            triggerKeyList.addAll(triggerKeySet);
        }

        return triggerKeyList;
    }

    public static JobDetail getJobDetail(Scheduler scheduler, JobKey jobKey) throws SchedulerException {
        return scheduler.getJobDetail(jobKey);
    }

    public static Class getJobClass(JobDetail jobDetail) throws SchedulerException {
        return jobDetail.getJobClass();
    }


    public static ScheduleJob getScheduleJob(Scheduler scheduler, TriggerKey triggerKey) throws SchedulerException {
        ScheduleJob scheduleJob = new ScheduleJob();
        Trigger trigger = scheduler.getTrigger(triggerKey);
        JobKey jobKey = trigger.getJobKey();
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);

        scheduleJob.setJobName(jobKey.getName());
        scheduleJob.setJobGroup(jobKey.getGroup());
        scheduleJob.setTriggerName(trigger.getKey().getName());
        scheduleJob.setTriggerGroup(trigger.getKey().getGroup());

        // jobDetail 其实也有 descption ,但是兼容的配置方法中没有声明,需要用新的配置才行
        scheduleJob.setDescription(trigger.getDescription());

        Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
        StatusConstant.SchedulerStatus schedulerStatus = StatusConstant.SchedulerStatus.get(triggerState.name());
        String status;
        if (schedulerStatus == null) {
            status = "没有找到这个状态:" + triggerState.name();
        } else {
            status = schedulerStatus.getDescription();
        }
        scheduleJob.setStatus(status);

        scheduleJob.setGmtCreate(trigger.getStartTime());
        scheduleJob.setGmtModify(new Date());

        scheduleJob.setPreviousFireTime(trigger.getPreviousFireTime());
        scheduleJob.setNextFireTime(trigger.getNextFireTime());

        if (trigger instanceof CronTrigger) {
            CronTrigger cronTrigger = (CronTrigger) trigger;
            String cronExpression = cronTrigger.getCronExpression();
            scheduleJob.setCronExpression(cronExpression);
        }

        scheduleJob.setIsConcurrent(
                !jobDetail.isConcurrentExectionDisallowed()
        );


        /*
        TODO 以后再添加
        jobDetail.isDurable();
        jobDetail.isPersistJobDataAfterExecution();
        jobDetail.requestsRecovery();
        */
        return scheduleJob;
    }


    public static List<ScheduleJob> getScheduleJobList(Scheduler scheduler, JobKey jobKey) throws SchedulerException {
        JobDetail jobDetail = getJobDetail(scheduler, jobKey);
        List<ScheduleJob> scheduleJobList = new ArrayList<>();
        List<Trigger> triggerKeyList = (List<Trigger>) getTriggerList(scheduler, jobDetail.getKey());

        for (Trigger trigger : triggerKeyList) {
            ScheduleJob scheduleJob = getScheduleJob(scheduler, trigger.getKey());
            scheduleJobList.add(scheduleJob);
        }
        return scheduleJobList;
    }

    /**
     * 多个 trigger 对应一个 job
     *
     * @param scheduler
     * @return
     * @throws SchedulerException
     */
    public static List<ScheduleJob> getAllScheduleJobList(Scheduler scheduler) throws SchedulerException {
        Set<JobKey> jobKeySet = scheduler.getJobKeys( GroupMatcher.anyJobGroup());
        List<ScheduleJob> allScheduleJobList = new ArrayList<>();
        for (JobKey jobKey : jobKeySet) {
            List<ScheduleJob> scheduleJobList = SchedulerUtil.getScheduleJobList(scheduler, jobKey);
            allScheduleJobList.addAll(scheduleJobList);
        }

        return allScheduleJobList;
    }

    public static List<ScheduleJob> getExecutingJobList(Scheduler scheduler) throws SchedulerException {
        List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
        List<ScheduleJob> scheduleJobList = new ArrayList<>(executingJobs.size());
        for (JobExecutionContext executingJob : executingJobs) {

            // executingJob.getJobRunTime();
            // executingJob.getResult();

            // 不再显示已经删除的任务
            if (checkValid(scheduler, executingJob.getTrigger())) {
                ScheduleJob scheduleJob = getScheduleJob(scheduler, executingJob.getTrigger().getKey());

                // 转换阻塞状态为正常运行，因为这个阻塞状态是形容后面在排队的任务
                if (scheduleJob.getStatus().equals(
                        StatusConstant.SchedulerStatus.BLOCKED.getDescription())) {
                    scheduleJob.setStatus(
                            StatusConstant.SchedulerStatus.NORMAL.getDescription()
                    );
                }
                scheduleJobList.add(scheduleJob);
            }
        }
        return scheduleJobList;

    }

}
