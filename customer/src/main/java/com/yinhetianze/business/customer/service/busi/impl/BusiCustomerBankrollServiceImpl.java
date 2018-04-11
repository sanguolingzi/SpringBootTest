package com.yinhetianze.business.customer.service.busi.impl;

import com.yinhetianze.business.customer.mapper.busi.BusiCustomerBankrollMapper;
import com.yinhetianze.business.customer.model.BusiCustomerBankrollFlowModel;
import com.yinhetianze.business.customer.model.BusiUpdateBankrollModel;
import com.yinhetianze.business.customer.service.busi.BusiCustomerBankrollFlowService;
import com.yinhetianze.business.customer.service.busi.BusiCustomerBankrollService;
import com.yinhetianze.business.customer.service.info.BusiCustomerBankrollInfoService;
import com.yinhetianze.core.business.BusinessException;
import com.yinhetianze.pojo.customer.BusiCustomerBankrollFlowPojo;
import com.yinhetianze.pojo.customer.BusiCustomerBankrollPojo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.StringJoiner;

@Service
@Transactional
public class BusiCustomerBankrollServiceImpl implements BusiCustomerBankrollService
{
    @Autowired
    private BusiCustomerBankrollMapper busiCustomerBankrollMapper;

    @Autowired
    private BusiCustomerBankrollInfoService busiCustomerBankrollInfoServiceImpl;

    @Autowired
    private BusiCustomerBankrollFlowService busiCustomerBankrollFlowServiceImpl;

    @Value("${orderService}")
    private String orderService;

    @Override
    public int insert(BusiCustomerBankrollPojo record) {
        return busiCustomerBankrollMapper.insert(record);
    }

    @Override
    public int insertSelective(BusiCustomerBankrollPojo record) {
        return busiCustomerBankrollMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(BusiCustomerBankrollPojo record) {
        return busiCustomerBankrollMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(BusiCustomerBankrollPojo record) {
        return busiCustomerBankrollMapper.updateByPrimaryKey(record);
    }

    @Override
    public int add(BusiCustomerBankrollPojo busiCustomerBankrollPojo) {
        initData(busiCustomerBankrollPojo);
        return insertSelective(busiCustomerBankrollPojo);
    }

    @Override
    public String updateBankrollForOrder(BusiUpdateBankrollModel busiUpdateBankrollModel)
    throws BusinessException{

        StringJoiner stringJoiner = new StringJoiner(",","","");
        BusiCustomerBankrollPojo busiCustomerBankrollPojo =
                busiCustomerBankrollInfoServiceImpl.selectByCustomerId(busiUpdateBankrollModel.getCustomerId());
        if(busiUpdateBankrollModel.getAmount() != null){
           int result =  busiCustomerBankrollMapper.updateBankrollAmount(busiCustomerBankrollPojo.getId()
                    ,busiUpdateBankrollModel.getAmount(),busiCustomerBankrollPojo.getAmount(),(short)1);

           if(result <= 0)
               throw new BusinessException("BC0037", "余额修改失败");
        }

        if(busiUpdateBankrollModel.getIntegral() != null){
            int result =  busiCustomerBankrollMapper.updateBankrollIntegral(busiCustomerBankrollPojo.getId()
                    ,busiUpdateBankrollModel.getIntegral(),busiCustomerBankrollPojo.getIntegral(),(short)1);

            if(result <= 0)
                throw new BusinessException("BC0037", "积分修改失败");
        }

        if(busiUpdateBankrollModel.getStarCoin() != null){
            int result =  busiCustomerBankrollMapper.updateBankrollStarCoin(busiCustomerBankrollPojo.getId()
                    ,busiUpdateBankrollModel.getStarCoin(),busiCustomerBankrollPojo.getStarCoin(),(short)1);

            if(result <= 0)
                throw new BusinessException("BC0037", "星币修改失败");
        }

        List<BusiCustomerBankrollFlowModel> list = busiUpdateBankrollModel.getList();
        list.forEach(busiCustomerBankrollFlowModel -> {
            BusiCustomerBankrollFlowPojo busiCustomerBankrollFlowPojo = new BusiCustomerBankrollFlowPojo();
            BeanUtils.copyProperties(busiCustomerBankrollFlowModel,busiCustomerBankrollFlowPojo);
            busiCustomerBankrollFlowPojo.setBankrollId(busiCustomerBankrollPojo.getId());
            busiCustomerBankrollFlowServiceImpl.insertSelective(busiCustomerBankrollFlowPojo);
            stringJoiner.add(String.valueOf(busiCustomerBankrollFlowPojo.getId()));
        });

        if(stringJoiner.length() == 0){
            throw new BusinessException("BC0050", "账户流水添加失败");
        }

        return stringJoiner.toString();
    }


    /***
     * 初始化 消费者账户数据
     * @param busiCustomerBankrollPojo
     */
    void initData(BusiCustomerBankrollPojo busiCustomerBankrollPojo){
        //奖励金
        busiCustomerBankrollPojo.setAmount(new BigDecimal("0"));
        //积分
        busiCustomerBankrollPojo.setIntegral(new BigDecimal("0"));
        //星币
        busiCustomerBankrollPojo.setStarCoin(new BigDecimal("0"));
    }

    public static void main(String[] s){
        StringJoiner stringJoiner = new StringJoiner(",","","");

        System.out.println(stringJoiner.add("asd"));
        System.out.println(stringJoiner);

    }

}