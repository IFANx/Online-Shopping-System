package com.kkxu.demo.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kkxu.demo.common.domain.*;
import com.kkxu.demo.mapper.BuyerMapper;
import com.kkxu.demo.mapper.LoginMapper;
import com.kkxu.demo.mapper.SellerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private BuyerMapper buyerMapper;

    @Autowired
    private SellerMapper sellerMapper;

    public PageInfo<Login> findLogins(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        List<Login> logins = loginMapper.selectByExample(null);
        return new PageInfo<>(logins);
    }

    @Override
    public Integer insertintologin(Login login){
        return loginMapper.insert(login);
    }

    @Override
    public Integer insertintobuyer(Buyer buyer) {
        return buyerMapper.insert(buyer);
    }

    @Override
    public Integer insertintoseller(Seller seller) {
        return sellerMapper.insert(seller);
    }

    @Override
    public Login selectloginById(String account_id) {
        Login login=loginMapper.selectByPrimaryKey(account_id);
        return login;
    }

    @Override
    public List<Buyer> selectbuyerById(String account) {
        BuyerExample buyerExample=new BuyerExample();
        buyerExample.createCriteria().andAccountIdEqualTo(account);
        List<Buyer> buyer= buyerMapper.selectByExample(buyerExample);
        return buyer;
    }

    @Override
    public List<Seller> selectsellerById(String account) {
        SellerExample sellerExample=new SellerExample();
        sellerExample.createCriteria().andAccountIdEqualTo(account);
        List<Seller> seller=sellerMapper.selectByExample(sellerExample);
        return seller;
    }

    @Override
    public int UpdateLogin(String account_id, Login login) {
        Integer s = loginMapper.updateById(account_id, login);
        return s;
    }

    @Override
    public int UpdateBuyer(String account_id, Buyer buyer) {
        Integer s =buyerMapper.updateById(account_id,buyer);
        return s;
    }

    @Override
    public int UpdateSeller(String account_id, Seller seller) {
        Integer s =sellerMapper.updateById(account_id,seller);
        return s;
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
    public Integer selectLoginMaxID() {
        return loginMapper.selectMaxID();
    }




}
