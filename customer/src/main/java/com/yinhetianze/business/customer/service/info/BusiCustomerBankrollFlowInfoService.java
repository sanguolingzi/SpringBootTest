package com.yinhetianze.business.customer.service.info;

import com.yinhetianze.business.customer.model.BusiCustomerBankrollFlowModel;
import com.yinhetianze.pojo.customer.BusiCustomerBankrollFlowPojo;

import java.util.List;

public interface BusiCustomerBankrollFlowInfoService {

    BusiCustomerBankrollFlowPojo selectOne(BusiCustomerBankrollFlowPojo record);

    List<BusiCustomerBankrollFlowModel> selectList(BusiCustomerBankrollFlowModel busiCustomerBankrollFlowModel);
}