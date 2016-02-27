package com.vastiny.javaweb.quartz.mvcweb.common.enums;

import com.vastiny.javaweb.quartz.mvcweb.task.DataConversionTask;
import com.vastiny.javaweb.quartz.mvcweb.task.SyncUserDataTask;

/**
 * @author yangzhi
 * @since 2016/2/27
 */
public enum JobClassEnum {
    DATACONVERSION(DataConversionTask.class, "数据转换任务"),
    SYNCUSERDATA(SyncUserDataTask.class, "用户同步数据");

    private Class type;
    private String desc;

    JobClassEnum(Class type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
