package com.vastiny.javaweb.quartz.mvcweb.mapper.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author yangzhi
 * @since 2016/2/26
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
