package com.t1.controller;

import com.t1.db.dao.NewsMapper;
import com.t1.db.model.News;
import com.t1.db.model.NewsExample;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

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
        NewsExample newsExample = new NewsExample();
//        int count = newsMapper.countByExample(newsExample);

        List<News> list1 = newsMapper.selectByExample( newsExample,new RowBounds(4,1));

        List<News> list = newsMapper.selectByExample( newsExample,new RowBounds(4,2));
        int count = list.size();
        request.setAttribute("count",""+count);
        return "test/t1";
    }

	@RequestMapping(value = "/login")
    public String login(){
        News news = new News();
        news.setCategoryid(1);
        news.setContent("ccccc");
        news.setEditorid(1);
        news.setTitle("222");
        news.setTitlepic("ssss");
        news.setStatus(2);
        news.setStarttime( new Date());

        newsMapper.insert(news );
        return "test/t2";
    }
}
