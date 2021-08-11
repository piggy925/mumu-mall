package com.mumu.mumumall.model.dao;

import com.mumu.mumumall.model.pojo.Product;
import org.apache.ibatis.annotations.Param;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    Product selectByName(String productName);

    int batchUpdateStatus(@Param("ids") Integer[] ids, @Param("status") Integer status);
}