package com.mumu.mumumall.common;

import com.google.common.collect.Sets;
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
}
