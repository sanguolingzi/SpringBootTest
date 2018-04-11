package com.yinhetianze.business.customer.mapper.info;

import com.yinhetianze.business.customer.model.BusiCustomerBankrollFlowModel;
import com.yinhetianze.mybatis.mapper.InfoMapper;
import com.yinhetianze.pojo.customer.BusiCustomerBankrollFlowPojo;

import java.util.List;

public interface BusiCustomerBankrollFlowInfoMapper extends InfoMapper<BusiCustomerBankrollFlowPojo> {
    List<BusiCustomerBankrollFlowModel> selectList(BusiCustomerBankrollFlowModel busiCustomerBankrollFlowModel);
}