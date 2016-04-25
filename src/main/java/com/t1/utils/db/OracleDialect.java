package com.t1.utils.db;

/**
 * Created by User on 2016/4/25.
 */
/**
 *
 * @ClassName: OracleDialect <br>
 * @Description: Oracle的分页方言实现 <br>
 *
 * @author Freud
 */
public class OracleDialect extends Dialect
{

    public boolean supportsLimit()
    {
        return true;
    }

    public boolean supportsLimitOffset()
    {
        return true;
    }

    public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder)
    {
        sql = sql.trim();
        boolean isForUpdate = false;
        if (sql.toLowerCase().endsWith(" for update"))
        {
            sql = sql.substring(0, sql.length() - 11);
            isForUpdate = true;
        }

        StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
        if (offset > 0)
        {
            pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
        }
        else
        {
            pagingSelect.append("select * from ( ");
        }
        pagingSelect.append(sql);
        if (offset > 0)
        {
            // int end = offset+limit;
            String endString = offsetPlaceholder + "+" + limitPlaceholder;
            pagingSelect.append(" ) row_ ) where rownum_ <= " + endString + " and rownum_ > " + offsetPlaceholder);
        }
        else
        {
            pagingSelect.append(" ) where rownum <= " + limitPlaceholder);
        }

        if (isForUpdate)
        {
            pagingSelect.append(" for update");
        }

        return pagingSelect.toString();
    }
}
