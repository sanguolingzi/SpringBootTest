package com.yinhetianze.business.customer.model;

import com.yinhetianze.core.business.httprequest.BasicModel;

import java.math.BigDecimal;

/**
 * 个人中心  实体
 */
public class BusiCustomerCenterModel extends BasicModel {

    private Integer id;

    /**
     * 账号
     */
    private String account;

    /**
     * 昵称
     */
    private String nickName;



    /**
     * 头像
     */
    private String photoUrl;

    /**
     * 个人二维码图片地址
     */
    private String personQrcode;

    /**
     * 星币
     */
    private BigDecimal starCoin;

    /**
     * 奖励金
     */
    private BigDecimal amount;

    /**
     * 收藏数
     */
    private String collectNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getPersonQrcode() {
        return personQrcode;
    }

    public void setPersonQrcode(String personQrcode) {
        this.personQrcode = personQrcode;
    }

    public BigDecimal getStarCoin() {
        return starCoin;
    }

    public void setStarCoin(BigDecimal starCoin) {
        this.starCoin = starCoin;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(String collectNum) {
        this.collectNum = collectNum;
    }
}