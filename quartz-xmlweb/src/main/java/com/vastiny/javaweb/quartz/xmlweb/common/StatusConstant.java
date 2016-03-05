package com.vastiny.javaweb.quartz.xmlweb.common;

/**
 * @author yangzhi
 * @since 2016/1/18
 */
public class StatusConstant {

    /**
     * 返回 项目中 所有任务相关的状态码
     */
    public enum SchedulerStatus {
        NONE("NONE", "未改变"),
        NORMAL("NORMAL", "任务正常"),
        WAITING("WAITING", "任务等待"),
        ACQUIRED("ACQUIRED", "获得，不知道怎么翻译"),
        EXECUTING("EXECUTING", "正在运行"),
        PAUSED("PAUSED", "任务暂停"),
        BLOCKED("BLOCKED", "上一个正在运行「排队中」"),
        PAUSED_BLOCKED("PAUSED_BLOCKED", "任务暂停「排队中」"),
        ERROR("ERROR", "任务运行错误");


        private String code;
        private String description;

        SchedulerStatus(String code, String description) {
            this.code = code;
            this.description = description;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public static SchedulerStatus get(String code) {
            for (SchedulerStatus taskStatus : values()) {
                if (taskStatus.getCode().equals(code)) {
                    return taskStatus;
                }
            }
            return null;
        }

        @Override
        public String toString() {
            return "SchedulerStatus{" +
                    "code='" + code + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }
    }

}
