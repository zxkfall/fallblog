package com.flywinter.fallblog.myservice.impl;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.flywinter.fallblog.entity.*;
import com.flywinter.fallblog.mapper.*;
import com.flywinter.fallblog.myservice.PublicService;
import com.flywinter.fallblog.util.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User:Zhang Xingkun
 * Date:2021/8/24 0:21
 * Description:
 */
@Service
public class PublicServiceImpl implements PublicService {
    @Autowired
    TArticleMapper tArticleMapper;
    @Autowired
    TTagMapper tagMapper;
    @Autowired
    TCategoryMapper categoryMapper;
    @Autowired
    TWebViewPeopleMapper webViewPeopleMapper;
    @Autowired
    TCommentMapper commentMapper;
    @Autowired
    TImageMapper imageMapper;


    @Override
    public Page<TArticle> getArticlePage(Integer index, Integer size) {
        Page<TArticle> page = new Page<>(index,size);
        page.addOrder(OrderItem.desc("create_time"));
        return tArticleMapper.selectPage(page, null);
    }

    @Override
    public TArticle getArticleById(String id) {
        return tArticleMapper.selectById(id);
    }

    @Override
    public TCategory getCategoryById(Integer id) {
        return categoryMapper.selectById(id);
    }

    @Override
    public List<TComment> getCommentsByArticleId(String id) {
        return commentMapper.selectList(Wrappers.<TComment>lambdaQuery().eq(TComment::getArticleId, id));
    }

    @Override
    public void insertComment(TComment comment, HttpServletRequest request) {
        if (StringUtils.isNotBlank(comment.getNickname())||StringUtils.isNotBlank(comment.getEmail())
                ||StringUtils.isNotBlank(comment.getContent())) {
            comment.setCreateTime(LocalDateTime.now());
            comment.setUpdateTime(LocalDateTime.now());
            comment.setTarget(null);
            comment.setDevice(request.getHeader("User-Agent"));
            comment.setIp(IpUtil.getIp(request));

            commentMapper.insert(comment);
        }
    }

    @Override
    public List<TTag> getTagList() {
        return tagMapper.selectList(null);
    }

    @Override
    public List<TCategory> getCategoryList() {
        return categoryMapper.selectList(null);
    }

    @Override
    public List<TImage> getImages() {
        return imageMapper.selectList(null);
    }

    @Override
    public List<TArticle> getArticlesByCategoryId(Integer categoryId) {
        return tArticleMapper.selectList(Wrappers.<TArticle>lambdaQuery().eq(TArticle::getCategoryId,categoryId));
    }

    @Override
    public List<TArticle> getArticlesDESC() {
        return tArticleMapper.selectList(Wrappers.<TArticle>lambdaQuery().orderByDesc(TArticle::getCreateTime));
    }


}
