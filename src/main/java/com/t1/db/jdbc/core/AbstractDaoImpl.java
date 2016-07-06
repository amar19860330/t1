package com.t1.db.jdbc.core;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 * Created by User on 2016/6/29.
 */
@Repository
public class AbstractDaoImpl<T> implements IBaseDao<T> {

    protected JdbcTemplate jdbcTemplate;

    protected Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDaoImpl() {
        // 获取真实泛型数据类型
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //查询
    @Override
    public List<T> queryList(String sql, Object... params) throws Exception {
        return jdbcTemplate.queryForList(sql, type, params);
    }

    @Override
    public List<T> queryList(String sql) throws Exception {
        return jdbcTemplate.queryForList(sql, type);
    }

    @Override
    public Map<String,Object> queryMap(String sql) {
        return jdbcTemplate.queryForMap(sql);
    }

    @Override
    public Map<String,Object> queryMap(String sql,Object...params) {
        return jdbcTemplate.queryForMap(sql,params);
    }

    //删除类
    @Override
    public int delete(String sql) throws Exception {
        return jdbcTemplate.update(sql);
    }

    @Override
    public int delete(String sql, Object... param) throws Exception {
        return jdbcTemplate.update(sql,param);
    }

    //更新类
    @Override
    public int update(String sql) throws Exception {
        return jdbcTemplate.update(sql);
    }
    @Override
    public int update(String sql,Object...params) throws Exception {
        return jdbcTemplate.update(sql,params);
    }
    //新增类
    @Override
    public int add(String sql) throws Exception {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        return connection.prepareStatement(sql,new String[]{"id"});
                    }
                }, keyHolder);
        return keyHolder.getKey().intValue();
    }
    @Override
    public int add(String sql, Object... params) throws Exception {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        return connection.prepareStatement(sql);
                    }
                }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public int add(T t) throws Exception {
        return 0;
    }
}
