package com.vastiny.javaweb.quartz.mvcweb.controller;

import com.vastiny.javaweb.quartz.mvcweb.common.json.GsonUtil;
import com.vastiny.javaweb.quartz.mvcweb.entity.ScheduleJob;
import com.vastiny.javaweb.quartz.mvcweb.service.ScheduleInit;
import com.vastiny.javaweb.quartz.mvcweb.service.ScheduleJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yangzhi
 * @since 2016/1/22
 */

@RestController
public class TaskController {

    @Autowired
    ScheduleInit scheduleInit;

    @Autowired
    ScheduleJobService scheduleJobService;

    public static Logger LOG = LoggerFactory.getLogger(TaskController.class);


    @RequestMapping(value = "index", method = RequestMethod.GET)
    @ResponseBody
    public String index() {


        return "index";
    }

    @RequestMapping("debug")
    @ResponseBody
    public String debug() {
        List<ScheduleJob> executingJobList = scheduleJobService.getExecutingJobList();
        return GsonUtil.toJson(executingJobList);
    }


}
