package com.flywinter.fallblog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by IntelliJ IDEA
 * User:Zhang Xingkun
 * Date:2021/8/12 21:42
 * Description:
 */
@Controller
public class AdminController {

    /**
     * @description 登录页单独列出
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String admin(){
        return "admin/login";
    }


    /**
     * @description 注册页单独列出，暂时保留实现
     * @return
     */
    @GetMapping("/register")
    public String register(){
        return "admin/register";
    }

    @GetMapping("/forget")
    public String forget(){
        return "admin/forget";
    }

    /**
     * @description 管理页首页，展示各种基本信息
     * @return
     */
    @GetMapping("/admin")
    public String dashboard(){
        return "admin/dashboard";
    }
    /**
     * @description 发布文章页，发布文章
     * @return
     */
    @GetMapping("/admin/publish")
    public String publish(){
        return "admin/publish";
    }

    /**
     * @description 管理模块
     * @return
     */
    @GetMapping("/admin/article")
    public String article(){
        return "admin/article";
    }

    @GetMapping("/admin/comment")
    public String comment(){
        return "admin/comment";
    }

    @GetMapping("/admin/category")
    public String category(){
        return "admin/category";
    }

    @GetMapping("/admin/tags")
    public String tags(){
        return "admin/tags";
    }

    @GetMapping("/admin/images")
    public String images(){
        return "admin/images";
    }


    /**
     * @description 系统模块
     * @return
     */
    @GetMapping("/admin/webinfo")
    public String webinfo(){
        return "admin/webinfo";
    }

    @GetMapping("/admin/personal")
    public String personal(){
        return "admin/personal";
    }

}
