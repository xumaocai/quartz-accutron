package com.vastiny.javaweb.quartz.mvcweb.common.constant;

/**
 * @author yangzhi
 * @since 2016/1/18
 */
public class StatusConstant {

    /**
     * 返回 项目中 所有任务相关的状态码
     */
    public enum TaskStatusCode {
        NORMAL("NORMAL", "正常状态"),
        PAUSED("PAUSED", "暂停状态"),
        COMPLETE("COMPLETE", "触发器完成，但是任务可能还正在执行中"),
        BLOCKED("BLOCKED", "线程阻塞状态"),
        ERROR("ERROR", "出现错误");

        private String code;
        private String description;

        TaskStatusCode(String code, String description) {
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

        @Override
        public String toString() {
            return "TaskStatusCode{" +
                    "code='" + code + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }
    }

}
