package com.flywinter.fallblog;

import com.flywinter.fallblog.dao.ArticleMapper;
import com.flywinter.fallblog.dao.UserMapper;
import com.flywinter.fallblog.entity.TArticle;
import com.flywinter.fallblog.entity.TUser;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootTest
class FallblogApplicationTests {

    @Test
    void contextLoads() {


        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("123456"); //{bcrypt}$2a$10$QeOl0J8ZiiGWv7W4znoleOQMceQEkz9aC4pkHXFAEzek1R1Ctnn/m
        System.out.printf(encode);
    }

//    @Autowired
//    UserMapper userMapper;
    @Autowired
    SqlSession sqlSession;
    @Test
    void test(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        TUser userByEmail = mapper.getUserByEmail("1475795322@qq.com");
        System.out.println(userByEmail);
    }
    @Autowired
    ArticleMapper articleMapper;
    @Test
    void mapper(){
        List<TArticle> tArticles = articleMapper.selectList(null);
        System.out.println(tArticles);
    }


}

