package com.kkxu.demo.service;

import com.kkxu.demo.common.domain.Buyer;
import com.kkxu.demo.common.domain.BuyerExample;
import com.kkxu.demo.common.domain.Login;
import com.kkxu.demo.mapper.BuyerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BuyerServiceImpl implements IBuyerService {
    @Autowired
    private BuyerMapper buyerMapper;
    @Override
    public Integer insert(Buyer buyer){
        BuyerExample buyerExample =new BuyerExample();
        int insert = buyerMapper.insert(buyer);
        return insert;
    }
}
