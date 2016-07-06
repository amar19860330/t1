package com.t1.db.jdbc.business;

import com.t1.db.jdbc.core.BaseDao;
import com.t1.db.model.News;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.validation.ObjectError;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 2016/7/5.
 */
@Repository
public class NewsDao extends BaseDao<News> {

    //@Resource(name = "newsDao")

    public News add(News news)throws Exception
    {
        //int id = add("insert into news.news (editorid,title,content,titlepic) values (?,?,?,?)",new Object[]{2,"title 2","content 2","pic path 2"});
        int id = add("insert into news.news (editorid,title,content,titlepic,releasetime,lastedittime,starttime) values (?,?,?,?,?,?,?)",
                news.getEditorid(),news.getTitle(),news.getContent(),news.getTitlepic(),news.getReleasetime(),news.getLastedittime(),news.getStarttime());

        //String sql = "insert into news.news (editorid,title,content,titlepic) values (2,'title 1','content 1','test titlepic 1')";

        news.setId( id);
        return news;
    }

}
