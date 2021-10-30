package com.mumu.mumumall.service;

import com.mumu.mumumall.vo.CartVO;

import java.util.List;

/**
 * 购物车service接口
 */
public interface CartService {

    /**
     * 更新用户购物车内商品数目
     *
     * @param userId    用户id
     * @param productId 商品id
     * @param count     商品数目
     * @return 购物车对象List
     */
    List<CartVO> update(Integer userId, Integer productId, Integer count);


    /**
     * 删除用户购物车内商品
     *
     * @param userId    用户id
     * @param productId 商品id
     * @return 购物车对象List
     */
    List<CartVO> delete(Integer userId, Integer productId);


    /**
     * 更新用户购物车内商品是否被选中
     *
     * @param userId    用户id
     * @param productId 商品id
     * @param selected  商品是否被选中
     * @return 购物车对象List
     */
    List<CartVO> selectOrNot(Integer userId, Integer productId, Integer selected);

    /**
     * 用户全选/全不选购物车内商品
     *
     * @param userId   用户id
     * @param selected 是否全选
     * @return 购物车对象List
     */
    List<CartVO> selectAllOrNot(Integer userId, Integer selected);

    /**
     * 获取用户购物车列表
     *
     * @param userId 用户id
     * @return 购物车对象List
     */
    List<CartVO> list(Integer userId);

    /**
     * 将商品添加到购物车
     *
     * @param userId    用户id
     * @param productId 商品id
     * @param count     商品数量
     * @return 购物车对象List
     */
    List<CartVO> add(Integer userId, Integer productId, Integer count);
}