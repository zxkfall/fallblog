package com.flywinter.fallblog.dao;

import com.flywinter.fallblog.entity.TUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by IntelliJ IDEA
 * User:Zhang Xingkun
 * Date:2021/8/16 23:28
 * Description:
 */
@Mapper
public interface UserMapper {

    @Select("select * from t_user where email = #{email}")
    TUser getUserByEmail(@Param("email") String email);
}
