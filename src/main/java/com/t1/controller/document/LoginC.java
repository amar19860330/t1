package com.t1.controller.document;

import com.t1.db.dao.SecUserMapper;
import com.t1.db.model.SecUser;
import com.t1.db.model.SecUserExample;
import com.t1.framework.BaseController;
import com.t1.utils.ServletUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by User on 2016/5/19.
 */
@Controller
@RequestMapping("/user")
public class LoginC extends BaseController {

    @Resource(name = "secUserMapper")
    SecUserMapper secUserMapper;

    @RequestMapping("/toLogin")
    public String toLogin(HttpServletRequest request, HttpServletResponse response) {


        return "document/login/login";
    }

    @RequestMapping("/toRegist")
    public String toRegist(HttpServletRequest request, HttpServletResponse response) {


        return "document/login/regist";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {

        String loginName = request.getParameter("loginname");
        String pw = request.getParameter("pw");

        SecUserExample secUserExample = new SecUserExample();
        secUserExample.createCriteria().andLoginnameEqualTo(loginName).andPwEqualTo(pw);
        List<SecUser> secUserList = secUserMapper.selectByExample(secUserExample);
        if( secUserList !=null && secUserList.size()>0)
        {
            request.getSession().setAttribute("user",secUserList.get(0));
            return "forward:/doc/toupload";
        }
        else
        {
            return "document/login/login";
        }
    }

    @RequestMapping("/regist")
    public String regist(HttpServletRequest request, HttpServletResponse response) {

        SecUser secUser = ServletUtil.request2Bean(request,SecUser.class);
        secUserMapper.insert(secUser);

        return "document/login/login";
    }

    @RequestMapping("/checkUserName")
    public String checkUserName(HttpServletRequest request, HttpServletResponse response) {
        String loginName = request.getParameter("loginname");
        SecUserExample secUserExample = new SecUserExample();
        secUserExample.createCriteria().andLoginnameEqualTo(loginName);
        List<SecUser> secUserList = secUserMapper.selectByExample(secUserExample);
        if( secUserList !=null && secUserList.size()>0) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return "document/login/checkUserName";
    }

}
