package com.mumu.mumumall.controller;

import com.github.pagehelper.PageInfo;
import com.mumu.mumumall.common.ApiRestResponse;
import com.mumu.mumumall.model.pojo.Product;
import com.mumu.mumumall.model.request.ListProductReq;
import com.mumu.mumumall.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 前台商品Controller
 */
@Api(tags = "前台商品管理接口")
@RestController
public class ProductCustomerController {
    @Resource
    ProductService productService;

    @ApiOperation("前台商品详情")
    @GetMapping("/product/detail")
    public ApiRestResponse detail(@RequestParam Integer id) {
        Product product = productService.detail(id);
        return ApiRestResponse.success(product);
    }

    @ApiOperation("前台商品列表")
    @GetMapping("/product/list")
    public ApiRestResponse list(ListProductReq listProductReq) {
        PageInfo pageInfo = productService.listForCustomer(listProductReq);
        return ApiRestResponse.success(pageInfo);
    }
}
