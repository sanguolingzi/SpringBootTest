package com.yinhetianze.pojo.customer;

import javax.persistence.*;

@Table(name = "busi_customer_receiveaddress")
public class BusiCustomerReceiveaddressPojo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 联系电话
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 城市
     */
    @Column(name = "city")
    private String city;

    /**
     * 乡镇
     */
    @Column(name = "area_county")
    private String areaCounty;

    /**
     * 详细地址
     */
    @Column(name = "address")
    private String address;

    /**
     * 邮政编码
     */
    @Column(name = "post_code")
    private String postCode;

    /**
     * 消费者id  customer_id
     */
    @Column(name = "customer_id")
    private Integer customerId;

    /**
     * 0 正常 1 已删除
     */
    @Column(name = "del_flag")
    private Short delFlag;

    /**
     * 是否默认收货地址 0 是 1 不是
     */
    @Column(name = "default_status")
    private Short defaultStatus;

    /**
     * 州省
     */
    @Column(name = "region_location")
    private String regionLocation;

    /**
     * 收货人姓名
     */
    @Column(name = "receive_name")
    private String receiveName;
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
     * 获取联系电话
     *
     * @return phone - 联系电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置联系电话
     *
     * @param phone 联系电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
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
     * 获取乡镇
     *
     * @return area_county - 乡镇
     */
    public String getAreaCounty() {
        return areaCounty;
    }

    /**
     * 设置乡镇
     *
     * @param areaCounty 乡镇
     */
    public void setAreaCounty(String areaCounty) {
        this.areaCounty = areaCounty;
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
     * 获取邮政编码
     *
     * @return post_code - 邮政编码
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * 设置邮政编码
     *
     * @param postCode 邮政编码
     */
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    /**
     * 获取消费者id  customer_id
     *
     * @return customer_id - 消费者id  customer_id
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * 设置消费者id  customer_id
     *
     * @param customerId 消费者id  customer_id
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * 获取0 正常 1 已删除
     *
     * @return del_flag - 0 正常 1 已删除
     */
    public Short getDelFlag() {
        return delFlag;
    }

    /**
     * 设置0 正常 1 已删除
     *
     * @param delFlag 0 正常 1 已删除
     */
    public void setDelFlag(Short delFlag) {
        this.delFlag = delFlag;
    }

    public Short getDefaultStatus() {
        return defaultStatus;
    }

    public void setDefaultStatus(Short defaultStatus) {
        this.defaultStatus = defaultStatus;
    }

    public String getRegionLocation() {
        return regionLocation;
    }

    public void setRegionLocation(String regionLocation) {
        this.regionLocation = regionLocation;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }
}