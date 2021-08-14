package com.mumu.mumumall.service.impl;

import com.mumu.mumumall.common.Constant;
import com.mumu.mumumall.exception.MallException;
import com.mumu.mumumall.exception.MallExceptionEnum;
import com.mumu.mumumall.model.dao.CartMapper;
import com.mumu.mumumall.model.dao.ProductMapper;
import com.mumu.mumumall.model.pojo.Cart;
import com.mumu.mumumall.model.pojo.Product;
import com.mumu.mumumall.service.CartService;
import com.mumu.mumumall.vo.CartVO;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Resource
    ProductMapper productMapper;
    @Resource
    CartMapper cartMapper;

    @Override
    public List<CartVO> add(Integer userId, Integer productId, Integer count) {
        validProduct(productId, count);
        Cart cart = cartMapper.selectCartByUserIdAndProductId(userId, productId);
        if (ObjectUtils.isEmpty(cart)) {
            //商品不在购物车内，需要新增一个记录
            cart = new Cart();
            cart.setUserId(userId);
            cart.setProductId(productId);
            cart.setQuantity(count);
            cart.setSelected(Constant.CART_STATUS.CHECKED);
            cartMapper.insertSelective(cart);
        } else {
            //商品已存在与购物车内，数量相加即可
            count += cart.getQuantity();
            Cart newCart = new Cart();
            newCart.setQuantity(count);
            newCart.setId(cart.getId());
            newCart.setUserId(userId);
            newCart.setProductId(productId);
            cartMapper.updateByPrimaryKeySelective(newCart);
        }

        return this.list(userId);
    }

    @Override
    public List<CartVO> list(Integer userId) {
        List<CartVO> cartVOS = cartMapper.selectList(userId);
        for (CartVO cartVO : cartVOS) {
            cartVO.setTotalPrice(cartVO.getPrice() * cartVO.getQuantity());
        }
        return cartVOS;
    }

    //验证添加到购物车的商品是否有效
    private void validProduct(Integer productId, Integer count) {
        Product product = productMapper.selectByPrimaryKey(productId);
        //判断商品是否存在，是否上架
        if (product == null || product.getStatus().equals(Constant.PRODUCT_STATUS.NOT_SALE)) {
            throw new MallException(MallExceptionEnum.NOT_SALE);
        }

        //判断商品库存是否足够
        if (count > product.getStock()) {
            throw new MallException(MallExceptionEnum.NOT_ENOUGH);
        }
    }
}