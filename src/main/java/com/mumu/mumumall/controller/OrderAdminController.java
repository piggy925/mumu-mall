package com.mumu.mumumall.controller;

import com.github.pagehelper.PageInfo;
import com.mumu.mumumall.common.ApiRestResponse;
import com.mumu.mumumall.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "后台订单管理接口")
@RestController
public class OrderAdminController {
    @Resource
    OrderService orderService;

    @ApiOperation(value = "管理员订单列表")
    @GetMapping("/admin/order/list")
    public ApiRestResponse listForAdmin(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        PageInfo pageInfo = orderService.listForAdmin(pageNum, pageSize);
        return ApiRestResponse.success(pageInfo);
    }
}