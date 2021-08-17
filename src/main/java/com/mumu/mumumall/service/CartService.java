package com.mumu.mumumall.service;

import com.mumu.mumumall.vo.CartVO;

import java.util.List;

public interface CartService {
    List<CartVO> update(Integer userId, Integer productId, Integer count);

    List<CartVO> delete(Integer userId, Integer productId);

    List<CartVO> list(Integer userId);

    List<CartVO> add(Integer userId, Integer productId, Integer count);
}