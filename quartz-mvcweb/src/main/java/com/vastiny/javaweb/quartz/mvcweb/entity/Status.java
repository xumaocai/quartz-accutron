package com.vastiny.javaweb.quartz.mvcweb.entity;

/**
 * @author yangzhi
 * @since 2016/1/15
 */
public class Status {

    /**
     * 状态码
     * 默认值为 -1，不可更改
     */
    private int code;

    /**
     * json 数据
     */
    private String data;

    public Status(int code, String data) {
        this.code = code;
        this.data = data;
    }

    public Status() {
        this.code = -1;
        this.data = "";
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Status{" +
                "code='" + code + "'," +
                "data='" + data + "'" +
                "}";
    }
}
