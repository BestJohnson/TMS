package com.kaishengit.tms.service.impl;

import com.kaishengit.tms.entity.StoreAccount;
import com.kaishengit.tms.entity.TicketStore;
import com.kaishengit.tms.entity.TicketStoreExample;
import com.kaishengit.tms.mapper.StoreAccountMapper;
import com.kaishengit.tms.mapper.TicketStoreMapper;
import com.kaishengit.tms.service.TicketStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 年票业务接口的实现类
 */
@Service
public class TicketStoreServiceImpl implements TicketStoreService {

    @Autowired
    private TicketStoreMapper ticketStoreMapper;
    @Autowired
    private StoreAccountMapper storeAccountMapper;

    /**
     * 查找所有的销售网点
     *
     * @return
     */
    @Override
    public List<TicketStore> findAllStore() {
        TicketStoreExample ticketStoreExample = new TicketStoreExample();
        return ticketStoreMapper.selectByExample(ticketStoreExample);

    }

    /**
     * 新增售票点
     *
     * @param ticketStore
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveTicketStore(TicketStore ticketStore) {

        ticketStore.setCreateTime(new Date());
        ticketStoreMapper.insertSelective(ticketStore);

        //store_account表中也需要新创建
        StoreAccount storeAccount = new StoreAccount();
        storeAccount.setStoreAccount(ticketStore.getStoreMobile());
        storeAccount.setStorePassword(ticketStore.getStoreMobile().substring(5));
        storeAccount.setCreateTime(new Date());
        storeAccount.setTicketStoreId(ticketStore.getId());
        storeAccount.setStoreState(StoreAccount.ACCOUNT_STATE_NOMAL);

        storeAccountMapper.insertSelective(storeAccount);

        //两表相互设置store_account_id和ticket_store_id之后，更新ticket_store表
        ticketStore.setStoreAccountId(storeAccount.getId());
        ticketStoreMapper.updateByPrimaryKeySelective(ticketStore);
    }

    /**
     * 根据ID查销售点
     *
     * @param id 销售点ID
     * @return
     */
    @Override
    public TicketStore findStoreById(Integer id) {
        return ticketStoreMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据storeAccountId去查找销售点对应的账号
     *
     * @param id 销售点账户的id
     * @return
     */
    @Override
    public StoreAccount findStoreAccountById(Integer id) {
        return storeAccountMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新销售点信息
     *
     * @param ticketStore
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateStore(TicketStore ticketStore) {

        ticketStore.setUpdateTime(new Date());

        //判断手机号码是否发生修改，修改的话需要重新设置StoreAccount表中的账号和密码
        StoreAccount storeAccount = storeAccountMapper.selectByPrimaryKey(ticketStore.getStoreAccountId());

        if(!ticketStore.getStoreMobile().equals(storeAccount.getStoreAccount())) {
            storeAccount.setStoreAccount(ticketStore.getStoreMobile());
            storeAccount.setStorePassword(ticketStore.getStoreMobile().substring(5));
            storeAccount.setUpdateTime(new Date());

            storeAccountMapper.updateByPrimaryKeySelective(storeAccount);
        }

        ticketStoreMapper.updateByPrimaryKeySelective(ticketStore);
    }

    /**
     * 根据ID删除售票点
     *
     * @param id
     */
    @Override
    public void deleteStoreById(Integer id) {
        TicketStore ticketStore = ticketStoreMapper.selectByPrimaryKey(id);

        if(ticketStore != null) {
            //删除售票点时，需要判断关联的账号是否跟其他业务之间存在联系


            ticketStoreMapper.deleteByPrimaryKey(id);
            storeAccountMapper.deleteByPrimaryKey(ticketStore.getStoreAccountId());
        }


    }

    /**
     * 禁用StoreAccount账号
     *
     * @param id
     */
    @Override
    public void disableStoreAccountById(Integer id) {

        TicketStore ticketStore = ticketStoreMapper.selectByPrimaryKey(id);
        StoreAccount storeAccount = storeAccountMapper.selectByPrimaryKey(ticketStore.getStoreAccountId());

        if(storeAccount != null) {
            storeAccount.setStoreState(StoreAccount.ACCOUNT_STATE_DISABLE);
            storeAccountMapper.updateByPrimaryKeySelective(storeAccount);
        }
    }


}
