package com.flywinter.fallblog.mymapper;

import com.flywinter.fallblog.dto.ArticleCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User:Zhang Xingkun
 * Date:2021/8/23 16:38
 * Description:
 */
@Mapper
public interface MyArticleCategoryMapper {
    List<ArticleCategory> getArticleCategory();
}
