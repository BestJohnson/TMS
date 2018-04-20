package com.kaishengit.tms.service;

import com.kaishengit.tms.entity.StoreAccount;
import com.kaishengit.tms.entity.TicketStore;

import java.util.List;

/**
 * 年票业务类接口
 */
public interface TicketStoreService {

    /**
     * 查找所有的销售网点
     * @return
     */
    List<TicketStore> findAllStore();

    /**
     * 新增售票点
     * @param ticketStore
     */
    void saveTicketStore(TicketStore ticketStore);

    /**
     * 根据ID查销售点
     * @param id 销售点ID
     * @return
     */
    TicketStore findStoreById(Integer id);

    /**
     * 根据storeAccountId去查找销售点对应的账号
     * @param id 销售点账户的id
     * @return
     */
    StoreAccount findStoreAccountById(Integer id);

    /**
     * 更新销售点信息
     * @param ticketStore
     */
    void updateStore(TicketStore ticketStore);

    /**
     * 根据ID删除售票点
     * @param id
     */
    void deleteStoreById(Integer id);

    /**
     * 禁用StoreAccount账号
     * @param id
     */
    void disableStoreAccountById(Integer id);
}
