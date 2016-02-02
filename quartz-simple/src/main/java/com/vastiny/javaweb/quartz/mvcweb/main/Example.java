package com.vastiny.javaweb.quartz.mvcweb.main;

import com.vastiny.javaweb.quartz.mvcweb.task.SimpleTask;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.DateBuilder.evenMinuteDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * 定时任务的示例方法
 * @author yangzhi
 * @since 2015/12/30
 */

public class Example {

    private final static Logger log = LoggerFactory.getLogger(Example.class);

    public static void SkeletonExample() throws SchedulerException {

        // Grab the Scheduler instance from the Factory
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        scheduler.start();
        try {
            Thread.sleep(365 * 24 * 3600L * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scheduler.shutdown();
    }

    public static void SimpleExample() throws SchedulerException {

        // Grab the Scheduler instance from the Factory
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        // SchedulerFactory sf = new StdSchedulerFactory();
        // Scheduler scheduler = sf.getScheduler();

        // define the job and tie it to our SimpleTask class
        JobDetail job = newJob(SimpleTask.class)
                .withIdentity("job1", "group1")
                .build();


        // compute a time that is on the next round minute
        Date runTime = evenMinuteDate(new Date());
        // Trigger the job to run on the next round minute
        Trigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .startAt(runTime)
                .build();

        // Trigger the job to run now, and then repeat every 40 seconds
        Trigger trigger2 = newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(5)
                        .repeatForever())
                .build();

        // Tell quartz to schedule the job using our trigger
        scheduler.scheduleJob(job, trigger2);

        scheduler.start();
        try {
            Thread.sleep(120L * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scheduler.shutdown();
    }

    public static void CronTriggerExample() throws SchedulerException {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();

        // Job #1 is scheduled to run every 20 seconds
        JobDetail job = newJob(SimpleTask.class)
                .withIdentity("job1", "group1")
                .build();
        CronTrigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .withSchedule(cronSchedule("0/2 * * * * ?"))
                .build();
        sched.scheduleJob(job, trigger);

        // Job #2 is scheduled to run every other minute starting at 15 seconds past the minute.
        job = newJob(SimpleTask.class)
                .withIdentity("job2", "group1")
                .build();
        trigger = newTrigger()
                .withIdentity("trigger2", "group1")
                .withSchedule(cronSchedule("15 0/2 * * * ?"))
                .build();
        sched.scheduleJob(job, trigger);

        // Job #3 is scheduled to run every other minute between 8am and 5pm (17 :00).
        job = newJob(SimpleTask.class)
                .withIdentity("job3", "group1")
                .build();
        trigger = newTrigger()
                .withIdentity("trigger3", "group1")
                .withSchedule(cronSchedule("0 0/2 8-17 * * ?"))
                .build();
        sched.scheduleJob(job, trigger);

        // Job #4 is scheduled to run every three minutes, but only between 5pm and 11pm
                job = newJob(SimpleTask.class)
                .withIdentity("job4", "group1")
                .build();
        trigger = newTrigger()
                .withIdentity("trigger4", "group1")
                .withSchedule(cronSchedule("0 0/3 17-23 * * ?"))
                .build();
        sched.scheduleJob(job, trigger);

        // Job #5 is scheduled to run at 10am on the 1st and 15th days of the month
                job = newJob(SimpleTask.class)
                .withIdentity("job5", "group1")
                .build();
        trigger = newTrigger()
                .withIdentity("trigger5", "group1")
                .withSchedule(cronSchedule("0 0 10am 1,15 * ?"))
                .build();
        sched.scheduleJob(job, trigger);

        // Job #6 is scheduled to run every 30 seconds on weekdays (Monday through Friday)
        job = newJob(SimpleTask.class)
                .withIdentity("job6", "group1")
                .build();
        trigger = newTrigger()
                .withIdentity("trigger6", "group1")
                .withSchedule(cronSchedule("0,30 * * ? * MON-FRI"))
                .build();
        sched.scheduleJob(job, trigger);

        // Job #7 is scheduled to run every 30 seconds on weekends (Saturday and Sunday)
        job = newJob(SimpleTask.class)
                .withIdentity("job7", "group1")
                .build();
        trigger = newTrigger()
                .withIdentity("trigger7", "group1")
                .withSchedule(cronSchedule("0,30 * * ? * SAT,SUN"))
                .build();
        sched.scheduleJob(job, trigger);

        // The scheduler is then started (it also would have been fine to start it before scheduling the jobs).
        sched.start();

        // To let the scheduler have an opportunity to run the job, our program sleeps for five minutes (300 seconds).
        // The scheduler is running in the background and should fire off several jobs during that time.
        // Note:
        // Because many of the jobs have hourly and daily restrictions on them, not all of the jobs will run in this example.
        // For example: Job #6 only runs on weekdays while Job #7 only runs on weekends.

        try {
            Thread.sleep(300L * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Finally, the program gracefully shuts down the scheduler:
        sched.shutdown(true);

        // Note:
        // Passing "true" into the shutdown() method tells the Quartz Scheduler to wait until all jobs have completed running
        // before returning from the method call.

    }

    public static void PlugInExample() throws SchedulerException {
        Logger log = LoggerFactory.getLogger(Example.class);

        // First we must get a reference to a scheduler
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = null;
        try {
            sched = sf.getScheduler();
        } catch (NoClassDefFoundError e) {
            log.error(" Unable to load a class - most likely you do not have jta.jar on the classpath. If not present in the examples/lib folder, please " +
                    "add it there for this sample to run.", e);
            return;
        }

        log.info("------- Initialization Complete -----------");

        log.info("------- (Not Scheduling any Jobs - relying on XML definitions --");

        log.info("------- Starting Scheduler ----------------");

        // start the schedule
        sched.start();

        log.info("------- Started Scheduler -----------------");

        log.info("------- Waiting five minutes... -----------");

        // wait five minutes to give our jobs a chance to run
        try {
            Thread.sleep(300L * 1000L);
        } catch (Exception e) {
            //
        }

        // shut down the scheduler
        log.info("------- Shutting Down ---------------------");
        sched.shutdown(true);
        log.info("------- Shutdown Complete -----------------");

        SchedulerMetaData metaData = sched.getMetaData();
        log.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
    }

    public static void CronExpressExample() throws SchedulerException {
// First we must get a reference to a scheduler
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();

        log.info("------- Initialization Complete --------");

        log.info("------- Scheduling Jobs ----------------");

        // jobs can be scheduled before sched.start() has been called

        // job 1 will run every 20 seconds
        JobDetail job = newJob(SimpleTask.class).withIdentity("job1", "group1").build();

        CronTrigger trigger = newTrigger().withIdentity("trigger1", "group1").withSchedule(cronSchedule("*/20 * * * * ?"))
                .build();

        Date ft = sched.scheduleJob(job, trigger);
        log.info(job.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: "
                + trigger.getCronExpression());

        // job 2 will run every other minute (at 15 seconds past the minute)
        job = newJob(SimpleTask.class).withIdentity("job2", "group1").build();

        trigger = newTrigger().withIdentity("trigger2", "group1").withSchedule(cronSchedule("15 0/2 * * * ?")).build();

        ft = sched.scheduleJob(job, trigger);
        log.info(job.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: "
                + trigger.getCronExpression());

        log.info("------- Starting Scheduler ----------------");

        // All of the jobs have been added to the scheduler, but none of the jobs
        // will run until the scheduler has been started
        sched.start();

        log.info("------- Started Scheduler -----------------");

        log.info("------- Waiting five minutes... ------------");
        try {
            // wait five minutes to show jobs
            Thread.sleep(300L * 1000L);
            // executing...
        } catch (Exception e) {
            //
        }

        log.info("------- Shutting Down ---------------------");

        sched.shutdown(true);

        log.info("------- Shutdown Complete -----------------");

        SchedulerMetaData metaData = sched.getMetaData();
        log.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
    }

    public static void main(String[] args) throws SchedulerException {

        SkeletonExample();

//        SimpleExample();

//        CronTriggerExample();

//        PlugInExample();

//        CronExpressExample();


    }
}