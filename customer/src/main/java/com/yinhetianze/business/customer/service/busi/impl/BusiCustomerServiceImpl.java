package com.yinhetianze.business.customer.service.busi.impl;

import com.yinhetianze.business.customer.mapper.busi.BusiCustomerMapper;
import com.yinhetianze.business.customer.model.BusiRegeisterModel;
import com.yinhetianze.business.customer.service.busi.BusiCustomerBankrollService;
import com.yinhetianze.business.customer.service.busi.BusiCustomerRecommendRelationService;
import com.yinhetianze.business.customer.service.busi.BusiCustomerService;
import com.yinhetianze.business.customer.service.info.BusiCustomerInfoService;
import com.yinhetianze.business.customer.util.CustomerUtil;
import com.yinhetianze.core.business.BusinessException;
import com.yinhetianze.core.business.httpresponse.ResponseConstant;
import com.yinhetianze.pojo.customer.BusiCustomerBankrollPojo;
import com.yinhetianze.pojo.customer.BusiCustomerPojo;
import com.yinhetianze.pojo.customer.BusiCustomerRecommendRelationPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BusiCustomerServiceImpl implements BusiCustomerService
{
    @Autowired
    private BusiCustomerMapper busiCustomerMapper;

    @Autowired
    private BusiCustomerBankrollService busiCustomerBankrollServiceImpl;

    @Autowired
    private BusiCustomerInfoService busiCustomerInfoServiceImpl;

    @Autowired
    private BusiCustomerRecommendRelationService busiCustomerRecommendRelationServiceImpl;




    @Override
    public int insert(BusiCustomerPojo record) {
        return busiCustomerMapper.insert(record);
    }

    @Override
    public int insertSelective(BusiCustomerPojo record) {
        return busiCustomerMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(BusiCustomerPojo record){
        return busiCustomerMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(BusiCustomerPojo record) {
        return busiCustomerMapper.updateByPrimaryKey(record);
    }

    @Override
    public int addCustomerInfo(BusiCustomerPojo busiCustomerPojo)   throws BusinessException {
        /**
         * 登陆密码进行2次md5加密
         */
        if(busiCustomerPojo.getLoginPassword() != null){
            String loginPassword = busiCustomerPojo.getLoginPassword();
            busiCustomerPojo.setLoginPassword(CustomerUtil.createPassword(loginPassword));
        }

        int addCustomer = insertSelective(busiCustomerPojo);
        BusiCustomerBankrollPojo busiCustomerBankrollPojo = new BusiCustomerBankrollPojo();
        busiCustomerBankrollPojo.setCustomerId(busiCustomerPojo.getId());
        int addBankroll = busiCustomerBankrollServiceImpl.add(busiCustomerBankrollPojo);
        if((addCustomer & addBankroll) != 1)
            throw new BusinessException("BC0050", ResponseConstant.RESULT_DESCRIPTION_FAILED);

        return 1;
    }


    @Override
    public int addRegeisterCustomer(BusiRegeisterModel busiRegeisterModel) throws BusinessException {

        BusiCustomerPojo busiCustomerPojo = new BusiCustomerPojo();
        busiCustomerPojo.setPhone(busiRegeisterModel.getPhone());
        busiCustomerPojo.setLoginPassword(busiRegeisterModel.getLoginPassword());
        addCustomerInfo(busiCustomerPojo);


        //推荐人号码存在 则添加推荐人关系
        if(busiRegeisterModel.getRecommendPhone() != null){
            BusiCustomerPojo temp = busiCustomerInfoServiceImpl.selectByPhone(busiRegeisterModel.getRecommendPhone());
            //推荐人
            BusiCustomerRecommendRelationPojo busiCustomerRecommendRelationPojo = new BusiCustomerRecommendRelationPojo();
            //被推荐人 也就是刚刚新增的customer
            busiCustomerRecommendRelationPojo.setRecomCustomerId(temp.getId());
            busiCustomerRecommendRelationPojo.setRecomedCustomerId(busiCustomerPojo.getId());
            busiCustomerRecommendRelationServiceImpl.insertSelective(busiCustomerRecommendRelationPojo);
        }

        return 1;
    }
}