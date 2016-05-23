package com.t1.service.document;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2016/5/22.
 */
public class FileUtil {
    List<FileNode> nodeList;

    private String docRootPath;

    public String getDocRootPath() {
        return docRootPath;
    }

    public void setDocRootPath(String docRootPath) {
        this.docRootPath = docRootPath;
    }

    public List<FileNode> getDirNodebyFile(File file) {
        nodeList = new ArrayList<>();
        nodeList.add(new FileNode(file));

        getDirNodeDetail(file);
        return nodeList;
    }

    public void getDirNodeDetail(File file) {
        File[] files = file.listFiles();
        if (files != null && files.length > 0) {
            for (File subFile : files) {
                if (subFile.isDirectory())
                    nodeList.add(new FileNode(subFile));
                if (subFile.isDirectory()) {
                    getDirNodeDetail(subFile);
                }
            }
        }
    }

    public List<FileNode> getFileNodeByPath(String path) {
        File file = new File(path);
        File[] files = file.listFiles();
        List<FileNode> nodeList = new ArrayList<>();
        if (files != null && files.length > 0) {
            for (File subFile : files) {
                nodeList.add(new FileNode(subFile));
            }
        }
        return nodeList;
    }

}
