package com.yinhetianze.pojo.shop;

import javax.persistence.*;
import java.util.Date;

@Table(name = "busi_shop_companyinfo")
public class BusiShopCompanyPojo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 公司名称  
     */
    @Column(name = "company_name")
    private String companyName;

    /**
     * 营业执照url  
     */
    @Column(name = "business_license")
    private String businessLicense;

    /**
     * 法人代表  
     */
    @Column(name = "legal_owner")
    private String legalOwner;

    /**
     * 注册时间  
     */
    @Column(name = "regeister_time")
    private Date regeisterTime;

    /**
     * 税务登记照片url  
     */
    @Column(name = "tax_regeister")
    private String taxRegeister;

    /**
     * 法人联系电话
     */
    @Column(name = "legal_owner_phone")
    private Integer legalOwnerPhone;

    /**
     * 0 审核通过 1 审核不通过  2待审核
     */
    @Column(name = "audit_status")
    private Short auditStatus;

    /**
     * 所属店铺id
     */
    @Column(name = "shop_id")
    private Integer shopId;

    @Column(name = "del_flag")
    private Short delFlag;

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
     * 获取公司名称  
     *
     * @return company_name - 公司名称  
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 设置公司名称  
     *
     * @param companyName 公司名称  
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * 获取营业执照url  
     *
     * @return business_license - 营业执照url  
     */
    public String getBusinessLicense() {
        return businessLicense;
    }

    /**
     * 设置营业执照url  
     *
     * @param businessLicense 营业执照url  
     */
    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    /**
     * 获取法人代表  
     *
     * @return legal_owner - 法人代表  
     */
    public String getLegalOwner() {
        return legalOwner;
    }

    /**
     * 设置法人代表  
     *
     * @param legalOwner 法人代表  
     */
    public void setLegalOwner(String legalOwner) {
        this.legalOwner = legalOwner;
    }

    /**
     * 获取注册时间  
     *
     * @return regeister_time - 注册时间  
     */
    public Date getRegeisterTime() {
        return regeisterTime;
    }

    /**
     * 设置注册时间  
     *
     * @param regeisterTime 注册时间  
     */
    public void setRegeisterTime(Date regeisterTime) {
        this.regeisterTime = regeisterTime;
    }

    /**
     * 获取税务登记照片url  
     *
     * @return tax_regeister - 税务登记照片url  
     */
    public String getTaxRegeister() {
        return taxRegeister;
    }

    /**
     * 设置税务登记照片url  
     *
     * @param taxRegeister 税务登记照片url  
     */
    public void setTaxRegeister(String taxRegeister) {
        this.taxRegeister = taxRegeister;
    }

    /**
     * 获取法人联系电话
     *
     * @return legal_owner_phone - 法人联系电话
     */
    public Integer getLegalOwnerPhone() {
        return legalOwnerPhone;
    }

    /**
     * 设置法人联系电话
     *
     * @param legalOwnerPhone 法人联系电话
     */
    public void setLegalOwnerPhone(Integer legalOwnerPhone) {
        this.legalOwnerPhone = legalOwnerPhone;
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
     * 获取所属店铺id
     *
     * @return shop_id - 所属店铺id
     */
    public Integer getShopId() {
        return shopId;
    }

    /**
     * 设置所属店铺id
     *
     * @param shopId 所属店铺id
     */
    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Short getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Short delFlag) {
        this.delFlag = delFlag;
    }
}