package com.t1.controller.document;


import com.t1.framework.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;

/**
 * Created by User on 2016/5/19.
 */
@Controller
@RequestMapping("/doc")
public class DashBoardC extends BaseController {

    @RequestMapping("/toupload")
    public String toUploadFile() {
        return "document/upload/toupload";
    }

    @RequestMapping("/upload")
    public String uploadFile(HttpServletRequest request, HttpServletResponse response) {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());

        if (multipartResolver.isMultipart(request)) {

            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

            //Iterator iter = multiRequest.getFileNames();
            //multiRequest.getMultiFileMap(); CommonsMultipartFile
            List fileList = multiRequest.getMultiFileMap().get("files");
            Iterator iter = fileList.iterator();

            while (iter.hasNext()) {
                CommonsMultipartFile file =(CommonsMultipartFile) iter.next();
                System.out.println(file.getFileItem().getName());

            }
        }

        return "document/upload/result";
    }
}
