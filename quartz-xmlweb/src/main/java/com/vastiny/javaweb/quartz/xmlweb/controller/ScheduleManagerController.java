package com.vastiny.javaweb.quartz.xmlweb.controller;

import com.vastiny.javaweb.quartz.xmlweb.service.ScheduleJobService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yangzhi
 * @since 2016/3/3
 */

@Controller
@RequestMapping("/scheduler")
public class ScheduleManagerController {

    @Autowired
    ScheduleJobService scheduleJobService;

    @RequestMapping({"/", "/index"})
    public String index() {

        try {
            scheduleJobService.getAll();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        return "index";
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "hahaha";
    }
}
