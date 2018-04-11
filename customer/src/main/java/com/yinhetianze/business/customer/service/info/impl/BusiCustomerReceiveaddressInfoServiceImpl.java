package com.yinhetianze.business.customer.service.info.impl;

import com.yinhetianze.business.customer.service.info.BusiCustomerReceiveaddressInfoService;
import com.yinhetianze.pojo.customer.BusiCustomerReceiveaddressPojo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.yinhetianze.business.customer.mapper.info.BusiCustomerReceiveaddressInfoMapper;

import java.util.List;

@Service
public class BusiCustomerReceiveaddressInfoServiceImpl implements BusiCustomerReceiveaddressInfoService
{
    @Autowired
    private BusiCustomerReceiveaddressInfoMapper busiCustomerReceiveaddressInfoMapper;

    @Override
    public BusiCustomerReceiveaddressPojo selectOne(BusiCustomerReceiveaddressPojo record) {
        record.setDelFlag((short)0);
        return busiCustomerReceiveaddressInfoMapper.selectOne(record);
    }

    @Override
    public List<BusiCustomerReceiveaddressPojo> selectByCustomerId(Integer customerId) {
        return busiCustomerReceiveaddressInfoMapper.selectByCustomerId(customerId);
    }
}