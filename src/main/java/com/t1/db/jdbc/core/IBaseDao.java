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



    public List<T> query(String sql)throws Exception;
    public List<T> query(T t)throws Exception;
    public void update(T t) throws Exception;
    public void delete(int id) throws Exception;
    public int add(T t) throws Exception;

}
