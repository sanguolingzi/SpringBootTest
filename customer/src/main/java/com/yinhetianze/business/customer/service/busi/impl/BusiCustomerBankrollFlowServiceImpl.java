package com.yinhetianze.business.customer.service.busi.impl;

import com.yinhetianze.business.customer.mapper.busi.BusiCustomerBankrollFlowMapper;
import com.yinhetianze.business.customer.service.busi.BusiCustomerBankrollFlowService;
import com.yinhetianze.pojo.customer.BusiCustomerBankrollFlowPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BusiCustomerBankrollFlowServiceImpl implements BusiCustomerBankrollFlowService {

    @Autowired
    private BusiCustomerBankrollFlowMapper busiCustomerBankrollFlowMapper;

    @Override
    public int insert(BusiCustomerBankrollFlowPojo record) {
        return busiCustomerBankrollFlowMapper.insert(record);
    }

    @Override
    public int insertSelective(BusiCustomerBankrollFlowPojo record) {
        return busiCustomerBankrollFlowMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(BusiCustomerBankrollFlowPojo record) {
        return busiCustomerBankrollFlowMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(BusiCustomerBankrollFlowPojo record) {
        return busiCustomerBankrollFlowMapper.updateByPrimaryKey(record);
    }
}