package com.kkxu.demo.controller;

import com.kkxu.demo.common.domain.Goods;
import com.kkxu.demo.common.domain.Seller;
import com.kkxu.demo.service.IGoodsService;
import com.kkxu.demo.service.ISellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;


@Controller
public class GoodsController {
    @Autowired
    private ISellerService iSellerService;

    @Autowired
    private IGoodsService iGoodsService;

    //1.卖家添加商品，商品中的sellerid应该由登陆的seller提供，用session保存。
    //参数列表{name=?? &info=?? &price=?? &soldcount=?? &restcount=??}
    @RequestMapping("/sellerinsertgoods")
    public String SellerInserGoods(HttpSession session, String name, String info, Double price, Integer soldcount, Integer restcount) {
        Seller seller = (Seller) session.getAttribute("seller");
        Integer MaxID = iGoodsService.selectGoodsMaxID();
        Goods goods = new Goods();
        goods.setId(MaxID + 1);
        goods.setSellerId(seller.getId());
        goods.setPrice(price);
        goods.setName(name);
        goods.setInfo(info);
        goods.setSoldCount(soldcount);
        goods.setRestCount(restcount);
        iGoodsService.insert(goods);
        return "goodslist";
    }

    //2.Goods信息更新,商家对自己店铺的商品进行修改
    //除了商品ID，商品所属的SellerID不可更改外，其余均可修改
    //其实商品的ID不允许修改，应该由点击某一个商品，该商品自然会将这个商品的ID传递到session中。
    //参数列表{name=?? &price=?? &info=?? &soldcount=?? &restcount=??}
    @RequestMapping("/sellerupdategoods")
    @ResponseBody
    public String SellerUpdateGoods(HttpSession session, String name, Double price, String info,Integer soldcount,Integer restcount) {
        //Goods good= (Goods) session.getAttribute("goods");
        //暂时没有合适的，我们设置一个session
        session.setAttribute("good",(Integer)13);
        Goods goods = new Goods();
        goods.setName(name);
        goods.setPrice(price);
        goods.setInfo(info);
        goods.setSoldCount(soldcount);
        goods.setRestCount(restcount);
        iGoodsService.goodsupdate(13,goods);
        return "success";
    }

    //3.根据id或者name删除goods数据
    @RequestMapping("/deletegoodsbyidorname")
    @ResponseBody
    public String DeleteGoodsByIdOrName(Integer id, String name) {
        if (id != null) {
            iGoodsService.deleteById(id);
        } else if (name != null) {
            iGoodsService.deleteByName(name);
        }
        return "success";
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
