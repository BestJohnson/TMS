package com.kaishengit.tms.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.tms.entity.*;
import com.kaishengit.tms.exception.ServiceException;
import com.kaishengit.tms.mapper.TicketInRecordMapper;
import com.kaishengit.tms.mapper.TicketMapper;
import com.kaishengit.tms.mapper.TicketOutRecordMapper;
import com.kaishengit.tms.mapper.TicketStoreMapper;
import com.kaishengit.tms.service.TicketService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private TicketStoreMapper ticketStoreMapper;
    @Autowired
    private TicketInRecordMapper ticketInRecordMapper;
    @Autowired
    private TicketOutRecordMapper ticketOutRecordMapper;




    /**
     * 新增年票入库
     *
     * @param ticketInRecord
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void addTicketInRecord(TicketInRecord ticketInRecord) {

        //解决BeginNum数值太大时无法用Int类型进行计算的问题
        BigInteger newStart = new BigInteger(ticketInRecord.getBeginTicketNum());
        BigInteger newEnd = new BigInteger(ticketInRecord.getEndTicketNum());
        //起始票号不能大于截止票号
        if(newStart.compareTo(newEnd) >= 0) {
            throw new ServiceException("起始票号不能大于截止票号");
        }

        //判断插入的票号区间是否已经存在，存在则不能插入
        TicketInRecordExample ticketInRecordExample = new TicketInRecordExample();
        List<TicketInRecord> ticketInRecordList = ticketInRecordMapper.selectByExample(ticketInRecordExample);
        for(TicketInRecord ticketInRecord1 : ticketInRecordList) {
            BigInteger start = new BigInteger(ticketInRecord1.getBeginTicketNum());
            BigInteger end = new BigInteger(ticketInRecord1.getEndTicketNum());

            Boolean flag = (newStart.compareTo(start) <= 0 && newEnd.compareTo(start) <= 0) || (newStart.compareTo(end) >= 0 && newEnd.compareTo(end) >= 0);
            if(!flag) {
                throw new ServiceException("该区段票号已存在，不能添加");
            }
        }

        BigInteger totalNum = newEnd.subtract(newStart).add(new BigInteger(String.valueOf(1)));

        ticketInRecord.setTotalNum(totalNum.intValue());

        //获取当前进行入库操作的账户
        Subject subject = SecurityUtils.getSubject();
        Account account = (Account) subject.getPrincipal();

        ticketInRecord.setAccountName(account.getAccountName());
        ticketInRecord.setAccountId(account.getId());
        ticketInRecord.setContent("入库：" + ticketInRecord.getBeginTicketNum() + "-" + ticketInRecord.getEndTicketNum());
        ticketInRecord.setCreateTime(new Date());
        ticketInRecordMapper.insertSelective(ticketInRecord);

        //批量插入到Ticket表中
        List<Ticket> ticketList = new ArrayList<>();
        for(int i = 0; i < totalNum.intValue(); i++) {
            Ticket ticket = new Ticket();
            ticket.setTicketNum(newStart.add(new BigInteger(String.valueOf(i))).toString());
            ticket.setCreateTime(new Date());
            ticket.setTicketInTime(new Date());
            ticket.setTicketState(Ticket.TICKET_STATE_IN_STORE);

            ticketList.add(ticket);
        }
        ticketMapper.batchInsert(ticketList);
    }

    /**
     * 查找所有年票的入库记录
     *
     * @return
     */
    @Override
    public List<TicketInRecord> findAllTicketInRecord() {
        TicketInRecordExample ticketInRecordExample = new TicketInRecordExample();

        List<TicketInRecord> ticketInRecordList = ticketInRecordMapper.selectByExample(ticketInRecordExample);
        return ticketInRecordList;

    }

    /**
     * 根据ID查找年票如库对象
     *
     * @param id
     * @return
     */
    @Override
    public TicketInRecord findTicketInRecordById(Integer id) {
        return ticketInRecordMapper.selectByPrimaryKey(id);
    }

    /**
     * 修改入库信息
     *
     * @param ticketInRecord
     */
    @Override
    public void updateTicketInRecord(TicketInRecord ticketInRecord) {

        ticketInRecord.setUpdateTime(new Date());

        ticketInRecordMapper.updateByPrimaryKeySelective(ticketInRecord);
    }

    /**
     * 根据ID删除对应的年票入库对象
     *
     * @param id
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void delTicketInRecordById(Integer id) {
        TicketInRecord ticketInRecord = ticketInRecordMapper.selectByPrimaryKey(id);

        if(ticketInRecord != null) {
            //判断该批次年票中是否有状态不是已入库的（不是，证明已经有下发），有，不能删除
            TicketExample ticketExample = new TicketExample();
            ticketExample.createCriteria()
                    .andTicketNumGreaterThanOrEqualTo(ticketInRecord.getBeginTicketNum())
                    .andTicketNumLessThanOrEqualTo(ticketInRecord.getEndTicketNum())
                    .andTicketStateEqualTo(Ticket.TICKET_STATE_IN_STORE);
            List<Ticket> ticketList = ticketMapper.selectByExample(ticketExample);

            if(!ticketInRecord.getTotalNum().equals(ticketList.size())) {
                throw new ServiceException("该批次年票中已有下发，不能删除");
            }

            //删除入库记录
            ticketInRecordMapper.deleteByPrimaryKey(id);
            //删除对应的年票
            List<Long> idList = new ArrayList<>();

            for(Ticket ticket : ticketList){
                idList.add(ticket.getId());
            }
            ticketMapper.batchDelete(idList);
        }

    }


    /**
     * 新增下发记录
     */
    @Override
    public void addTicketOutRecord(TicketOutRecord ticketOutRecord) {
        ticketOutRecord.setOutTime(new Date());

        BigInteger start = new BigInteger(ticketOutRecord.getTicketBeginNum());
        BigInteger end = new BigInteger(ticketOutRecord.getTicketEndNum());
        BigInteger totalNum = end.subtract(start).add(new BigInteger(String.valueOf(1)));
        ticketOutRecord.setTotalNum(totalNum.intValue());

        
        ticketOutRecord.setMoney();
        ticketOutRecord.setState(TicketOutRecord.NOT_PAY);

        Subject subject = SecurityUtils.getSubject();
        Account account = (Account) subject.getPrincipal();
        ticketOutRecord.setAccountName(account.getAccountName());

        ticketOutRecordMapper.insertSelective(ticketOutRecord);
    }

    /**
     * 根据当前页码查询所有下发记录
     *
     * @param pageNo
     * @return
     */
    @Override
    public PageInfo<TicketOutRecord> findAllTicketOutRecordByPageNo(Integer pageNo) {
        PageHelper.startPage(pageNo,5);
        TicketOutRecordExample ticketOutRecordExample = new TicketOutRecordExample();
        ticketOutRecordExample.setOrderByClause("id DESC");

        List<TicketOutRecord> ticketOutRecordList = ticketOutRecordMapper.selectByExample(ticketOutRecordExample);
        return new PageInfo<>(ticketOutRecordList);
    }

    /**
     * 查找所有的销售网点
     *
     * @return
     */
    @Override
    public List<TicketStore> findAllTicketStore() {
        return ticketStoreMapper.selectByExample(new TicketStoreExample());
    }


}
