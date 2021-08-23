package com.flywinter.fallblog.controller.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.flywinter.fallblog.entity.*;
import com.flywinter.fallblog.mapper.*;
import com.flywinter.fallblog.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
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
    @Autowired
    TArticleMapper tArticleMapper;
    @Autowired
    TTagMapper tagMapper;
    @Autowired
    TCategoryMapper categoryMapper;
    @Autowired
    TWebViewPeopleMapper webViewPeopleMapper;
    @GetMapping("/")
    public String index(Model model){
        Page<TArticle> page = new Page<>(0,6);
        page.addOrder(OrderItem.desc("create_time"));
        Page<TArticle> articlePage = tArticleMapper.selectPage(page, null);
        long pageCurrent = articlePage.getCurrent();
        long pages = articlePage.getPages();
        List<Long> pagelist = new ArrayList<>();
        for (long i = 0; i < pages; i++) {
            pagelist.add(i+1);
        }
        List<TArticle> articles = articlePage.getRecords();
        if (articles.size() == 0) {
            return "error/404";
        }
        List<TTag> tagList = tagMapper.selectList(null);
        List<TCategory> categoryList = categoryMapper.selectList(null);
        List<TImage> images = imageMapper.selectList(null);
        model.addAttribute("artilcelist",articles);
        model.addAttribute("taglist",tagList);
        model.addAttribute("categorylist",categoryList);
        model.addAttribute("imagelist",images);
        model.addAttribute("pagelist",pagelist);
        model.addAttribute("pageCurrent",pageCurrent);
        return "index";
    }
    @GetMapping("/page/{index}")
    public String index(@PathVariable("index") Integer index, Model model){
        Page<TArticle> page = new Page<>(index,6);
        page.addOrder(OrderItem.desc("create_time"));
        Page<TArticle> articlePage = tArticleMapper.selectPage(page, null);
        long pageCurrent = articlePage.getCurrent();
        long pages = articlePage.getPages();
        List<Long> pagelist = new ArrayList<>();
        for (long i = 0; i < pages; i++) {
            pagelist.add(i+1);
        }
        List<TArticle> articles = articlePage.getRecords();
        if (articles.size() == 0) {
            return "error/404";
        }
        List<TTag> tagList = tagMapper.selectList(null);
        List<TCategory> categoryList = categoryMapper.selectList(null);
        List<TImage> images = imageMapper.selectList(null);
        model.addAttribute("artilcelist",articles);
        model.addAttribute("taglist",tagList);
        model.addAttribute("categorylist",categoryList);
        model.addAttribute("imagelist",images);
        model.addAttribute("pagelist",pagelist);
        model.addAttribute("pageCurrent",pageCurrent);
        return "index";
    }
    @Autowired
    TImageMapper imageMapper;
    @GetMapping("/article/{id}")
    public String article(@PathVariable("id") String id,Model model){
        TArticle article = tArticleMapper.selectById(id);
        List<TImage> images = imageMapper.selectList(null);
        TCategory category = categoryMapper.selectById(article.getCategoryId());
        List<TComment> comments = commentMapper.selectList(Wrappers.<TComment>lambdaQuery().eq(TComment::getArticleId, id));
        model.addAttribute("article",article);
        model.addAttribute("imagelist",images);
        model.addAttribute("category",category);
        model.addAttribute("comments",comments);
        return "/common/article";
    }
    @Autowired
    TCommentMapper commentMapper;
    @PostMapping("/comment")
    public String comment(TComment comment, HttpServletRequest request){
        if (StringUtils.isNotBlank(comment.getNickname())||StringUtils.isNotBlank(comment.getEmail())
                ||StringUtils.isNotBlank(comment.getContent())) {
            comment.setCreateTime(LocalDateTime.now());
            comment.setUpdateTime(LocalDateTime.now());
            comment.setTarget(null);
            comment.setDevice(request.getHeader("User-Agent"));
            comment.setIp(IpUtil.getIp(request));

            int insert = commentMapper.insert(comment);
        }
        return "redirect:/article/"+comment.getArticleId();
    }
    @GetMapping("/category/{id}")
    public String categoryId(@PathVariable("id")String id,Model model){
        List<TCategory> categoryList = categoryMapper.selectList(null);
        TCategory category = categoryMapper.selectById(id);
        List<TArticle> articles = tArticleMapper.selectList(Wrappers.<TArticle>lambdaQuery().eq(TArticle::getCategoryId, id));
        model.addAttribute("articleList",articles);
        model.addAttribute("selectCategory",category);
        model.addAttribute("categoryList",categoryList);
        return "/common/category";
    }
    @GetMapping("/category")
    public String category(Model model){
        List<TCategory> categoryList = categoryMapper.selectList(null);
        List<TArticle> articles = tArticleMapper.selectList(Wrappers.<TArticle>lambdaQuery().eq(TArticle::getCategoryId, categoryList.get(0).getId()));
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
        List<TArticle> articles = tArticleMapper.selectList(Wrappers.<TArticle>lambdaQuery().orderByDesc(TArticle::getCreateTime));
        model.addAttribute("articles",articles);
        return "/common/archive";
    }
    @GetMapping("/about")
    public String about(){
        return "/common/about";
    }



}
