package com.kkxu.demo.common.domain;

import com.kkxu.demo.common.domain.Extend.Shopping_CartAExtend;

public class Shopping_cartAKey extends Shopping_CartAExtend {
    private Integer buyerId;

    private Integer goodsId;

    private Integer soldCount;

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getSoldCount() {
        return soldCount;
    }

    public void setSoldCount(Integer soldCount) {
        this.soldCount = soldCount;
    }
}