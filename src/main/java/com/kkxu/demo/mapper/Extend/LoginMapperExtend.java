package com.kkxu.demo.mapper.Extend;

import com.kkxu.demo.common.domain.Login;

import java.util.List;

public interface LoginMapperExtend {

    String deleteByIds(List<Integer> ids);

    List<Login> selectAll();

    Integer updateById(String account_id, Login login);
}
