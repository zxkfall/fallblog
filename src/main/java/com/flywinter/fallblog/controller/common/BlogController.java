package com.flywinter.fallblog.controller.common;

import com.flywinter.fallblog.entity.TArticle;
import com.flywinter.fallblog.mapper.TArticleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by IntelliJ IDEA
 * User:Zhang Xingkun
 * Date:2021/8/12 14:45
 * Description:
 */
@Slf4j
@Controller
public class BlogController {
    @Autowired
    TArticleMapper tArticleMapper;

    @GetMapping("/")
    public String index(Model model){
        tArticleMapper.selectList(null);
        model.addAttribute("msg","Hello World!");
        return "index";
    }
    @GetMapping("/article/{id}")
    public String article(@PathVariable("id") String id){
        log.error("article id:"+id);
        return "/common/article";
    }
    @GetMapping("/category")
    public String category(){
        return "/common/category";
    }
    @GetMapping("/search")
    public String search(){
        return "/common/search";
    }
    @GetMapping("/archive")
    public String archive(){
        return "/common/archive";
    }
    @GetMapping("/about")
    public String about(){
        return "/common/about";
    }


}
