package com.vastiny.javaweb.quartz.mvcweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author yangzhi
 * @since 2016/1/22
 */

@Service
public interface ScheduleInit {

    public void init();
}
