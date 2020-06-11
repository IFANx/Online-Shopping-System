package com.kkxu.demo.controller;

import com.kkxu.demo.common.domain.Login;
import com.kkxu.demo.service.IBuyerService;
import com.kkxu.demo.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller


public class UserController {
//    @RequestMapping("/register")
//    public String Register(@RequestParam(name="uname") String name,
//    String password){
//    获取数据上面是方法一，下面是方法二
    @Autowired
    private IBuyerService iBuyerService;
//    @RequestMapping("/User/register")
//        public String Register(Buyer buyer, ModelMap modelMap){
////            System.out.println(buyer.getName()+buyer.getId()+buyer.getEmail());
////            modelMap.put("name1",buyer.getName());
////            modelMap.put("id1",buyer.getId());
//
//        for (int i=0;i<100;i++)
//        {
//            buyer.setId(i);
//            buyer.setName("scu"+i);
//            buyer.setAccountId("scu"+i);
//            buyer.setEmail(i+"@scu");
//            buyer.setSex(Math.random()>=0.5?true:false);
//            buyer.setPersonalsign("#scu"+i);
//            //BuyerExample buyerExample;
//            Integer flag=iBuyerService.insert(buyer);
//        }
//        return "ok";
//    }


}
