package com.t1.service.document;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2016/5/22.
 */
public class FileUtil {
    List<FileNode> nodeList;

    public List<FileNode> getDirNodebyFile(File file) {
        nodeList = new ArrayList<>();
        nodeList.add(new FileNode(file));

        getFileNodeDetail(file);
        return nodeList;
    }

    public void getFileNodeDetail(File file) {
        File[] files = file.listFiles();
        if (files != null && files.length > 0) {
            for (File subFile : files) {
                nodeList.add(new FileNode(subFile));
                if (subFile.isDirectory()) {
                    getFileNodeDetail(subFile);
                }
            }
        }
    }

}
