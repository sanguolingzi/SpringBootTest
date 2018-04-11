package com.yinhetianze.business.customer.service.busi.impl;

import com.yinhetianze.business.customer.mapper.busi.BusiCustomerReceiveaddressMapper;
import com.yinhetianze.business.customer.service.busi.BusiCustomerReceiveaddressService;
import com.yinhetianze.business.customer.service.info.BusiCustomerReceiveaddressInfoService;
import com.yinhetianze.core.business.BusinessException;
import com.yinhetianze.core.business.httpresponse.ResponseConstant;
import com.yinhetianze.core.utils.CommonUtil;
import com.yinhetianze.pojo.customer.BusiCustomerReceiveaddressPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BusiCustomerReceiveaddressServiceImpl implements BusiCustomerReceiveaddressService
{
    @Autowired
    private BusiCustomerReceiveaddressMapper busiCustomerReceiveaddressMapper;

    @Autowired
    private BusiCustomerReceiveaddressInfoService busiCustomerReceiveaddressInfoServiceImpl;

    @Override
    public int insert(BusiCustomerReceiveaddressPojo record) {
        return busiCustomerReceiveaddressMapper.insert(record);
    }

    @Override
    public int insertSelective(BusiCustomerReceiveaddressPojo record) {
        return busiCustomerReceiveaddressMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(BusiCustomerReceiveaddressPojo record){
        return busiCustomerReceiveaddressMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(BusiCustomerReceiveaddressPojo record) {
        return busiCustomerReceiveaddressMapper.updateByPrimaryKey(record);
    }


    @Override
    public int addCustomerAddress(BusiCustomerReceiveaddressPojo record) throws BusinessException {
        insertSelective(record);
        if(record.getDefaultStatus() == 0){
            updateDefaultAddress(record);
        }
        return 1;
    }

    @Override
    public int updateDefaultAddress(BusiCustomerReceiveaddressPojo record) throws BusinessException {
        //清空该customerid 下 默认收获地址标记
        List<BusiCustomerReceiveaddressPojo> busiCustomerReceiveaddressPojoList = busiCustomerReceiveaddressInfoServiceImpl.selectByCustomerId(
                record.getCustomerId());
        BusiCustomerReceiveaddressPojo temp = null;
        if(CommonUtil.isNotEmpty(busiCustomerReceiveaddressPojoList)){
            for(BusiCustomerReceiveaddressPojo pojo :busiCustomerReceiveaddressPojoList){
                if(pojo.getDefaultStatus() == 0){
                    temp = new BusiCustomerReceiveaddressPojo();
                    temp.setId(pojo.getId());
                    temp.setDefaultStatus((short)1);
                    updateByPrimaryKeySelective(temp);
                }
            }
        }

        //重新设置 这里只处理 默认收货地址这个字段
        temp = new BusiCustomerReceiveaddressPojo();
        temp.setId(record.getId());
        temp.setDefaultStatus((short)0);
        int result = updateByPrimaryKeySelective(temp);
        if(result <= 0)
            throw new BusinessException("BC0037", ResponseConstant.RESULT_DESCRIPTION_FAILED);
        return 1;
    }
}