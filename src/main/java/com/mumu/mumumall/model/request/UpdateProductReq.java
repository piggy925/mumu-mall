package com.mumu.mumumall.model.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 商品表
 */
public class UpdateProductReq {
    /**
     * 商品id
     */
    @NotNull(message = "商品id不能为空")
    private Integer id;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 产品图片,相对路径地址
     */
    private String image;
    /**
     * 商品详情
     */
    private String detail;
    /**
     * 分类id
     */
    private Integer categoryId;
    /**
     * 价格,单位-分
     */
    @Min(value = 1, message = "单价不能小于1分")
    private Integer price;
    /**
     * 库存数量
     */
    @Max(value = 10000, message = "库存不能超过10000")
    private Integer stock;
    /**
     * 商品上架状态：0-下架，1-上架
     */
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}