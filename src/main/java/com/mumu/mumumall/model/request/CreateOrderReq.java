package com.mumu.mumumall.model.request;

import javax.validation.constraints.NotNull;

/**
 * 订单表;
 */
public class CreateOrderReq {
    /**
     * 收货人姓名快照
     */
    @NotNull
    private String receiverName;

    /**
     * 收货人手机号快照
     */
    @NotNull
    private String receiverMobile;

    /**
     * 收货地址快照
     */
    @NotNull
    private String receiverAddress;

    /**
     * 运费，默认为0
     */
    private Integer postage;

    /**
     * 支付类型,1-在线支付
     */
    private Integer paymentType;

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public Integer getPostage() {
        return postage;
    }

    public void setPostage(Integer postage) {
        this.postage = postage;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }
}