package com.kkxu.demo.mapper;

import com.kkxu.demo.common.domain.Shopping_cartBExample;
import com.kkxu.demo.common.domain.Shopping_cartBKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface Shopping_cartBMapper{
    long countByExample(Shopping_cartBExample example);

    int deleteByExample(Shopping_cartBExample example);

    int deleteByPrimaryKey(Shopping_cartBKey key);

    int insert(Shopping_cartBKey record);

    int insertSelective(Shopping_cartBKey record);

    List<Shopping_cartBKey> selectByExample(Shopping_cartBExample example);

    int updateByExampleSelective(@Param("record") Shopping_cartBKey record, @Param("example") Shopping_cartBExample example);

    int updateByExample(@Param("record") Shopping_cartBKey record, @Param("example") Shopping_cartBExample example);
}