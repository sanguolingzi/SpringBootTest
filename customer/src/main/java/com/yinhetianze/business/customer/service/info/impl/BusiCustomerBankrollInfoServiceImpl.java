package com.yinhetianze.business.customer.service.info.impl;

import com.yinhetianze.business.customer.mapper.info.BusiCustomerBankrollInfoMapper;
import com.yinhetianze.business.customer.service.info.BusiCustomerBankrollInfoService;
import com.yinhetianze.pojo.customer.BusiCustomerBankrollPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusiCustomerBankrollInfoServiceImpl implements BusiCustomerBankrollInfoService {

    @Autowired
    private BusiCustomerBankrollInfoMapper busiCustomerBankrollInfoMapper;


    @Override
    public BusiCustomerBankrollPojo selectOne(BusiCustomerBankrollPojo record) {
        record.setDelFlag((short)0);
        return busiCustomerBankrollInfoMapper.selectOne(record);
    }

    @Override
    public BusiCustomerBankrollPojo selectByCustomerId(Integer customerId) {
        return busiCustomerBankrollInfoMapper.selectByCustomerId(customerId);
    }
}