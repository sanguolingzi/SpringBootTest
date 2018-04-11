package com.yinhetianze.pojo.shop;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "busi_shop_bankroll_flow")
public class BusiShopBankrollFlowPojo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 1 提现
     */
    @Column(name = "flow_category")
    private Short flowCategory;

    /**
     * 0 收入 1 支出
     */
    @Column(name = "flow_type")
    private Short flowType;

    /**
     * 根据flowCategory 关联的业务数据id
     */
    @Column(name = "relation_id")
    private Integer relationId;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 流水号
     */
    @Column(name = "flow_number")
    private String flowNumber;

    @Column(name = "flow_description")
    private Short flowDescription;

    @Column(name = "del_flag")
    private Short delFlag;

    /**
     * 所属账户
     */
    @Column(name = "bankroll_id")
    private Integer bankrollId;

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
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
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
     * @return del_flag
     */
    public Short getDelFlag() {
        return delFlag;
    }

    /**
     * @param delFlag
     */
    public void setDelFlag(Short delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 获取所属账户
     *
     * @return bankroll_id - 所属账户
     */
    public Integer getBankrollId() {
        return bankrollId;
    }

    /**
     * 设置所属账户
     *
     * @param bankrollId 所属账户
     */
    public void setBankrollId(Integer bankrollId) {
        this.bankrollId = bankrollId;
    }

    public Integer getRelationId() {
        return relationId;
    }

    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
    }
}