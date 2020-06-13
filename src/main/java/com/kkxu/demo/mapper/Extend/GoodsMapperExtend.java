package com.kkxu.demo.mapper.Extend;

import com.kkxu.demo.common.domain.Goods;

public interface GoodsMapperExtend {
    Integer updateById(Integer goodsid, Goods goods);

    Integer selectMaxID();
}
