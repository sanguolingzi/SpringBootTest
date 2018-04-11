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

/**
 * 消费者/会员 获取收获地址列表
 */

@Component
public class GetCustomerAddressExecutor extends AbstractRestBusiExecutor<Object> {


    @Autowired
    private BusiCustomerReceiveaddressInfoService busiCustomerReceiveaddressInfoServiceImpl;

    @Override
    protected Object executeBusiness(HttpServletRequest request, HttpServletResponse response, BasicModel model, Object... params) throws BusinessException {

        BusiCustomerReceiveaddressPojo busiCustomerReceiveaddressPojo = new BusiCustomerReceiveaddressPojo();
        busiCustomerReceiveaddressPojo.setId(Integer.parseInt(params[0].toString()));
        busiCustomerReceiveaddressPojo = busiCustomerReceiveaddressInfoServiceImpl.selectOne(busiCustomerReceiveaddressPojo);
        return busiCustomerReceiveaddressPojo;
    }

    @Override
    protected ErrorMessage validate(HttpServletRequest request, BasicModel model, Object[] params){
        ErrorMessage errorMessage  = new ErrorMessage();
        errorMessage.rejectNull("addressId",params[0].toString(),"addressId");
        errorMessage.rejectDigital("addressId",params[0].toString(),"addressId");
        return errorMessage;
    }

}
