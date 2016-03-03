package com.vastiny.javaweb.quartz.xmlweb.service.impl;

import com.vastiny.javaweb.quartz.xmlweb.entity.ScheduleJob;
import com.vastiny.javaweb.quartz.xmlweb.service.ScheduleJobService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangzhi
 * @since 2016/1/22
 */

@Service
public class ScheduleJobServiceImpl implements ScheduleJobService {

    @Autowired
    private Scheduler scheduler;


//    private final static Logger LOG = LoggerFactory.getLogger(ScheduleJobService.class);

    public List<ScheduleJob> getAll() {
        return new ArrayList<>();
    }

}
