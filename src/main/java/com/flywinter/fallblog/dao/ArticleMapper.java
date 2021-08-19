package com.flywinter.fallblog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flywinter.fallblog.entity.TArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by IntelliJ IDEA
 * User:Zhang Xingkun
 * Date:2021/8/19 13:46
 * Description:
 */
@Mapper
public interface ArticleMapper extends BaseMapper<TArticle> {

//    @Select("select * from t_article where id = #{id}")
//    TArticle getArticleById(@Param("id")String id);
}
