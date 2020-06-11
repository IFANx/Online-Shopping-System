package com.kkxu.demo.mapper;

import com.kkxu.demo.common.domain.Shopping_cartExample;
import com.kkxu.demo.common.domain.Shopping_cartKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Shopping_cartMapper {
    long countByExample(Shopping_cartExample example);

    int deleteByExample(Shopping_cartExample example);

    int deleteByPrimaryKey(Shopping_cartKey key);

    int insert(Shopping_cartKey record);

    int insertSelective(Shopping_cartKey record);

    List<Shopping_cartKey> selectByExample(Shopping_cartExample example);

    int updateByExampleSelective(@Param("record") Shopping_cartKey record, @Param("example") Shopping_cartExample example);

    int updateByExample(@Param("record") Shopping_cartKey record, @Param("example") Shopping_cartExample example);
}