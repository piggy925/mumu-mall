package com.mumu.mumumall.model.dao;

import com.mumu.mumumall.model.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * The interface User mapper.
 */
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return 用户对象
     */
    User selectByName(String username);

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户对象
     */
    User selectLogin(@Param("username") String username, @Param("password") String password);
}