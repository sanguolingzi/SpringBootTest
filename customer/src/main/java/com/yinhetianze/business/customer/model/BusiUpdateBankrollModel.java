package com.yinhetianze.business.customer.model;

import com.yinhetianze.core.business.httprequest.BasicModel;

import java.math.BigDecimal;
import java.util.List;
/**
 *  修改账户 (积分、余额) 实体
 */
public class BusiUpdateBankrollModel extends BasicModel {

    /**
     * 消费者id
     */
    private Integer customerId;

    /**
     * 余额
     */
    private BigDecimal amount;

    /**
     * 星币
     */
    private BigDecimal starCoin;

    /**
     * 积分
     */
    private BigDecimal integral;

    /**
     * 流水相关信息集合
     */
    List<BusiCustomerBankrollFlowModel> list;

    public List<BusiCustomerBankrollFlowModel> getList() {
        return list;
    }

    public void setList(List<BusiCustomerBankrollFlowModel> list) {
        this.list = list;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getStarCoin() {
        return starCoin;
    }

    public void setStarCoin(BigDecimal starCoin) {
        this.starCoin = starCoin;
    }

    public BigDecimal getIntegral() {
        return integral;
    }

    public void setIntegral(BigDecimal integral) {
        this.integral = integral;
    }
}