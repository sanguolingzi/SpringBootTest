package com.yinhetianze.business.customer.model;

import com.yinhetianze.core.business.httprequest.BasicModel;

/**
 *   短信验证码 model
 */
public class BusiSmsCodeModel extends BasicModel {


    /**
     * 联系电话
     */
    private String phone;

    /**
     * 业务类型  1 注册业务  2 修改密码
     */
    private String busiType = "1";

    /**
     * 短信验证码
     */
    private String smsCode;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }
}