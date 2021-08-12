package com.mumu.mumumall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mumu.mumumall.common.Constant;
import com.mumu.mumumall.exception.MallException;
import com.mumu.mumumall.exception.MallExceptionEnum;
import com.mumu.mumumall.model.dao.ProductMapper;
import com.mumu.mumumall.model.pojo.Product;
import com.mumu.mumumall.model.request.ListProductReq;
import com.mumu.mumumall.query.ProductListQuery;
import com.mumu.mumumall.service.CategoryService;
import com.mumu.mumumall.service.ProductService;
import com.mumu.mumumall.vo.CategoryVO;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    ProductMapper productMapper;
    @Resource
    CategoryService categoryService;

    @Override
    public void addProduct(Product product) {
        Product oldProduct = productMapper.selectByName(product.getName());
        if (oldProduct != null) {
            throw new MallException(MallExceptionEnum.NAME_EXISTED);
        }

        int count = productMapper.insertSelective(product);
        if (count == 0) {
            throw new MallException(MallExceptionEnum.INSERT_FAIL);
        }
    }

    @Override
    public void updateProduct(Product product) {
        Product oldProduct = productMapper.selectByName(product.getName());
        if (oldProduct != null && !oldProduct.getId().equals(product.getId())) {
            throw new MallException(MallExceptionEnum.NAME_EXISTED);
        }

        int count = productMapper.updateByPrimaryKeySelective(product);
        if (count == 0) {
            throw new MallException(MallExceptionEnum.UPDATE_FAIL);
        }
    }

    @Override
    public void deleteProduct(Integer id) {
        Product oldProduct = productMapper.selectByPrimaryKey(id);
        if (oldProduct == null) {
            throw new MallException(MallExceptionEnum.DELETE_FAIL);
        }

        int count = productMapper.deleteByPrimaryKey(id);
        if (count == 0) {
            throw new MallException(MallExceptionEnum.DELETE_FAIL);
        }
    }

    @Override
    public void batchUpdateStatus(Integer[] ids, Integer status) {
        int count = productMapper.batchUpdateStatus(ids, status);
        if (count == 0) {
            throw new MallException(MallExceptionEnum.UPDATE_FAIL);
        }
    }

    @Override
    public PageInfo listForAdmin(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Product> products = productMapper.listForAdmin();
        PageInfo<Object> pageInfo = new PageInfo(products);
        return pageInfo;
    }

    @Override
    public Product detail(Integer id) {
        Product product = productMapper.selectByPrimaryKey(id);
        return product;
    }

    @Override
    public PageInfo listForCustomer(ListProductReq listProductReq) {
        ProductListQuery query = new ProductListQuery();
        if (!ObjectUtils.isEmpty(listProductReq.getKeyword())) {
            String keyword = "%" + listProductReq.getKeyword() + "%";
            query.setKeyword(keyword);
        }

        if (listProductReq.getCategoryId() != null) {
            List<CategoryVO> categoryVOList = categoryService.listForCustomer(listProductReq.getCategoryId());
            List<Integer> categoryIds = new ArrayList<>();
            categoryIds.add(listProductReq.getCategoryId());
            getCategoryIds(categoryVOList, categoryIds);
            query.setCategoryIds(categoryIds);
        }

        String orderBy = listProductReq.getOrderBy();
        if (Constant.ProductListOrderBy.PRICE_ASC_DESC.contains(orderBy)) {
            PageHelper.startPage(listProductReq.getPageNum(), listProductReq.getPageSize(), orderBy);
        } else {
            PageHelper.startPage(listProductReq.getPageNum(), listProductReq.getPageSize());
        }

        List<Product> products = productMapper.listForCustomer(query);
        PageInfo pageInfo = new PageInfo(products);
        return pageInfo;
    }

    private void getCategoryIds(List<CategoryVO> categoryVOList, List<Integer> categoryIds) {
        for (int i = 0; i < categoryVOList.size(); i++) {
            CategoryVO categoryVO = categoryVOList.get(i);
            if (categoryVO != null) {
                categoryIds.add(categoryVO.getId());
                getCategoryIds(categoryVO.getChildCategory(), categoryIds);
            }
        }
    }
}
