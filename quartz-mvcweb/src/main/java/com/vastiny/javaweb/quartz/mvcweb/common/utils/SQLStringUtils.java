package com.vastiny.javaweb.quartz.mvcweb.common.utils;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by Corey.xu on 2016/2/1.
 */
public class SQLStringUtils {
    protected final static Logger log = LoggerFactory.getLogger(SQLStringUtils.class);
    //组装动态查询参数
    public static Map<String,Object> getDynamicSearchParams(String param){
        final int KEY_VALUE_SIZE=2;
        Map<String,Object> mapParams = Maps.newHashMap();
        String commaParams[]=param.split(",");
        String colonParams [];
        for (String str:commaParams){
            colonParams = str.split(":");
            if (colonParams.length==KEY_VALUE_SIZE) {
                mapParams.put(colonParams[0], "%"+colonParams[1]+"%");
            }else {
                log.warn(String.format("the colon params size is %d != 2",colonParams.length));
            }
        }
        return mapParams;
    }
}
