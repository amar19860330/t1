package com.t1.db.jdbc.core;

import java.util.List;

/**
 * Created by User on 2016/6/29.
 */
public interface IBaseDao<T> {
    public void create(T t) throws Exception;
    public T read(Integer id) throws Exception;
    public void delete(Integer id) throws Exception;
    public List<T> listAll() throws Exception;








    //查询
    public List<T> query(String sql,Object... params)throws Exception;
    public List<T> query(T t,Object... params)throws Exception;
    public int count(String sql,Object... params)throws Exception;


    public List<T> query(String sql)throws Exception;
    public List<T> query(T t)throws Exception;
    public int count(String sql)throws Exception;



    //删除类
    public int delete(String sql) throws Exception;

    //更新类
    public int update(String sql)throws Exception;

    public void update(T t) throws Exception;

    //新增类
    public int add(T t) throws Exception;



}
