package com.t1.controller;

import com.t1.db.dao.NewsMapper;
import com.t1.db.jdbc.business.INewsDao;
import com.t1.db.jdbc.business.NewsDao;
import com.t1.db.jdbc.core.BaseDao;
import com.t1.db.jdbc.core.IBaseDao;
import com.t1.db.model.News;
import com.t1.db.model.NewsExample;
import com.t1.framework.AppConfig;
import com.t1.framework.BaseController;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author amar
 */
@Controller
@RequestMapping("/test")
public class TestController extends BaseController {

    @Resource(name = "newsMapper")
    NewsMapper newsMapper;

    @Resource(name = "appConfig")
    AppConfig appConfig;

    @Resource(name = "newsDao")
    INewsDao newsDao;

    @RequestMapping
    public String list(HttpServletRequest request, HttpServletResponse response) throws Exception {
        NewsExample newsExample = new NewsExample();
//        int count = newsMapper.countByExample(newsExample);

        List<News> list1 = newsMapper.selectByExample(newsExample, new RowBounds(4, 1));

        String aa = appConfig.getByKey("db.pw");

        //List<News> list = newsMapper.selectByExample( newsExample,new RowBounds(4,2));
        List<News> list = newsMapper.selectByExample(newsExample);
        int count = list.size();
        request.setAttribute("count", "" + count);

        boolean a = false;
        if (a)
            throw new Exception();
        return "test/t1";
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    @RequestMapping(value = "/testDao")
    public String testDao() throws Exception {

        News news = new News();
        news.setReleasetime(new Date(new Date().getTime() - 7200 * 1000));
        news.setLastedittime(new Date(new Date().getTime() + 7200 * 1000));
        news.setStarttime(new Date());
        news.setSourceby("source by 111");
        news.setTitle("title 1");
        news.setTitlepic("pic.jpg");
        news.setContent("xxxxxxxxxxxxx");
        news.setEditorid(123);
        news.setCategoryid(2);
        news.setStatus(2);

        //News news2 = newsDao.add(news);

        List<News> list = newsDao.queryList("select * from news");

        //List<Map<String,Object>> mapList = newsDao.queryMapList("select * from news");

//        boolean a = true;
//        if( a)
//            throw new Exception();

        return "test/t2";
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    @RequestMapping(value = "/testDao2")
    public String testDao2() throws Exception {

        News news = new News();
        news.setReleasetime(new Date(new Date().getTime() - 7200 * 1000));
        news.setLastedittime(new Date(new Date().getTime() + 7200 * 1000));
        news.setStarttime(new Date());
        news.setSourceby("source by 111");
        news.setTitle("title 1");
        news.setTitlepic("pic.jpg");
        news.setContent("xxxxxxxxxxxxx");
        news.setEditorid(123);
        news.setCategoryid(2);
        news.setStatus(2);

        News news2 = newsDao.add(news);

        //List<News> list = newsDao.queryList("select * from news");

        //List<Map<String,Object>> mapList = newsDao.queryMapList("select * from news");

//        boolean a = true;
//        if( a)
//            throw new Exception();

        return "test/t2";
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    @RequestMapping(value = "/login")
    public String login() throws Exception {
        News news = new News();
        news.setCategoryid(1);
        news.setContent("ccccc");
        news.setEditorid(1);
        news.setTitle("222");
        news.setTitlepic("ssss");
        news.setStatus(2);
        news.setStarttime(new Date());

        newsMapper.insert(news);
        boolean a = true;
        if (a)
            throw new Exception();
        return "test/t2";
    }

    @RequestMapping(value = "/login/{id}/{name}")
    public
    @ResponseBody
    News json(@PathVariable int id, @PathVariable String name) throws Exception {
        News news = new News();
        news.setCategoryid(1);
        news.setContent("ccccc");
        news.setEditorid(1);
        news.setTitle("222");
        news.setTitlepic("ssss");
        news.setStatus(2);
        news.setStarttime(new Date());

        return news;
    }

    @RequestMapping(value = "/toReceiveJson/")
    public String toReceiveJson() {
        return "test/receiveJson";
    }

    @RequestMapping(value = "/recJson/",method = {RequestMethod.POST })
    @ResponseBody
    public void recJson(@RequestBody String map,HttpServletRequest request, HttpServletResponse response) {

        try {

            response.getOutputStream().write( map.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void insertTest() throws Exception {

    }


}
