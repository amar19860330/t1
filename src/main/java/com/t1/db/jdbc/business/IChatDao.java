package com.t1.db.jdbc.business;

import com.t1.db.jdbc.core.IBaseDao;
import com.t1.db.model.Friend;
import com.t1.db.model.Userinfo;

import java.util.List;

/**
 * Created by amar on 16/9/15.
 */
public interface IChatDao extends IBaseDao {

    Userinfo findUser(String username);

    int regist(String userName, String shortName, String pw);

    Userinfo login(String userName, String pw);

    /***
     *
     * @param userid 根据id进行下面的参数设置
     * @param shortName
     * @param pw
     * @param addUser
     * @return
     */
    boolean updateUserSetting(int userid, String shortName, String pw, int addUser);

    boolean inviteFriend(int userId, String friendUserName);

    boolean editUserToken(int userId, String token);

    /***
     * 处理别人的邀请请求，也可以用于更新关系状态
     * @param userId
     * @param whoInviteYouId 申请人用户id
     * @param howtoDeal 怎么处理，同意：Friend.Status_IsFriend，不同意Friend.Status_Refused
     * @return
     */
    boolean dealInviteFriend(String userId, int whoInviteYouId,int howtoDeal);

    boolean editFriendName(int userId, int friendUserId, String friendShortName);

    boolean deleteFriend(int userId, int friendId);

    /**
     * @param status @see Friend
     * @return
     */
    List<Friend> findFriends(int userid, int status);

    /**
     * 查找是不是已经加过好友
     * @param userid
     * @param friendId
     * @return
     */
    int checkFriend(int userid, int friendId);

}
