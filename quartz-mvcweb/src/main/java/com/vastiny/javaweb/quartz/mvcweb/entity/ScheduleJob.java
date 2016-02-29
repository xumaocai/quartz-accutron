package com.vastiny.javaweb.quartz.mvcweb.entity;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by liyd on 12/19/14.
 */
@Table(name = "schedule_job")
public class ScheduleJob {

    private static final long  serialVersionUID = -4216107640768329946L;

    /** 任务调度的参数key */
    public static final String JOB_PARAM_KEY    = "scheduleJob";

    /** 任务id */
    @Id
    @Column(name = "schedule_job_id")
    private Long             scheduleJobId;

    /** 任务名称 */
    @Column(name = "job_name")
    private String             jobName;

    /** 任务别名 */
    @Column(name = "alias_name")
    private String             aliasName;

    /** 任务分组 */
    @Column(name = "job_group")
    private String             jobGroup;

    /** 触发器 */
    @Column(name = "job_trigger")
    private String             jobTrigger;

    /** 任务状态 */
    private String             status;

    /** 任务运行时间表达式 */
    @Column(name = "cron_expression")
    private String             cronExpression;

    /** 是否异步 */
    @Column(name = "is_sync")
    private Boolean            isSync;

    /** 任务描述 */
    private String             description;

    /** 创建时间 */
    @Column(name = "gmt_create")
    private Date               gmtCreate;

    /** 修改时间 */
    @Column(name = "gmt_modify")
    private Date               gmtModify;

    public Long getScheduleJobId() {
        return scheduleJobId;
    }

    public void setScheduleJobId(Long scheduleJobId) {
        this.scheduleJobId = scheduleJobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getJobTrigger() {
        return jobTrigger;
    }

    public void setJobTrigger(String jobTrigger) {
        this.jobTrigger = jobTrigger;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public Boolean getIsSync() {
        return isSync;
    }

    public void setIsSync(Boolean isSync) {
        this.isSync = isSync;
    }
}
