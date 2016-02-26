package com.vastiny.javaweb.quartz.mvcweb.service.base;

import com.vastiny.javaweb.quartz.mvcweb.entity.PageRequest;
import com.vastiny.javaweb.quartz.mvcweb.mapper.base.BaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;


public abstract class BaseService<T> {

    private static final Logger LOG = LoggerFactory.getLogger(BaseService.class);

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private BaseMapper<T> mapper;

    //Base Read Function Start
    public List<T> findAll() {
        return mapper.selectAll();
    }

    public T find(Object id) {
        return mapper.selectByPrimaryKey(id);
    }

    public List<T> findListByEntity(T entity) {
        return (entity == null ? null : mapper.select(entity));
    }

    public T findOneByEntity(T entity) {
        return mapper.selectOne(entity);
    }

    public int count(T entity) {
        return mapper.selectCount(entity);
    }

    public List<T> findByExample(Object example) {
        return mapper.selectByExample(example);
    }
    //Base Read Function End

    //Base Create Function Start
    public int insert(T entity) {
        return mapper.insert(entity);
    }

    public int insertUseGeneratedKeys(T entity) {
        return mapper.insertUseGeneratedKeys(entity);
    }

    public int insertWithoutNullColumn(T entity) {
        return mapper.insertSelective(entity);
    }
    //Base Create Function End

    //Base Update Function Start
    public int update(T entity) {
        return mapper.updateByPrimaryKey(entity);
    }

    public int updateWithoutNullColumn(T entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    public int updateWithoutNullColumnByColumn(T entity, String condition, Class<T> entityClass) {
        Example example = new Example(entityClass);
        example.createCriteria().andCondition(condition);
        return mapper.updateByExampleSelective(entity, example);
    }
    //Base Update Function End

    //Base Delete Function Start
    public int delete(Object id) {
        return mapper.deleteByPrimaryKey(id);
    }

    public int deleteByEntity(T entity) {
        return mapper.delete(entity);
    }

    public int deleteByColumn(Object value, String keyColumn, Class<?> entityClass) {
        Example example = new Example(entityClass);
        example.createCriteria().andCondition(keyColumn + '=', value);
        return mapper.deleteByExample(example);
    }
    //Base Delete Function End

    private StringBuilder buildOrderByString(List<PageRequest.Order> orders, List<PageRequest.Columns> columnses) {
        StringBuilder orderBy = new StringBuilder();
        if (orders.size() <= 2) {
            boolean flag = false;
            for (PageRequest.Order order : orders) {
                if (!flag) {
                    flag = true;
                } else {
                    orderBy.append(',');
                }
                String sortColumn = columnses.get(order.getColumn()).getData();
                String[] splitSortColumns = sortColumn.split("\\.");
                sortColumn = splitSortColumns[splitSortColumns.length - 1];
                sortColumn = StringUtil.camelhumpToUnderline(sortColumn);

                orderBy.append(sortColumn)
                        .append(' ').append(order.getDir());
            }

        } else {
            LOG.error("order list size > 2. order by string build failed. ");
        }
        return orderBy;
    }
}

