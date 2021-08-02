package com.mumu.mumumall.service;

import com.mumu.mumumall.exception.MallException;
import com.mumu.mumumall.model.pojo.User;

/**
 * 用户接口.
 */
public interface UserService {

    /**
     * 通过用户id查询用户.
     *
     * @param userId 用户id
     * @return 用户对象
     */
    User getUserById(Integer userId);


    /**
     * 用户注册功能.
     *
     * @param username 用户名
     * @param password 密码
     * @throws MallException the mall exception
     */
    void register(String username, String password) throws MallException;
}
