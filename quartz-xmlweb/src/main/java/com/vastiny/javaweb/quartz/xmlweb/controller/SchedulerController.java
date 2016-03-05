package com.vastiny.javaweb.quartz.xmlweb.controller;

import com.vastiny.javaweb.quartz.xmlweb.common.SchedulerUtil;
import com.vastiny.javaweb.quartz.xmlweb.entity.ScheduleJob;
import com.vastiny.javaweb.quartz.xmlweb.service.ScheduleJobService;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangzhi
 * @since 2016/3/3
 */

@Controller
@RequestMapping
public class SchedulerController {

    @Autowired
    ScheduleJobService scheduleJobService;

    @Autowired
    Scheduler scheduler;

    @RequestMapping({"/", "/index"})
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("scheduleJobList", scheduleJobService.getAllScheduleJobList());
        modelMap.addAttribute("executingJobList", scheduleJobService.getExecutingJobList());
        modelMap.addAttribute("scheduleInfo", scheduleJobService.getInfo());

        return "index";
    }

    @RequestMapping("/pause")
    public String pause(@RequestParam String triggerName, @RequestParam String triggerGroup, ModelMap modelMap) {
        TriggerKey triggerKey = new TriggerKey(triggerName, triggerGroup);
        scheduleJobService.pauseJob(triggerKey);

        return "redirect:/";
    }

    @RequestMapping("/resume")
    public String resume(@RequestParam String triggerName, @RequestParam String triggerGroup, ModelMap modelMap) {
        TriggerKey triggerKey = new TriggerKey(triggerName, triggerGroup);
        scheduleJobService.resumeJob(triggerKey);

        return "redirect:/";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam String triggerName, @RequestParam String triggerGroup, ModelMap modelMap) {
        TriggerKey triggerKey = new TriggerKey(triggerName, triggerGroup);
        scheduleJobService.deleteJob(triggerKey);

        return "redirect:/";
    }

    @RequestMapping("/runOnce")
    public String runOnce(@RequestParam String triggerName, @RequestParam String triggerGroup, ModelMap modelMap) {
        TriggerKey triggerKey = new TriggerKey(triggerName, triggerGroup);
        scheduleJobService.runOnceNow(triggerKey);

        return "redirect:/";
    }


    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "hahaha";
    }
}
