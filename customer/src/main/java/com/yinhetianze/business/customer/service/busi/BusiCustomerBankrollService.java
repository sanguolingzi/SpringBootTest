package com.yinhetianze.business.customer.service.busi;

import com.yinhetianze.business.customer.model.BusiUpdateBankrollModel;
import com.yinhetianze.core.business.BusinessException;
import com.yinhetianze.pojo.customer.BusiCustomerBankrollPojo;

public interface BusiCustomerBankrollService
{

    int insert(BusiCustomerBankrollPojo record);

    int insertSelective(BusiCustomerBankrollPojo record);

    int updateByPrimaryKeySelective(BusiCustomerBankrollPojo record);

    int updateByPrimaryKey(BusiCustomerBankrollPojo record);

    int add(BusiCustomerBankrollPojo busiCustomerBankrollPojo);

    String updateBankrollForOrder(BusiUpdateBankrollModel busiUpdateBankrollModel) throws BusinessException;

}