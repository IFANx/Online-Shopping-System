package com.kkxu.demo.service;

import com.kkxu.demo.common.domain.Goods;
import com.kkxu.demo.common.domain.Seller;

import java.util.List;


public interface ISellerService {
    Integer insert(Seller seller);

    List<Goods> selectbysellerid(Integer sellerid);

    List<Seller> selectbystorename(String storename);
}
