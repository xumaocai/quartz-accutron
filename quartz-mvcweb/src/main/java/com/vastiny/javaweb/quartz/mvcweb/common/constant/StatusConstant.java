package com.vastiny.javaweb.quartz.mvcweb.common.constant;

/**
 * @author yangzhi
 * @since 2016/1/18
 */
public class StatusConstant {

    /**
     * 返回 项目中 所有任务相关的状态码
     */
    public enum TaskStatus {
        NONE("NONE", "未改变"),
        WAITING("WAITING", "任务等待"),
        ACQUIRED("ACQUIRED", "获得，不知道怎么翻译"),
        EXECUTING("EXECUTING", "正在执行中"),
        PAUSED("PAUSED", "任务暂停"),
        BLOCKED("BLOCKED", "线程阻塞"),
        PAUSED_BLOCKED("PAUSED_BLOCKED", "任务线程阻塞"),
        ERROR("ERROR", "执行错误");


        private String code;
        private String description;

        TaskStatus(String code, String description) {
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

        public static TaskStatus get(String code) {
            for (TaskStatus taskStatus : values()) {
                if (taskStatus.getCode().equals(code)) {
                    return taskStatus;
                }
            }
            return null;
        }

        @Override
        public String toString() {
            return "TaskStatus{" +
                    "code='" + code + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }
    }

}
