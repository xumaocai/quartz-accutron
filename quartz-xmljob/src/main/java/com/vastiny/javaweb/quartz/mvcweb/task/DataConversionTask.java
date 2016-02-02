package com.vastiny.javaweb.quartz.mvcweb.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yangzhi
 * @since 2016/1/22
 */

public class DataConversionTask {

    /** 日志对象 */
    private static final Logger LOG = LoggerFactory.getLogger(DataConversionTask.class);
    public void run() {
        if (LOG.isInfoEnabled()) {
            LOG.info("数据转换任务线程开始执行");
        }
    }
}
