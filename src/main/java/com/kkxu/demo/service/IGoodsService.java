package com.kkxu.demo.service;

import com.kkxu.demo.common.domain.Goods;

import java.util.List;

public interface IGoodsService {
    Integer insert(Goods goods);

    List<Goods> listAll();

    List<Goods> goodsSearch(String keyword);

    List<Goods> goodsSearchByName_Price(String name, Double lowerprice, Double higherprice);

    Integer goodsupdate(Integer id, Goods goods);

    int deleteById(Integer id);

    Integer deleteByName(String name);

    Goods selectById(int id);

    List<Goods> selectBySellerId(Integer id);

    Integer selectGoodsMaxID();
}
