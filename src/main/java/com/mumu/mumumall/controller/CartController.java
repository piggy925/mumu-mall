package com.mumu.mumumall.controller;

import com.mumu.mumumall.common.ApiRestResponse;
import com.mumu.mumumall.filter.UserFilter;
import com.mumu.mumumall.service.CartService;
import com.mumu.mumumall.vo.CartVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "购物车管理接口")
@RestController
@RequestMapping("/cart")
public class CartController {
    @Resource
    CartService cartService;

    @ApiOperation("购物车列表")
    @GetMapping("/list")
    public ApiRestResponse list() {
        List<CartVO> cartVOList = cartService.list(UserFilter.currentUser.getId());
        return ApiRestResponse.success(cartVOList);
    }

    @ApiOperation("购物车添加商品")
    @PostMapping("/add")
    public ApiRestResponse add(@RequestParam Integer productId, @RequestParam Integer count) {
        Integer userId = UserFilter.currentUser.getId();
        List<CartVO> cartVOList = cartService.add(userId, productId, count);
        return ApiRestResponse.success(cartVOList);
    }

    @ApiOperation("购物车更新商品")
    @PostMapping("/update")
    public ApiRestResponse update(@RequestParam Integer productId, @RequestParam Integer count) {
        Integer userId = UserFilter.currentUser.getId();
        List<CartVO> cartVOList = cartService.update(userId, productId, count);
        return ApiRestResponse.success(cartVOList);
    }

    @ApiOperation("购物车删除商品")
    @PostMapping("/delete")
    public ApiRestResponse delete(@RequestParam Integer productId) {
        Integer userId = UserFilter.currentUser.getId();
        List<CartVO> cartVOList = cartService.delete(userId, productId);
        return ApiRestResponse.success(cartVOList);
    }

    @ApiOperation("购物车选中/不选中某商品")
    @PostMapping("/select")
    public ApiRestResponse select(@RequestParam Integer productId, @RequestParam Integer selected) {
        Integer userId = UserFilter.currentUser.getId();
        List<CartVO> cartVOList = cartService.selectOrNot(userId, productId, selected);
        return ApiRestResponse.success(cartVOList);
    }

    @ApiOperation("购物车全选/全不选商品")
    @PostMapping("/selectAll")
    public ApiRestResponse selectAll(@RequestParam Integer selected) {
        Integer userId = UserFilter.currentUser.getId();
        List<CartVO> cartVOList = cartService.selectAllOrNot(userId, selected);
        return ApiRestResponse.success(cartVOList);
    }
}