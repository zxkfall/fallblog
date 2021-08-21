package com.flywinter.fallblog.controller.common;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.flywinter.fallblog.entity.*;
import com.flywinter.fallblog.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
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
    @GetMapping("/")
    public String index(Model model){
        List<TArticle> articles = tArticleMapper.selectList(null);
        List<TTag> tagList = tagMapper.selectList(null);
        List<TCategory> categoryList = categoryMapper.selectList(null);
        model.addAttribute("artilcelist",articles);
        model.addAttribute("taglist",tagList);
        model.addAttribute("categorylist",categoryList);
        List<TImage> images = imageMapper.selectList(null);
        model.addAttribute("imagelist",images);
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
            comment.setIp(getIp(request));

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

    /**
     * 获取ip地址
     * @author gaodongyang
     * @date 2020/8/11 14:06
     * @param request 请求的request
     * @return String ip地址
     **/
    private static String getIp(HttpServletRequest request){
        String ipAddress = request.getHeader("x-forwarded-for");
        String unknown = "unknown";
        if (ipAddress == null || ipAddress.length() == 0 || unknown.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || unknown.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || unknown.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            String benji = "127.0.0.1";
            String bj = "0:0:0:0:0:0:0:1";
            if (benji.equals(ipAddress) || bj.equals(ipAddress)) {
                ///根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                if(inet != null){
                    ipAddress = inet.getHostAddress();
                }
            }
        }
        ///对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        int i = 15;
        String s = ",";
        if (ipAddress != null && ipAddress.length() > i) {
            if (ipAddress.indexOf(s) > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

}
