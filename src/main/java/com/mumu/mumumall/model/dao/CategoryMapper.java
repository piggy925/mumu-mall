package com.mumu.mumumall.model.dao;

import com.mumu.mumumall.model.pojo.Category;

import java.util.List;


/**
 * 商品分类操作接口.
 */
public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    /**
     * 通过分类名查询购物车.
     *
     * @param name 分类名
     * @return 购物车对象
     */
    Category selectByName(String name);

    /**
     * 查询分类列表.
     *
     * @return 分类列表
     */
    List<Category> selectList();

    /**
     * 查询上一级分类（父分类）.
     *
     * @param parentId 父分类id
     * @return 父分类列表
     */
    List<Category> selectByParentId(Integer parentId);
}