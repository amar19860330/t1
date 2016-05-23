package com.t1.service.document;

import com.t1.utils.DatetimeUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 2016/5/22.
 */
public class FileNode {
    private String path;
    private String name;
    /***
     * 当id使用
     */
    private String id;
    private boolean isDir;
    private String pId;
    private String size;
    private String lastModifyTime;

    public FileNode(File file) {
        this.id = file.getPath();
        this.name = file.getName();
        this.pId = file.getParentFile().getPath();
        this.isDir = file.isDirectory();
        this.path = id.substring(0, id.lastIndexOf("\\"));
        this.lastModifyTime = DatetimeUtil.longToDatetime(file.lastModified());
        this.size = file.getTotalSpace() / 1024 + "";
    }

    public FileNode(String path, String name, String pathAndName, boolean isDir, String parentId) {
        this.path = path;
        this.name = name;
        this.id = pathAndName;
        this.isDir = isDir;
        this.pId = parentId;
    }

    public Map<String, Object> toMap4Tree() {
        Map<String, Object> resultMap = new HashMap<>();

        resultMap.put("id", id);
        resultMap.put("pId", pId);
        resultMap.put("isDir", isDir);
        resultMap.put("name", name);
        resultMap.put("path", path);

        return resultMap;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isDir() {
        return isDir;
    }

    public void setDir(boolean dir) {
        isDir = dir;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(String lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }
}
