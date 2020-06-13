package com.kkxu.demo.service;

import com.github.pagehelper.PageInfo;
import com.kkxu.demo.common.domain.Buyer;
import com.kkxu.demo.common.domain.Login;
import com.kkxu.demo.common.domain.Seller;

import java.util.List;

public interface IUserService {

    PageInfo<Login> findLogins(int pageNo, int pageSize);

    Integer selectLoginMaxID();

    Integer insertintologin(Login login);

    Integer insertintobuyer(Buyer buyer);

    Integer insertintoseller(Seller seller);

    Login selectloginById(String account_id);

    List<Buyer> selectbuyerById(String account);

    List<Seller> selectsellerById(String account);

    int UpdateLogin(String account_id, Login login);

    int UpdateBuyer(String account_id, Buyer buyer);

    int UpdateSeller(String account_id, Seller seller);

    List<Login> list(Integer id1, Integer id2);

    List<Login> selectBynormalId(String account);

    String deleteById(String id);

    String deleteByIds(List<Integer> ids);

    List<Login> selectAll();



}
