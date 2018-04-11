package com.yinhetianze.business.customer.service.info;

import com.yinhetianze.pojo.customer.BusiCustomerBankrollPojo;

public interface BusiCustomerBankrollInfoService
{

    BusiCustomerBankrollPojo selectOne(BusiCustomerBankrollPojo record);



    /**
     * 根据消费者Id 查询关联账户
     * @param customerId
     * @return
     */
    BusiCustomerBankrollPojo selectByCustomerId(Integer customerId);

}