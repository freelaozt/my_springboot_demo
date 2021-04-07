package com.fxd.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

class ResultMessage {

    String diskUrl;

    public String getDiskUrl() {
        return diskUrl;
    }

    public void setDiskUrl(String diskUrl) {
        this.diskUrl = diskUrl;
    }
}

/**
 * url 一级目录
 */
@Controller
public class GeneralController {

    /**
     * 当war包启动，client尝试连接server
     * @param request
     * @return 返回 success JSON 信息
     */
    @RequestMapping(value = "/file", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> fileConnection(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        StringBuffer url = request.getRequestURL();
        String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(),
                url.length()).append("/").toString();

        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setDiskUrl(tempContextUrl + "resumes/file/listRoot");
        modelMap.put("success", resultMessage);

        return modelMap;
    }
}
