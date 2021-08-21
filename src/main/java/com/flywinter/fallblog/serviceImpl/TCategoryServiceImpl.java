package com.flywinter.fallblog.serviceImpl;

import com.flywinter.fallblog.entity.TCategory;
import com.flywinter.fallblog.mapper.TCategoryMapper;
import com.flywinter.fallblog.service.ITCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zxkfall
 * @since 2021-08-20
 */
@Service
public class TCategoryServiceImpl extends ServiceImpl<TCategoryMapper, TCategory> implements ITCategoryService {

}
