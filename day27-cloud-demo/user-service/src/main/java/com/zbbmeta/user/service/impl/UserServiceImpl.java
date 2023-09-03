package com.zbbmeta.user.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zbbmeta.user.entity.User;
import com.zbbmeta.user.mapper.UserMapper;
import com.zbbmeta.user.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author springboot葵花宝典
 * @description: TODO
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
