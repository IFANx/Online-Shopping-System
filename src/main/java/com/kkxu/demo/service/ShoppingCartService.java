package com.kkxu.demo.service;

import com.kkxu.demo.common.domain.Shopping_cartAKey;

import java.util.List;


public interface ShoppingCartService {

    List<Shopping_cartAKey> SClist(String account_id);
    int deletecarts(String acc, int[] num);

    void addToSC(String acc, int goodsId, int sold_count);
}
