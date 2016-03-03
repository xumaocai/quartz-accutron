package com.vastiny.javaweb.quartz.xmlweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yangzhi
 * @since 2016/3/3
 */

@Controller
@RequestMapping("/scheduler")
public class ScheduleManagerController {

    @RequestMapping({"/", "/index"})
    public String index() {
        return "index";
    }
}
