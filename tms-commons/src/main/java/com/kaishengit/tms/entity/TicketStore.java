package com.kaishengit.tms.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class TicketStore implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 网点名称
     */
    private String storeName;

    /**
     * 法人电话
     */
    private String storeMobile;

    /**
     * 网点地址
     */
    private String storeAddress;

    /**
     * 法人姓名
     */
    private String storeManager;

    /**
     * 营业执照照片
     */
    private String storeAttachment;

    /**
     * 法人身份证照片
     */
    private String managerAttachment;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private Integer storeAccountId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreMobile() {
        return storeMobile;
    }

    public void setStoreMobile(String storeMobile) {
        this.storeMobile = storeMobile;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getStoreManager() {
        return storeManager;
    }

    public void setStoreManager(String storeManager) {
        this.storeManager = storeManager;
    }

    public String getStoreAttachment() {
        return storeAttachment;
    }

    public void setStoreAttachment(String storeAttachment) {
        this.storeAttachment = storeAttachment;
    }

    public String getManagerAttachment() {
        return managerAttachment;
    }

    public void setManagerAttachment(String managerAttachment) {
        this.managerAttachment = managerAttachment;
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

    public Integer getStoreAccountId() {
        return storeAccountId;
    }

    public void setStoreAccountId(Integer storeAccountId) {
        this.storeAccountId = storeAccountId;
    }
}