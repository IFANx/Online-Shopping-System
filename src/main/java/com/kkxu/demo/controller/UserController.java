package com.kkxu.demo.controller;

import com.kkxu.demo.common.domain.Buyer;
import com.kkxu.demo.common.domain.Login;
import com.kkxu.demo.common.domain.Seller;
import com.kkxu.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private IUserService iUserService;

    //1.login表注册 需要参数  ?account_id=kk1&password=ll&pwd=ll&isseller=0
    @RequestMapping("/register")
    @ResponseBody
    public String Register(String account_id, String password, String pwd, Integer isseller) {
        Login login = iUserService.selectloginById(account_id);
        Login loginnew = new Login();
        Buyer buyer = new Buyer();
        Seller seller = new Seller();
        Integer MaxID = iUserService.selectLoginMaxID();
        if (login != null) {
            return "此账号已存在，请换一个名称";
        } else if (password.equals(pwd)) {

            //向login表中添加此人信息
            loginnew.setAccountId(account_id);//accountid更像是自己的游戏昵称，自定义，但唯一
            loginnew.setPassword(password);
            loginnew.setIsseller(isseller);//赋初值为0，代表注册的用户开始都只是buyer
            loginnew.setId(MaxID + 1);//ID不允许修改，系统赋予的一个类似于唯一标识
            if (isseller == 1) {
                loginnew.setSellerId(MaxID + 1);
            }
            iUserService.insertintologin(loginnew);

            //根据传入的isseller判断该login是否为seller
            //向buyer表中添加此人信息,因为卖家也可以买东西，所以任何login都会在buyer中注册，但不一定会在sellser中注册
            buyer.setId(MaxID + 1);
            buyer.setAccountId(account_id);
            buyer.setSex(false);//因为下面三项sex，name，email均设置不为空，所以我们暂时统一初值，可在个人页面修改
            buyer.setName("buyername");
            buyer.setEmail("buyer@scu");
            buyer.setPersonalsign("personalsign");
            iUserService.insertintobuyer(buyer);


//            向seller表中添加此人信息
            if (isseller == 1) {
                seller.setId(MaxID+1);
                seller.setAccountId(account_id);
                seller.setSex(true);//因为下面三项sex，name，email均设置不为空，所以我们暂时统一初值，可在个人页面修改
                seller.setStoreName("storename");
                seller.setEmail("seller@scu");
                seller.setStoreInfo("storeinfo");
                seller.setSellerName("sellername");
                iUserService.insertintoseller(seller);
            }
            return "Register Success";
        } else {
            return "两次输入的密码不一致，请修改";
        }
    }


    //2.login登录,需要参数  ?account=scu44&pwd=scu44
    @RequestMapping("/login")
    public String Login(String account, String pwd, HttpSession session, ModelMap modelMap) {
        Login login = iUserService.selectloginById(account);
        if (login == null) {
            modelMap.put("false1", "账号不存在，失败");
            return "false.html";
        } else if (login!=null && pwd.equals(login.getPassword())) {
            session.setAttribute("account_id", account);
            modelMap.put("success", "登陆成功");
            //查询到此用户是buyer
           Buyer buyer = null;
           Seller seller=null;
            if (login.getIsseller() == 0) {
                buyer = iUserService.selectbuyerById(account).get(0);
                //将buyer相关的信息存入session，我们可以在其他页面调用
                session.setAttribute("buyer",buyer);
                session.setAttribute("name",buyer.getName());
                session.setAttribute("email",buyer.getEmail());
                session.setAttribute("sex",buyer.getSex());
                session.setAttribute("id",buyer.getId());
                session.setAttribute("bpersonalsign",buyer.getPersonalsign());
            }
            else{
                //将seller相关的信息存入session，我们可以在其他页面调用
                seller =iUserService.selectsellerById(account).get(0);
                session.setAttribute("seller",seller);
                session.setAttribute("name",seller.getSellerName());
                session.setAttribute("email",seller.getEmail());
                session.setAttribute("sex",seller.getSex());
                session.setAttribute("id",seller.getId());
                session.setAttribute("storename",seller.getStoreName());
                session.setAttribute("storeinfo",seller.getStoreInfo());
            }

            modelMap.put("login", login);
            if(buyer!=null) {
                modelMap.put("buyer", buyer);
            }
            if(seller!=null) {
                modelMap.put("seller", seller);
            }
            return "logindetail";

        } else
            modelMap.put("false1", "密码错误,登录失败");
        return "false.html";
    }

    //3.User信息更新,包括更新login表和相应的buyer表，根据account_id修改其他信息，account_id,id，sellerid应不允许改变
    @RequestMapping("/updateBuyer")
    @ResponseBody
    public String UpdateBuyer(HttpSession session, String name,String password,Boolean sex,String email,String personalsign) {
        Login login = new Login();
        Buyer buyer=new Buyer();
        //更改后的信息加入buyer对象中
        login.setPassword(password);
        buyer.setName(name);buyer.setSex(sex);buyer.setEmail(email);buyer.setPersonalsign(personalsign);
        //根据session保留的accountid对login进行修改
        iUserService.UpdateLogin((String) session.getAttribute("account_id"), login);
        //相应对Buyer表进行修改
        iUserService.UpdateBuyer((String)session.getAttribute("account_id"),buyer);
        return "success";
    }

    //4.User信息更新,包括更新login表和相应的seller表，根据account_id修改其他信息，account_id,id，sellerid应不允许改变
    @RequestMapping("/updateSeller")
    @ResponseBody
    public String UpdateSeller(HttpSession session, String sellername,String password,boolean sex,String email,String storename,String storeinfo) {
        Login login = new Login();
        Seller seller=new Seller();
        //更改后的信息加入seller对象中
        login.setPassword(password);
        seller.setSellerName(sellername);seller.setSex(sex);seller.setEmail(email);seller.setStoreName(storename);seller.setStoreInfo(storeinfo);
        iUserService.UpdateLogin((String) session.getAttribute("account_id"), login);
        iUserService.UpdateSeller((String)session.getAttribute("account_id"),seller);
        return "success";
    }

    //3.显示已注册login账号列表 --意义不大，因为没有管理员  参数列表  ?id1=4&id2=20
    @RequestMapping("/loginlist")
    @ResponseBody
    public List<Login> loginlist(Integer id1, Integer id2) {
        List<Login> list = iUserService.list(id1, id2);
        return list;
    }







    // 以下为用处不大的功能


    //4.插入数据到login表 --用于循环生成较多数据，无实际意义

    @RequestMapping("/LoginInsert")
    @ResponseBody
    public String Register2(Login login) {
        for (int i = 0; i < 100; i++) {
            login.setId(i);
            login.setAccountId("scu" + i);
            login.setIsseller(Math.random() >= 0.5 ? 0 : 1);
            login.setPassword("scu" + i);
            login.setSellerId(i);
            Integer flag = iUserService.insertintologin(login);
        }
        return "ok";
    }


    //5.login详情(根据login表中对应的account_id，查询该login详细数据)
    @RequestMapping("/Userdetails")
    public String Userdetail(String account_id, ModelMap modelMap, HttpSession session) {
        Login login = iUserService.selectloginById((account_id));
        modelMap.put("login", login);
        return "logindetail";
    }




    //6.根据id删除login数据
    @RequestMapping("/deleteById")
    @ResponseBody
    public String deleteloginById(String account_id) {
        String flag = iUserService.deleteById(account_id);
        return flag;
    }


    //根据ids删除login数据
    @RequestMapping("/deleteByIds")
    @ResponseBody
    public String deleteloginByIds(List<Integer> ids) {
        String flag = iUserService.deleteByIds(ids);
        return flag;
    }


}
