package com.yinhetianze.business.customer.service.info.impl;

import com.yinhetianze.business.customer.mapper.info.BusiCustomerRecommendRelationInfoMapper;
import com.yinhetianze.business.customer.service.info.BusiCustomerRecommendRelationInfoService;
import com.yinhetianze.pojo.customer.BusiCustomerPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusiCustomerRecommendRelationInfoServiceImpl implements BusiCustomerRecommendRelationInfoService
{
    @Autowired
    private BusiCustomerRecommendRelationInfoMapper busiCustomerRecommendRelationInfoMapper;

    @Override
    public List<BusiCustomerPojo> selectRecommendRelationList(Integer customerId) {
        return busiCustomerRecommendRelationInfoMapper.selectRecommendRelationList(customerId);
    }
}