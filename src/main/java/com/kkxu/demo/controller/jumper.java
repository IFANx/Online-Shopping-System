package com.kkxu.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class jumper {
    @RequestMapping("/toupdatebuyer")
    public  String toupdatebuyer(){
        return "updatebuyer";
    }
    @RequestMapping("/toupdateseller")
    public  String toupdateseller(){
        return "updateseller";
    }
    @RequestMapping("/tosellerinsertgoods")
    public  String tosellerinsertgoods(){
        return "selleraddgoods";
    }
    @RequestMapping("/tosellerupdategoods")
    public  String tosellerupdategoods(Integer sellerid, Integer goodsid,ModelMap modelMap){
        modelMap.addAttribute("sellerid",sellerid);
        modelMap.addAttribute("goodsid",goodsid);
        return "sellerupdategoods";
    }
    @RequestMapping("/tobutton")
    public  String tobutton(){
        return "button";
    }

}
