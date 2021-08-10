package com.mumu.mumumall.service;

import com.mumu.mumumall.model.pojo.Product;

public interface ProductService {
    /**
     * 添加商品.
     *
     * @param addProductReq the add product req
     */
    void addProduct(Product product);

    /**
     * 更新商品信息.
     *
     * @param updateProductReq the update product req
     */
    void updateProduct(Product product);

    void deleteProduct(Integer id);
}
