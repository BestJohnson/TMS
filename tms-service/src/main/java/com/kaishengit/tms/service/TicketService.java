package com.kaishengit.tms.service;


import com.github.pagehelper.PageInfo;
import com.kaishengit.tms.entity.TicketInRecord;
import com.kaishengit.tms.entity.TicketOutRecord;
import com.kaishengit.tms.entity.TicketStore;

import java.util.List;
import java.util.Map;

/**
 * 年票业务类的接口
 */
public interface TicketService {

    /**
     * 新增年票入库
     * @param ticketInRecord
     */
    void addTicketInRecord(TicketInRecord ticketInRecord);

    /**
     * 查找所有年票的入库记录
     * @return
     */
    List<TicketInRecord> findAllTicketInRecord();

    /**
     * 根据ID查找年票如库对象
     * @param id
     * @return
     */
    TicketInRecord findTicketInRecordById(Integer id);

    /**
     * 修改入库信息
     * @param ticketInRecord
     */
    void updateTicketInRecord(TicketInRecord ticketInRecord);

    /**
     * 根据ID删除对应的年票入库对象
     * @param id
     */
    void delTicketInRecordById(Integer id);


    /**
     * 新增下发记录
     */
    void addTicketOutRecord(TicketOutRecord ticketOutRecord);


    /**
     * 根据当前页码查询所有下发记录
     * @param pageNo
     * @return
     */
    PageInfo<TicketOutRecord> findAllTicketOutRecordByPageNo(Integer pageNo);

    /**
     * 查找所有的销售网点
     * @return
     */
    List<TicketStore> findAllTicketStore();

    /**
     * 根据id删除未支付的下发
     * @param id
     */
    void delTicketOutStorage(Integer id);

    /**
     * 根据当前页码和搜索参数动态查询出下发记录
     * @param pageNo
     * @param queryParam
     * @return
     */
    PageInfo<TicketOutRecord> findTicketOutRecordByPageNoAndQueryParam(Integer pageNo, Map<String, Object> queryParam);

    /**
     * 根据id查找对应的下发记录
     * @param id
     * @return
     */
    TicketOutRecord findTicketOutRecordById(Integer id);

    /**
     * 根据id支付对应的下发
     * @param id
     */
    void payTicketOutRecord(Integer id);
}
