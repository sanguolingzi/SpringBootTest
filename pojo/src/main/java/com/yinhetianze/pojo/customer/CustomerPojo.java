package com.yinhetianze.pojo.customer;

import com.yinhetianze.pojo.BasicPojo;

import javax.persistence.Table;

/**
 * Created by Administrator
 * on 2018/1/26.
 */
@Table(name="tb_cust_basic_info")
public class CustomerPojo extends BasicPojo
{
    private String customerId;

    private String customerCode;

    private String nickName;

    public String getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId(String customerId)
    {
        this.customerId = customerId;
    }

    public String getCustomerCode()
    {
        return customerCode;
    }

    public void setCustomerCode(String customerCode)
    {
        this.customerCode = customerCode;
    }

    public String getNickName()
    {
        return nickName;
    }

    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }
}
