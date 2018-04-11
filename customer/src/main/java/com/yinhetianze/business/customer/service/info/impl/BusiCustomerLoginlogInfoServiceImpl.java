package com.yinhetianze.business.customer.service.info.impl;

import com.yinhetianze.business.customer.service.info.BusiCustomerLoginlogInfoService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.yinhetianze.business.customer.mapper.info.BusiCustomerLoginlogInfoMapper;

@Service
public class BusiCustomerLoginlogInfoServiceImpl implements BusiCustomerLoginlogInfoService
{
    @Autowired
    private BusiCustomerLoginlogInfoMapper mapper;
}