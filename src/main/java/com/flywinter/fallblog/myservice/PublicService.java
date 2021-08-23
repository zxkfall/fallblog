package com.flywinter.fallblog.myservice;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.flywinter.fallblog.entity.*;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User:Zhang Xingkun
 * Date:2021/8/24 0:20
 * Description:
 */
public interface PublicService {
    Page<TArticle> getArticlePage(Integer index,Integer size);
    TArticle getArticleById(String id);
    TCategory getCategoryById(Integer id);
    List<TComment> getCommentsByArticleId(String id);
    void insertComment(TComment comment, HttpServletRequest request);
    List<TTag> getTagList();
    List<TCategory> getCategoryList();
    List<TImage> getImages();
    List<TArticle> getArticlesByCategoryId(Integer categoryId);
    List<TArticle> getArticlesDESC();

}
