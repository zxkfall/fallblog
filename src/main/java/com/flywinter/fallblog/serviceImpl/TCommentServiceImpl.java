package com.flywinter.fallblog.serviceImpl;

import com.flywinter.fallblog.entity.TComment;
import com.flywinter.fallblog.mapper.TCommentMapper;
import com.flywinter.fallblog.service.ITCommentService;
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
public class TCommentServiceImpl extends ServiceImpl<TCommentMapper, TComment> implements ITCommentService {

}
