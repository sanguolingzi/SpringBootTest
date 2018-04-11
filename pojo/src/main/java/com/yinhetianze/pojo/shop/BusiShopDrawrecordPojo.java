package com.yinhetianze.pojo.shop;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "busi_shop_drawrecord")
public class BusiShopDrawrecordPojo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 提现类型 1 货款提现 
     */
    @Column(name = "draw_type")
    private Short drawType;

    /**
     * 提现金额  
     */
    @Column(name = "draw_amount")
    private BigDecimal drawAmount;

    /**
     * 提现收款账号  bank_card
     */
    @Column(name = "bank_number")
    private String bankNumber;

    /**
     * 提现申请时间  apply_time
     */
    @Column(name = "apply_time")
    private Date applyTime;

    /**
     * 0 审核通过 1 审核不通过  2待审核
     */
    @Column(name = "audit_status")
    private Short auditStatus;

    /**
     * 审核人
     */
    @Column(name = "audit_user")
    private Integer auditUser;

    /**
     * 审核时间  audit_time
     */
    @Column(name = "audit_time")
    private Date auditTime;

    /**
     * 收款人  receive_user
     */
    @Column(name = "receive_user")
    private String receiveUser;

    /**
     * 所属账户
     */
    @Column(name = "bankroll_id")
    private Integer bankrollId;

    @Column(name = "del_flag")
    private Short delFlag;

    /**
     * 提现流水号
     */
    @Column(name = "draw_number")
    private String drawNumber;

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
     * 获取提现类型 1 货款提现 
     *
     * @return draw_type - 提现类型 1 货款提现 
     */
    public Short getDrawType() {
        return drawType;
    }

    /**
     * 设置提现类型 1 货款提现 
     *
     * @param drawType 提现类型 1 货款提现 
     */
    public void setDrawType(Short drawType) {
        this.drawType = drawType;
    }

    /**
     * 获取提现金额  
     *
     * @return draw_amount - 提现金额  
     */
    public BigDecimal getDrawAmount() {
        return drawAmount;
    }

    /**
     * 设置提现金额  
     *
     * @param drawAmount 提现金额  
     */
    public void setDrawAmount(BigDecimal drawAmount) {
        this.drawAmount = drawAmount;
    }

    /**
     * 获取提现收款账号  bank_number
     *
     * @return bank_number - 提现收款账号  bank_number
     */
    public String getBankNumber() {
        return bankNumber;
    }

    /**
     * 设置提现收款账号  bank_number
     *
     * @param bankNumber 提现收款账号  bank_number
     */
    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    /**
     * 获取提现申请时间  apply_time
     *
     * @return apply_time - 提现申请时间  apply_time
     */
    public Date getApplyTime() {
        return applyTime;
    }

    /**
     * 设置提现申请时间  apply_time
     *
     * @param applyTime 提现申请时间  apply_time
     */
    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    /**
     * 获取0 审核通过 1 审核不通过  2待审核
     *
     * @return audit_status - 0 审核通过 1 审核不通过  2待审核
     */
    public Short getAuditStatus() {
        return auditStatus;
    }

    /**
     * 设置0 审核通过 1 审核不通过  2待审核
     *
     * @param auditStatus 0 审核通过 1 审核不通过  2待审核
     */
    public void setAuditStatus(Short auditStatus) {
        this.auditStatus = auditStatus;
    }

    /**
     * 获取审核人
     *
     * @return audit_user - 审核人
     */
    public Integer getAuditUser() {
        return auditUser;
    }

    /**
     * 设置审核人
     *
     * @param auditUser 审核人
     */
    public void setAuditUser(Integer auditUser) {
        this.auditUser = auditUser;
    }

    /**
     * 获取审核时间  audit_time
     *
     * @return audit_time - 审核时间  audit_time
     */
    public Date getAuditTime() {
        return auditTime;
    }

    /**
     * 设置审核时间  audit_time
     *
     * @param auditTime 审核时间  audit_time
     */
    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    /**
     * 获取收款人  receive_user
     *
     * @return receive_user - 收款人  receive_user
     */
    public String getReceiveUser() {
        return receiveUser;
    }

    /**
     * 设置收款人  receive_user
     *
     * @param receiveUser 收款人  receive_user
     */
    public void setReceiveUser(String receiveUser) {
        this.receiveUser = receiveUser;
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

    public String getDrawNumber() {
        return drawNumber;
    }

    public void setDrawNumber(String drawNumber) {
        this.drawNumber = drawNumber;
    }
}