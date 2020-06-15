package com.kkxu.demo.controller;

import com.kkxu.demo.service.ShoppingCartService;
import com.kkxu.demo.common.domain.Shopping_cartAKey;
import com.kkxu.demo.common.utils.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ShoppingCartController {

    @Autowired
    ShoppingCartService shoppingCartService;

    @RequestMapping("/shoppingcart")
    public String shoppingcart(HttpSession session,ModelMap modelMap){
        if(session.getAttribute("account_id") == null){
            return "cart-list-fail";
        }

        List<Shopping_cartAKey> list=shoppingCartService.SClist((String) session.getAttribute("account_id"));
        modelMap.addAttribute("clist",list);

        return "cart-list";
    }

    @RequestMapping("/deletecarts")
    public String deletecarts(HttpSession session, ModelMap modelMap, HttpServletRequest request){
        String[] strs = request.getParameterValues("goodsIds");
        int[] num= Tools.strArrayToIntArray(strs);
        shoppingCartService.deletecarts((String)session.getAttribute("account_id"),num);
        List<Shopping_cartAKey> list=shoppingCartService.SClist((String) session.getAttribute("account_id"));
        modelMap.addAttribute("clist",list);
        return "cart-list";
    }


}
