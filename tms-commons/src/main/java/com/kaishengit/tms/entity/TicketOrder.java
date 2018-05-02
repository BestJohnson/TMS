package com.kaishengit.tms.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class TicketOrder implements Serializable {

    public static final String ORDER_TYPE_NEW = "新办订单";
    public static final String ORDER_TYPE_RENEW = "续费订单";
    public static final String ORDER_TYPE_REPLACE = "补办订单";

    private Integer id;

    private String ticketOrderNum;

    private Integer ticketId;

    private Integer customerId;

    private Integer ticketStoreId;

    private Long price;

    private String ticketOrderType;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTicketOrderNum() {
        return ticketOrderNum;
    }

    public void setTicketOrderNum(String ticketOrderNum) {
        this.ticketOrderNum = ticketOrderNum;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getTicketStoreId() {
        return ticketStoreId;
    }

    public void setTicketStoreId(Integer ticketStoreId) {
        this.ticketStoreId = ticketStoreId;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getTicketOrderType() {
        return ticketOrderType;
    }

    public void setTicketOrderType(String ticketOrderType) {
        this.ticketOrderType = ticketOrderType;
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
}