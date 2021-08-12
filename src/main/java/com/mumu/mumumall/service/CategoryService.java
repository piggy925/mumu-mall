package com.mumu.mumumall.service;

import com.github.pagehelper.PageInfo;
import com.mumu.mumumall.model.request.AddCategoryReq;
import com.mumu.mumumall.model.request.UpdateCategoryReq;
import com.mumu.mumumall.vo.CategoryVO;

import java.util.List;

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

    /**
     * 后台显示分类信息.
     *
     * @param pageNum  当前页号
     * @param pageSize 页面大小
     * @return the page info
     */
    PageInfo listForAdmin(Integer pageNum, Integer pageSize);

    /**
     * 前台显示分类信息.
     *
     * @return the list
     */
    List<CategoryVO> listForCustomer(Integer categoryId);
}
