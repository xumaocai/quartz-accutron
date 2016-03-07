package com.vastiny.javaweb.quartz.xmlweb.service;

import com.vastiny.javaweb.quartz.xmlweb.entity.ScheduleJob;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author yangzhi
 * @since 2016/1/22
 */

@Service
public interface ScheduleJobService {

    List<ScheduleJob> getAllScheduleJobList();
    List<ScheduleJob> getExecutingJobList();

    void pauseJob(TriggerKey triggerKey);
    void resumeJob(TriggerKey triggerKey);
    boolean deleteJob(TriggerKey triggerKey);
    void runOnceNow(TriggerKey triggerKey);

    Map<String, String> getInfo();
}
