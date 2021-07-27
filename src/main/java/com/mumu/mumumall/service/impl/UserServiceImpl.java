package com.mumu.mumumall.service.impl;

import com.mumu.mumumall.model.dao.UserMapper;
import com.mumu.mumumall.model.pojo.User;
import com.mumu.mumumall.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    @Override
    public User getUserById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }
}