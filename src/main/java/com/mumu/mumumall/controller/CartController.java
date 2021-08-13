package com.mumu.mumumall.controller;

import com.mumu.mumumall.common.ApiRestResponse;
import com.mumu.mumumall.filter.UserFilter;
import com.mumu.mumumall.service.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "购物车管理接口")
@RestController
@RequestMapping("/cart")
public class CartController {
    @Resource
    CartService cartService;

    @ApiOperation("添加到购物车")
    @PostMapping("/add")
    public ApiRestResponse add(@RequestParam Integer productId, @RequestParam Integer count) {
        Integer userId = UserFilter.currentUser.getId();
        cartService.add(userId, productId, count);
        return ApiRestResponse.success();
    }
}
