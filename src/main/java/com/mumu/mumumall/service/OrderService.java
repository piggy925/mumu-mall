package com.mumu.mumumall.service;

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
}