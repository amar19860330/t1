package com.t1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.t1.db.jdbc.business.FriendDao;
import com.t1.db.jdbc.business.IChatDao;
import com.t1.db.jdbc.business.UserinfoDao;
import com.t1.db.model.Friend;
import com.t1.db.model.Userinfo;
import com.t1.framework.BaseController;
import com.t1.model.Data;
import com.t1.utils.MailUtil;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by amar on 16/9/14.
 */
@Controller
@RequestMapping("/chat")
public class ChatController extends BaseController {

    @Resource(name = "userinfoDao")
    UserinfoDao userinfoDao;

    @Resource(name = "friendDao")
    FriendDao friendDao;

    @Resource(name = "mailUtil")
    MailUtil mailUtil;

    @RequestMapping(value = "/checkUser")
    public void checkUserIsExists(@RequestBody String params, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String data = "";
        Map paramMap = paramsMap(params);
        String username = paramMap.get("username").toString();

        Userinfo findUser = userinfoDao.findAllUser(username);
        boolean isExist = findUser != null;
        if (isExist) {
            data = Data.ErrorData(Data.Error_Exist_Username).toString();
        } else {
            data = Data.OkData().toString();
        }
        response.getOutputStream().write(data.getBytes());
    }

    @RequestMapping(value = "/regist")
    public void regist(@RequestBody String params, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map paramMap = paramsMap(params);
        String username = paramMap.get("username").toString();
        String password = paramMap.get("password").toString();

        String token = new Random().nextInt(100000) + "";
        int id = userinfoDao.regist(username, "", password, token);
        String data = "";
        if (id > 0)
            data = Data.OkData(String.format("{\"id\":%d}", id)).toString();
        else
            data = Data.ErrorData(Data.Error_Regist_Fail).toString();
        sendEmailCode(username, token);
        response.getOutputStream().write(data.getBytes());
    }

    @RequestMapping(value = "/login")
    public void login(@RequestBody String params, HttpServletRequest request, HttpServletResponse response) throws
            Exception {
        Map paramMap = paramsMap(params);
        String username = paramMap.get("username").toString();
        String password = paramMap.get("password").toString();


        Userinfo loginUser = userinfoDao.login(username, password);
        String data = "";
        if (loginUser == null)
            data = Data.ErrorData(Data.Error_Password_Or_Username).toString();
        else if (loginUser.getStatus() == Userinfo.Status_Normal) {
            String token = setToken(loginUser.getId());
            data = String.format("{\"token\":\"%s\"}", token);
            data = Data.OkData(data).toString();
        } else
            data = Data.ErrorData(Data.Error_User_Status).toString();

        response.getOutputStream().write(data.getBytes());
    }

    private String setToken(int userId) {
        String token = new Random().nextInt(100000) + "";
        userinfoDao.editUserToken(userId, token);
        return token;
    }


    @RequestMapping(value = "/sendEmail")
    public void sendEmail(@RequestBody String params, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map paramMap = paramsMap(params);
        String username = paramMap.get("username").toString();
        int id = (int) paramMap.get("id");

        String token = new Random().nextInt(100000) + "";
        userinfoDao.editUserToken(id, token);
        sendEmailCode(username, token);

        sendData(response, Data.OkData());
    }

    public void sendEmailCode(String email, String token) {
        mailUtil.senEmail(email, "chat验证码", "您的验证码是:" + token);
    }

    @RequestMapping(value = "/validateEmail")
    public void validateEmail(@RequestBody String params, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map paramMap = paramsMap(params);
        String username = paramMap.get("username").toString();
        String token = paramMap.get("token").toString();

        Data data = null;
        Userinfo userinfo = userinfoDao.findAllUser(username);
        if (userinfo == null) {
            data = Data.ErrorData(Data.Error_User_Not_Exist);
        } else if (userinfo.getStatus() == Userinfo.Status_Normal) {
            data = Data.ErrorData(Data.Error_IsValidated);
        } else if (token.equals(userinfo.getToken())) {
            userinfoDao.editUserStatus(userinfo.getId(), Userinfo.Status_Normal);
            data = Data.OkData();
        } else if (!token.equals(userinfo.getToken())) {
            data = Data.ErrorData(Data.Error_Incorrect_Token);
        }
        sendData(response, data);
    }

