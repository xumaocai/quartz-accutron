package com.vastiny.javaweb.quartz.mvcweb.controller;

import com.vastiny.javaweb.quartz.mvcweb.entity.ScheduleJob;
import com.vastiny.javaweb.quartz.mvcweb.service.TaskInit;
import com.vastiny.javaweb.quartz.mvcweb.task.DataConversionTask;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
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

    public static Logger LOG = LoggerFactory.getLogger(TaskController.class);


    @RequestMapping("index")
    public String index() {




        return "index";
    }


}
