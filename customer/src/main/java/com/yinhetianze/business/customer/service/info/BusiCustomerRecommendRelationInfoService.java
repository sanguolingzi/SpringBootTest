package com.yinhetianze.business.customer.service.info;

import com.yinhetianze.pojo.customer.BusiCustomerPojo;

import java.util.List;

public interface BusiCustomerRecommendRelationInfoService
{
    /**
     * 以推荐人为维度 查询推荐信息
     * @param customerId  推荐人id
     * @return
     */
    List<BusiCustomerPojo> selectRecommendRelationList(Integer customerId);

}