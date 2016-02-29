package com.vastiny.javaweb.quartz.mvcweb.controller;

import com.vastiny.javaweb.quartz.mvcweb.common.json.GsonUtil;
import com.vastiny.javaweb.quartz.mvcweb.entity.ScheduleJob;
import com.vastiny.javaweb.quartz.mvcweb.entity.Status;
import com.vastiny.javaweb.quartz.mvcweb.service.ScheduleJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yangzhi
 * @since 2016/1/22
 */

@Controller
@Transactional
@RequestMapping
public class TaskController {

    @Autowired
    ScheduleJobService scheduleJobService;

    private static final Logger LOG = LoggerFactory.getLogger(TaskController.class);

    @RequestMapping(value = "debug")
    @ResponseBody
    public String debug() {
        List<ScheduleJob> executingJobList = scheduleJobService.getExecutingJobList();
        return GsonUtil.toJson(executingJobList);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap model) {
        model.addAttribute("scheduleJobList", scheduleJobService.getAll());
        model.addAttribute("executingJobList", scheduleJobService.getExecutingJobList());
        return "index";
    }


    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create() {
        return "create";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create1(@ModelAttribute ScheduleJob scheduleJob, Model model) {
        LOG.info(GsonUtil.toJson(scheduleJob));
        int row = scheduleJobService.createScheduleJob(scheduleJob);
        return "redirect:/";

    }


    @RequestMapping(value = "/{scheduleJobId}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable(value = "scheduleJobId") Long scheduleJobId, ModelMap modelMap) {
        modelMap.addAttribute("scheduleJob", scheduleJobService.findByScheduleJobId(scheduleJobId));
        return "edit";
    }

    @RequestMapping(value = "/{scheduleJobId}/edit", method = RequestMethod.POST)
    public String edit1(@PathVariable(value = "scheduleJobId") Long scheduleJobId,
                        @ModelAttribute ScheduleJob scheduleJob, ModelMap modelMap) {
        int row = scheduleJobService.updateScheduleJob(scheduleJob);
        return "redirect:/";
    }

    @RequestMapping(value = "/{scheduleJobId}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable(value = "scheduleJobId") Long scheduleJobId) {
        scheduleJobService.deleteScheduleJob(scheduleJobId);
        return "redirect:/";
    }

    @RequestMapping(value = "{scheduleJobId}/pause", method = RequestMethod.GET)
    public String pause(@PathVariable(value = "scheduleJobId") Long scheduleJobId) {
        scheduleJobService.pause(scheduleJobId);
        return "redirect:/";
    }

    @RequestMapping(value = "{scheduleJobId}/resume", method = RequestMethod.GET)
    public String resume(@PathVariable(value = "scheduleJobId") Long scheduleJobId) {
        scheduleJobService.resume(scheduleJobId);
        return "redirect:/";
    }

    @RequestMapping(value = "{scheduleJobId}/run-once", method = RequestMethod.GET)
    public String runOne(@PathVariable(value = "scheduleJobId") Long scheduleJobId) {
        scheduleJobService.runOne(scheduleJobId);
        return "redirect:/";
    }

}
