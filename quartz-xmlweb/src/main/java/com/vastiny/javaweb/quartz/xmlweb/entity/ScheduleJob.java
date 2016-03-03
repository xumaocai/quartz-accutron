package com.vastiny.javaweb.quartz.xmlweb.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Table(name = "schedule_job")
public class ScheduleJob {

    /** 任务调度的参数key */
    @Transient
    public static final String JOB_PARAM_KEY    = "scheduleJob";

    @Id
    @Column(name = "schedule_job_id")
    private Long scheduleJobId;

    @Column(name = "job_name")
    private String jobName;

    @Column(name = "alias_name")
    private String aliasName;

    @Column(name = "job_group")
    private String jobGroup;

    @Column(name = "job_trigger")
    private String jobTrigger;

    private String status;

    @Column(name = "cron_expression")
    private String cronExpression;

    @Column(name = "is_sync")
    private Boolean isSync;

    @Column(name = "gmt_create")
    private Date gmtCreate;

    @Column(name = "gmt_modify")
    private Date gmtModify;

    private String description;

    /**
     * @return schedule_job_id
     */
    public Long getScheduleJobId() {
        return scheduleJobId;
    }

    /**
     * @param scheduleJobId
     */
    public void setScheduleJobId(Long scheduleJobId) {
        this.scheduleJobId = scheduleJobId;
    }

    /**
     * @return job_name
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * @param jobName
     */
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    /**
     * @return alias_name
     */
    public String getAliasName() {
        return aliasName;
    }

    /**
     * @param aliasName
     */
    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    /**
     * @return job_group
     */
    public String getJobGroup() {
        return jobGroup;
    }

    /**
     * @param jobGroup
     */
    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    /**
     * @return job_trigger
     */
    public String getJobTrigger() {
        return jobTrigger;
    }

    /**
     * @param jobTrigger
     */
    public void setJobTrigger(String jobTrigger) {
        this.jobTrigger = jobTrigger;
    }

    /**
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return cron_expression
     */
    public String getCronExpression() {
        return cronExpression;
    }

    /**
     * @param cronExpression
     */
    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    /**
     * @return is_sync
     */
    public Boolean getIsSync() {
        return isSync;
    }

    /**
     * @param isSync
     */
    public void setIsSync(Boolean isSync) {
        this.isSync = isSync;
    }

    /**
     * @return gmt_create
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * @param gmtCreate
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * @return gmt_modify
     */
    public Date getGmtModify() {
        return gmtModify;
    }

    /**
     * @param gmtModify
     */
    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}