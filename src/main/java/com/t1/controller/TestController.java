package com.t1.controller;

import com.t1.db.dao.NewsMapper;
import com.t1.db.model.News;
import com.t1.db.model.NewsExample;
import com.t1.framework.AppConfig;
import com.t1.framework.BaseController;
import com.t1.utils.DatetimeUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author amar
 */
@Controller
@RequestMapping("/test")
public class TestController extends BaseController
{

    @Resource(name = "newsMapper")
    NewsMapper newsMapper;

    @Resource(name = "appConfig")
    AppConfig appConfig;

    @RequestMapping
    public String list(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
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
    @RequestMapping(value = "/login")
    public String login() throws Exception
    {
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
    News json(@PathVariable int id, @PathVariable String name) throws Exception
    {
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

    private void insertTest() throws Exception
    {

    }

    @RequestMapping(value = "/toUpload")
    public String toUpload() throws Exception
    {
        String dataRootPath = appConfig.getByKey("data.root.path");
        String picPath = appConfig.getByKey("pic.path");
        String path = dataRootPath + dataRootPath;

        News news = new News();
        news.setCategoryid(1);
        news.setContent("ccccc");
        news.setEditorid(1);
        news.setTitle("222");
        news.setTitlepic("ssss");
        news.setStatus(2);
        news.setStarttime(new Date());

        return "test/upload";
    }


    @RequestMapping(value = "/upload")
    public String upload(HttpServletRequest request) throws Exception
    {
            String dataRootPath = appConfig.getByKey("data.root.path");
            String picPath = appConfig.getByKey("pic.path");
            String path = dataRootPath + picPath+"/";
            File saveDir = new File(path);
            if( !saveDir.exists())
                saveDir.mkdirs();

            long startTime = System.currentTimeMillis();
            //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                    request.getSession().getServletContext());
            //检查form中是否有enctype="multipart/form-data"
            if (multipartResolver.isMultipart(request))
            {
                //将request变成多部分request
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                //获取multiRequest 中所有的文件名
                Iterator iter = multiRequest.getFileNames();

                while (iter.hasNext())
                {
                    //一次遍历所有文件
                    MultipartFile file = multiRequest.getFile(iter.next().toString());
                    if (file != null)
                    {
                        int index = file.getOriginalFilename().lastIndexOf(".");
                        String fileName = System.currentTimeMillis()+""+file.getOriginalFilename().substring(index);
                        request.setAttribute("fileName",fileName);
                        request.setAttribute("CKEditorFuncNum",request.getParameter("CKEditorFuncNum"));
                        String savePath =path + fileName;//file.getOriginalFilename();
                        //上传
                        file.transferTo(new File(savePath));
                    }
                }
            }
            long endTime = System.currentTimeMillis();

        return "test/uploadresult";
    }

    @RequestMapping("/pic/{photoName}/")
    public void picToJSP(@PathVariable String photoName,HttpServletRequest request,HttpServletResponse response)throws Exception{
        String dataRootPath = appConfig.getByKey("data.root.path");
        String picPath = appConfig.getByKey("pic.path");
        String path = dataRootPath + picPath+"/";

        FileInputStream in;
        //response.setContentType("application/octet-stream;charset=UTF-8");
        response.setContentType("image/jpeg");
        //图片读取路径
        in=new FileInputStream(path+photoName);
        int i=in.available();
        byte[]data=new byte[i];
        in.read(data);
        in.close();

        //写图片
        OutputStream outputStream=new BufferedOutputStream(response.getOutputStream());
        outputStream.write(data);
        outputStream.flush();
        outputStream.close();
    }

}
