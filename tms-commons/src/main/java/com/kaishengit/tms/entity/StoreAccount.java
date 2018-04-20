package com.kaishengit.tms.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class StoreAccount implements Serializable {

    public static final String ACCOUNT_STATE_NOMAL = "正常";
    public static final String ACCOUNT_STATE_DISABLE = "异常";


    /**
     * 主键
     */
    private Integer id;

    /**
     * 账户名称(法人手机)
     */
    private String storeAccount;

    /**
     * 账户密码（手机后6位）
     */
    private String storePassword;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 销售点状态（营业，关闭）
     */
    private String storeState;

    private Integer ticketStoreId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStoreAccount() {
        return storeAccount;
    }

    public void setStoreAccount(String storeAccount) {
        this.storeAccount = storeAccount;
    }

    public String getStorePassword() {
        return storePassword;
    }

    public void setStorePassword(String storePassword) {
        this.storePassword = storePassword;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getStoreState() {
        return storeState;
    }

    public void setStoreState(String storeState) {
        this.storeState = storeState;
    }

    public Integer getTicketStoreId() {
        return ticketStoreId;
    }

    public void setTicketStoreId(Integer ticketStoreId) {
        this.ticketStoreId = ticketStoreId;
    }
}