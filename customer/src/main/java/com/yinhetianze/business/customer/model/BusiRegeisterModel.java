package com.yinhetianze.business.customer.model;

import com.yinhetianze.core.business.httprequest.BasicModel;

/**
 *   消费者注册  model
 */
public class BusiRegeisterModel extends BasicModel {


    /**
     * 联系电话
     */
    private String phone;

    /**
     * 登录密码
     */
    private String loginPassword;

    /**
     * 短信验证码
     */
    private String smsCode;

    /**
     *  推荐人手机号
     */
    private String recommendPhone;

    /**
     * 校验类型  1 手机浏览器  2 微信 3 app 4 pc
     */
    private String clientType = "1";

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getRecommendPhone() {
        return recommendPhone;
    }

    public void setRecommendPhone(String recommendPhone) {
        this.recommendPhone = recommendPhone;
    }
}