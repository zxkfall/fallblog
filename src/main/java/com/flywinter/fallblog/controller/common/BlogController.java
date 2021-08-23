package com.flywinter.fallblog.controller.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.flywinter.fallblog.entity.*;
import com.flywinter.fallblog.myservice.PublicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User:Zhang Xingkun
 * Date:2021/8/12 14:45
 * Description:
 */
@Slf4j
@Controller
public class BlogController {

    private final PublicService publicService;

    public BlogController(PublicService publicService) {
        this.publicService = publicService;
    }

    @GetMapping("/")
    public String index(Model model){
        Page<TArticle> page = publicService.getArticlePage(0, 6);
        return articlePage(model, page);
    }

    @GetMapping("/page/{index}")
    public String index(@PathVariable("index") Integer index, Model model){
        Page<TArticle> page = publicService.getArticlePage(index, 6);
        return articlePage(model, page);
    }

    @GetMapping("/article/{id}")
    public String article(@PathVariable("id") String id,Model model){
        TArticle article = publicService.getArticleById(id);
        TCategory category = publicService.getCategoryById(article.getCategoryId());
        List<TComment> comments = publicService.getCommentsByArticleId(id);
        model.addAttribute("article",article);
        model.addAttribute("category",category);
        model.addAttribute("comments",comments);
        return "/common/article";
    }

    @PostMapping("/comment")
    public String comment(TComment comment, HttpServletRequest request){
        publicService.insertComment(comment, request);
        return "redirect:/article/"+comment.getArticleId();
    }



    @GetMapping("/category/{id}")
    public String categoryId(@PathVariable("id")Integer id,Model model){
        List<TCategory> categoryList = publicService.getCategoryList();
        TCategory category = publicService.getCategoryById(id);
        List<TArticle> articles = publicService.getArticlesByCategoryId(id);
        model.addAttribute("articleList",articles);
        model.addAttribute("selectCategory",category);
        model.addAttribute("categoryList",categoryList);
        return "/common/category";
    }
    @GetMapping("/category")
    public String category(Model model){
        List<TCategory> categoryList = publicService.getCategoryList();
        List<TArticle> articles = publicService.getArticlesByCategoryId(categoryList.get(0).getId());
        model.addAttribute("articleList",articles);
        model.addAttribute("selectCategory",categoryList.get(0));
        model.addAttribute("categoryList",categoryList);
        return "/common/category";
    }
    @GetMapping("/search")
    public String search(){
        return "/common/search";
    }


    @GetMapping("/archive")
    public String archive(Model model){
        List<TArticle> articles = publicService.getArticlesDESC();
        model.addAttribute("articles",articles);
        return "/common/archive";
    }


    @GetMapping("/about")
    public String about(){
        return "/common/about";
    }


    private String articlePage(Model model, Page<TArticle> articlePage) {
        long pageCurrent = articlePage.getCurrent();
        long pages = articlePage.getPages();
        List<TArticle> articles = articlePage.getRecords();
        if (articles.size() == 0) {
            return "error/404";
        }

        List<Long> pagelist = new ArrayList<>();
        for (long i = 0; i < pages; i++) {
            pagelist.add(i+1);
        }
        List<TTag> tagList = publicService.getTagList();
        List<TCategory> categoryList = publicService.getCategoryList();
        List<TImage> images = publicService.getImages();
        model.addAttribute("artilcelist",articles);
        model.addAttribute("taglist",tagList);
        model.addAttribute("categorylist",categoryList);
        model.addAttribute("imagelist",images);
        model.addAttribute("pagelist",pagelist);
        model.addAttribute("pageCurrent",pageCurrent);
        return "index";
    }
}
