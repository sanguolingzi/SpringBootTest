package com.yinhetianze.business.customer.service.busi;

import com.yinhetianze.core.business.BusinessException;
import com.yinhetianze.pojo.customer.BusiCustomerReceiveaddressPojo;

public interface BusiCustomerReceiveaddressService
{
    int insert(BusiCustomerReceiveaddressPojo record);

    int insertSelective(BusiCustomerReceiveaddressPojo record);

    int updateByPrimaryKeySelective(BusiCustomerReceiveaddressPojo record);

    int updateByPrimaryKey(BusiCustomerReceiveaddressPojo record);

    int updateDefaultAddress(BusiCustomerReceiveaddressPojo record) throws BusinessException;

    int addCustomerAddress(BusiCustomerReceiveaddressPojo record) throws BusinessException;
}