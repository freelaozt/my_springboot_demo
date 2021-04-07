package com.fxd.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fxd.entity.DiskAttr;
import com.fxd.entity.FileAttr;
import com.fxd.service.FileBrowserService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class FileBrowserServiceImpl implements FileBrowserService {

    /**
     * 获取不同服务器的磁盘
     *
     * @return 获取到的磁盘
     */
    @Override
    public ArrayList<DiskAttr> getListRoots(String domain) {
        //Java获得磁盘盘符，并且分析电脑各个盘符中的大小
        File[] roots = File.listRoots();
        ArrayList s = new ArrayList<FileAttr>();
        for (int i = 0; i < roots.length; i++) {
            DiskAttr diskAttr = new DiskAttr();
            diskAttr.setId(i);
            diskAttr.setName(roots[i].getPath().replace("\\",""));
            diskAttr.setPath(roots[i].getPath());
            diskAttr.setAbsolutePath(roots[i].getAbsolutePath());
            diskAttr.setLastModified(roots[i].lastModified());
            diskAttr.setLength(roots[i].length());
            diskAttr.setExists(roots[i].exists());
            diskAttr.setDirectory(roots[i].isDirectory());
            diskAttr.setFile(roots[i].isFile());
            diskAttr.setFreeSpace(roots[i].getFreeSpace());
            diskAttr.setUsableSpace(roots[i].getUsableSpace());
            diskAttr.setTotalSpace(roots[i].getTotalSpace());
            if (roots[i].isDirectory()){
                diskAttr.setUrl(domain + "resumes/file/listFiles?path=" + roots[i].getPath().replace("\\","/"));
            }
            s.add(diskAttr);
        }
        return s;
    }

    @Override
    public ArrayList<FileAttr> getListFiles(String filePath, String domain) {
        //初始化文件对象
        //File file = new File("E:\\妖精的尾巴\\1-174");
        File file = new File(filePath);
        //文件对象转成文件集合
        File[] tempList = file.listFiles();
        ArrayList s = new ArrayList<FileAttr>();
        for (int i = 0; i < tempList.length; i++) {
            FileAttr fileAttr = new FileAttr();
            fileAttr.setId(i);
            fileAttr.setName(tempList[i].getName());
            fileAttr.setPath(tempList[i].getPath());
            fileAttr.setAbsoluteFile(tempList[i].getAbsoluteFile());
            fileAttr.setAbsolutePath(tempList[i].getAbsolutePath());
            fileAttr.setParent(tempList[i].getParent());
            fileAttr.setDirectory(tempList[i].isDirectory());
            fileAttr.setFile(tempList[i].isFile());
            fileAttr.setAbsolute(tempList[i].isAbsolute());
            fileAttr.setHidden(tempList[i].isHidden());
            fileAttr.setExists(tempList[i].exists());
            fileAttr.setLastModified(tempList[i].lastModified());
            fileAttr.setLength(tempList[i].length());
            if (tempList[i].isDirectory()){
                fileAttr.setUrl(domain + "resumes/file/listFiles?path=" + tempList[i].getPath().replace("\\","/"));
            }
            if (tempList[i].isFile()){
                fileAttr.setUrl(domain + "resumes/browser/" + tempList[i].getPath());
            }
            s.add(fileAttr);
        }
        return s;
    }
}
