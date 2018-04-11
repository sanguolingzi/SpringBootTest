package com.yinhetianze.business.customer.model;

import com.yinhetianze.core.business.httprequest.PageModel;

import java.math.BigDecimal;
import java.util.Date;

public class BusiCustomerBankrollFlowModel extends PageModel {
    private Integer id;

    /**
     * 1 提现 2 推荐奖励 3 余额 4 积分
     */
    private Short flowCategory;

    /**
     * 0 收入 1 支出
     */
    private Short flowType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 流水号
     */
    private String flowNumber;

    private Short flowDescription;

    /**
     * 所属账户id
     */
    private Integer bankrollId;

    /**
     * 所属消费者
     */
    private Integer customerId;

    /**
     * 关联数据id
     */
    private Integer relationId;
    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取1 提现
     *
     * @return flow_category - 1 提现
     */
    public Short getFlowCategory() {
        return flowCategory;
    }

    /**
     * 设置1 提现
     *
     * @param flowCategory 1 提现
     */
    public void setFlowCategory(Short flowCategory) {
        this.flowCategory = flowCategory;
    }

    /**
     * 获取0 收入 1 支出
     *
     * @return flow_type - 0 收入 1 支出
     */
    public Short getFlowType() {
        return flowType;
    }

    /**
     * 设置0 收入 1 支出
     *
     * @param flowType 0 收入 1 支出
     */
    public void setFlowType(Short flowType) {
        this.flowType = flowType;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取金额
     *
     * @return amount - 金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置金额
     *
     * @param amount 金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取流水号
     *
     * @return flow_number - 流水号
     */
    public String getFlowNumber() {
        return flowNumber;
    }

    /**
     * 设置流水号
     *
     * @param flowNumber 流水号
     */
    public void setFlowNumber(String flowNumber) {
        this.flowNumber = flowNumber;
    }

    /**
     * @return flow_description
     */
    public Short getFlowDescription() {
        return flowDescription;
    }

    /**
     * @param flowDescription
     */
    public void setFlowDescription(Short flowDescription) {
        this.flowDescription = flowDescription;
    }

    /**
     * 获取所属账户id
     *
     * @return bankroll_id - 所属账户id
     */
    public Integer getBankrollId() {
        return bankrollId;
    }

    /**
     * 设置所属账户id
     *
     * @param bankrollId 所属账户id
     */
    public void setBankrollId(Integer bankrollId) {
        this.bankrollId = bankrollId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getRelationId() {
        return relationId;
    }

    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
    }
}