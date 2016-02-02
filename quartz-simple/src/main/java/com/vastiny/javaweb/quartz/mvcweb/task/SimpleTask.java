package com.vastiny.javaweb.quartz.mvcweb.task;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Set;

public class SimpleTask implements Job {

    private static Logger log = LoggerFactory.getLogger(SimpleTask.class);

    public SimpleTask() {
    }

    @SuppressWarnings("unchecked")
    public void execute(JobExecutionContext context)
        throws JobExecutionException {

        // This job simply prints out its job name and the
        // date and time that it is running
        JobKey jobKey = context.getJobDetail().getKey();
        log.info("=====================start");
        log.info("Executing job: " + jobKey + " executing at " + new Date() + ", fired by: " + context.getTrigger().getKey());

        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        String key1 = jobDataMap.getString("someKey");
        log.info(key1);
        if(context.getMergedJobDataMap().size() > 0) {
            Set<String> keys = context.getMergedJobDataMap().keySet();
            for(String key: keys) {
                String val = context.getMergedJobDataMap().getString(key);
                log.info(" - jobDataMap entry: " + key + " = " + val);
            }
        }
        
        context.setResult("hello");
        log.info("=====================end");
    }

}
