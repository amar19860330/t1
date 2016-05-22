package com.t1.service.document;

import java.io.File;

/**
 * Created by User on 2016/5/22.
 */
public class FileNode {
    private String path;
    private String name;
    /***
     * 当id使用
     */
    private String pathAndName;
    private boolean isDir;
    private String parentId;

    public FileNode(File file) {
        this.pathAndName = file.getPath();
        this.name = file.getName();
        this.parentId = file.getParentFile().getPath();
        this.isDir = file.isDirectory();
    }
    public FileNode(String path, String name, String pathAndName, boolean isDir, String parentId) {
        this.path = path;
        this.name = name;
        this.pathAndName = pathAndName;
        this.isDir = isDir;
        this.parentId = parentId;
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

    public String getPathAndName() {
        return pathAndName;
    }

    public void setPathAndName(String pathAndName) {
        this.pathAndName = pathAndName;
    }

    public boolean isDir() {
        return isDir;
    }

    public void setDir(boolean dir) {
        isDir = dir;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
