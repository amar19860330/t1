package com.t1.controller;

import com.t1.db.dao.NewsMapper;
import com.t1.db.model.News;
import com.t1.db.model.NewsExample;
import com.t1.framework.BaseController;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
public class TestController extends BaseController{

    @Resource(name = "newsMapper")
    NewsMapper newsMapper;

	@RequestMapping
    public String list(HttpServletRequest request, HttpServletResponse response)throws Exception{
        NewsExample newsExample = new NewsExample();
//        int count = newsMapper.countByExample(newsExample);

        List<News> list1 = newsMapper.selectByExample( newsExample,new RowBounds(4,1));

        //List<News> list = newsMapper.selectByExample( newsExample,new RowBounds(4,2));
        List<News> list = newsMapper.selectByExample( newsExample);
        int count = list.size();
        request.setAttribute("count",""+count);

        boolean a = false;
        if( a)
        throw new Exception();
        return "test/t1";
    }

    @Transactional( propagation = Propagation.REQUIRED , rollbackFor = { Exception.class } )
	@RequestMapping(value = "/login")
    public String login()throws Exception{
        News news = new News();
        news.setCategoryid(1);
        news.setContent("ccccc");
        news.setEditorid(1);
        news.setTitle("222");
        news.setTitlepic("ssss");
        news.setStatus(2);
        news.setStarttime( new Date());

        newsMapper.insert(news );
        boolean a = true;
        if( a)
            throw new Exception();
        return "test/t2";
    }


    private void insertTest()throws Exception
    {

    }
}
