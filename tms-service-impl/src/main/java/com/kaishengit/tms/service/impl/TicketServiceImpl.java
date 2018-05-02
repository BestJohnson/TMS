package com.kaishengit.tms.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.tms.entity.*;
import com.kaishengit.tms.exception.ServiceException;
import com.kaishengit.tms.mapper.*;
import com.kaishengit.tms.service.TicketService;
import com.kaishengit.tms.util.SnowFlake;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private TicketOrderMapper ticketOrderMapper;




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

            boolean flag = (newStart.compareTo(start) <= 0 && newEnd.compareTo(start) <= 0) || (newStart.compareTo(end) >= 0 && newEnd.compareTo(end) >= 0);
            if(!flag) {
                throw new ServiceException("该区段票号已存在，不能添加");
            }
        }

        BigInteger totalNum = newEnd.subtract(newStart).add(new BigInteger(String.valueOf(1)));

        ticketInRecord.setTotalNum(totalNum.intValue());

        /*//获取当前进行入库操作的账户
        Subject subject = SecurityUtils.getSubject();
        Account account = (Account) subject.getPrincipal();

        ticketInRecord.setAccountName(account.getAccountName());
        ticketInRecord.setAccountId(account.getId());*/


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
            List<Integer> idList = new ArrayList<>();

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
    @Transactional(rollbackFor = RuntimeException.class)
    public void addTicketOutRecord(TicketOutRecord ticketOutRecord) {
        if(ticketOutRecord.getTicketBeginNum().compareTo(ticketOutRecord.getTicketEndNum()) >= 0) {
            throw new ServiceException("起始票号不能大于截止票号");
        }


        /*List<TicketInRecord> ticketInRecordList = ticketInRecordMapper.selectByExample(new TicketInRecordExample());
        for(TicketInRecord ticketInRecord : ticketInRecordList) {
            boolean flag = (ticketOutRecord.getTicketBeginNum().compareTo(ticketInRecord.getBeginTicketNum()) >= 0) && (ticketOutRecord.getTicketEndNum().compareTo(ticketInRecord.getEndTicketNum()) <= 0);

            if(!flag) {
                ticketOutRecordMapper.deleteByPrimaryKey(ticketOutRecord.getId());
                throw new ServiceException("未查找到该区段内相关年票信息，请重新核对");
            }
        }*/

        TicketExample ticketExample = new TicketExample();
        ticketExample.createCriteria()
                .andTicketNumGreaterThanOrEqualTo(ticketOutRecord.getTicketBeginNum())
                .andTicketNumLessThanOrEqualTo(ticketOutRecord.getTicketEndNum());
        List<Ticket> ticketList = ticketMapper.selectByExample(ticketExample);
        for(Ticket ticket :ticketList) {
            if(Ticket.TICKET_STATE_OUT_STORE.equals(ticket.getTicketState())) {
                throw new ServiceException("该区段已有年票下发，请勿重复操作");
            }
        }


        ticketOutRecord.setOutTime(new Date());

        BigInteger start = new BigInteger(ticketOutRecord.getTicketBeginNum());
        BigInteger end = new BigInteger(ticketOutRecord.getTicketEndNum());
        BigInteger totalNum = end.subtract(start).add(new BigInteger(String.valueOf(1)));
        ticketOutRecord.setTotalNum(totalNum.intValue());


        ticketOutRecord.setMoney(ticketOutRecord.getPrice().multiply(new BigDecimal(totalNum)));
        ticketOutRecord.setState(TicketOutRecord.NOT_PAY);

        Subject subject = SecurityUtils.getSubject();
        Account account = (Account) subject.getPrincipal();
        ticketOutRecord.setAccountName(account.getAccountName());

        ticketOutRecordMapper.insertSelective(ticketOutRecord);
        /*ticketOutRecordMapper.updateByPrimaryKeySelective(ticketOutRecord);*/
    }

    /**
     * 根据当前页码查询所有下发记录
     * @param pageNo
     * @return
     */
    @Override
    public PageInfo<TicketOutRecord> findAllTicketOutRecordByPageNo(Integer pageNo) {
        PageHelper.startPage(pageNo,5);
        System.out.println(pageNo);
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

    /**
     * 根据id删除未支付的下发
     *
     * @param id
     */
    @Override
    public void delTicketOutStorage(Integer id) {
        TicketOutRecord ticketOutRecord = ticketOutRecordMapper.selectByPrimaryKey(id);

        if(ticketOutRecord != null) {
            ticketOutRecordMapper.deleteByPrimaryKey(id);
        }
    }

    /**
     * 根据当前页码和搜索参数动态查询出下发记录
     *
     * @param pageNo
     * @param queryParam
     * @return
     */
    @Override
    public PageInfo<TicketOutRecord> findTicketOutRecordByPageNoAndQueryParam(Integer pageNo, Map<String, Object> queryParam) {
        PageHelper.startPage(pageNo,5);

        TicketOutRecordExample ticketOutRecordExample = new TicketOutRecordExample();
        TicketOutRecordExample.Criteria criteria = ticketOutRecordExample.createCriteria();

        String state = (String) queryParam.get("state");
        if(StringUtils.isNotEmpty(state)) {
            criteria.andStateEqualTo(state);
        }

        List<TicketOutRecord> ticketOutRecordList = ticketOutRecordMapper.selectByExample(ticketOutRecordExample);

        return new PageInfo<>(ticketOutRecordList);
    }

    /**
     * 根据id查找对应的下发记录
     *
     * @param id
     * @return
     */
    @Override
    public TicketOutRecord findTicketOutRecordById(Integer id) {
        return ticketOutRecordMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据id支付对应的下发
     *
     * @param id
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void payTicketOutRecord(Integer id) {
        TicketOutRecord ticketOutRecord = ticketOutRecordMapper.selectByPrimaryKey(id);
        if(ticketOutRecord != null && ticketOutRecord.getState().equals(TicketOutRecord.NOT_PAY)) {
            ticketOutRecord.setState(TicketOutRecord.PAY);

            ticketOutRecordMapper.updateByPrimaryKeySelective(ticketOutRecord);

            BigInteger start = new BigInteger(ticketOutRecord.getTicketBeginNum());
            BigInteger end = new BigInteger(ticketOutRecord.getTicketEndNum());
            //321行，空指针异常
            for(int i = start.intValue(); i <= end.intValue(); i++) {
                Ticket ticket = ticketMapper.selectByPrimaryKey(i);
                ticket.setTicketState(Ticket.TICKET_STATE_OUT_STORE);
                ticket.setTicketOutTime(new Date());

                //表设计有问题，改表麻烦，这一步是为了设置store_account_id到Ticket表中 ---------------
                TicketStoreExample ticketStoreExample = new TicketStoreExample();
                ticketStoreExample.createCriteria().andStoreNameEqualTo(ticketOutRecord.getTicketStore());
                List<TicketStore> ticketStoreList = ticketStoreMapper.selectByExample(ticketStoreExample);
                TicketStore ticketStore = ticketStoreList.get(0);
                //----------------------------

                ticket.setStoreAccountId(ticketStore.getId());

                ticketMapper.updateByPrimaryKeySelective(ticket);
            }
        }

    }

    /**
     * 统计当前销售点的票务
     *
     * @param id
     * @return
     */
    @Override
    public Map<String, Integer> countTicketByStateAndStoreAccountId(Integer id) {
        return ticketMapper.countByStateAndStoreAccountId(id);
    }

    /**
     * 新办理年票
     *
     * @param customer 客户对象
     * @param ticketNum 年票票号
     * @param ticketStore 销售点
     * @param price 价格
     */
    @Override
    @Transactional(rollbackFor =  RuntimeException.class)
    public void saleTicket(Customer customer, String ticketNum, TicketStore ticketStore, Long price) {

        TicketExample ticketExample = new TicketExample();
        ticketExample.createCriteria().andTicketNumEqualTo(ticketNum);

        List<Ticket> ticketList = ticketMapper.selectByExample(ticketExample);

        if(ticketList != null && !ticketList.isEmpty()) {
            Ticket ticket = ticketList.get(0);
            //先查看给票号是否是已下发
            if(Ticket.TICKET_STATE_OUT_STORE.equals(ticket.getTicketState())) {
                //判断该年票是否属于该销售点
                if(ticket.getStoreAccountId().equals(ticketStore.getId())) {




                    //判断客户是否办理过年票；办理过，且年票可用，则不让办理；其他情况允许办理，但是只有新版年票可用
                    CustomerExample customerExample = new CustomerExample();
                    customerExample.createCriteria().andCustomerIdcardEqualTo(customer.getCustomerIdcard());
                    List<Customer> customerList = customerMapper.selectByExample(customerExample);

                    if(customerList != null && !customerList.isEmpty()) {
                        Customer existCustomer = customerList.get(0);
                        Ticket ticket1 = ticketMapper.selectByPrimaryKey(existCustomer.getTicketId());

                        if(ticket1 != null && ticket1.getTicketState().equals(Ticket.TICKET_STATE_OUT_STORE)) {
                            throw new ServiceException("客户已办理过年票，请勿重复办理");
                        } else {
                            customer = existCustomer;
                        }

                    } else {

                        customer.setTicketId(ticket.getId());;
                        customer.setCreateTime(new Date());;

                        customerMapper.insertSelective(customer);

                    }


                    //修改年票为已销售状态
                    ticket.setTicketState(Ticket.TICKET_STATE_SALE);
                    ticket.setTicketValidityStart(new Date());
                    DateTime endDate = DateTime.now().plusYears(1);
                    ticket.setTicketValidityEnd(endDate.toDate());
                    ticket.setCustomerId(customer.getId());

                    ticketMapper.updateByPrimaryKeySelective(ticket);

                    //创建销售订单
                    TicketOrder ticketOrder = new TicketOrder();
                    ticketOrder.setCreateTime(new Date());
                    ticketOrder.setCustomerId(customer.getId());
                    ticketOrder.setTicketStoreId(ticketStore.getId());
                    ticketOrder.setTicketId(ticket.getId());

                    //数据库中price设计成decimal，为什么生成的实体类里面就成了Long？？还有，怎么把Bigdecimal转成Long？？
                    //这里是把原来price的Bigdecimal类型改成Long了
                    ticketOrder.setPrice(price);

                    //流水号
                    //SnowFlake snowFlake = new SnowFlake();

                    ticketOrder.setTicketOrderType(TicketOrder.ORDER_TYPE_NEW);

                    ticketOrderMapper.insertSelective(ticketOrder);



                }else {
                    throw new ServiceException("该年票不属于当前销售点，请仔细核对");
                }

            } else {
                throw new ServiceException("该年票不能办理，请检查年票状态");
            }


        } else {
            throw new ServiceException("该票号不存在，请重新检查");
        }


    }

    /**
     * 年票挂失
     *
     * @param ticketNum
     * @param customerIdcard
     */
    @Override
    public void lostTicket(String ticketNum, String customerIdcard) {
        TicketExample ticketExample = new TicketExample();
        ticketExample.createCriteria().andTicketNumEqualTo(ticketNum);
        List<Ticket> ticketList = ticketMapper.selectByExample(ticketExample);

        if(ticketList !=null && !ticketList.isEmpty()) {
            Ticket ticket = ticketList.get(0);

            CustomerExample customerExample = new CustomerExample();
            customerExample.createCriteria().andCustomerIdcardEqualTo(customerIdcard);
            List<Customer> customerList = customerMapper.selectByExample(customerExample);

            if(customerList != null && !customerList.isEmpty()) {
                Customer customer = customerList.get(0);

                if(ticket.getCustomerId().equals(customer.getId())) {

                    //写到这里了

                } else {
                    throw new ServiceException("票号和客户身份证不匹配");
                }

            } else {
                throw new ServiceException("客户身份证号码有误，请检查");
            }


        } else {
            throw new ServiceException("票号有误，请检查");
        }


    }


}
