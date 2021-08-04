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

    /**
     * 用户登录.
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户对象
     * @throws MallException the mall exception
     */
    User login(String username, String password) throws MallException;

    /**
     * 更新用户个人信息.
     *
     * @param user 用户对象
     * @throws MallException the mall exception
     */
    void updateUserInfo(User user) throws MallException;

    /**
     * 验证用户管理员权限.
     *
     * @param user 用户对象
     * @return the boolean
     */
    boolean checkAdminRole(User user);
}
