package com.yinhetianze.business.customer.model;

import com.yinhetianze.core.business.httprequest.BasicModel;

/**
 *   校验手机号状态model
 */
public class BusiCheckPhoneStatusModel extends BasicModel {


    /**
     * 联系电话
     */
    private String phone;

    /**
     * 校验类型  1 是否已注册 2 是否合作商手机号码
     */
    private String type = "1";

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}