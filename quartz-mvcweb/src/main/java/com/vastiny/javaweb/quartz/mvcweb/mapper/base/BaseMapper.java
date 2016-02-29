package com.vastiny.javaweb.quartz.mvcweb.mapper.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.base.BaseSelectMapper;
import tk.mybatis.mapper.common.base.update.UpdateByPrimaryKeyMapper;

/**
 * @author yangzhi
 * @since 2016/2/26
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T>,BaseSelectMapper<T>, UpdateByPrimaryKeyMapper<T> {
}
