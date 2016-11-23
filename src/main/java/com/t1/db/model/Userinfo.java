package com.t1.db.model;

import java.util.Date;

/**
 * Created by amar on 16/9/15.
 */
public class Userinfo {

    public static int AddUserStatus_DontNeedCheck = 1;
    public static int AddUserStatus_NeedCheck = 2;
    public static int AddUserStatus_Refuse = 3;

    public static int Status_Unvalidate = 1;
    public static int Status_Normal = 2;
    public static int Status_Forbid = 3;
    public static int Status_Incorrect_pw = 4;

    private int id;
    private String username;
    private String shortname;
    private String password;
    private Date registdate;
    private Date lastlogindate;
    private int adduser; //1不验证添加，2需要同意才添加，3拒绝添加
    private String token;
    private int status; //'1未验证，2状态正常，3禁止登录'

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistdate() {
        return registdate;
    }

    public void setRegistdate(Date registdate) {
        this.registdate = registdate;
    }

    public Date getLastlogindate() {
        return lastlogindate;
    }

    public void setLastlogindate(Date lastlogindate) {
        this.lastlogindate = lastlogindate;
    }

    public int getAdduser() {
        return adduser;
    }

    public void setAdduser(int adduser) {
        this.adduser = adduser;
    }
}
