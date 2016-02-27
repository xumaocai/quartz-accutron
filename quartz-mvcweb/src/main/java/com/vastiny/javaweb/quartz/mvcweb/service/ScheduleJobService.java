package com.vastiny.javaweb.quartz.mvcweb.service;

import com.vastiny.javaweb.quartz.mvcweb.entity.PageRequest;
import com.vastiny.javaweb.quartz.mvcweb.entity.PageResponse;
import com.vastiny.javaweb.quartz.mvcweb.entity.ScheduleJob;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author yangzhi
 * @since 2016/1/22
 */

@Service
public interface ScheduleJobService {

    public List<ScheduleJob> getAll ();

    public List<ScheduleJob> getExecutingJobList();


}
