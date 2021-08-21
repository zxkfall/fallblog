package com.flywinter.fallblog.service;

import com.flywinter.fallblog.entity.TImage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zxkfall
 * @since 2021-08-20
 */
public interface ITImageService extends IService<TImage> {

    TImage uploadImage(MultipartFile file);

    boolean downloadImage(String imageName, HttpServletResponse response);

}
