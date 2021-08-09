package com.mumu.mumumall.service;

import com.github.pagehelper.PageInfo;
import com.mumu.mumumall.model.request.AddCategoryReq;
import com.mumu.mumumall.model.request.UpdateCategoryReq;

public interface CategoryService {
    /**
     * 后台新增商品分类信息.
     *
     * @param addCategoryReq the add category req
     */
    void add(AddCategoryReq addCategoryReq);

    /**
     * 后台更新商品分类信息.
     *
     * @param updateCategoryReq the update category req
     */
    void update(UpdateCategoryReq updateCategoryReq);

    /**
     * 后台删除商品分类.
     *
     * @param id 商品id
     */
    void delete(Integer id);

    PageInfo listForAdmin(Integer pageNum, Integer pageSize);
}
