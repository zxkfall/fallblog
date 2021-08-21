package com.flywinter.fallblog.serviceImpl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.flywinter.fallblog.entity.TImage;
import com.flywinter.fallblog.mapper.TImageMapper;
import com.flywinter.fallblog.service.ITImageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zxkfall
 * @since 2021-08-20
 */
@Slf4j
@Service
public class TImageServiceImpl extends ServiceImpl<TImageMapper, TImage> implements ITImageService {

    String rootPath = System.getProperty("user.dir") + "/file";

    private TImageMapper tImageMapper;
    @Autowired
    public void settImageMapper(TImageMapper tImageMapper) {
        this.tImageMapper = tImageMapper;
    }

    @Override
    public TImage uploadImage(MultipartFile file) {
        log.debug(file.getName() + file.getOriginalFilename() + file.getContentType() + file.getSize());
        String uploadFileName = file.getOriginalFilename();
        if (uploadFileName == null) {
            return null;
        }
        int index = uploadFileName.lastIndexOf(".");
        if (index == -1) {
            return null;
        }
        String type = uploadFileName.substring(index + 1);
        String name = uploadFileName.substring(0, index - 1);
        name = new SimpleDateFormat("yyyyMMddHHmmss_").format(new Date()) + UUID.randomUUID().toString().replace("-", "");
        String relativePath = "/image/" + type;
        File tempFullPath = new File(rootPath + relativePath);
        if (!tempFullPath.exists()) {
            if (!tempFullPath.mkdirs()) {
                return null;
            }
        }
        String fullName = name + "." + type;
        File save = new File(tempFullPath.getAbsolutePath() + "/" + fullName);
        log.info(save.getAbsolutePath());
        log.info(save.getPath());
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            inputStream = file.getInputStream();
            outputStream = new FileOutputStream(save);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }

        }
        TImage tImages = new TImage();
        tImages.setPath(relativePath);
        tImages.setCreateTime(LocalDateTime.now());
        tImages.setUpdateTime(LocalDateTime.now());
        tImages.setVersion(1L);
        tImages.setType(type);
        tImages.setName(fullName);
        tImages.setUrl(tImages.getId());
        tImageMapper.insert(tImages);
        return tImages;

    }

    @Override
    public boolean downloadImage(String imageName, HttpServletResponse response) {
        TImage tImage = tImageMapper.selectOne(Wrappers.<TImage>lambdaQuery().eq(TImage::getName, imageName));
        if (tImage == null) {
            return false;
        }
        String fullPath = rootPath + "/" + tImage.getPath() + "/" + tImage.getName();
        File fileImage = new File(fullPath);
        if (!fileImage.exists()) {
            return false;
        }
        response.setContentType("image/" + tImage.getType());
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            fis = new FileInputStream(fileImage);
            bis = new BufferedInputStream(fis);
            os = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = bis.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
        } catch (IOException e) {
            log.error(e.getMessage(),e);
            return false;
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (bis != null) {
                    bis.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
        return  true;
    }
}
