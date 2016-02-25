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

    /**
     * 初始化任务
     */
    public void initScheduleJob();

    public Long insert(ScheduleJob scheduleJob);

    public boolean delete(Long scheduleJobId);

    public ScheduleJob get(Long scheduleJobId);

    public boolean update(Long scheduleJobId, ScheduleJob scheduleJob);

    List<ScheduleJob> selectScheduleJobs();
}
