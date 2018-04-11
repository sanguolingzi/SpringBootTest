package com.yinhetianze.business.customer.service.info.impl;

import com.yinhetianze.business.customer.mapper.info.BusiCustomerInfoMapper;
import com.yinhetianze.business.customer.service.info.BusiCustomerInfoService;
import com.yinhetianze.pojo.customer.BusiCustomerPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusiCustomerInfoServiceImpl implements BusiCustomerInfoService
{
    @Autowired
    private BusiCustomerInfoMapper busiCustomerInfoMapper;

    @Override
    public BusiCustomerPojo selectOne(BusiCustomerPojo record) {
        record.setDelFlag((short)0);
        return busiCustomerInfoMapper.selectOne(record);
    }


    @Override
    public BusiCustomerPojo selectByPhone(String phone) {
        BusiCustomerPojo record = new BusiCustomerPojo();
        record.setPhone(phone);
        return selectOne(record);
    }
}