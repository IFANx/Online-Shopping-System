package com.kkxu.demo.service;

import com.kkxu.demo.common.domain.Goods;
import com.kkxu.demo.common.domain.GoodsExample;
import com.kkxu.demo.common.domain.Seller;
import com.kkxu.demo.mapper.GoodsMapper;
import com.kkxu.demo.mapper.SellerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerServiceImpl implements ISellerService {
    @Autowired
    private SellerMapper sellerMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public Integer insert(Seller seller) {
        int insert = sellerMapper.insert(seller);
        return insert;
    }

    @Override
    public List<Goods> selectbysellerid(Integer sellerid) {
        GoodsExample goodsExample=new GoodsExample();
        goodsExample.createCriteria().andSellerIdEqualTo(sellerid);
        List<Goods> goods = goodsMapper.selectByExample(goodsExample);
        return goods;
    }
}