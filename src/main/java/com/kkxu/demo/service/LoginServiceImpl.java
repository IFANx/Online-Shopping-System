package com.kkxu.demo.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kkxu.demo.common.domain.Login;
import com.kkxu.demo.common.domain.LoginExample;
import com.kkxu.demo.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LoginServiceImpl implements ILoginService {
    @Autowired
    private LoginMapper loginMapper;

    public PageInfo<Login> findLogins(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        List<Login> logins = loginMapper.selectByExample(null);
        return new PageInfo<>(logins);
    }

    @Override
    public Integer insert(Login login){
        int insert1 = loginMapper.insert(login);
        return insert1;
    }

    @Override
    public Login selectById(String id) {
        Login login=loginMapper.selectByPrimaryKey(id);
      //  System.out.println(login.getAccountId()+"--"+login.getIsseller());
        return login;
    }

    @Override
    public List<Login> list(Integer id1, Integer id2) {
        //处理请求的地方
        //调用会哦取数据的操作
        LoginExample loginExample =new LoginExample();
        loginExample.createCriteria().andIdBetween(id1,id2);
        List<Login> list = loginMapper.selectByExample(loginExample);
        return list;
    }

    @Override
    public List<Login> selectBynormalId(String account) {
        LoginExample loginExample=new LoginExample();
        loginExample.createCriteria().andAccountIdEqualTo(account);
        List<Login> login= loginMapper.selectByExample(loginExample);
        return login;
    }

    @Override
    public String deleteById(String id) {
//        LoginExample loginExample=new LoginExample();
//        loginExample.createCriteria().andAccountIdEqualTo(id);
        loginMapper.deleteByPrimaryKey(id);
        return null;
    }

    @Override
    public String deleteByIds(List<Integer> ids) {
        String deletebyids = loginMapper.deleteByIds(ids);
        return deletebyids;
    }

    @Override
    public List<Login> selectAll() {
        List<Login> alllogins=loginMapper.selectByExample(null);
        return alllogins;
    }

    @Override
    public int updatelogin(String account_id, Login login) {
        Integer s = loginMapper.updateById(account_id, login);
        return s;
    }


}
