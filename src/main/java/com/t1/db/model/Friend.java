package com.t1.db.model;

/**
 * Created by amar on 16/9/15.
 */
public class Friend {

//    public static int Status_IsFriend = 1;
//    public static int Status_Validate = 2;
//    public static int Status_Refused = 3;
//    public static int Status_Deleted = 4;

    public static int Relation_NotFriend = 0;
    public static int Relation_IsFriend = 1;
    public static int Relation_IsValiding = 2;
    public static int Relation_IsRefused = 3;
    public static int Relation_IsDeleted = 4;

    private int id;
    private int userid;
    private int friendid;
    private String friendshortname;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getFriendid() {
        return friendid;
    }

    public void setFriendid(int friendid) {
        this.friendid = friendid;
    }

    public String getFriendshortname() {
        return friendshortname;
    }

    public void setFriendshortname(String friendshortname) {
        this.friendshortname = friendshortname;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