    @RequestMapping(value = "/findUserForInvite")
    public void findUserForInvite(@RequestBody String params, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map paramMap = paramsMap(params);
        int userId = (int) paramMap.get("id");
        String friendname = paramMap.get("friendname").toString();


        Userinfo friend = userinfoDao.findUserByStatus(friendname, Userinfo.Status_Normal);
        String status = "{\"status\":%d}";
        if (friend != null) {
            int nowRelation = friendDao.checkFriend(userId, friend.getId());
            if (nowRelation != Friend.Relation_IsFriend) {
                status = String.format(status, 1);//还不是好友，可以发出邀请

            } else {
                status = String.format(status, 2);//已经是好友了，不要邀请
            }
        } else {
            status = String.format(status, 3);//找不到这个人
        }
        response.getOutputStream().write(Data.OkData(status).toString().getBytes());
    }

    @RequestMapping(value = "/findAllFriends")
    public void findAllFriends(@RequestBody String params, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map paramMap = paramsMap(params);
        int userId = (int) paramMap.get("id");

        List<Friend> list = friendDao.findFriends(userId, Friend.Relation_IsFriend);
        String data = Data.OkData(new ObjectMapper().writeValueAsString(list)).toString();
        response.getOutputStream().write(data.getBytes());
    }

    @RequestMapping(value = "/inviteFriend")
    public void inviteFriend(@RequestBody String params, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map paramMap = paramsMap(params);
        int userId = (int) paramMap.get("id");
        int friendId = (int) paramMap.get("friendid");

        Data data = null;
        if (friendDao.inviteFriend(userId, friendId, "")) {
            data = Data.OkData();
        } else {
            data = Data.ErrorData(Data.Error_Inner);
        }
        sendData(response, data);
    }

    @RequestMapping(value = "/dealInviteFriend")
    public void dealInviteFriend(@RequestBody String params, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map paramMap = paramsMap(params);
        int userId = (int) paramMap.get("id");
        int friendId = (int) paramMap.get("friendid");//谁邀请的你
        int howtodeal = (int) paramMap.get("howtodeal");//Friend.Relation_IsFriend ,Relation_IsRefused

        friendDao.dealInviteFriend(userId, friendId, howtodeal);
        response.getOutputStream().write(Data.OkData("").toString().getBytes());

        Data data = null;
        if (friendDao.dealInviteFriend(userId, friendId, howtodeal)) {
            data = Data.OkData();
        } else {
            data = Data.ErrorData(Data.Error_Inner);
        }
        sendData(response, data);
    }

    @RequestMapping(value = "/editFriend")
    public void editFriend(@RequestBody String params, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map paramMap = paramsMap(params);
        int userId = (int) paramMap.get("id");
        int friendId = (int) paramMap.get("friendid");
        String friendName = paramMap.get("friendname").toString();

        Data data = null;
        if (friendDao.editFriendName(userId, friendId, friendName)) {
            data = Data.OkData();
        } else {
            data = Data.ErrorData(Data.Error_Inner);
        }
        sendData(response, data);
    }

    @RequestMapping(value = "/deleteFriend")
    public void deleteFriend(@RequestBody String params, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map paramMap = paramsMap(params);
        int userId = (int) paramMap.get("id");
        int friendId = (int) paramMap.get("friendid");


        Data data = null;
        if (friendDao.deleteFriend(userId, friendId)) {
            data = Data.OkData();
        } else {
            data = Data.ErrorData(Data.Error_Inner);
        }
        sendData(response, data);
    }

}
