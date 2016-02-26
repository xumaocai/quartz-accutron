package com.vastiny.javaweb.quartz.mvcweb.entity;

import java.util.List;

/**
 * Created by Corey.xu on 2015/10/16.
 */
public class PageResponse
{
    private  int draw;
    private long  recordsTotal;
    private long  recordsFiltered;
    private List data;

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
