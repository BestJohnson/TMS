package com.kaishengit.tms.mapper;

import com.kaishengit.tms.entity.Ticket;
import com.kaishengit.tms.entity.TicketExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TicketMapper {
    long countByExample(TicketExample example);

    int deleteByExample(TicketExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Ticket record);

    int insertSelective(Ticket record);

    List<Ticket> selectByExample(TicketExample example);

    Ticket selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Ticket record, @Param("example") TicketExample example);

    int updateByExample(@Param("record") Ticket record, @Param("example") TicketExample example);

    int updateByPrimaryKeySelective(Ticket record);

    int updateByPrimaryKey(Ticket record);

    void batchInsert(List<Ticket> ticketList);

    void batchDelete(List<Integer> idList);

    Map<String,Integer> countByStateAndStoreAccountId(Integer id);
}