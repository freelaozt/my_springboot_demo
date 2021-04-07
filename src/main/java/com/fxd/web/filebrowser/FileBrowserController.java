package com.fxd.web.filebrowser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fxd.entity.FileAttr;
import com.fxd.enums.StateEnum;
import com.fxd.service.FileBrowserService;
import com.fxd.util.HttpServletRequestUtil;
import com.fxd.util.JsonChang;
import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("file")
public class FileBrowserController {

    @Autowired
    private FileBrowserService fileBrowserService;

    /**
     * 获取根的磁盘及其信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "listRoot", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getListRoot(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        StringBuffer url = request.getRequestURL();
        String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(),
                url.length()).append("/").toString();
        //依赖的json插件没作用 去掉
//        String jsonArray = JSONArray.toJSONString(fileBrowserService.getListRoots(tempContextUrl));
        modelMap.put("success", fileBrowserService.getListRoots(tempContextUrl));
        return modelMap;

    }

    /**
     * 提交路径，获取文件或文件夹及其信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "listFiles", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getListFiles(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String filePath = HttpServletRequestUtil.getString(request, "path");
        try {
            StringBuffer url = request.getRequestURL();
            String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(),
                    url.length()).append("/").toString();
            modelMap.put("success", fileBrowserService.getListFiles(filePath,tempContextUrl));
        } catch (Exception e) {
            modelMap.put("error", "错误路径");
        }
        return modelMap;
    }

    @RequestMapping(value = "download", method = RequestMethod.GET)
    @ResponseBody
    public HttpServletResponse download(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //web服务器上文件存储路径
            //String filePath = "D:\\用户目录\\我的图片\\武田玲奈1st写真集 「short」 武田玲奈写真集\\image00032.jpeg";
            String filePath = "E:23.mkv";
            java.io.File file = new java.io.File(filePath);
            //在浏览器上显示的下载文件名
            String fileName = "23.mkv";

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(filePath));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header URLEncoder对文件名编码输出，防止中文文件名出现乱码
            response.addHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;

    }
}
