package com.mumu.mumumall.model.dao;

import com.mumu.mumumall.model.pojo.Order;

import java.util.List;


/**
 * 订单操作接口.
 */
public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);


    /**
     * 通过订单号查询订单.
     *
     * @param orderNo 订单号
     * @return 订单对象
     */
    Order selectByOrderNo(String orderNo);

    /**
     * 通过用户id查询用户订单列表.
     *
     * @param userId the user id
     * @return the list
     */
    List<Order> selectForCustomer(Integer userId);


    /**
     * 后台管理员查询所有用户的所有订单.
     *
     * @return 订单列表
     */
    List<Order> selectAllForAdmin();
}