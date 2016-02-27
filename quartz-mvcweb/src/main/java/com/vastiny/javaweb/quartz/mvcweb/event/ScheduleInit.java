package com.vastiny.javaweb.quartz.mvcweb.event;

import com.vastiny.javaweb.quartz.mvcweb.common.json.GsonUtil;
import com.vastiny.javaweb.quartz.mvcweb.entity.ScheduleJob;
import com.vastiny.javaweb.quartz.mvcweb.service.ScheduleJobService;
import com.vastiny.javaweb.quartz.mvcweb.service.impl.ScheduleJobServiceImpl;
import com.vastiny.javaweb.quartz.mvcweb.common.utils.ScheduleUtils;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;


// #1 启用 quartz 的方法，同时启动 thrift 也可以
// BaseTaskServer baseTaskServer = (BaseTaskServer)context.getBean("baseTaskServer");
// baseTaskServer.serve();

// #2 启动 quartz 的第二种方法，使用 postConstruct 注解，在 BaseTaskInit 中
// 但是注意的是，如果使用 spring-web， 需要在 WEB-INF 中添加 quartz Factory 对应的启动类

//        String a = "a";
//        String b = a + "b";
//        String c = "ab";
//        String d = new String("ab");
//        String e = getA() + "b";
//        System.out.println(b.toString() == "ab");
//        System.out.println( "ab"== e);

/**
 * @author yangzhi
 * @since 2016/2/25
 */
@Service
public class ScheduleInit {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    ScheduleJobService scheduleJobService;

    protected final static Logger LOG = LoggerFactory.getLogger(ScheduleJobServiceImpl.class);

    /**
     * 初始化定时任务，从数据库中获取任务数据
     * PostConstruct 注解能自启动
     */
    @PostConstruct
    public void init() {

        // #1 来自数据库的任务

        List<ScheduleJob> scheduleJobList = scheduleJobService.getAll();
        for (ScheduleJob scheduleJob : scheduleJobList) {
            ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
        }

        LOG.info("==db jobs==");
        try {
            LOG.info(GsonUtil.toJson(scheduler.getCurrentlyExecutingJobs()));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }


        /*
        ScheduleJob scheduleJob = new ScheduleJob();
        scheduleJob.setScheduleJobId(12345L);
        scheduleJob.setJobName("jobName1");
        scheduleJob.setJobGroup("jobGroup1");
        scheduleJob.setAliasName("haha");
        scheduleJob.setCronExpression("0/5 * * * * ?");
        scheduleJob.setDescription("hahaha");
        scheduleJob.setIsSync(false);
        scheduleJob.setStatus("1");

        ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
        */


        // #2 来自jobs.xml 的定时任务



        try {
            scheduler.start();
            System.out.println("schedule is started...");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }
}
