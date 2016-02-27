package com.vastiny.javaweb.quartz.mvcweb.common.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GsonUtil {

    private static final Logger log = LoggerFactory.getLogger(GsonUtil.class);

    private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";
    private static Gson gson = new GsonBuilder().setDateFormat(dateFormat).create();


    public static Object jsonToEntity(String json, Class<?> classOfT) throws Exception {
        return gson.fromJson(json, classOfT);
    }

    public static String entityToJson(Object object) {
        return gson.toJson(object);
    }

    public static String toJson(Object object) {
        return gson.toJson(object);
    }

}
