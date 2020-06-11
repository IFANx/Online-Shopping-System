package com.kkxu.demo.mapper;

import com.kkxu.demo.common.domain.Buyer;
import com.kkxu.demo.common.domain.BuyerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BuyerMapper {
    long countByExample(BuyerExample example);

    int deleteByExample(BuyerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Buyer record);

    int insertSelective(Buyer record);

    List<Buyer> selectByExample(BuyerExample example);

    Buyer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Buyer record, @Param("example") BuyerExample example);

    int updateByExample(@Param("record") Buyer record, @Param("example") BuyerExample example);

    int updateByPrimaryKeySelective(Buyer record);

    int updateByPrimaryKey(Buyer record);
}