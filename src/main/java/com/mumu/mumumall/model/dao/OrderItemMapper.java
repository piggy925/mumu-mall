package com.mumu.mumumall.model.dao;

import com.mumu.mumumall.model.pojo.OrderItem;

import java.util.List;


/**
 * 订单商品操作接口.
 */
public interface OrderItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    OrderItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);


    /**
     * 通过订单号查询商品列表.
     *
     * @param orderNo 订单号
     * @return 商品列表
     */
    List<OrderItem> selectByOrderNo(String orderNo);
}