package com.t1.controller.document;

import com.t1.framework.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by User on 2016/5/19.
 */
@Controller
@RequestMapping("/user")
public class LoginC extends BaseController {


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


        return "document/login/login";
    }

    @RequestMapping("/regist")
    public String getFolderInfo(HttpServletRequest request, HttpServletResponse response) {


        return "document/login/login";
    }

    @RequestMapping("/checkUserName")
    public String checkUserName(HttpServletRequest request, HttpServletResponse response) {
            String loginName = request.getParameter("loginname");

        //response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        boolean isExist = false;

        return "document/login/checkUserName";
    }

}
