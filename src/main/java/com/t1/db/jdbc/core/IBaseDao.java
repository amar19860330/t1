package com.t1.db.jdbc.core;

import java.util.List;
import java.util.Map;

/**
 * Created by User on 2016/6/29.
 */
public interface IBaseDao<T> {

    //查询类
    public List<T> queryList(String sql, Object... params) throws Exception;

    public List<T> queryList(String sql) throws Exception;

    public Map<String, Object> queryMap(String sql);

    public Map<String, Object> queryMap(String sql, Object... params);

    //删除类
    public int delete(String sql) throws Exception;

    public int delete(String sql, Object... param) throws Exception;

    //更新类
    public int update(String sql) throws Exception;

    public int update(String sql, Object... params) throws Exception;

    //新增类
    //public int add(T t) throws Exception;

    public int add(String sql) throws Exception;

    public int add(String sql,Object...params) throws Exception;
}
