package com.kkxu.demo.controller;

import com.kkxu.demo.common.domain.Goods;
import com.kkxu.demo.common.domain.Seller;
import com.kkxu.demo.service.IGoodsService;
import com.kkxu.demo.service.ISellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

public class StoreController {
    @Autowired
    private IGoodsService iGoodsService;

    @Autowired
    private ISellerService iSellerService;


    //1.卖家添加商品，商品中的sellerid应该由登陆的seller提供，用session保存。
    //参数列表{name=?? &info=?? &price=?? &sold_count=?? &rest_count=??}
    @RequestMapping("/sellerinsertgoods")
    public String SellerInserGoods(HttpSession session, String name, String info, Double price, Integer sold_count, Integer rest_count) {
        Seller seller = (Seller) session.getAttribute("seller");
        Integer MaxID = iGoodsService.selectGoodsMaxID();
        Goods goods = new Goods();
        goods.setId(MaxID + 1);
        goods.setSellerId(seller.getId());
        goods.setPrice(price);
        goods.setName(name);
        goods.setInfo(info);
        goods.setSoldCount(sold_count);
        goods.setRestCount(rest_count);
        iGoodsService.insert(goods);
        return "goodslist";
    }

    //2.Goods信息更新,商家对自己店铺的商品进行修改
    //除了商品ID，商品所属的SellerID不可更改外，其余均可修改
    //其实商品的ID不允许修改，应该由点击某一个商品，该商品自然会将这个商品的ID传递到session中。
    //参数列表{name=?? &price=?? &info=?? &soldcount=?? &restcount=??}
    @RequestMapping("/sellerupdategoods")
    @ResponseBody
    public String SellerUpdateGoods(HttpSession session, String name, Double price, String info, Integer soldcount, Integer restcount) {
        //暂时没有合适的，我们设置一个session
        session.setAttribute("good", (Integer) 13);
        Goods goods = new Goods();
        goods.setName(name);
        goods.setPrice(price);
        goods.setInfo(info);
        goods.setSoldCount(soldcount);
        goods.setRestCount(restcount);
        iGoodsService.goodsupdate(13, goods);
        return "success";
    }

    //3.根据id或者name删除goods数据
    @RequestMapping("/sellerdeletegoods")
    @ResponseBody
    public String DeleteGoodsByIdOrName(Integer goodsid) {
        if (goodsid != null) {
            iGoodsService.deleteById(goodsid);
        }
//        } else if (name != null) {
//            iGoodsService.deleteByName(name);
//        }
        return "移除成功";
    }


    //    ====================================
    //方便跳转
    @RequestMapping("/addgoods")
    public String tiaozhuan1() {
        return "insertOrupdategoods";
    }



    @RequestMapping("/detail")
    public String detail(@RequestParam(value="id") Integer id, ModelMap modelMap){
        Goods goods = iGoodsService.selectById(id);
        modelMap.addAttribute("goods",goods);
        return "goodslist";
    }
}
