package com.yinhetianze.business.customer.service.info.impl;

import com.yinhetianze.business.customer.mapper.info.BusiCustomerBankrollFlowInfoMapper;
import com.yinhetianze.business.customer.model.BusiCustomerBankrollFlowModel;
import com.yinhetianze.business.customer.service.info.BusiCustomerBankrollFlowInfoService;
import com.yinhetianze.pojo.customer.BusiCustomerBankrollFlowPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BusiCustomerBankrollFlowInfoServiceImpl implements BusiCustomerBankrollFlowInfoService{

    @Autowired
    private BusiCustomerBankrollFlowInfoMapper busiCustomerBankrollFlowInfoMapper;

    @Override
    public BusiCustomerBankrollFlowPojo selectOne(BusiCustomerBankrollFlowPojo record) {
        record.setDelFlag((short)0);
        return busiCustomerBankrollFlowInfoMapper.selectOne(record);
    }

    @Override
    public List<BusiCustomerBankrollFlowModel> selectList(BusiCustomerBankrollFlowModel busiCustomerBankrollFlowModel) {
        return busiCustomerBankrollFlowInfoMapper.selectList(busiCustomerBankrollFlowModel);
    }
}