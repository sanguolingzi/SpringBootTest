package com.yinhetianze.business.customer.mapper.info;

import com.yinhetianze.mybatis.mapper.InfoMapper;
import com.yinhetianze.pojo.customer.BusiCustomerReceiveaddressPojo;

import java.util.List;

public interface BusiCustomerReceiveaddressInfoMapper extends InfoMapper<BusiCustomerReceiveaddressPojo> {

    public List<BusiCustomerReceiveaddressPojo> selectByCustomerId(Integer customerId);
}