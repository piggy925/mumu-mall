package com.mumu.mumumall.service;

import com.mumu.mumumall.model.pojo.Product;

public interface ProductService {
    /**
     * 添加商品.
     *
     * @param product 商品对象
     */
    void addProduct(Product product);

    /**
     * 更新商品信息.
     *
     * @param product the update product req
     */
    void updateProduct(Product product);

    /**
     * 删除商品.
     *
     * @param id 商品id
     */
    void deleteProduct(Integer id);

    /**
     * 批量更新商品状态.
     *
     * @param ids    商品id数组
     * @param status 商品状态
     */
    void batchUpdateStatus(Integer[] ids, Integer status);
}
