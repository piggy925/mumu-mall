package com.mumu.mumumall.model.dao;

import com.mumu.mumumall.model.pojo.Cart;
import com.mumu.mumumall.vo.CartVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 购物车操作接口.
 */
public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);


    /**
     * 通过用户id与商品id查询购物车.
     *
     * @param userId    用户id
     * @param productId 商品id
     * @return 购物车对象
     */
    Cart selectCartByUserIdAndProductId(@Param("userId") Integer userId, @Param("productId") Integer productId);

    /**
     * 通过用户id查询购物车列表.
     *
     * @param userId 用户id
     * @return 购物车列表
     */
    List<CartVO> selectList(Integer userId);

    /**
     * 更新购物车是否已被用户选中.
     *
     * @param userId    用户id
     * @param productId 商品id
     * @param selected  是否被选中
     */
    void selectOrNot(@Param("userId") Integer userId, @Param("productId") Integer productId, @Param("selected") Integer selected);
}