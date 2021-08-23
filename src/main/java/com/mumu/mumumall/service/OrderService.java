package com.mumu.mumumall.service;

import com.github.pagehelper.PageInfo;
import com.mumu.mumumall.model.request.CreateOrderReq;
import com.mumu.mumumall.vo.OrderVO;

/**
 * The interface Order service.
 */
public interface OrderService {
    /**
     * 创建订单.
     *
     * @param createOrderReq the create order req
     * @return the string
     */
    String create(CreateOrderReq createOrderReq);

    /**
     * 订单详情.
     *
     * @param orderNo 订单编号
     * @return order对象
     */
    OrderVO detail(String orderNo);

    /**
     * 前台显示订单列表.
     *
     * @param pageNum  当前页号
     * @param pageSize 每页大小
     * @return PageInfo分页对象
     */
    PageInfo listForCustomer(Integer pageNum, Integer pageSize);

    /**
     * 取消订单.
     *
     * @param orderNo 订单编号
     */
    void cancel(String orderNo);
}