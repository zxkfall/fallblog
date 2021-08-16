package com.flywinter.fallblog.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA
 * User:Zhang Xingkun
 * Date:2021/8/12 14:45
 * Description:
 */
@Controller
public class IndexController {
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("msg","Hello World!");
        return "index";
    }
    @GetMapping("/login")
    public String login(){
        return "/common/login";
    }
}
