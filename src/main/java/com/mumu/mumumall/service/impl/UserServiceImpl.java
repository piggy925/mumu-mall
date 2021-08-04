package com.mumu.mumumall.service.impl;

import com.mumu.mumumall.common.Constant;
import com.mumu.mumumall.exception.MallException;
import com.mumu.mumumall.exception.MallExceptionEnum;
import com.mumu.mumumall.model.dao.UserMapper;
import com.mumu.mumumall.model.pojo.User;
import com.mumu.mumumall.service.UserService;
import com.mumu.mumumall.util.MD5Utils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    @Override
    public User getUserById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public void register(String username, String password) throws MallException {
        User result = userMapper.selectByName(username);
        if (!ObjectUtils.isEmpty(result)) {
            throw new MallException(MallExceptionEnum.NAME_EXISTED);
        }

        User user = new User();
        user.setUsername(username);
        try {
            user.setPassword(MD5Utils.getMD5Str(password));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        int count = userMapper.insertSelective(user);
        if (count == 0) {
            throw new MallException(MallExceptionEnum.INSERT_FAIL);
        }
    }

    @Override
    public User login(String username, String password) throws MallException {
        String md5Password = null;
        try {
            md5Password = MD5Utils.getMD5Str(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        User user = userMapper.selectLogin(username, md5Password);
        if (ObjectUtils.isEmpty(user)) {
            throw new MallException(MallExceptionEnum.WRONG_PASSWORD);
        }
        return user;
    }

    @Override
    public void updateUserInfo(User user) throws MallException {
        int count = userMapper.updateByPrimaryKeySelective(user);
        if (count > 1) {
            throw new MallException(MallExceptionEnum.UPDATE_FAIL);
        }
    }

    @Override
    public boolean checkAdminRole(User user) {
        return user.getRole().equals(Constant.ROLE_ADMIN);
    }
}