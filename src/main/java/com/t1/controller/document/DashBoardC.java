package com.t1.controller.document;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.t1.framework.AppConfig;
import com.t1.framework.BaseController;
import com.t1.service.document.FileNode;
import com.t1.service.document.FileUtil;
import org.apache.commons.fileupload.FileItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by User on 2016/5/19.
 */
@Controller
@RequestMapping("/doc")
public class DashBoardC extends BaseController {

    @Resource(name = "appConfig")
    AppConfig appConfig;

    @RequestMapping("/")
    public String toUploadFile(HttpServletRequest request, HttpServletResponse response) {
        String docRootPath = appConfig.getDocRootPath();
        FileUtil fileUtil = new FileUtil();
        fileUtil.setDocRootPath(docRootPath);
        List<FileNode> nodeList = fileUtil.getDirNodebyFile(new File(docRootPath));

        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(nodeList);
            request.setAttribute("data",jsonInString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "document/upload/toupload";
    }

    @RequestMapping("/folderInfo")
    public String getFolderInfo(HttpServletRequest request, HttpServletResponse response) {
        String clientPath = request.getParameter("clientPath");

        String path = appConfig.getDocRootPath() + clientPath;
        FileUtil fileUtil = new FileUtil();
        List<FileNode> nodeList = fileUtil.getFileNodeByPath(path);
        request.setAttribute("dirInfo",nodeList);
        return "document/upload/folder";
    }

    @RequestMapping("/upload")
    public String uploadFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());

        if (multipartResolver.isMultipart(request)) {
            List<String> uploadFileNameList = uploadFileDetail(request); //第一步,先上传文件
            //第二步,分析文件,建立索引
        }

        return "document/upload/result";
    }

    public void indexFileDetail() {

    }



    public List<String>  uploadFileDetail(HttpServletRequest request) throws IOException {
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        List fileList = multiRequest.getMultiFileMap().get("files");
        Iterator iter = fileList.iterator();

        List<String> uploadFileNameList = new ArrayList<>();
        while (iter.hasNext()) {
            CommonsMultipartFile file = (CommonsMultipartFile) iter.next();
            FileItem fileItem = file.getFileItem();
            String fileFullName = fileItem.getName();
            int lastDivisionSymbolIndex = fileFullName.lastIndexOf("/");
            String fileName = fileFullName.substring(lastDivisionSymbolIndex + 1, fileFullName.length());
            String clientPath = fileFullName.substring(0, lastDivisionSymbolIndex) + "/";
            String serverPath = appConfig.getDocRootPath() + clientPath;

            makeSurePathExist(serverPath);

            String serverFile = serverPath +"/" +fileName;
            uploadFileNameList.add(serverFile);
            file.transferTo(new File(serverFile));
        }
        return uploadFileNameList;
    }


    public void makeSurePathExist(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

    }
}
