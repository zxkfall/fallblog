package com.flywinter.fallblog.serviceImpl;

import com.flywinter.fallblog.entity.TUser;
import com.flywinter.fallblog.mapper.TUserMapper;
import com.flywinter.fallblog.service.ITUserService;
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
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements ITUserService {

}
