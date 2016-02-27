package com.vastiny.javaweb.quartz.mvcweb.controller;

import com.vastiny.javaweb.quartz.mvcweb.common.json.GsonUtil;
import com.vastiny.javaweb.quartz.mvcweb.entity.ScheduleJob;
import com.vastiny.javaweb.quartz.mvcweb.service.ScheduleJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author yangzhi
 * @since 2016/1/22
 */

@Controller
@Transactional
@RequestMapping(value = "/group")
public class TaskController {

    @Autowired
    ScheduleJobService scheduleJobService;

    public static Logger LOG = LoggerFactory.getLogger(TaskController.class);

    @RequestMapping(value = "debug")
    @ResponseBody
    public String debug() {
        List<ScheduleJob> executingJobList = scheduleJobService.getExecutingJobList();
        return GsonUtil.toJson(executingJobList);

    }

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index() {
        return "ulc/group/index";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create() {
        return "ulc/group/create";
    }

    @RequestMapping(value = "{groupId}", method = RequestMethod.GET)
    public String show(@PathVariable(value = "groupId") Long groupId) {
        return "redirect:group/" + groupId + "/task";
    }

    @RequestMapping(value = "{groupId}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable(value = "groupId") Long groupId, Model model) {

//        model.addAttribute("group", parentTask);
        return "ulc/group/edit";
    }

    @RequestMapping(value = "{groupId}/update", method = RequestMethod.POST)
    public String update(@PathVariable(value = "groupId") Long groupId,
                         RedirectAttributes redirectAttributes) throws Exception {

        redirectAttributes.addAttribute("message", "修改成功");
        return "redirect:/group/index";
    }
    @RequestMapping(value = "{groupId}/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(@PathVariable(value = "groupId")Long groupId){

        return "";
    }


}
