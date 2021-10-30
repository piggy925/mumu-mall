package com.mumu.mumumall.model.dao;

import com.mumu.mumumall.model.pojo.Product;
import com.mumu.mumumall.query.ProductListQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品操作接口.
 */
public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    /**
     * 通过商品名查询商品
     *
     * @param productName 商品名
     * @return 商品对象
     */
    Product selectByName(String productName);

    /**
     * 批量更新商品状态
     *
     * @param ids    商品id集合
     * @param status 商品状态
     * @return 是否更新成功
     */
    int batchUpdateStatus(@Param("ids") Integer[] ids, @Param("status") Integer status);


    /**
     * 后台管理页获取商品列表
     *
     * @return 商品列表
     */
    List<Product> listForAdmin();


    /**
     * 前台展示页获取商品名列表
     *
     * @param query 商品查询对象
     * @return 商品列表
     */
    List<Product> listForCustomer(@Param("query") ProductListQuery query);
}