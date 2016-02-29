package com.vastiny.javaweb.quartz.mvcweb.service;

import com.vastiny.javaweb.quartz.mvcweb.entity.ScheduleJob;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yangzhi
 * @since 2016/1/22
 */

@Service
public interface ScheduleJobService {

    List<ScheduleJob> getAll();

    List<ScheduleJob> getExecutingJobList();

    int createScheduleJob(ScheduleJob scheduleJob);

    int updateScheduleJob(ScheduleJob scheduleJob);

    int deleteScheduleJob(Long scheduleJobId);

    ScheduleJob findByScheduleJobId(Long scheduleJobId);

    void runOne(Long scheduleJobId);

    int pause(Long scheduleJobId);

    int resume(Long scheduleJobId);
}
