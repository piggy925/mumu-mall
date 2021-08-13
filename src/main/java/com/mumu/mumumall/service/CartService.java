package com.mumu.mumumall.service;

import com.mumu.mumumall.vo.CartVO;

import java.util.List;

public interface CartService {
    List<CartVO> add(Integer userId, Integer productId, Integer count);
}
