package com.kaishengit.tms.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 
 */
public class TicketOutRecord implements Serializable {

    public static final String NOT_PAY = "未支付";
    public static final String PAY = "已支付";

    private Integer id;

    private String ticketStore;

    private String ticketBeginNum;

    private String ticketEndNum;

    private Date outTime;

    private BigDecimal price;

    private Integer totalNum;

    private BigDecimal money;

    private String state;

    private String accountName;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTicketStore() {
        return ticketStore;
    }

    public void setTicketStore(String ticketStore) {
        this.ticketStore = ticketStore;
    }

    public String getTicketBeginNum() {
        return ticketBeginNum;
    }

    public void setTicketBeginNum(String ticketBeginNum) {
        this.ticketBeginNum = ticketBeginNum;
    }

    public String getTicketEndNum() {
        return ticketEndNum;
    }

    public void setTicketEndNum(String ticketEndNum) {
        this.ticketEndNum = ticketEndNum;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}