package com.kkxu.demo.service;

import com.kkxu.demo.common.domain.*;
import com.kkxu.demo.mapper.*;
import com.kkxu.demo.mapper.LoginMapper;
import com.kkxu.demo.mapper.Shopping_cartAMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImp implements ShoppingCartService {
    @Autowired
    LoginMapper loginMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    Shopping_cartAMapper shopping_cartAMapper;

    @Autowired
    Shopping_cartBMapper shopping_cartBMapper;

    @Override
    public List<Shopping_cartAKey> SClist(String account_id) {
        LoginExample loginExample =new LoginExample();
        loginExample.createCriteria().andAccountIdEqualTo(account_id);
        List<Login> logins=loginMapper.selectByExample(loginExample);
        Login login=logins.get(0);
        if(login.getIsseller().equals(0)){
            List<Shopping_cartAKey> list=shopping_cartAMapper.selectMe(login.getId());
            return list;
        }
        List<Shopping_cartAKey> list1=shopping_cartAMapper.selectMe1(login.getSellerId());
        return list1;
    }

    public int deletecarts(String acc,int[] num) {
        LoginExample loginExample = new LoginExample();
        loginExample.createCriteria().andAccountIdEqualTo(acc);
        List<Login> logins = loginMapper.selectByExample(loginExample);
        Login login = logins.get(0);


        List<Integer> numl = Arrays.stream(num).boxed().collect(Collectors.toList());
        if (login.getIsseller()==0) {
            Shopping_cartAExample shopping_cartAExample = new Shopping_cartAExample();
            shopping_cartAExample.createCriteria().andBuyerIdEqualTo(login.getId()).andGoodsIdIn(numl);
            return shopping_cartAMapper.deleteByExample(shopping_cartAExample);
        }
        Shopping_cartBExample shopping_cartBExample = new Shopping_cartBExample();
        shopping_cartBExample.createCriteria().andSellerIdEqualTo(login.getSellerId()).andGoodsIdIn(numl);
        return shopping_cartBMapper.deleteByExample(shopping_cartBExample);
    }

    public void addToSC(String acc, int goodsId, int sold_count){
        LoginExample loginExample =new LoginExample();
        loginExample.createCriteria().andAccountIdEqualTo(acc);
        List<Login> logins=loginMapper.selectByExample(loginExample);
        Login login=logins.get(0);
        List<Shopping_cartAKey> rd =SClist(acc);
        boolean exsit=false;
        int lastSoldCount=0;
        for(Shopping_cartAKey ex:rd){
            if(ex.getGoodsId()==goodsId){
                lastSoldCount += ex.getSoldCount();
                exsit=true;
            }
        }

        if(login.getIsseller().equals(0)){
            if(!exsit){
            Shopping_cartAKey record=new Shopping_cartAKey();
            record.setBuyerId(login.getId());record.setGoodsId(goodsId);record.setSoldCount(sold_count);
            shopping_cartAMapper.insert(record);
            return;
            }
            else {
                Shopping_cartAExample shopping_cartAExample=new Shopping_cartAExample();
                shopping_cartAExample.createCriteria().andBuyerIdEqualTo(login.getId()).andGoodsIdEqualTo(goodsId);
                Shopping_cartAKey record=new Shopping_cartAKey();
                record.setBuyerId(login.getId());record.setGoodsId(goodsId);record.setSoldCount(sold_count+lastSoldCount);
                shopping_cartAMapper.updateByExample(record,shopping_cartAExample);
                return;
            }

        }
        else{
            if(!exsit){
                Shopping_cartBKey record=new Shopping_cartBKey();
                record.setSellerId(login.getSellerId());record.setGoodsId(goodsId);record.setSoldCount(sold_count);
                shopping_cartBMapper.insert(record);
                return;
            }
            else {
                Shopping_cartBExample shopping_cartBExample=new Shopping_cartBExample();
                shopping_cartBExample.createCriteria().andSellerIdEqualTo(login.getSellerId()).andGoodsIdEqualTo(goodsId);
                Shopping_cartBKey record=new Shopping_cartBKey();
                record.setSellerId(login.getSellerId());record.setGoodsId(goodsId);record.setSoldCount(sold_count+lastSoldCount);
                shopping_cartBMapper.updateByExample(record,shopping_cartBExample);
                return;
            }
        }

    }
}
