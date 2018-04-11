package com.yinhetianze.business.customer.service.info;

import com.yinhetianze.pojo.customer.BusiCustomerPojo;

public interface BusiCustomerInfoService
{
     BusiCustomerPojo selectOne(BusiCustomerPojo record);

     BusiCustomerPojo selectByPhone(String phone);


}