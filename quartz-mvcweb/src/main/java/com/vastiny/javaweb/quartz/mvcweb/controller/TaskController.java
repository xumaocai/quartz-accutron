package com.vastiny.javaweb.quartz.mvcweb.controller;

import com.vastiny.javaweb.quartz.mvcweb.entity.ScheduleJob;
import com.vastiny.javaweb.quartz.mvcweb.service.TaskInit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangzhi
 * @since 2016/1/22
 */

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskInit taskInit;


    @RequestMapping("index")
    public String index() {

        for (int i = 0; i < 5; i++) {
            ScheduleJob job = new ScheduleJob();
            job.setScheduleJobId((long) (10001 + i));
            job.setJobName("data_import" + i);
            job.setJobGroup("dataWork");
            job.setStatus("1");
            job.setCronExpression("0/5 * * * * ?");
            job.setDescription("数据导入任务");
            addJob(job);
        }


        return "index";
    }

    /** 计划任务map */
    private static Map<String, ScheduleJob> jobMap = new HashMap<String, ScheduleJob>();
    /**
     * 添加任务
     * @param scheduleJob
     */
    public static void addJob(ScheduleJob scheduleJob) {
        jobMap.put(scheduleJob.getJobGroup() + "_" + scheduleJob.getJobName(), scheduleJob);
    }
}
