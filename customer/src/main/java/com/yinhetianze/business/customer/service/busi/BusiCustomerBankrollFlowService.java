package com.yinhetianze.business.customer.service.busi;

import com.yinhetianze.pojo.customer.BusiCustomerBankrollFlowPojo;

public interface BusiCustomerBankrollFlowService {

    int insert(BusiCustomerBankrollFlowPojo record);

    int insertSelective(BusiCustomerBankrollFlowPojo record);

    int updateByPrimaryKeySelective(BusiCustomerBankrollFlowPojo record);

    int updateByPrimaryKey(BusiCustomerBankrollFlowPojo record);
}