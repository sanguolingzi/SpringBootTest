package com.yinhetianze.business.customer.executor;

import com.yinhetianze.core.business.AbstractRestBusiExecutor;
import com.yinhetianze.core.business.BusinessException;
import com.yinhetianze.core.business.httprequest.BasicModel;
import com.yinhetianze.core.business.httpresponse.message.ErrorMessage;
import com.yinhetianze.business.customer.service.info.BusiCustomerBankrollInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 消费者/会员 获取账户信息
 */

@Component
public class GetCustomerBankrollInfoExecutor extends AbstractRestBusiExecutor<Object> {


    @Autowired
    private BusiCustomerBankrollInfoService busiCustomerBankrollInfoServiceImpl;

    @Override
    protected Object executeBusiness(HttpServletRequest request, HttpServletResponse response, BasicModel model, Object... params) throws BusinessException {
        return busiCustomerBankrollInfoServiceImpl.selectByCustomerId(Integer.parseInt(params[0].toString()));
    }

    @Override
    protected ErrorMessage validate(HttpServletRequest request, BasicModel model, Object[] params){
        ErrorMessage errorMessage  = new ErrorMessage();
        errorMessage.rejectNull("customerId",params[0].toString(),"customerId");
        errorMessage.rejectDigital("customerId",params[0].toString(),"customerId");
        return errorMessage;
    }

}
