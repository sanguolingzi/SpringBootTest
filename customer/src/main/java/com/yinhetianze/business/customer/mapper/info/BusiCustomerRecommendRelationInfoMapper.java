package com.yinhetianze.business.customer.mapper.info;

import com.yinhetianze.mybatis.mapper.InfoMapper;
import com.yinhetianze.pojo.customer.BusiCustomerPojo;
import com.yinhetianze.pojo.customer.BusiCustomerRecommendRelationPojo;

import java.util.List;

public interface BusiCustomerRecommendRelationInfoMapper extends InfoMapper<BusiCustomerRecommendRelationPojo> {

    List<BusiCustomerPojo> selectRecommendRelationList(Integer recommendId);
}