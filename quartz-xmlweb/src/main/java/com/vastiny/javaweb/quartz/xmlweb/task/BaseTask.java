package com.vastiny.javaweb.quartz.xmlweb.task;

import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yangzhi
 * @since 2016/2/27
 */
public abstract class BaseTask {

    protected static final Logger LOG = LoggerFactory.getLogger(BaseTask.class);


    /**
     * program will execute this method.
     */
    public abstract void execute();

}
