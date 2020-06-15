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

    //1.login表注册 需要参数  参数列表{account_id=?? &email=??&password=?? &pwd=?? &isseller=?}
    @RequestMapping("/register")
    public String Register(ModelMap modelMap,String account_id, String email,String password, String pwd, Integer isseller) {
        if(account_id.isEmpty()||email.isEmpty()||password.isEmpty()||pwd.isEmpty()||isseller.equals(null))
        {
            modelMap.addAttribute("RegisterFailMsg","任何输入的数据均不能为空，请重新填写");
            return "register";
        }
        else {
            Login login = iUserService.selectloginById(account_id);
            Login loginnew = new Login();
            Buyer buyer = new Buyer();
            Seller seller = new Seller();
            Integer MaxID = iUserService.selectLoginMaxID();
            if (login != null) {
                modelMap.addAttribute("RegisterFailMsg","此账号已存在，请换一个名称");
                return "register";
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
                buyer.setEmail(email);
                buyer.setPersonalsign("personalsign");
                iUserService.insertintobuyer(buyer);


//            向seller表中添加此人信息
                if (isseller == 1) {
                    seller.setId(MaxID + 1);
                    seller.setAccountId(account_id);
                    seller.setSex(true);//因为下面三项sex，name，email均设置不为空，所以我们暂时统一初值，可在个人页面修改
                    seller.setStoreName("storename");
                    seller.setEmail(email);
                    seller.setStoreInfo("storeinfo");
                    seller.setSellerName("sellername");
                    iUserService.insertintoseller(seller);
                }
                return "login";
            } else {
                modelMap.addAttribute("RegisterFailMsg","两次输入的密码不一致，请修改");
                return "register";
            }
        }
    }


    //2.login登录,需要参数  参数列表{account=?? &pwd=??}
    @RequestMapping("/login")
    public String Login(String account, String pwd, HttpSession session, ModelMap modelMap) {
        if(account.isEmpty()||pwd.isEmpty()){
            modelMap.addAttribute("LoginFailMsg","任何输入不能为空，请重新填写！");
            return "login";
        }
        else {
            Login login = iUserService.selectloginById(account);
            if (login == null) {
                modelMap.put("LoginFailMsg", "账号不存在，登录失败");
                return "login";
            } else if (login != null && pwd.equals(login.getPassword())) {
                session.setAttribute("account_id", account);
                modelMap.put("success", "登陆成功");
                session.setAttribute("login",login);
                //查询到此用户是buyer
                Buyer buyer = null;
                Seller seller = null;
                if (login.getIsseller() == 0) {
                    buyer = iUserService.selectbuyerById(account).get(0);
                    //将buyer相关的信息存入session，我们可以在其他页面调用
                    session.setAttribute("buyer", buyer);
                    session.setAttribute("name", buyer.getName());
                    session.setAttribute("email", buyer.getEmail());
                    session.setAttribute("sex", buyer.getSex());
                    session.setAttribute("id", buyer.getId());
                    session.setAttribute("bpersonalsign", buyer.getPersonalsign());
                } else {
                    //将seller相关的信息存入session，我们可以在其他页面调用
                    seller = iUserService.selectsellerById(account).get(0);
                    session.setAttribute("seller", seller);
                    session.setAttribute("name", seller.getSellerName());
                    session.setAttribute("email", seller.getEmail());
                    session.setAttribute("sex", seller.getSex());
                    session.setAttribute("id", seller.getId());
                    session.setAttribute("storename", seller.getStoreName());
                    session.setAttribute("storeinfo", seller.getStoreInfo());
                }
                session.setAttribute("isseller", login.getIsseller());
                modelMap.put("login", login);
                if (buyer != null) {
                    modelMap.put("buyer", buyer);
                }
                if (seller != null) {
                    modelMap.put("seller", seller);
                }
                return "userdetails";
            } else
                modelMap.put("LoginFailMsg", "密码错误,登录失败");
            return "login";
        }
    }

    //3.User信息更新,包括更新login表和相应的buyer表，根据account_id修改其他信息，
    // account_id,id，sellerid应不允许改变
    //参数列表{name=?? &password=?? &sex=? &email=?? &personalsign=??}
    @RequestMapping("/updateBuyer")
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
        return "button";
    }

    //4.User信息更新,包括更新login表和相应的seller表，根据account_id修改其他信息，
    // account_id,id，sellerid应不允许改变
    //参数列表{sellername=?? &password=?? &sex=?? &email=?? &storename=?? &storeinfo=??}
    @RequestMapping("/updateSeller")
    public String UpdateSeller(HttpSession session, String sellername,String password,boolean sex,String email,String storename,String storeinfo) {
        Login login = new Login();
        Seller seller=new Seller();
        //更改后的信息加入seller对象中
        login.setPassword(password);
        seller.setSellerName(sellername);seller.setSex(sex);seller.setEmail(email);seller.setStoreName(storename);seller.setStoreInfo(storeinfo);
        iUserService.UpdateLogin((String) session.getAttribute("account_id"), login);
        iUserService.UpdateSeller((String)session.getAttribute("account_id"),seller);
        return "button";
    }

    //5.显示已注册login账号列表 --意义不大，因为没有管理员  参数列表  ?id1=4&id2=20
    @RequestMapping("/loginlist")
    @ResponseBody
    public List<Login> loginlist(Integer id1, Integer id2) {
        List<Login> list = iUserService.list(id1, id2);
        return list;
    }

    //6.根据id删除login数据,更像是删除用户。不能删除seller，在某些情况下buyer，seller数据库还未删，当login中对应的password和acc都删除了，就没办法登陆了。
    @RequestMapping("/deleteById")
    @ResponseBody
    public String deleteloginById(String account_id) {
        String flag = iUserService.deleteById(account_id);
        return flag;
    }

    //注销功能，消除session，跳转到登陆界面
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("seller");
        session.removeAttribute("buyer");
        session.removeAttribute("account_id");
        return "login";
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





    //根据ids删除login数据
    @RequestMapping("/deleteByIds")
    @ResponseBody
    public String deleteloginByIds(List<Integer> ids) {
        String flag = iUserService.deleteByIds(ids);
        return flag;
    }


}
