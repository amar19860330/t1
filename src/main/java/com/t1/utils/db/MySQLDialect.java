package com.t1.utils.db;

/**
 * Created by User on 2016/4/25.
 */

/**
 * @author Freud
 * @ClassName: MySQLDialect <br>
 * @Description: MySql的分页方言实现 <br>
 */
public class MySQLDialect extends Dialect {

    public boolean supportsLimitOffset() {
        return true;
    }

    public boolean supportsLimit() {
        return true;
    }

    /**
     * @param sql
     * @param pageCount         每页多少条数据
     * @param offsetPlaceholder
     * @param pageNo            第几页
     * @param limitPlaceholder
     * @return
     */
    public String getLimitString(String sql, int pageCount, String offsetPlaceholder, int pageNo, String limitPlaceholder) {

        if (pageCount > 0 && pageNo > 0) {
            int start = pageCount * (pageNo - 1) + 1 - 1; //从0开始
            //int end = pageCount * pageNo;
            return sql + " limit " + start + "," + pageCount;
        } else {
            return sql;
        }
    }

//    public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder)
//    {
//        if (offset > 0)
//        {
//            return sql + " limit " + offsetPlaceholder + "," + limitPlaceholder;
//        }
//        else
//        {
//            return sql + " limit " + limitPlaceholder;
//        }
//    }
}
