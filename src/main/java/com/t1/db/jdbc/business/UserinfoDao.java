package com.t1.db.jdbc.business;

import com.t1.db.jdbc.core.BaseDao;
import com.t1.db.model.Userinfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by amar on 16/9/17.
 */

@Repository
public class UserinfoDao extends BaseDao<Userinfo> {
    public Userinfo findAllUser(String username) {
        Userinfo userinfo = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("select username,shortname,registdate,lastlogindate,adduser,token from userinfo ");
            sql.append(" where username=?");
            List<Userinfo> list = queryList(sql.toString(), username);
            userinfo = list == null ? null : list.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userinfo;
    }

    public Userinfo findUserByStatus(String username, int status) {
        Userinfo userinfo = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("select username,shortname,registdate,lastlogindate,adduser,token from userinfo ");
            sql.append(" where username=? and status=?");
            List<Userinfo> list = queryList(sql.toString(), username, status);
            userinfo = list == null ? null : list.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userinfo;
    }

    /**
     * @param userid
     * @param status 1未验证，2状态正常，3禁止登录
     * @return
     */
    public boolean editUserStatus(int userid, int status) {
        boolean editSuccess = false;

        try {
            StringBuilder sql = new StringBuilder();
            sql.append("update userinfo set status =? where id=?");
            update(sql.toString(), status, userid);
            editSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return editSuccess;
    }

    public int regist(String userName, String shortName, String pw, String token) {
        int id = -1;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("insert into userinfo(username,shortname,password,adduser,token,lastlogindate,status)values(");
            sql.append("'").append(userName).append("'");
            sql.append(",'").append(shortName).append("'");
            sql.append(",PASSWORD('").append(pw).append("')");
            sql.append(",1");
            sql.append(",'");
            sql.append(token).append("'");
            sql.append(",").append("CURRENT_TIMESTAMP");
            sql.append(",1");//1未验证
            sql.append(")");
            id = add(sql.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }


    public Userinfo login(String userName, String pw) {
        Userinfo userinfo = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("select username,shortname,registdate,lastlogindate,adduser,token from userinfo ");
            sql.append(" where username=? and password=PASSWORD(?) and status=2");
            List<Userinfo> list = queryList(sql.toString(), userName, pw);
            userinfo = list == null ? null : list.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userinfo;
    }

    public boolean editUserToken(int userId, String token) {
        boolean editSuccess = false;
        StringBuilder sql = new StringBuilder();
        try {
            sql.append("update userinfo set token = ? where userid=?");
            int row = update(sql.toString(), token, userId);
            if (row == 1)//只更新了一行
                editSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return editSuccess;
    }

    public boolean updateUserSetting(int userid, String shortName, String pw, int addUser) {
        boolean operationStatus = false;
        StringBuilder sql = new StringBuilder();
        try {
            sql.append("update userinfo set shortname=?,pw=PASSWORD(?),adduser=? where userid=?");
            int row = update(sql.toString(), shortName, pw, addUser, userid);
            if (row == 1)//只更新了一行
                operationStatus = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return operationStatus;
    }
}
