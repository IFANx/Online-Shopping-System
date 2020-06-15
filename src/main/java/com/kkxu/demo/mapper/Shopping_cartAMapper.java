package com.kkxu.demo.mapper;

import com.kkxu.demo.common.domain.Shopping_cartAExample;
import com.kkxu.demo.common.domain.Shopping_cartAKey;
import com.kkxu.demo.mapper.Extend.Shopping_cartAMapperExtend;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface Shopping_cartAMapper extends Shopping_cartAMapperExtend {
    long countByExample(Shopping_cartAExample example);

    int deleteByExample(Shopping_cartAExample example);

    int deleteByPrimaryKey(Shopping_cartAKey key);

    int insert(Shopping_cartAKey record);

    int insertSelective(Shopping_cartAKey record);

    List<Shopping_cartAKey> selectByExample(Shopping_cartAExample example);

    int updateByExampleSelective(@Param("record") Shopping_cartAKey record, @Param("example") Shopping_cartAExample example);

    int updateByExample(@Param("record") Shopping_cartAKey record, @Param("example") Shopping_cartAExample example);
}