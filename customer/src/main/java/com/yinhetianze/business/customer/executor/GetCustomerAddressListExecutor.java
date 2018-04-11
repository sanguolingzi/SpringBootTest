package com.yinhetianze.business.customer.executor;

import com.yinhetianze.business.customer.service.info.BusiCustomerReceiveaddressInfoService;
import com.yinhetianze.core.business.AbstractRestBusiExecutor;
import com.yinhetianze.core.business.BusinessException;
import com.yinhetianze.core.business.httprequest.BasicModel;
import com.yinhetianze.core.business.httpresponse.message.ErrorMessage;
import com.yinhetianze.pojo.customer.BusiCustomerReceiveaddressPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 消费者/会员 获取收获地址列表
 */

@Component
public class GetCustomerAddressListExecutor extends AbstractRestBusiExecutor<Object> {


    @Autowired
    private BusiCustomerReceiveaddressInfoService busiCustomerReceiveaddressInfoServiceImpl;

    @Override
    protected Object executeBusiness(HttpServletRequest request, HttpServletResponse response, BasicModel model, Object... params) throws BusinessException {

        List<BusiCustomerReceiveaddressPojo> list = busiCustomerReceiveaddressInfoServiceImpl.selectByCustomerId(Integer.parseInt(params[0].toString()));
        return list;
    }

    @Override
    protected ErrorMessage validate(HttpServletRequest request, BasicModel model, Object[] params){
        ErrorMessage errorMessage  = new ErrorMessage();
        errorMessage.rejectNull("customerId",params[0].toString(),"customerId");
        errorMessage.rejectDigital("customerId",params[0].toString(),"customerId");
        return errorMessage;
    }

}
