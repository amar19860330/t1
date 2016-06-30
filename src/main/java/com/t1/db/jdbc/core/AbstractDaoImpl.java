package com.t1.db.jdbc.core;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 * Created by User on 2016/6/29.
 */
public abstract class AbstractDaoImpl<T> implements IBaseDao<T> {

    protected JdbcTemplate jdbcTemplate;

    protected String table_name;

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

    public List<T> query(String sql)throws Exception{
       return jdbcTemplate.queryForList(sql,type);
        //return null ;//jdbcTemplate.queryForList(sql);
    }



    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }




    @Override
    public void create(T t) throws Exception {
        if (t != null) {
            try {
                Field[] fields = t.getClass().getDeclaredFields();
                String nameString = "";
                String valueString = "";
                String sql = "insert into " + table_name;
                Object[] values = new Object[fields.length];
                //设置SQL中的参数名和参数个数
                for (int i = 0; i < fields.length; i++) {
                    fields[i].setAccessible(true);//设置private属性可访问

                    nameString += fields[i].getName() + ",";
                    valueString += "?,";

                    values[i] = fields[i].get(t);
                }
                //组织SQL语句
                nameString = "(" + nameString.substring(0, nameString.length() - 1) + ")values";
                valueString = "(" + valueString.substring(0, valueString.length() - 1) + ")";
                sql = sql + nameString + valueString;

                jdbcTemplate.update(sql, values);

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }

    @Override
    public T read(Integer id) throws Exception {

        final Class tempType = type;

        T t = jdbcTemplate.queryForObject(
                "select * from " + table_name + " where id=?;", new Object[]{id},
                new RowMapper<T>() {

                    public T mapRow(java.sql.ResultSet rs, int arg1)
                            throws SQLException {

                        try {

                            Object temp = tempType.newInstance();//实例化一个类用于获取field的值

                            Field[] fields = tempType.getDeclaredFields();

                            for (int i = 0; i < fields.length; i++) {
                                fields[i].setAccessible(true);//设置field可访问
                                fields[i].set(temp, rs.getObject(fields[i].getName()));//设置field的值
                            }
                            return (T) temp;
                        } catch (Exception e) {
                            e.printStackTrace();
                            return null;
                        }
                    }
                });
        return t;
    }

    @Override
    public void update(T t) throws Exception {

        if (t != null) {
            try {
                String sql = "update " + table_name + " set ";

                Field[] fields = t.getClass().getDeclaredFields();

                Object[] values = new Object[fields.length];

                int index = 0;
                //构造SQL语句
                for (Field field : fields) {
                    field.setAccessible(true);
                    //判断是不是主键
                    if (field.getName().equals("id")) {
                        values[values.length - 1] = field.get(t);
                    } else {
                        sql += field.getName() + "=?,";
                        values[index] = field.get(t);
                        index++;
                    }
                }

                sql = sql.substring(0, sql.length() - 1) + " where id=?";//为了去掉逗号

                jdbcTemplate.update(sql, values);

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Integer id) throws Exception {

        jdbcTemplate.update("delete from " + table_name + " where id=?", new Object[]{id});
    }

    @Override
    public List<T> listAll() throws Exception {

        final Class tempType = type;

        return jdbcTemplate.query("select id,startTime,stateTime,positionInfo,sourceInfo,destinationInfo from " + table_name + ";",
                new RowMapper<T>() {

                    @Override
                    public T mapRow(ResultSet rs, int arg1) throws SQLException {

                        try {

                            Object temp = tempType.newInstance();//实例化一个类用于获取field的值

                            Field[] fields = tempType.getDeclaredFields();

                            for (int i = 0; i < fields.length; i++) {
                                fields[i].setAccessible(true);//设置field可访问
                                fields[i].set(temp, rs.getObject(fields[i].getName()));//设置field的值
                            }
                            return (T) temp;
                        } catch (Exception e) {
                            e.printStackTrace();
                            return null;
                        }
                    }
                });
    }
}
