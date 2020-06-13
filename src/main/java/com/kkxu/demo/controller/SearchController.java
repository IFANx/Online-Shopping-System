package com.kkxu.demo.controller;

import com.kkxu.demo.common.domain.Goods;
import com.kkxu.demo.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SearchController {
    @Autowired
    private IGoodsService iGoodsService;

    @RequestMapping("/GoodsInsert")
    @ResponseBody
    //卖家添加商品，商品中的sellerid应该由登陆的seller提供，用session保存。
    public String GoodsOperate(HttpSession session,Goods goods) {
        for (int i = 0; i < 100; i++) {
            goods.setId(i);
            goods.setName("goods" + i);
            goods.setPrice(Math.random() * 100);
            goods.setInfo("good" + i + "info");
            goods.setRestCount((int) (Math.random() * 1000));
            goods.setSoldCount((int) (Math.random() * 1000));
            goods.setSellerId(i);
            iGoodsService.insert(goods);
        }
        return "ok";

//        这里是想添加一个商品，但商品的ID应不同

//        Goods goods1 = iGoodsService.selectById(id);
//        Goods goodsnew = new Goods();
//        Integer num = iGoodsService.selectAll().size();
//        if (goods1 != null) {
//            return "此goodsid已有，请换一个";
//        } else {
//              goodsnew.setId(num+1);
//              goodsnew.setInfo("dd");
//              iGoodsService.insert(goodsnew);
//
//            return "success";
//        }

    }

    //商品列表
    @RequestMapping("/GoodsList")
    @ResponseBody
    public List<Goods> GoodsList() {
        List<Goods> goods = iGoodsService.listAll();
        return goods;
    }

    //根据名称模糊搜索商品
    @RequestMapping("/GoodsSearch")
    @ResponseBody
    public List<Goods> GoodsSearch(String name) {
        List<Goods> goods = iGoodsService.goodsSearch(name);
        return goods;
    }


    //根据名称和价格搜索商品
    @RequestMapping("/GoodsSearchByName_Price")
    @ResponseBody
    public List<Goods> GoodsSearchByName_Price(String name, Double price) {
        List<Goods> goods = iGoodsService.goodsSearchByName_Price(name, price);
        int size = goods.size();
        Goods temp = new Goods();
        for (int i = 1; i < size; i++) {
            for (int j = i; j < size - 1; j++) {
                if (goods.get(j).getSoldCount() < goods.get(j + 1).getSoldCount())
                    temp = goods.get(j);
                goods.set(j, goods.get(j + 1));
                goods.set(j + 1, temp);
            }
        }
        return goods;
    }


    //Goods信息更新
    @RequestMapping("/GoodsUpdate")
    @ResponseBody
    public String GoodsUpdate(Integer id, String name, Double price, String info) {
        Goods goods = new Goods();
        goods.setId(id);
        goods.setName(name);
        goods.setPrice(price);
        goods.setInfo(info);
        iGoodsService.goodsupdate(id, goods);
        return "success";
    }

    //根据id或者name删除goods数据
    @RequestMapping("/deletegoods")
    @ResponseBody
    public String DeleteGoodsById(Integer id, String name) {
        Integer flag = null;
        if (id != null) {
            flag= iGoodsService.deleteById(id);
        }
        else if (name != null) {
            flag = iGoodsService.deleteByName(name);
        }
        return "success";
    }


}
