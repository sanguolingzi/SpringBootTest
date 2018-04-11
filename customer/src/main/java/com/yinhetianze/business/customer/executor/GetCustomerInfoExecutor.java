package com.yinhetianze.business.customer.executor;

import com.yinhetianze.business.customer.service.info.BusiCustomerInfoService;
import com.yinhetianze.core.business.AbstractRestBusiExecutor;
import com.yinhetianze.core.business.BusinessException;
import com.yinhetianze.core.business.httprequest.BasicModel;
import com.yinhetianze.core.business.httpresponse.message.ErrorMessage;
import com.yinhetianze.pojo.customer.BusiCustomerPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 消费者/会员 获取基础信息
 */

@Component
public class GetCustomerInfoExecutor extends AbstractRestBusiExecutor<Object> {


    @Autowired
    private BusiCustomerInfoService busiCustomerInfoServiceImpl;

    @Override
    protected Object executeBusiness(HttpServletRequest request, HttpServletResponse response, BasicModel model, Object... params) throws BusinessException {
        BusiCustomerPojo busiCustomerPojo = new BusiCustomerPojo();
        busiCustomerPojo.setId(Integer.parseInt(params[0].toString()));
        busiCustomerPojo = busiCustomerInfoServiceImpl.selectOne(busiCustomerPojo);
        return busiCustomerPojo;
    }

    @Override
    protected ErrorMessage validate(HttpServletRequest request, BasicModel model, Object[] params){
        ErrorMessage errorMessage  = new ErrorMessage();
        errorMessage.rejectNull("id",params[0].toString(),"id");
        errorMessage.rejectDigital("id",params[0].toString(),"id");
        return errorMessage;
    }

}
