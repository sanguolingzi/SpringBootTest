package com.yinhetianze.business.customer.mapper.info;

import com.yinhetianze.mybatis.mapper.InfoMapper;
import com.yinhetianze.pojo.customer.BusiCustomerBankrollPojo;

public interface BusiCustomerBankrollInfoMapper extends InfoMapper<BusiCustomerBankrollPojo> {

    BusiCustomerBankrollPojo selectByPrimaryKey(Integer id);

    BusiCustomerBankrollPojo selectByCustomerId(Integer customerId);
}