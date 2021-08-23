package com.flywinter.fallblog;


import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.flywinter.fallblog.entity.TImage;
import com.flywinter.fallblog.entity.TTag;
import com.flywinter.fallblog.entity.TUser;
import com.flywinter.fallblog.dto.WeekView;
import com.flywinter.fallblog.mapper.*;
import com.flywinter.fallblog.mymapper.MyWebViewPeopleMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;

@Slf4j
@SpringBootTest
class FallblogApplicationTests {

    @Test
    void contextLoads() {


        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("123456"); //{bcrypt}$2a$10$QeOl0J8ZiiGWv7W4znoleOQMceQEkz9aC4pkHXFAEzek1R1Ctnn/m
        System.out.printf(encode);
    }

    @Autowired
    TUserMapper tUserMapper;

    @Test
    void generatorT() {
        List<TUser> tUsers = tUserMapper.selectList(null);
        System.out.println(tUsers);
    }

    @Test
    public void gePath() throws FileNotFoundException {
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if (!path.exists()) {
            path = new File("");
        }
        log.debug(path.getAbsolutePath());
        log.debug(path.getPath());
        log.debug(path.getParent());
        String property = System.getProperty("user.dir");
        log.debug(property);
        Properties properties = System.getProperties();
        System.out.println(properties);
    }

    //    @Autowired
//    UserMapper userMapper;
//    @Autowired
//    SqlSession sqlSession;
//    @Test
//    void test(){
//        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//
//        TUser userByEmail = mapper.getUserByEmail("1475795322@qq.com");
//        System.out.println(userByEmail);
//    }
    @Autowired
    TTagMapper tTagsMapper;

    @Test
    @Transactional(rollbackFor = RuntimeException.class)
    void mapper() {
        TTag entity = new TTag();
        entity.setId(1L);
        entity.setCreateTime(LocalDateTime.now());
        entity.setUpdateTime(LocalDateTime.now());
        entity.setVersion(1L);
        entity.setName("MM");
        tTagsMapper.insert(entity);
        entity.setName("C+");
        entity.setId(2L);
        tTagsMapper.insert(entity);
    }
    @Autowired
    TImageMapper tImagesMapper;
    @Test
    public void testPage(){
        Page<TImage> page = new Page<>(2,5);
        page.addOrder(OrderItem.asc("update_time"));
        Page<TImage> page1 = tImagesMapper.selectPage(page, Wrappers.<TImage>lambdaQuery().eq(TImage::getType, "jpeg"));
        log.error("总条数 -------------> {}", page1.getTotal());
        log.error("当前页数 -------------> {}", page1.getCurrent());
        log.error("当前每页显示数 -------------> {}", page1.getSize());
        List<TImage> records = page1.getRecords();
        for (TImage record : records) {
            log.debug(record.getId());
        }
    }

    @Autowired
    TWebViewPeopleMapper webViewPeopleMapper;
    @Autowired
    MyWebViewPeopleMapper myWebViewPeopleMapper;

    @Test
    public void testViewPeople(){
        List<WeekView> recentWeekView = myWebViewPeopleMapper.getRecentWeekView();
        System.out.println(recentWeekView);
    }

}

