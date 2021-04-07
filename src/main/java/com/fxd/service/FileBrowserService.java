package com.fxd.service;

import com.fxd.entity.DiskAttr;
import com.fxd.entity.FileAttr;

import java.util.ArrayList;

/**
 * 服务器的磁盘文件浏览 （服务器：Windows Linux mac）
 */
public interface FileBrowserService {

    /**
     * 阐述：获取windows下，所有磁盘及其信息
     */
    ArrayList<DiskAttr> getListRoots(String domain);

    /**
     * 阐述：获取windows下，磁盘中的，文件或文件夹及其信息
     */
    ArrayList<FileAttr> getListFiles(String filePath,String domain);

}
