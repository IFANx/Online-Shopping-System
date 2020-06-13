package com.kkxu.demo.controller;

import com.kkxu.demo.common.domain.Goods;
import com.kkxu.demo.common.domain.Seller;
import com.kkxu.demo.service.ISellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class GoodsController {
    @Autowired
    private ISellerService iSellerService;
    
    @RequestMapping("/SellerInsert")
    @ResponseBody
    //插入数据到seller表
    public String SellerInsert(Seller seller) {
        for (int i = 0; i < 10; i++) {
            seller.setAccountId("scu"+i);
            seller.setId(i);
            seller.setSellerName("scu"+i);
            seller.setEmail(i+"@scu");
            seller.setSex(Math.random()>=0.5?true:false);
            seller.setStoreName("scustore"+i);
            seller.setStoreInfo("storeinfu"+i);
            Integer insert = iSellerService.insert(seller);
        }
        return "ok";
    }

    @RequestMapping("/Seller_Goodslist")
    @ResponseBody
    //根据sellerid查询该seller的所有商品
    public List<Goods> Seller_Goodslist(Integer sellerid){
            List<Goods> goods = iSellerService.selectbysellerid(sellerid);
            return goods;
    }
}
