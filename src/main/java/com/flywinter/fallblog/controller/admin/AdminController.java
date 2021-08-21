package com.flywinter.fallblog.controller.admin;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.flywinter.fallblog.entity.TArticle;
import com.flywinter.fallblog.entity.TCategory;
import com.flywinter.fallblog.entity.TImage;
import com.flywinter.fallblog.entity.TTag;
import com.flywinter.fallblog.mapper.TArticleMapper;
import com.flywinter.fallblog.mapper.TCategoryMapper;
import com.flywinter.fallblog.mapper.TTagMapper;
import com.flywinter.fallblog.service.ITImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA
 * User:Zhang Xingkun
 * Date:2021/8/12 21:42
 * Description:
 */
@Slf4j
@Controller
public class AdminController {

    private ITImageService imageService;

    @Autowired
    public AdminController(ITImageService imageService) {
        this.imageService = imageService;
    }


    /**
     * @return
     * @description 登录页单独列出
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String admin() {
        return "admin/login";
    }


    /**
     * @return
     * @description 注册页单独列出，暂时保留实现
     */
    @GetMapping("/register")
    public String register() {
        return "admin/register";
    }

    @GetMapping("/forget")
    public String forget() {
        return "admin/forget";
    }

    /**
     * @return
     * @description 管理页首页，展示各种基本信息
     */
    @GetMapping("/admin")
    public String dashboard() {
        return "admin/dashboard";
    }

    /**
     * @return
     * @description 发布文章页，发布文章
     */
    @GetMapping("/admin/publish")
    public String publish(Model model) {
        List<TCategory> categories = categoryMapper.selectList(null);
        model.addAttribute("categories", categories);
        List<TTag> tTags = tagMapper.selectList(null);
        model.addAttribute("tags",tTags);
        return "admin/publish";
    }
    @PostMapping("/admin/publish")
    public String insertArticle(TArticle article,@RequestParam("test-editor-markdown-doc") String content) {
        System.out.println(content);
        System.out.println(article);
        return "redirect:/admin/publish";
    }
    @Autowired
    TArticleMapper articleMapper;

    /**
     * @return
     * @description 管理模块
     */
    @GetMapping("/admin/article")
    public String article() {

        return "admin/article";
    }


    @GetMapping("/admin/comment")
    public String comment() {
        return "admin/comment";
    }

    @Autowired
    TCategoryMapper categoryMapper;

    @GetMapping("/admin/category")
    public String category(Model model) {
        List<TCategory> categories = categoryMapper.selectList(null);
        model.addAttribute("category_list", categories);
        return "admin/category";
    }

    @PostMapping("/admin/category")
    public String addCategory(TCategory category) {
        log.info(category.getId() + "", category);
        if (category.getId() == null) {
            if (StringUtils.isBlank(category.getName())) {
                return "admin/category";
            }
            category.setCreateTime(LocalDateTime.now());
            category.setUpdateTime(LocalDateTime.now());
            int insert = categoryMapper.insert(category);
            if (insert > 0) {
                return "redirect:/admin/category";
            } else {
                return "admin/category";
            }
        } else {
            if (StringUtils.isBlank(category.getName())) {
                return "admin/category";
            }
            category.setCreateTime(categoryMapper.selectById(category.getId()).getCreateTime());
            category.setUpdateTime(LocalDateTime.now());
            int update = categoryMapper.updateById(category);
            if (update > 0) {
                return "redirect:/admin/category";
            } else {
                return "admin/category";
            }
        }
    }

    @PostMapping("/admin/category/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        int i = categoryMapper.deleteById(id);
        return "redirect:/admin/category";

    }

    @Autowired
    TTagMapper tagMapper;

    @PostMapping("/admin/tag")
    public String addTag(TTag tag) {
        log.info(tag.getId() + "", tag);
        if (StringUtils.isBlank(tag.getName())) {
            return "admin/tag";
        }
        tag.setCreateTime(LocalDateTime.now());
        tag.setUpdateTime(LocalDateTime.now());
        int insert = tagMapper.insert(tag);
        if (insert > 0) {
            return "redirect:/admin/tag";
        } else {
            return "admin/tag";
        }

    }

    @PostMapping("/admin/tag/{id}")
    public String deleteTag(@PathVariable("id") Long id) {
        int i = tagMapper.deleteById(id);
        return "redirect:/admin/tag";

    }


    @GetMapping("/admin/tag")
    public String tags(Model model) {
        List<TTag> tTags = tagMapper.selectList(null);
        model.addAttribute("tags",tTags);
        return "admin/tag";
    }

    @GetMapping("/admin/images")
    public String images() {
        return "admin/images";
    }


    /**
     * @return
     * @description 系统模块
     */
    @GetMapping("/admin/webinfo")
    public String webinfo() {
        return "admin/webinfo";
    }

    @GetMapping("/admin/personal")
    public String personal() {
        return "admin/personal";
    }

    @PostMapping("/admin/image")
    @ResponseBody
    public Map imageUpload(@RequestParam("editormd-image-file") MultipartFile file) {
        TImage image = imageService.uploadImage(file);
        HashMap<String, Object> map = new HashMap<>();
        if (image == null) {
            map.put("success", 0);
            map.put("url", null);
            map.put("message", "上传失败");
        } else {
            map.put("success", 1);
            map.put("url", "/images/" + image.getName());
            map.put("message", "上传成功");
        }
        return map;
    }


    @GetMapping("/images/{imageName}")
    public void getImage(@PathVariable("imageName") String imageName, HttpServletResponse response) {
        imageService.downloadImage(imageName, response);
    }


//
//    @RequestMapping(value = "/get",produces = MediaType.IMAGE_JPEG_VALUE)
//    @ResponseBody
//    public byte[] getImage() throws IOException {
//        File file = new File("D:/test.jpg");
//        FileInputStream inputStream = new FileInputStream(file);
//        byte[] bytes = new byte[inputStream.available()];
//        inputStream.read(bytes, 0, inputStream.available());
//        return bytes;
//    }


}
