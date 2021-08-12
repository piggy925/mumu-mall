package com.mumu.mumumall.service;

import com.github.pagehelper.PageInfo;
import com.mumu.mumumall.model.pojo.Product;
import com.mumu.mumumall.model.request.ListProductReq;

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

    /**
     * 分页查询后台商品列表.
     *
     * @param pageNum  当前页号
     * @param pageSize 页面大小
     * @return 分页对象
     */
    PageInfo listForAdmin(Integer pageNum, Integer pageSize);

    /**
     * 显示商品详情.
     *
     * @param id 商品id
     * @return 商品对象
     */
    Product detail(Integer id);

    /**
     * 分页查询前台商品.
     *
     * @param listProductReq the list product req
     * @return the page info
     */
    PageInfo listForCustomer(ListProductReq listProductReq);
}
