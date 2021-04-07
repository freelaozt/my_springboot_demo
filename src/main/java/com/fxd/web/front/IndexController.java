package com.fxd.web.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 二级目录
 * 设置 前端访问路由
 */
@Controller
@RequestMapping(value = "home",method = RequestMethod.GET)
public class IndexController {

    @RequestMapping(value = "/file")
    public String FileBrowser(){
        System.out.println("文件列表：/file");
        return "filebrowser/index";
    }

    @RequestMapping(value = "/router3")
    public String FileRouter(){
        System.out.println("文件列表：/router");
        return "filebrowser/router";
    }

    @RequestMapping(value = "/test")
    public String Test(){
        System.out.println("个人用户：/test");
        return "index";
    }

    @RequestMapping(value = "/index")
    public String ResumesHomeList(){
        System.out.println("个人用户：person/index");
        return "person/index";
    }

    @RequestMapping(value = "/user")
    public String ResumesUser(){
        System.out.println("个人用户：person/user");
        return "person/user";
    }

    @RequestMapping(value = "/add")
    public String ResumesAddEdit(){
        System.out.println("个人用户：person/addedit");
        return "person/addedit";
    }

    @RequestMapping(value = "/show")
    public String ResumesShow(){
        System.out.println("个人用户：person/show");
        return "person/show";
    }

    @RequestMapping(value = "/login")
    public String ResumesLogin(){
        System.out.println("个人用户：person/login");
        return "person/login";
    }
}

