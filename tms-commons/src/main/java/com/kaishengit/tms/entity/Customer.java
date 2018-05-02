package com.kaishengit.tms.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Customer implements Serializable {
    private Integer id;

    private String customerName;

    private String sex;

    private String customerMobile;

    private String address;

    private String customerIdcard;

    private Integer ticketId;

    private String customerIdcardPhoto;

    private String customerIdcardPhotoBack;

    private String customerPhoto;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomerIdcard() {
        return customerIdcard;
    }

    public void setCustomerIdcard(String customerIdcard) {
        this.customerIdcard = customerIdcard;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public String getCustomerIdcardPhoto() {
        return customerIdcardPhoto;
    }

    public void setCustomerIdcardPhoto(String customerIdcardPhoto) {
        this.customerIdcardPhoto = customerIdcardPhoto;
    }

    public String getCustomerIdcardPhotoBack() {
        return customerIdcardPhotoBack;
    }

    public void setCustomerIdcardPhotoBack(String customerIdcardPhotoBack) {
        this.customerIdcardPhotoBack = customerIdcardPhotoBack;
    }

    public String getCustomerPhoto() {
        return customerPhoto;
    }

    public void setCustomerPhoto(String customerPhoto) {
        this.customerPhoto = customerPhoto;
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