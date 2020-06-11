package com.kkxu.demo.service;

import com.github.pagehelper.PageInfo;
import com.kkxu.demo.common.domain.Login;

import java.util.List;

public interface ILoginService {

   PageInfo<Login> findLogins(int pageNo, int pageSize);

    Integer insert(Login login);

    Login selectById(String id);

    List<Login> list(Integer id1, Integer id2);

    List<Login> selectBynormalId(String account);

    String deleteById(String id);

    String deleteByIds(List<Integer> ids);

    List<Login> selectAll();

    int  updatelogin(String account_id, Login login);
}
