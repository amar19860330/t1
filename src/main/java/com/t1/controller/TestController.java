package com.t1.controller;

import com.t1.db.dao.NewsMapper;
import com.t1.db.model.NewsExample;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author amar
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Resource(name = "newsMapper")
    NewsMapper newsMapper;

	@RequestMapping
    public String list(HttpServletRequest request, HttpServletResponse response){
        int count = newsMapper.countByExample(new NewsExample());
        count = 333;
        request.setAttribute("count",""+count);
        return "test/t1";
    }

	@RequestMapping(value = "/login")
    public String login(){
        return "test/t2";
    }
}
