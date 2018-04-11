package com.yinhetianze.pojo.shop;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "busi_shop")
public class BusiShopPojo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 店铺名称
     */
    @Column(name = "shop_name")
    private String shopName;

    /**
     * 州省
     */
    @Column(name = "region_location")
    private String regionLocation;

    /**
     * 城市
     */
    private String city;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 店铺二维码
     */
    @Column(name = "shop_qrcode")
    private String shopQrcode;

    /**
     * 店铺LOGO
     */
    @Column(name = "shop_logo")
    private String shopLogo;

    /**
     * 店铺简介
     */
    @Column(name = "shop_memo")
    private String shopMemo;

    /**
     * 店铺描述
     */
    @Column(name = "shop_description")
    private String shopDescription;

    /**
     * 身份证号
     */
    @Column(name = "id_card")
    private String idCard;

    /**
     * 身份证正面
     */
    @Column(name = "id_card_p")
    private String idCardP;

    /**
     * 身份证反面
     */
    @Column(name = "id_card_n")
    private String idCardN;

    /**
     * 0 审核通过 1 审核不通过  2待审核
     */
    @Column(name = "audit_status")
    private Short auditStatus;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 线下店铺支付二维码
     */
    @Column(name = "line_pay_qrcode")
    private String linePayQrcode;

    /**
     * 邮费
     */
    private BigDecimal postage;

    /**
     * 0 通过  1 未通过 2 待审核
     */
    @Column(name = "company_audit")
    private Short companyAudit;

    /**
     * 开户行
     */
    @Column(name = "bank_number")
    private String bankNumber;

    /**
     * 账号
     */
    private String account;

    /**
     * 0 已激活  1 未激活
     */
    @Column(name = "is_member")
    private Short isMember;

    /**
     * 所属消费者/会员
     */
    @Column(name = "customer_id")
    private Integer customerId;

    /**
     * 0 线上店铺 1 线下店铺
     */
    @Column(name = "shop_type")
    private Short shopType;

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
     * 获取店铺名称
     *
     * @return shop_name - 店铺名称
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * 设置店铺名称
     *
     * @param shopName 店铺名称
     */
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    /**
     * 获取州省
     *
     * @return region_location - 州省
     */
    public String getRegionLocation() {
        return regionLocation;
    }

    /**
     * 设置州省
     *
     * @param regionLocation 州省
     */
    public void setRegionLocation(String regionLocation) {
        this.regionLocation = regionLocation;
    }

    /**
     * 获取城市
     *
     * @return city - 城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置城市
     *
     * @param city 城市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取详细地址
     *
     * @return address - 详细地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置详细地址
     *
     * @param address 详细地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取店铺二维码
     *
     * @return shop_qrcode - 店铺二维码
     */
    public String getShopQrcode() {
        return shopQrcode;
    }

    /**
     * 设置店铺二维码
     *
     * @param shopQrcode 店铺二维码
     */
    public void setShopQrcode(String shopQrcode) {
        this.shopQrcode = shopQrcode;
    }

    /**
     * 获取店铺LOGO
     *
     * @return shop_logo - 店铺LOGO
     */
    public String getShopLogo() {
        return shopLogo;
    }

    /**
     * 设置店铺LOGO
     *
     * @param shopLogo 店铺LOGO
     */
    public void setShopLogo(String shopLogo) {
        this.shopLogo = shopLogo;
    }

    /**
     * 获取店铺简介
     *
     * @return shop_memo - 店铺简介
     */
    public String getShopMemo() {
        return shopMemo;
    }

    /**
     * 设置店铺简介
     *
     * @param shopMemo 店铺简介
     */
    public void setShopMemo(String shopMemo) {
        this.shopMemo = shopMemo;
    }

    /**
     * 获取店铺描述
     *
     * @return shop_description - 店铺描述
     */
    public String getShopDescription() {
        return shopDescription;
    }

    /**
     * 设置店铺描述
     *
     * @param shopDescription 店铺描述
     */
    public void setShopDescription(String shopDescription) {
        this.shopDescription = shopDescription;
    }

    /**
     * 获取身份证号
     *
     * @return id_card - 身份证号
     */
    public String getIdCard() {
        return idCard;
    }

    /**
     * 设置身份证号
     *
     * @param idCard 身份证号
     */
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    /**
     * 获取身份证正面
     *
     * @return id_card_p - 身份证正面
     */
    public String getIdCardP() {
        return idCardP;
    }

    /**
     * 设置身份证正面
     *
     * @param idCardP 身份证正面
     */
    public void setIdCardP(String idCardP) {
        this.idCardP = idCardP;
    }

    /**
     * 获取身份证反面
     *
     * @return id_card_n - 身份证反面
     */
    public String getIdCardN() {
        return idCardN;
    }

    /**
     * 设置身份证反面
     *
     * @param idCardN 身份证反面
     */
    public void setIdCardN(String idCardN) {
        this.idCardN = idCardN;
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
     * 获取线下店铺支付二维码
     *
     * @return line_pay_qrcode - 线下店铺支付二维码
     */
    public String getLinePayQrcode() {
        return linePayQrcode;
    }

    /**
     * 设置线下店铺支付二维码
     *
     * @param linePayQrcode 线下店铺支付二维码
     */
    public void setLinePayQrcode(String linePayQrcode) {
        this.linePayQrcode = linePayQrcode;
    }

    /**
     * 获取邮费
     *
     * @return postage - 邮费
     */
    public BigDecimal getPostage() {
        return postage;
    }

    /**
     * 设置邮费
     *
     * @param postage 邮费
     */
    public void setPostage(BigDecimal postage) {
        this.postage = postage;
    }

    /**
     * 获取0 通过  1 未通过 2 待审核
     *
     * @return company_audit - 0 通过  1 未通过 2 待审核
     */
    public Short getCompanyAudit() {
        return companyAudit;
    }

    /**
     * 设置0 通过  1 未通过 2 待审核
     *
     * @param companyAudit 0 通过  1 未通过 2 待审核
     */
    public void setCompanyAudit(Short companyAudit) {
        this.companyAudit = companyAudit;
    }

    /**
     * 获取开户行
     *
     * @return bank_number - 开户行
     */
    public String getBankNumber() {
        return bankNumber;
    }

    /**
     * 设置开户行
     *
     * @param bankNumber 开户行
     */
    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    /**
     * 获取账号
     *
     * @return account - 账号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置账号
     *
     * @param account 账号
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取0 已激活  1 未激活
     *
     * @return is_member - 0 已激活  1 未激活
     */
    public Short getIsMember() {
        return isMember;
    }

    /**
     * 设置0 已激活  1 未激活
     *
     * @param isMember 0 已激活  1 未激活
     */
    public void setIsMember(Short isMember) {
        this.isMember = isMember;
    }

    /**
     * 获取所属消费者/会员
     *
     * @return customer_id - 所属消费者/会员
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * 设置所属消费者/会员
     *
     * @param customerId 所属消费者/会员
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * 获取0 线上店铺 1 线下店铺
     *
     * @return shop_type - 0 线上店铺 1 线下店铺
     */
    public Short getShopType() {
        return shopType;
    }

    /**
     * 设置0 线上店铺 1 线下店铺
     *
     * @param shopType 0 线上店铺 1 线下店铺
     */
    public void setShopType(Short shopType) {
        this.shopType = shopType;
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
}