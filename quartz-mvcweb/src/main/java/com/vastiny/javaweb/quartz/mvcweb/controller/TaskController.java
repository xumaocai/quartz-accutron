package com.vastiny.javaweb.quartz.mvcweb.controller;

import com.vastiny.javaweb.quartz.mvcweb.service.TaskInit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

        return "index";
    }
}
