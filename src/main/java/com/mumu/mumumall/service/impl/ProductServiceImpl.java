package com.mumu.mumumall.service.impl;

import com.mumu.mumumall.exception.MallException;
import com.mumu.mumumall.exception.MallExceptionEnum;
import com.mumu.mumumall.model.dao.ProductMapper;
import com.mumu.mumumall.model.pojo.Product;
import com.mumu.mumumall.model.request.AddProductReq;
import com.mumu.mumumall.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    ProductMapper productMapper;

    @Override
    public void addProduct(AddProductReq addProductReq) {
        Product product = new Product();
        BeanUtils.copyProperties(addProductReq, product);
        Product oldProduct = productMapper.selectByName(addProductReq.getName());
        if (oldProduct != null) {
            throw new MallException(MallExceptionEnum.NAME_EXISTED);
        }
        int count = productMapper.insertSelective(product);
        if (count == 0) {
            throw new MallException(MallExceptionEnum.INSERT_FAIL);
        }
    }
}
