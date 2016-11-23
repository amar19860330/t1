package com.t1.db.jdbc.business;

import com.t1.db.jdbc.core.BaseDao;
import com.t1.db.model.Friend;
import com.t1.db.model.Userinfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by amar on 16/9/17.
 */

@Repository
public class FriendDao extends BaseDao<Friend> {


    public int checkFriend(int userid, int friendId) {
        int relation = Friend.Relation_NotFriend;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("select id,userid,friendid,status from friend where userid=? and friendid=? ");
            List<Friend> list = queryList(sql.toString(), userid, friendId);

            if (list == null || list.size() == 0) {
                relation = Friend.Relation_NotFriend;
            } else {
                Friend lastStatus = list.get(0);
                relation = lastStatus.getStatus();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return relation;
    }

    public boolean inviteFriend(int userId, int friendUserId, String friendName) {
        boolean inviteSuccess = false;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("insert into friend (userid,friendid,friendshortname,status)values(");
            sql.append(userId).append(",").append(friendUserId);
            sql.append(",'").append(friendName).append("'");
            sql.append(",").append(Friend.Relation_IsValiding).append(")");
            add(sql.toString());
            inviteSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inviteSuccess;
    }

    public boolean dealInviteFriend(int userId, int whoInviteYouId, int howtoDeal) {
        boolean operation = false;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("update friend set status=?,modifydate=CURRENT_TIMESTAMP where userid=? and friendid=?");
            update(sql.toString(), howtoDeal, whoInviteYouId, userId);
            operation = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return operation;
    }

    public boolean editFriendName(int userId, int friendUserId, String friendShortName) {
        boolean operation = false;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("update friend set friendshortname=?,modifydate=CURRENT_TIMESTAMP where userid=? ");
            sql.append("and friendid=?");
            update(sql.toString(), friendShortName, userId, friendUserId);
            operation = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return operation;
    }

    public boolean deleteFriend(int userId, int friendId) {
        boolean operation = false;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("update friend set status=4,modifydate=CURRENT_TIMESTAMP where userid=? and friendid=? ");
            update(sql.toString(), userId, friendId);
            operation = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return operation;
    }

    public List<Friend> findFriends(int userid, int status) {
        List<Friend> list = null;
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("select friendid,friendshortname from friend where userid=? and status=? ");
            list = queryList(sql.toString(), userid, status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
