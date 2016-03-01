package com.vastiny.javaweb.quartz.mvcweb.mapper;

import com.vastiny.javaweb.quartz.mvcweb.entity.ScheduleJob;
import com.vastiny.javaweb.quartz.mvcweb.mapper.base.BaseMapper;

public interface ScheduleJobMapper extends BaseMapper<ScheduleJob>
{

    ScheduleJob selectById(Long scheduleJobId);
}