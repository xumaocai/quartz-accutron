package com.vastiny.javaweb.quartz.mvcweb.mapper;

import com.vastiny.javaweb.quartz.mvcweb.entity.ScheduleJob;
import com.vastiny.javaweb.quartz.mvcweb.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ScheduleJobMapper extends BaseMapper<ScheduleJob>
{

    // status 是 ulc_execution 里面状态
    // app_type 查找 未实现
    List<ScheduleJob> selectExecutionListByUidStatus(@Param("app_type") int app_type,
                                                    @Param("uid") long uid);

    List<ScheduleJob>  selectDynamic(Map map);
}