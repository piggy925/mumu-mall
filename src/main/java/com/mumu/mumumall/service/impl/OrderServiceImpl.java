package com.mumu.mumumall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.zxing.WriterException;
import com.mumu.mumumall.common.Constant;
import com.mumu.mumumall.exception.MallException;
import com.mumu.mumumall.exception.MallExceptionEnum;
import com.mumu.mumumall.filter.UserFilter;
import com.mumu.mumumall.model.dao.CartMapper;
import com.mumu.mumumall.model.dao.OrderItemMapper;
import com.mumu.mumumall.model.dao.OrderMapper;
import com.mumu.mumumall.model.dao.ProductMapper;
import com.mumu.mumumall.model.pojo.Order;
import com.mumu.mumumall.model.pojo.OrderItem;
import com.mumu.mumumall.model.pojo.Product;
import com.mumu.mumumall.model.pojo.User;
import com.mumu.mumumall.model.request.CreateOrderReq;
import com.mumu.mumumall.service.CartService;
import com.mumu.mumumall.service.OrderService;
import com.mumu.mumumall.service.UserService;
import com.mumu.mumumall.util.OrderCodeFactory;
import com.mumu.mumumall.util.QRCodeGenerator;
import com.mumu.mumumall.vo.CartVO;
import com.mumu.mumumall.vo.OrderItemVO;
import com.mumu.mumumall.vo.OrderVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    CartService cartService;
    @Resource
    ProductMapper productMapper;
    @Resource
    CartMapper cartMapper;
    @Resource
    OrderMapper orderMapper;
    @Resource
    OrderItemMapper orderItemMapper;
    @Resource
    UserService userService;

    @Value("${file.upload.ip}")
    String ip;

    //数据库事务
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String create(CreateOrderReq createOrderReq) {
        Integer userId = UserFilter.currentUser.getId();
        List<CartVO> cartVOList = cartService.list(userId);
        cartVOList = cartVOList.stream().filter(s -> s.getSelected().equals(Constant.CART_STATUS.CHECKED)).collect(Collectors.toList());

        if (CollectionUtils.isEmpty(cartVOList)) {
            throw new MallException(MallExceptionEnum.CART_EMPTY);
        }

        //验证商品状态与库存
        validProductStatusAndStock(cartVOList);
        //将购物车对象转换为订单item对象
        List<OrderItem> orderItems = cartVOListToOrderItemList(cartVOList);
        //扣库存
        orderItems.forEach(orderItem -> {
            Product product = productMapper.selectByPrimaryKey(orderItem.getProductId());
            int stock = product.getStock() - orderItem.getQuantity();
            if (stock < 0) {
                throw new MallException(MallExceptionEnum.NOT_ENOUGH);
            }
            product.setStock(stock);
            productMapper.updateByPrimaryKeySelective(product);
        });
        //删除购物车中已勾选的商品
        cleanCart(cartVOList);
        //生成订单
        Order order = new Order();
        //生成订单号
        String orderNo = OrderCodeFactory.getOrderCode(Long.valueOf(userId));
        order.setOrderNo(orderNo);
        order.setUserId(userId);
        order.setTotalPrice(totalPrice(orderItems));
        order.setReceiverName(createOrderReq.getReceiverName());
        order.setReceiverAddress(createOrderReq.getReceiverAddress());
        order.setReceiverMobile(createOrderReq.getReceiverMobile());
        order.setOrderStatus(Constant.OrderStatusEnum.NOT_PAID.getCode());
        //0表示包邮
        order.setPostage(0);
        //1表示在线支付
        order.setPaymentType(1);
        //插入到order表
        orderMapper.insertSelective(order);
        //循环保存每个商品到order_item表
        orderItems.forEach(orderItem -> {
            orderItem.setOrderNo(order.getOrderNo());
            orderItemMapper.insertSelective(orderItem);
        });
        //订单创建成功，返回订单号
        return orderNo;
    }

    private void validProductStatusAndStock(List<CartVO> cartVOList) {
        for (CartVO cartVO : cartVOList) {
            Product product = productMapper.selectByPrimaryKey(cartVO.getProductId());
            //判断商品是否存在，是否上架
            if (product == null || product.getStatus().equals(Constant.PRODUCT_STATUS.NOT_SALE)) {
                throw new MallException(MallExceptionEnum.NOT_SALE);
            }
            //判断商品库存是否足够
            if (cartVO.getQuantity() > product.getStock()) {
                throw new MallException(MallExceptionEnum.NOT_ENOUGH);
            }
        }
    }

    private List<OrderItem> cartVOListToOrderItemList(List<CartVO> cartVOList) {
        List<OrderItem> orderItemList = new ArrayList<>();
        cartVOList.forEach(cartVO -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(cartVO.getProductId());
            orderItem.setProductName(cartVO.getProductName());
            orderItem.setProductImg(cartVO.getProductImage());
            orderItem.setUnitPrice(cartVO.getPrice());
            orderItem.setQuantity(cartVO.getQuantity());
            orderItem.setTotalPrice(cartVO.getTotalPrice());
            orderItemList.add(orderItem);
        });
        return orderItemList;
    }

    private void cleanCart(List<CartVO> cartVOList) {
        cartVOList.forEach(cartVO -> {
            cartMapper.deleteByPrimaryKey(cartVO.getId());
        });
    }

    private Integer totalPrice(List<OrderItem> orderItems) {
        Integer totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }

    @Override
    public OrderVO detail(String orderNo) {
        Order order = orderMapper.selectByOrderNo(orderNo);
        if (ObjectUtils.isEmpty(order)) {
            throw new MallException(MallExceptionEnum.NO_ORDER);
        }
        Integer userId = UserFilter.currentUser.getId();
        if (!(order.getUserId()).equals(userId)) {
            throw new MallException(MallExceptionEnum.NO_YOUR_ORDER);
        }

        OrderVO orderVO = getOrderVO(order);
        return orderVO;
    }

    private OrderVO getOrderVO(Order order) {
        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(order, orderVO);
        List<OrderItem> orderItemList = orderItemMapper.selectByOrderNo(order.getOrderNo());
        List<OrderItemVO> orderItemVOList = new ArrayList<>();
        orderItemList.forEach(orderItem -> {
            OrderItemVO orderItemVO = new OrderItemVO();
            BeanUtils.copyProperties(orderItem, orderItemVO);
            orderItemVOList.add(orderItemVO);
        });
        orderVO.setOrderItemVOList(orderItemVOList);
        orderVO.setOrderStatusName(Constant.OrderStatusEnum.codeOf(orderVO.getOrderStatus()).getStatus());

        return orderVO;
    }

    @Override
    public PageInfo listForCustomer(Integer pageNum, Integer pageSize) {
        Integer userId = UserFilter.currentUser.getId();
        PageHelper.startPage(pageNum, pageSize);
        List<Order> orders = orderMapper.selectForCustomer(userId);
        List<OrderVO> orderVOList = orderListToOrderVOList(orders);
        PageInfo<OrderVO> pageInfo = new PageInfo<>(orderVOList);
        return pageInfo;
    }

    @Override
    public PageInfo listForAdmin(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Order> orders = orderMapper.selectAllForAdmin();
        List<OrderVO> orderVOList = orderListToOrderVOList(orders);
        PageInfo<OrderVO> pageInfo = new PageInfo<>(orderVOList);
        return pageInfo;
    }

    private List<OrderVO> orderListToOrderVOList(List<Order> orders) {
        List<OrderVO> orderVOList = new ArrayList();
        orders.forEach(order -> {
            OrderVO orderVO = getOrderVO(order);
            orderVOList.add(orderVO);
        });
        return orderVOList;
    }

    @Override
    public void cancel(String orderNo) {
        Integer userId = UserFilter.currentUser.getId();
        Order order = orderMapper.selectByOrderNo(orderNo);
        if (ObjectUtils.isEmpty(order)) {
            throw new MallException(MallExceptionEnum.NO_ORDER);
        }
        if (!order.getUserId().equals(userId)) {
            throw new MallException(MallExceptionEnum.NO_YOUR_ORDER);
        }
        //只有订单处于未付款状态时才允许取消
        if (Constant.OrderStatusEnum.NOT_PAID.getCode().equals(order.getOrderStatus())) {
            order.setOrderStatus(Constant.OrderStatusEnum.CANCELED.getCode());
            order.setEndTime(new Date());
            orderMapper.updateByPrimaryKeySelective(order);
        } else {
            throw new MallException(MallExceptionEnum.WRONG_ORDER_STATUS);
        }
    }

    @Override
    public String qrcode(String orderNo) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String address = ip + ":" + request.getLocalPort();
        String payUrl = "http://" + address + "/pay?orderNo=" + orderNo;
        try {
            QRCodeGenerator.generateQRCodeImage(payUrl, 350, 350, Constant.FILE_UPLOAD_DIR + orderNo + ".png");
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String pngAddress = "http://" + address + "/images/" + orderNo + ".png";
        return pngAddress;
    }

    @Override
    public void pay(String orderNo) {
        Order order = orderMapper.selectByOrderNo(orderNo);
        if (ObjectUtils.isEmpty(order)) {
            throw new MallException(MallExceptionEnum.NO_ORDER);
        }
        if (order.getOrderStatus().equals(Constant.OrderStatusEnum.NOT_PAID.getCode())) {
            order.setOrderStatus(Constant.OrderStatusEnum.PAID.getCode());
            order.setPayTime(new Date());
            orderMapper.updateByPrimaryKeySelective(order);
        } else {
            throw new MallException(MallExceptionEnum.WRONG_ORDER_STATUS);
        }
    }

    @Override
    public void deliver(String orderNo) {
        Order order = orderMapper.selectByOrderNo(orderNo);
        if (ObjectUtils.isEmpty(order)) {
            throw new MallException(MallExceptionEnum.NO_ORDER);
        }
        //订单状态为已付款才允许发货
        if (order.getOrderStatus().equals(Constant.OrderStatusEnum.PAID.getCode())) {
            order.setOrderStatus(Constant.OrderStatusEnum.DELIVERED.getCode());
            order.setDeliveryTime(new Date());
            orderMapper.updateByPrimaryKeySelective(order);
        } else {
            throw new MallException(MallExceptionEnum.WRONG_ORDER_STATUS);
        }
    }

    @Override
    public void finish(String orderNo) {
        User user = UserFilter.currentUser;
        Order order = orderMapper.selectByOrderNo(orderNo);
        if (ObjectUtils.isEmpty(order)) {
            throw new MallException(MallExceptionEnum.NO_ORDER);
        }
        //只有管理员与订单所属用户可以完结订单
        if (!userService.checkAdminRole(user) && !user.getId().equals(order.getUserId())) {
            throw new MallException(MallExceptionEnum.NO_ORDER);
        }
        //订单状态为已发货才允许完结
        if (order.getOrderStatus().equals(Constant.OrderStatusEnum.DELIVERED.getCode())) {
            order.setOrderStatus(Constant.OrderStatusEnum.FINISHED.getCode());
            order.setEndTime(new Date());
            orderMapper.updateByPrimaryKeySelective(order);
        } else {
            throw new MallException(MallExceptionEnum.WRONG_ORDER_STATUS);
        }
    }
}