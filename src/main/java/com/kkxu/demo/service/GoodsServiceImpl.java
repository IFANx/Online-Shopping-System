package com.kkxu.demo.service;

import com.kkxu.demo.common.domain.Goods;
import com.kkxu.demo.common.domain.GoodsExample;
import com.kkxu.demo.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements IGoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public Integer insert(Goods goods) {
        int flag = goodsMapper.insert(goods);
        return flag;
    }

    @Override
    public List<Goods> listAll() {
        GoodsExample goodsExample=new GoodsExample();
        goodsExample.createCriteria().andIdIsNotNull();
        List<Goods> goods = goodsMapper.selectByExample(goodsExample);
        return goods;
    }

    @Override
    public List<Goods> goodsSearch(String keyword) {
        GoodsExample goodsExample=new GoodsExample();
        goodsExample.createCriteria().andNameLike("%"+keyword+"%");
        List<Goods> goods = goodsMapper.selectByExample(goodsExample);
        return goods;
    }

    @Override
    public List<Goods> goodsSearchByName_Price(String name, Double price) {
        GoodsExample goodsExample=new GoodsExample();
        goodsExample.createCriteria().andNameLike("%"+name+"%").andPriceLessThanOrEqualTo(price);
        List<Goods> goods = goodsMapper.selectByExample(goodsExample);
        return goods;
    }

    @Override
    public Integer goodsupdate(Integer id, Goods goods) {
        Integer s = goodsMapper.updateById(id, goods);
        return s;
    }

    @Override
    public int deleteById(Integer id) {
        GoodsExample goodsExample=new GoodsExample();
        goodsExample.createCriteria().andIdEqualTo(id);
        int i = goodsMapper.deleteByExample(goodsExample);
        return i;
    }

    @Override
    public Integer deleteByName(String name) {
        GoodsExample goodsExample=new GoodsExample();
        goodsExample.createCriteria().andNameEqualTo(name);
        int i = goodsMapper.deleteByExample(goodsExample);
        return i;
    }

    @Override
    public Goods selectById(int id) {
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        return goods;
    }

    @Override
    public List<Goods> selectBySellerId(Integer id) {
        GoodsExample goodsExample=new GoodsExample();
        goodsExample.createCriteria().andSellerIdEqualTo(id);
        List<Goods> goods = goodsMapper.selectByExample(goodsExample);
        return goods;
    }

    @Override
    public Integer selectGoodsMaxID() {
        Integer maxID = goodsMapper.selectMaxID();
        return maxID;
    }
}