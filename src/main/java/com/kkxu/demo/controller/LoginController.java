package com.kkxu.demo.controller;

import com.kkxu.demo.common.domain.Login;
import com.kkxu.demo.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private ILoginService iLoginService;

    //登录
    @RequestMapping("/login")
    public String login(String account, String pwd, HttpSession session) {
        List<Login> login = iLoginService.selectBynormalId(account);
        if (pwd.equals(login.get(0).getPassword())) {
            session.setAttribute("account_id", account);
            return "ok";
        }
        return "fail";
    }

    //账号列表
    @RequestMapping("/loginlist")
    @ResponseBody
    public List<Login> loginlist(Integer id1, Integer id2) {
        List<Login> list = iLoginService.list(id1, id2);
        return list;
    }


//    @GetMapping("/getlogins")
//    @ResponseBody
//    public Object getLoginss(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10")int pageSize){
//        return Result.success(ILoginService.findLogins(pageNo,pageSize),"分页 查询book 对象");
//    }


    @RequestMapping("/LoginInsert")
    @ResponseBody
    //插入数据到login表
    public String Register2(Login login) {
        int i = 103;
        login.setId(i);
        login.setAccountId("scu" + i);
        login.setIsseller(Math.random() >= 0.5 ? 0 : 1);
        login.setPassword("scu" + i);
        login.setSellerId(i);
        Integer flag = iLoginService.insert(login);
        return "ok";
    }


    //根据id查Login表
    @RequestMapping("/selectById")
    @ResponseBody
    public Login selectById(String id) {
        Login login1 = iLoginService.selectById(id);

        if (login1 != null) {
            System.out.println(login1.getAccountId());
            return login1;
        } else return null;
    }


    //根据id删除login数据
    @RequestMapping("/deleteById")
    @ResponseBody
    public String deleteloginById(String account_id) {
        String flag = iLoginService.deleteById(account_id);
        return flag;
    }


    //根据ids删除login数据
    @RequestMapping("/deleteByIds")
    @ResponseBody
    public String deleteloginByIds(List<Integer> ids) {
        String flag = iLoginService.deleteByIds(ids);
        return flag;
    }


    //注册
    @RequestMapping("/register")
    @ResponseBody
    public String register(String account_id, String password) {
        Login login = iLoginService.selectById(account_id);
        Login loginnew = new Login();
        Integer num = iLoginService.selectAll().size();
        if (login != null) {
            return "已有此账号，请换一个名称";
        } else {
            loginnew.setAccountId(account_id);
            loginnew.setPassword(password);
            loginnew.setId(num + 1);
            loginnew.setIsseller(0);
            iLoginService.insert(loginnew);
            return "success";
        }
    }

    //login详情
    @RequestMapping("/logindetails")
    public String logindetail(String account_id,
                             ModelMap modelMap) {
        Login login = iLoginService.selectById(account_id);
        modelMap.put("login", login);
        return "logindetail";
    }

    //login详情
    @RequestMapping("/updatelogin")
    @ResponseBody
    public String updatelogin(String account_id,Integer Id, String Password, Integer SellerId, Integer Isseller){
        Login login=new Login();
        login.setId(Id);
        login.setIsseller(Isseller);
        login.setPassword(Password);
        login.setAccountId(account_id);
        login.setSellerId(SellerId);
        iLoginService.updatelogin(account_id,login);
        return "success";
    }


}
