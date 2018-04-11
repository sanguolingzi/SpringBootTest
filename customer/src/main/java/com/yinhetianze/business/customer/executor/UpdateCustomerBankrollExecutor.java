package com.yinhetianze.business.customer.executor;

import com.yinhetianze.business.customer.model.BusiUpdateBankrollModel;
import com.yinhetianze.business.customer.service.busi.BusiCustomerBankrollService;
import com.yinhetianze.core.business.AbstractRestBusiExecutor;
import com.yinhetianze.core.business.BusinessException;
import com.yinhetianze.core.business.httprequest.BasicModel;
import com.yinhetianze.core.business.httpresponse.message.ErrorMessage;
import com.yinhetianze.core.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 消费者/会员 订单消费 修改账户信息、记录流水
 */

@Component
public class UpdateCustomerBankrollExecutor extends AbstractRestBusiExecutor<Object> {


    @Autowired
    private BusiCustomerBankrollService busiCustomerBankrollServiceImpl;

    @Override
    protected Object executeBusiness(HttpServletRequest request, HttpServletResponse response, BasicModel model, Object... params) throws BusinessException {
        return busiCustomerBankrollServiceImpl.updateBankrollForOrder((BusiUpdateBankrollModel)model);
    }


    @Override
    protected ErrorMessage validate(HttpServletRequest request, BasicModel model, Object[] params){
        ErrorMessage errorMessage  = new ErrorMessage();
        BusiUpdateBankrollModel busiUpdateBankrollModel = (BusiUpdateBankrollModel)model;


        if(CommonUtil.isEmpty(busiUpdateBankrollModel.getList())){
            errorMessage.rejectNull("list",null,"list","数据集合");
            return errorMessage;
        }


        if(CommonUtil.isEmpty(busiUpdateBankrollModel.getCustomerId())){
            errorMessage.rejectNull("customerId",null,"customerId","消费者id");
            return errorMessage;
        }

        if(CommonUtil.isNotEmpty(busiUpdateBankrollModel.getAmount())){
            errorMessage.rejectMoney("amount",busiUpdateBankrollModel.getAmount().toString(),"余额");
        }

        if(CommonUtil.isNotEmpty(busiUpdateBankrollModel.getIntegral())){
            errorMessage.rejectMoney("integral",busiUpdateBankrollModel.getIntegral().toString(),"积分");
        }

        if(CommonUtil.isNotEmpty(busiUpdateBankrollModel.getStarCoin())){
            errorMessage.rejectMoney("starCoin",busiUpdateBankrollModel.getStarCoin().toString(),"星币");
        }
        return errorMessage;
    }

}
