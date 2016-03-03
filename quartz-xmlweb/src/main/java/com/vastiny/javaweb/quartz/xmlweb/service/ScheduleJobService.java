package com.vastiny.javaweb.quartz.xmlweb.service;

import com.vastiny.javaweb.quartz.xmlweb.entity.ScheduleJob;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yangzhi
 * @since 2016/1/22
 */

@Service
public interface ScheduleJobService {

    List<ScheduleJob> getAll();
}
