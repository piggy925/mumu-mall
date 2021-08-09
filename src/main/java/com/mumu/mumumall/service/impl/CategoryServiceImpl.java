package com.mumu.mumumall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mumu.mumumall.exception.MallException;
import com.mumu.mumumall.exception.MallExceptionEnum;
import com.mumu.mumumall.model.dao.CategoryMapper;
import com.mumu.mumumall.model.pojo.Category;
import com.mumu.mumumall.model.request.AddCategoryReq;
import com.mumu.mumumall.model.request.UpdateCategoryReq;
import com.mumu.mumumall.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    @Resource
    CategoryMapper categoryMapper;

    @Override
    public void add(AddCategoryReq addCategoryReq) {
        Category category = new Category();
        BeanUtils.copyProperties(addCategoryReq, category);
        Category categoryOld = categoryMapper.selectByName(addCategoryReq.getName());
        if (categoryOld != null) {
            throw new MallException(MallExceptionEnum.NAME_EXISTED);
        }

        int count = categoryMapper.insertSelective(category);
        if (count == 0) {
            throw new MallException(MallExceptionEnum.CREATE_FAIL);
        }
    }

    @Override
    public void update(UpdateCategoryReq updateCategoryReq) {
        if (updateCategoryReq.getName() != null) {
            Category categoryOld = categoryMapper.selectByName(updateCategoryReq.getName());
            if (categoryOld != null && categoryOld.getId().equals(updateCategoryReq.getId())) {
                throw new MallException(MallExceptionEnum.NAME_EXISTED);
            }
        }

        Category category = new Category();
        BeanUtils.copyProperties(updateCategoryReq, category);

        int count = categoryMapper.updateByPrimaryKeySelective(category);
        if (count == 0) {
            throw new MallException(MallExceptionEnum.CREATE_FAIL);
        }
    }

    @Override
    public void delete(Integer id) {
        Category category = categoryMapper.selectByPrimaryKey(id);
        if (category == null) {
            throw new MallException(MallExceptionEnum.DELETE_FAIL);
        }

        int count = categoryMapper.deleteByPrimaryKey(id);
        if (count == 0) {
            throw new MallException(MallExceptionEnum.DELETE_FAIL);
        }
    }

    @Override
    public PageInfo listForAdmin(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize, "type, order_num");
        List<Category> categoryList = categoryMapper.selectList();
        PageInfo pageInfo = new PageInfo(categoryList);
        return pageInfo;
    }
}
