package com.vastiny.javaweb.quartz.mvcweb.task;

import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yangzhi
 * @since 2016/2/27
 */
public abstract class BaseTask {

    protected static final Logger LOG = LoggerFactory.getLogger(DataConversionTask.class);


    /**
     * program will execute this method.
     * @param context job execution context
     */
    public abstract void execute(JobExecutionContext context);

}
