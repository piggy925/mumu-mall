package com.mumu.mumumall.common;

import com.google.common.collect.Sets;
import com.mumu.mumumall.exception.MallException;
import com.mumu.mumumall.exception.MallExceptionEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class Constant {
    public static final String MALL_USER = "mall_user";

    public static final String SALT = "dasfewqeioq]dsada[";

    public static final int ROLE_USER = 1;
    public static final int ROLE_ADMIN = 2;

    public static String FILE_UPLOAD_DIR;

    @Value("${file.upload.dir}")
    public void setFileUploadDir(String fileUploadDir) {
        FILE_UPLOAD_DIR = fileUploadDir;
    }

    public interface ProductListOrderBy {
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price asc", "price desc");
    }

    public interface PRODUCT_STATUS {
        int SALE = 1; //商品状态为可售
        int NOT_SALE = 0; //商品状态为下架
    }

    public interface CART_STATUS {
        int UN_CHECKED = 0; //购物车商品为未被选中状态
        int CHECKED = 1; //购物车商品为被选中状态
    }

    public enum OrderStatusEnum {
        CANCELED(0, "订单已取消"),
        NOT_PAID(10, "订单未付款"),
        PAID(20, "已付款"),
        DELIVERED(30, "已发货"),
        FINISHED(40, "已完成");

        private Integer code;
        private String status;

        OrderStatusEnum(Integer code, String status) {
            this.code = code;
            this.status = status;
        }

        public static OrderStatusEnum codeOf(int code) {
            for (OrderStatusEnum orderStatusEnum : OrderStatusEnum.values()) {
                if (orderStatusEnum.getCode().equals(code)) {
                    return orderStatusEnum;
                }
            }
            throw new MallException(MallExceptionEnum.NO_ENUM);
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}