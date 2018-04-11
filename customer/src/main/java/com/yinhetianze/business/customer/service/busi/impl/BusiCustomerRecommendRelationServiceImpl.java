package com.yinhetianze.business.customer.service.busi.impl;

import com.yinhetianze.business.customer.mapper.busi.BusiCustomerRecommendRelationMapper;
import com.yinhetianze.business.customer.service.busi.BusiCustomerRecommendRelationService;
import com.yinhetianze.pojo.customer.BusiCustomerRecommendRelationPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusiCustomerRecommendRelationServiceImpl implements BusiCustomerRecommendRelationService
{
    @Autowired
    private BusiCustomerRecommendRelationMapper busiCustomerRecommendRelationMapper;

    @Override
    public int insertSelective(BusiCustomerRecommendRelationPojo record) {
        return busiCustomerRecommendRelationMapper.insertSelective(record);
    }
}