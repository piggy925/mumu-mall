package com.mumu.mumumall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mumu.mumumall.exception.MallException;
import com.mumu.mumumall.exception.MallExceptionEnum;
import com.mumu.mumumall.model.dao.ProductMapper;
import com.mumu.mumumall.model.pojo.Product;
import com.mumu.mumumall.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    ProductMapper productMapper;

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
}
