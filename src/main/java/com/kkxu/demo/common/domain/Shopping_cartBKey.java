package com.kkxu.demo.common.domain;

import com.kkxu.demo.common.domain.Extend.Shopping_CartAExtend;

public class Shopping_cartBKey extends Shopping_CartAExtend {
    private Integer sellerId;

    private Integer goodsId;

    private Integer soldCount;

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
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