package com.kaishengit.tms.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CustomerExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIsNull() {
            addCriterion("customer_name is null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIsNotNull() {
            addCriterion("customer_name is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameEqualTo(String value) {
            addCriterion("customer_name =", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotEqualTo(String value) {
            addCriterion("customer_name <>", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThan(String value) {
            addCriterion("customer_name >", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThanOrEqualTo(String value) {
            addCriterion("customer_name >=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThan(String value) {
            addCriterion("customer_name <", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThanOrEqualTo(String value) {
            addCriterion("customer_name <=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLike(String value) {
            addCriterion("customer_name like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotLike(String value) {
            addCriterion("customer_name not like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIn(List<String> values) {
            addCriterion("customer_name in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotIn(List<String> values) {
            addCriterion("customer_name not in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameBetween(String value1, String value2) {
            addCriterion("customer_name between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotBetween(String value1, String value2) {
            addCriterion("customer_name not between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andSexIsNull() {
            addCriterion("sex is null");
            return (Criteria) this;
        }

        public Criteria andSexIsNotNull() {
            addCriterion("sex is not null");
            return (Criteria) this;
        }

        public Criteria andSexEqualTo(String value) {
            addCriterion("sex =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(String value) {
            addCriterion("sex <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(String value) {
            addCriterion("sex >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(String value) {
            addCriterion("sex >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(String value) {
            addCriterion("sex <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(String value) {
            addCriterion("sex <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLike(String value) {
            addCriterion("sex like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotLike(String value) {
            addCriterion("sex not like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<String> values) {
            addCriterion("sex in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<String> values) {
            addCriterion("sex not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(String value1, String value2) {
            addCriterion("sex between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(String value1, String value2) {
            addCriterion("sex not between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andCustomerMobileIsNull() {
            addCriterion("customer_mobile is null");
            return (Criteria) this;
        }

        public Criteria andCustomerMobileIsNotNull() {
            addCriterion("customer_mobile is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerMobileEqualTo(String value) {
            addCriterion("customer_mobile =", value, "customerMobile");
            return (Criteria) this;
        }

        public Criteria andCustomerMobileNotEqualTo(String value) {
            addCriterion("customer_mobile <>", value, "customerMobile");
            return (Criteria) this;
        }

        public Criteria andCustomerMobileGreaterThan(String value) {
            addCriterion("customer_mobile >", value, "customerMobile");
            return (Criteria) this;
        }

        public Criteria andCustomerMobileGreaterThanOrEqualTo(String value) {
            addCriterion("customer_mobile >=", value, "customerMobile");
            return (Criteria) this;
        }

        public Criteria andCustomerMobileLessThan(String value) {
            addCriterion("customer_mobile <", value, "customerMobile");
            return (Criteria) this;
        }

        public Criteria andCustomerMobileLessThanOrEqualTo(String value) {
            addCriterion("customer_mobile <=", value, "customerMobile");
            return (Criteria) this;
        }

        public Criteria andCustomerMobileLike(String value) {
            addCriterion("customer_mobile like", value, "customerMobile");
            return (Criteria) this;
        }

        public Criteria andCustomerMobileNotLike(String value) {
            addCriterion("customer_mobile not like", value, "customerMobile");
            return (Criteria) this;
        }

        public Criteria andCustomerMobileIn(List<String> values) {
            addCriterion("customer_mobile in", values, "customerMobile");
            return (Criteria) this;
        }

        public Criteria andCustomerMobileNotIn(List<String> values) {
            addCriterion("customer_mobile not in", values, "customerMobile");
            return (Criteria) this;
        }

        public Criteria andCustomerMobileBetween(String value1, String value2) {
            addCriterion("customer_mobile between", value1, value2, "customerMobile");
            return (Criteria) this;
        }

        public Criteria andCustomerMobileNotBetween(String value1, String value2) {
            addCriterion("customer_mobile not between", value1, value2, "customerMobile");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andCustomerIdcardIsNull() {
            addCriterion("customer_idcard is null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdcardIsNotNull() {
            addCriterion("customer_idcard is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdcardEqualTo(String value) {
            addCriterion("customer_idcard =", value, "customerIdcard");
            return (Criteria) this;
        }

        public Criteria andCustomerIdcardNotEqualTo(String value) {
            addCriterion("customer_idcard <>", value, "customerIdcard");
            return (Criteria) this;
        }

        public Criteria andCustomerIdcardGreaterThan(String value) {
            addCriterion("customer_idcard >", value, "customerIdcard");
            return (Criteria) this;
        }

        public Criteria andCustomerIdcardGreaterThanOrEqualTo(String value) {
            addCriterion("customer_idcard >=", value, "customerIdcard");
            return (Criteria) this;
        }

        public Criteria andCustomerIdcardLessThan(String value) {
            addCriterion("customer_idcard <", value, "customerIdcard");
            return (Criteria) this;
        }

        public Criteria andCustomerIdcardLessThanOrEqualTo(String value) {
            addCriterion("customer_idcard <=", value, "customerIdcard");
            return (Criteria) this;
        }

        public Criteria andCustomerIdcardLike(String value) {
            addCriterion("customer_idcard like", value, "customerIdcard");
            return (Criteria) this;
        }

        public Criteria andCustomerIdcardNotLike(String value) {
            addCriterion("customer_idcard not like", value, "customerIdcard");
            return (Criteria) this;
        }

        public Criteria andCustomerIdcardIn(List<String> values) {
            addCriterion("customer_idcard in", values, "customerIdcard");
            return (Criteria) this;
        }

        public Criteria andCustomerIdcardNotIn(List<String> values) {
            addCriterion("customer_idcard not in", values, "customerIdcard");
            return (Criteria) this;
        }

        public Criteria andCustomerIdcardBetween(String value1, String value2) {
            addCriterion("customer_idcard between", value1, value2, "customerIdcard");
            return (Criteria) this;
        }

        public Criteria andCustomerIdcardNotBetween(String value1, String value2) {
            addCriterion("customer_idcard not between", value1, value2, "customerIdcard");
            return (Criteria) this;
        }

        public Criteria andTicketIdIsNull() {
            addCriterion("ticket_id is null");
            return (Criteria) this;
        }

        public Criteria andTicketIdIsNotNull() {
            addCriterion("ticket_id is not null");
            return (Criteria) this;
        }

        public Criteria andTicketIdEqualTo(Integer value) {
            addCriterion("ticket_id =", value, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketIdNotEqualTo(Integer value) {
            addCriterion("ticket_id <>", value, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketIdGreaterThan(Integer value) {
            addCriterion("ticket_id >", value, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ticket_id >=", value, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketIdLessThan(Integer value) {
            addCriterion("ticket_id <", value, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketIdLessThanOrEqualTo(Integer value) {
            addCriterion("ticket_id <=", value, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketIdIn(List<Integer> values) {
            addCriterion("ticket_id in", values, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketIdNotIn(List<Integer> values) {
            addCriterion("ticket_id not in", values, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketIdBetween(Integer value1, Integer value2) {
            addCriterion("ticket_id between", value1, value2, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ticket_id not between", value1, value2, "ticketId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdcardPhotoIsNull() {
            addCriterion("customer_idcard_photo is null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdcardPhotoIsNotNull() {
            addCriterion("customer_idcard_photo is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdcardPhotoEqualTo(String value) {
            addCriterion("customer_idcard_photo =", value, "customerIdcardPhoto");
            return (Criteria) this;
        }

        public Criteria andCustomerIdcardPhotoNotEqualTo(String value) {
            addCriterion("customer_idcard_photo <>", value, "customerIdcardPhoto");
            return (Criteria) this;
        }

        public Criteria andCustomerIdcardPhotoGreaterThan(String value) {
            addCriterion("customer_idcard_photo >", value, "customerIdcardPhoto");
            return (Criteria) this;
        }

        public Criteria andCustomerIdcardPhotoGreaterThanOrEqualTo(String value) {
            addCriterion("customer_idcard_photo >=", value, "customerIdcardPhoto");
            return (Criteria) this;
        }

        public Criteria andCustomerIdcardPhotoLessThan(String value) {
            addCriterion("customer_idcard_photo <", value, "customerIdcardPhoto");
            return (Criteria) this;
        }

        public Criteria andCustomerIdcardPhotoLessThanOrEqualTo(String value) {
            addCriterion("customer_idcard_photo <=", value, "customerIdcardPhoto");
            return (Criteria) this;
        }

        public Criteria andCustomerIdcardPhotoLike(String value) {
            addCriterion("customer_idcard_photo like", value, "customerIdcardPhoto");
            return (Criteria) this;
        }

        public Criteria andCustomerIdcardPhotoNotLike(String value) {
            addCriterion("customer_idcard_photo not like", value, "customerIdcardPhoto");
            return (Criteria) this;
        }

        public Criteria andCustomerIdcardPhotoIn(List<String> values) {
            addCriterion("customer_idcard_photo in", values, "customerIdcardPhoto");
            return (Criteria) this;
        }

        public Criteria andCustomerIdcardPhotoNotIn(List<String> values) {
            addCriterion("customer_idcard_photo not in", values, "customerIdcardPhoto");
            return (Criteria) this;
        }

        public Criteria andCustomerIdcardPhotoBetween(String value1, String value2) {
            addCriterion("customer_idcard_photo between", value1, value2, "customerIdcardPhoto");
            return (Criteria) this;
        }

        public Criteria andCustomerIdcardPhotoNotBetween(String value1, String value2) {
            addCriterion("customer_idcard_photo not between", value1, value2, "customerIdcardPhoto");
            return (Criteria) this;
        }

        public Criteria andCustomerIdcardPhotoBackIsNull() {
            addCriterion("customer_idcard_photo_back is null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdcardPhotoBackIsNotNull() {
            addCriterion("customer_idcard_photo_back is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdcardPhotoBackEqualTo(String value) {
            addCriterion("customer_idcard_photo_back =", value, "customerIdcardPhotoBack");
            return (Criteria) this;
        }

        public Criteria andCustomerIdcardPhotoBackNotEqualTo(String value) {
            addCriterion("customer_idcard_photo_back <>", value, "customerIdcardPhotoBack");
            return (Criteria) this;
        }

        public Criteria andCustomerIdcardPhotoBackGreaterThan(String value) {
            addCriterion("customer_idcard_photo_back >", value, "customerIdcardPhotoBack");
            return (Criteria) this;
        }

        public Criteria andCustomerIdcardPhotoBackGreaterThanOrEqualTo(String value) {
            addCriterion("customer_idcard_photo_back >=", value, "customerIdcardPhotoBack");
            return (Criteria) this;
        }

        public Criteria andCustomerIdcardPhotoBackLessThan(String value) {
            addCriterion("customer_idcard_photo_back <", value, "customerIdcardPhotoBack");
            return (Criteria) this;
        }

        public Criteria andCustomerIdcardPhotoBackLessThanOrEqualTo(String value) {
            addCriterion("customer_idcard_photo_back <=", value, "customerIdcardPhotoBack");
            return (Criteria) this;
        }

        public Criteria andCustomerIdcardPhotoBackLike(String value) {
            addCriterion("customer_idcard_photo_back like", value, "customerIdcardPhotoBack");
            return (Criteria) this;
        }

        public Criteria andCustomerIdcardPhotoBackNotLike(String value) {
            addCriterion("customer_idcard_photo_back not like", value, "customerIdcardPhotoBack");
            return (Criteria) this;
        }

        public Criteria andCustomerIdcardPhotoBackIn(List<String> values) {
            addCriterion("customer_idcard_photo_back in", values, "customerIdcardPhotoBack");
            return (Criteria) this;
        }

        public Criteria andCustomerIdcardPhotoBackNotIn(List<String> values) {
            addCriterion("customer_idcard_photo_back not in", values, "customerIdcardPhotoBack");
            return (Criteria) this;
        }

        public Criteria andCustomerIdcardPhotoBackBetween(String value1, String value2) {
            addCriterion("customer_idcard_photo_back between", value1, value2, "customerIdcardPhotoBack");
            return (Criteria) this;
        }

        public Criteria andCustomerIdcardPhotoBackNotBetween(String value1, String value2) {
            addCriterion("customer_idcard_photo_back not between", value1, value2, "customerIdcardPhotoBack");
            return (Criteria) this;
        }

        public Criteria andCustomerPhotoIsNull() {
            addCriterion("customer_photo is null");
            return (Criteria) this;
        }

        public Criteria andCustomerPhotoIsNotNull() {
            addCriterion("customer_photo is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerPhotoEqualTo(String value) {
            addCriterion("customer_photo =", value, "customerPhoto");
            return (Criteria) this;
        }

        public Criteria andCustomerPhotoNotEqualTo(String value) {
            addCriterion("customer_photo <>", value, "customerPhoto");
            return (Criteria) this;
        }

        public Criteria andCustomerPhotoGreaterThan(String value) {
            addCriterion("customer_photo >", value, "customerPhoto");
            return (Criteria) this;
        }

        public Criteria andCustomerPhotoGreaterThanOrEqualTo(String value) {
            addCriterion("customer_photo >=", value, "customerPhoto");
            return (Criteria) this;
        }

        public Criteria andCustomerPhotoLessThan(String value) {
            addCriterion("customer_photo <", value, "customerPhoto");
            return (Criteria) this;
        }

        public Criteria andCustomerPhotoLessThanOrEqualTo(String value) {
            addCriterion("customer_photo <=", value, "customerPhoto");
            return (Criteria) this;
        }

        public Criteria andCustomerPhotoLike(String value) {
            addCriterion("customer_photo like", value, "customerPhoto");
            return (Criteria) this;
        }

        public Criteria andCustomerPhotoNotLike(String value) {
            addCriterion("customer_photo not like", value, "customerPhoto");
            return (Criteria) this;
        }

        public Criteria andCustomerPhotoIn(List<String> values) {
            addCriterion("customer_photo in", values, "customerPhoto");
            return (Criteria) this;
        }

        public Criteria andCustomerPhotoNotIn(List<String> values) {
            addCriterion("customer_photo not in", values, "customerPhoto");
            return (Criteria) this;
        }

        public Criteria andCustomerPhotoBetween(String value1, String value2) {
            addCriterion("customer_photo between", value1, value2, "customerPhoto");
            return (Criteria) this;
        }

        public Criteria andCustomerPhotoNotBetween(String value1, String value2) {
            addCriterion("customer_photo not between", value1, value2, "customerPhoto");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    /**
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}