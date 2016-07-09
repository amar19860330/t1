package com.t1.db.jdbc.business;

import com.t1.db.jdbc.core.IBaseDao;
import com.t1.db.model.News;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;

import java.util.List;

/**
 * Created by amar on 16/7/9.
 */
//@EnableCaching
public interface INewsDao extends IBaseDao<News> {

    @CacheEvict( cacheNames = "allCache",allEntries=true)
    News add(News news) throws Exception;

    @Override
    @Cacheable( "allCache" )
    List<News> queryList(String sql) throws Exception;
}
