package com.kkxu.demo.controller;

import com.kkxu.demo.common.domain.Goods;
import com.kkxu.demo.common.domain.Seller;
import com.kkxu.demo.service.IGoodsService;
import com.kkxu.demo.service.ISellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

@Controller
public class SearchController {
    @Autowired
    private IGoodsService iGoodsService;

    @Autowired
    private ISellerService iSellerService;

    //1.商品列表,不需要输入任何信息，直接显示所有商品  参数列表{无参数}
    @RequestMapping("/goodslist")
    public String GoodsList(ModelMap modelMap) {
        List<Goods> goods = iGoodsService.listAll();
        modelMap.addAttribute("goods", goods);
        return "goodslist";
    }


    //2.根据名称模糊搜索商品  参数列表{name=??}
    @RequestMapping("/searchgoodsbyname")
    public String GoodsSearch(String name, ModelMap modelMap) {
        if (name.isEmpty()) {
            modelMap.addAttribute("searcherror1", "没有输入关键字");
            return "button";
        }
            List<Goods> goods = iGoodsService.goodsSearch(name);
            Collections.sort(goods, Goods::compareTo);
//        List<Integer> goodsid = null;
//        for (int i = 0; i <goods.size() ; i++) {
//            goodsid.add(i,goods.get(i).getId());
//        }
//        List<Integer> sellersid=null;
//        List<String> sellername=null;
//        for (int i = 0; i <goodsid.size(); i++) {
//            sellersid.add(i,iGoodsService.selectById(goodsid.get(i)).getSellerId());
//            sellername.add(i,iSellerService.selectbysellerid(sellersid.get(i)).get(i).getName());
//        }
            modelMap.addAttribute("goods", goods);
            return "goodslist";
        }



    //3.根据名称和价格搜索商品  参数列表{name=?? &price=??}
    @RequestMapping("/searchgoodsbyname_price")
    public String GoodsSearchByName_Price(String name, Double lowerprice,Double higherprice, ModelMap modelMap) {
        if (name.isEmpty()) {
            modelMap.addAttribute("error", "未输入查询关键字");
            return "button";
        } else if(lowerprice!=null&&higherprice!=null&&lowerprice>higherprice){
            modelMap.addAttribute("error", "价格下限不应大于价格上限");
            return "button";
        }
        else {
            if(lowerprice==null){lowerprice=(double)0;}
            if(higherprice==null){higherprice=(double)1000000;}//设定一个上限
        List<Goods> goods = iGoodsService.goodsSearchByName_Price(name, lowerprice, higherprice);
        Collections.sort(goods, Goods::compareTo);
        modelMap.addAttribute("goods", goods);
        return "goodslist";
        }
    }


    //4.根据名称搜索商铺  参数列表{name=??}
    @RequestMapping("/searchstorebyname")
    public String SearchStoreSyName(String storename, ModelMap modelMap,HttpSession session) {
        if (storename.isEmpty()) {
            modelMap.addAttribute("searcherror2", "没有输入关键字");
            return "button";
        }
        List<Seller> sellers = iSellerService.selectbystorename(storename);
        modelMap.addAttribute("sellers", sellers);
        session.setAttribute("sellers",sellers);
        return "storeslist";
    }

    //5.商家查看自己商铺已有的商品或者买家选择一个商铺后，买家查看该商家商铺的所有商品
    @RequestMapping("/store_goodslist")
    public String StoreGoodsList(HttpSession session,ModelMap modelMap,Integer id){
        Seller seller=null;
        List<Goods> goods=null;
        if(id==null) {
             seller = (Seller) session.getAttribute("seller");
            goods=iGoodsService.selectBySellerId(seller.getId());
        }
        else {
            goods = iGoodsService.selectBySellerId(id);
        }
        modelMap.addAttribute("goods",goods);
        return  "goodslist_seller";
    }
    }







