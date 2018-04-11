package com.yinhetianze.business.customer.executor;

import com.yinhetianze.business.customer.model.BusiCustomerReceiveaddressModel;
import com.yinhetianze.business.customer.service.busi.BusiCustomerReceiveaddressService;
import com.yinhetianze.core.business.AbstractRestBusiExecutor;
import com.yinhetianze.core.business.BusinessException;
import com.yinhetianze.core.business.httprequest.BasicModel;
import com.yinhetianze.core.business.httpresponse.message.ErrorMessage;
import com.yinhetianze.pojo.customer.BusiCustomerReceiveaddressPojo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 设置 默认收获地址
 */

@Component
public class UpdateDefaultAddressExecutor extends AbstractRestBusiExecutor<String> {


    @Autowired
    private BusiCustomerReceiveaddressService busiCustomerReceiveaddressServiceImpl;

    @Override
    protected String executeBusiness(HttpServletRequest request, HttpServletResponse response, BasicModel model, Object... params) throws BusinessException {
        BusiCustomerReceiveaddressPojo busiCustomerReceiveaddressPojo = new BusiCustomerReceiveaddressPojo();
        BeanUtils.copyProperties(model,busiCustomerReceiveaddressPojo);
        busiCustomerReceiveaddressServiceImpl.updateDefaultAddress(busiCustomerReceiveaddressPojo);
        return "success";
    }


    @Override
    protected ErrorMessage validate(HttpServletRequest request, BasicModel model, Object[] params) {
        BusiCustomerReceiveaddressModel busiCustomerReceiveaddressModel = (BusiCustomerReceiveaddressModel)model;
        ErrorMessage errorMessage = new ErrorMessage();


        if(busiCustomerReceiveaddressModel.getId() == null){
            errorMessage.rejectNull("id",null,"收货地址id");
            return errorMessage;
        }

        if(busiCustomerReceiveaddressModel.getCustomerId() == null){
            errorMessage.rejectNull("customerId",null,"收货地址所属消费者id");
            return errorMessage;
        }
        return errorMessage;
    }
}
