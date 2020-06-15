package com.kkxu.demo.controller;

import com.kkxu.demo.common.domain.Goods;
import com.kkxu.demo.common.domain.Login;
import com.kkxu.demo.common.domain.Seller;
import com.kkxu.demo.common.domain.Shopping_cartAKey;
import com.kkxu.demo.service.IGoodsService;
import com.kkxu.demo.service.ISellerService;
import com.kkxu.demo.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class GoodsController {
    @Autowired
    private ISellerService iSellerService;

    @Autowired
    private IGoodsService iGoodsService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    //2.根据名称模糊搜索商品  参数列表{}
    @RequestMapping("/goodsdetail")
    public String GoodsSearch(Integer id, ModelMap modelMap,HttpSession session) {
        Goods goods=iGoodsService.selectById(id);
        Seller seller=iSellerService.selectsellerbysellerid(id);
        modelMap.addAttribute("goods", goods);
        modelMap.addAttribute("seller",seller);
        modelMap.addAttribute("buyerid",session.getAttribute("account_id"));
        modelMap.addAttribute("isseller",session.getAttribute("isseller"));
        //自动判断进入的页面，根据是否是seller判断
        if((session.getAttribute("isseller").equals(0)))
            //buyer进入商品购买页面
        return "goodsdetail";
        //Seller进入商品详情以及修改界面
        return  "goodsdetailseller";
    }

    @RequestMapping("/addToSC")
    public String addToSC(HttpSession session, ModelMap modelMap,int goodsId, int sold_count){
        //System.out.println(session.getAttribute("account_id"));
        shoppingCartService.addToSC((String)session.getAttribute("account_id"),goodsId,sold_count);
        List<Shopping_cartAKey> list=shoppingCartService.SClist((String) session.getAttribute("account_id"));
        modelMap.addAttribute("clist",list);
        return "cart-list";
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
