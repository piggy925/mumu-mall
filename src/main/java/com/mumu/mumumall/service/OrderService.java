package com.mumu.mumumall.service;

import com.mumu.mumumall.model.request.CreateOrderReq;

public interface OrderService {
    String create(CreateOrderReq createOrderReq);
}