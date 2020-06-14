package com.kkxu.demo.controller;

import com.kkxu.demo.common.domain.Goods;
import com.kkxu.demo.common.domain.Seller;
import com.kkxu.demo.service.IGoodsService;
import com.kkxu.demo.service.ISellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
import java.util.List;

import static org.apache.tomcat.jni.Time.sleep;


@Controller
public class GoodsController {
    @Autowired
    private ISellerService iSellerService;

    @Autowired
    private IGoodsService iGoodsService;

    //2.根据名称模糊搜索商品  参数列表{}
    @RequestMapping("/goodsdetail")
    public String GoodsSearch(Integer id, ModelMap modelMap) {
        Goods goods=iGoodsService.selectById(id);
        Seller seller=iSellerService.selectsellerbysellerid(id);
        modelMap.addAttribute("goods", goods);
        modelMap.addAttribute("seller",seller);
        return "goodsdetail";
    }



//    @RequestMapping("/Seller_Goodslist")
//    //根据sellerid查询该seller的所有商品
//    public List<Goods> Seller_Goodslist(Integer sellerid){
//            List<Goods> goods = iSellerService.selectbysellerid(sellerid);
//            return goods;
//    }


    //    @RequestMapping("/SellerInsert")
//    @ResponseBody
//    //1.插入数据到seller表
//    public String SellerInsert(Seller seller) {
//        for (int i = 0; i < 10; i++) {
//            seller.setAccountId("scu"+i);
//            seller.setId(i);
//            seller.setSellerName("scu"+i);
//            seller.setEmail(i+"@scu");
//            seller.setSex(Math.random()>=0.5?true:false);
//            seller.setStoreName("scustore"+i);
//            seller.setStoreInfo("storeinfu"+i);
//            Integer insert = iSellerService.insert(seller);
//        }
//        return "ok";
//    }
}
