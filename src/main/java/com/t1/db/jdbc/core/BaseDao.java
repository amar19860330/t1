package com.t1.db.jdbc.core;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.*;
import java.sql.Date;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 * Created by User on 2016/6/29.
 */
//@Repository
public abstract class BaseDao<T> implements IBaseDao<T> {

    protected JdbcTemplate jdbcTemplate;

    protected Class<T> type;

    @SuppressWarnings("unchecked")
    public BaseDao() {
        // 获取真实泛型数据类型
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    @SuppressWarnings("unchecked")
    public Class<T> getType() {
        return type;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Resource
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //***********************************************
    //查询
    @SuppressWarnings("unchecked")
    public List<T> queryList(String sql, Object... params) throws Exception {
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(type), params);
    }

    @SuppressWarnings("unchecked")
    public List<T> queryList(String sql) throws Exception {
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(type));
    }

    public Map<String, Object> queryMap(String sql) {
        return jdbcTemplate.queryForMap(sql);
    }

    public Map<String, Object> queryMap(String sql, Object... params) {
        return jdbcTemplate.queryForMap(sql, params);
    }

    public List<Map<String, Object>> queryMapList(String sql) {
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> queryMapList(String sql, Object... params) {
        return jdbcTemplate.queryForList(sql, params);
    }

    //删除类
    public int delete(String sql) throws Exception {
        return jdbcTemplate.update(sql);
    }

    public int delete(String sql, Object... param) throws Exception {
        return jdbcTemplate.update(sql, param);
    }

    //更新类
    public int update(String sql) throws Exception {
        return jdbcTemplate.update(sql);
    }

    public int update(String sql, Object... params) throws Exception {
        return jdbcTemplate.update(sql, params);
    }

    //新增类
    public int add(String sql) throws Exception {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    }
                }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    public int add(String sql, Object... params) throws Exception {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = setParams(connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS), params);
                        return ps;
                    }
                }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    public PreparedStatement setParams(PreparedStatement ps, Object[] params) throws SQLException {

        if (params != null)
            for (int i = 0; i < params.length; i++) {
                Object param = params[i];
                int index = i + 1;
                if (param instanceof Integer) {
                    ps.setInt(index, Integer.parseInt(param.toString()));
                } else if (param instanceof Long) {
                    ps.setLong(index, Long.parseLong(param.toString()));
                } else if (param instanceof String) {
                    ps.setString(index, param.toString());
                }else if( param instanceof  java.sql.Date){
                    ps.setDate(index,(Date)param);
                }
                else if (param instanceof java.util.Date) {
                    java.util.Date paramDate = (java.util.Date)param;
                    ps.setObject(index, new java.sql.Timestamp( paramDate.getTime()));
                    //ps.setDate(index, new Date(paramDate.getTime()));
                } else if (param instanceof Float) {
                    ps.setFloat(index, Float.parseFloat(param.toString()));
                } else if (param instanceof Boolean) {
                    ps.setBoolean(index, Boolean.parseBoolean(param.toString()));
                } else {
                    ps.setObject(index, param);
                }
            }
        return ps;
    }

    //***********************************************************
}
