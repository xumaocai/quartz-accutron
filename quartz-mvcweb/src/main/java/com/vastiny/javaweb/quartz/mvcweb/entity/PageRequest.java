package com.vastiny.javaweb.quartz.mvcweb.entity;


import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by Corey.xu on 2015/10/22.
 */
public class PageRequest
{
    private int draw;
    private List<Columns> columns;
    private int start; //当前页开始行
    private int length;//每一页多少条数据
    private Search search;
    private  List<Order> order;
    private  String extra_search;
    private  int page;
    public PageRequest() {
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Search getSearch() {
        return search;
    }

    public void setSearch(Search search) {
        this.search = search;
    }

    public List<Columns> getColumns() {
        return columns;
    }

    public void setColumns(List<Columns> columns) {
        this.columns = columns;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    public String getExtra_search() {
        return extra_search;
    }

    public void setExtra_search(String extra_search) {
        this.extra_search = extra_search;
    }

    public int getPage() {

        return  start /length + 1;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public  class  Columns{
        private String data;
        private String name;
        private boolean searchable;
        private boolean orderable;
        private Search search;

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isSearchable() {
            return searchable;
        }

        public void setSearchable(boolean searchable) {
            this.searchable = searchable;
        }

        public boolean isOrderable() {
            return orderable;
        }

        public void setOrderable(boolean orderable) {
            this.orderable = orderable;
        }

        public Search getSearch() {
            return search;
        }

        public void setSearch(Search search) {
            this.search = search;
        }
    }
   public class Order{
        private int column;
        private String dir;
        public int getColumn() {
            return column;
        }

        public void setColumn(int column) {
            this.column = column;
        }

        public String getDir() {
            return dir;
        }

        public void setDir(String dir) {
            this.dir = dir;
        }
    }

   public class Search{
        private String value;
        private boolean regex;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public boolean isRegex() {
            return regex;
        }

        public void setRegex(boolean regex) {
            this.regex = regex;
        }
    }
    public String buildSimpleOrder(){
      String sortColumn =  columns.get(order.get(0).column).getData();
        sortColumn = camelhumpToUnderline(sortColumn);
        StringBuilder stringBuilder =new StringBuilder(sortColumn);
        stringBuilder.append(" ");
        stringBuilder.append(order.get(0).dir);
        return stringBuilder.toString();
    }

    public static String camelhumpToUnderline(String str){
        int size;
        char[] chars;
        StringBuilder sb = new StringBuilder((size = (chars = str.toCharArray()).length) * 3 / 2 + 1);
        for(int i = 0; i < size; ++i) {
            char c = chars[i];
            String s =String.valueOf(c);
            if(StringUtils.isAllUpperCase(s)) {
                sb.append('_').append(StringUtils.lowerCase(s));
            } else {
                sb.append(StringUtils.lowerCase(s));
            }
        }

        return sb.charAt(0) == 95?sb.substring(1):sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(camelhumpToUnderline("appName"));
    }
}
