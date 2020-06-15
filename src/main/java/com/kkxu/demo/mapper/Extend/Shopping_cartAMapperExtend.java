package com.kkxu.demo.mapper.Extend;

import com.kkxu.demo.common.domain.Shopping_cartAKey;

import java.util.List;

public interface Shopping_cartAMapperExtend {
    List<Shopping_cartAKey> selectMe(int id);
    List<Shopping_cartAKey> selectMe1(int id);
}
