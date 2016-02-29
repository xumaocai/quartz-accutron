package com.vastiny.javaweb.quartz.mvcweb.mapper;

import com.vastiny.javaweb.quartz.mvcweb.entity.ScheduleJob;
import com.vastiny.javaweb.quartz.mvcweb.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ScheduleJobMapper extends BaseMapper<ScheduleJob>
{

    ScheduleJob selectById(Long scheduleJobId);
}