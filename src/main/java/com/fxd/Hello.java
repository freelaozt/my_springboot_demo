package com.fxd;

import com.fxd.dao.Dao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class Hello {

    @RequestMapping(value = "methodName", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> methodName(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("success","你好 Spring Boot!");
        return modelMap;
    }
}
