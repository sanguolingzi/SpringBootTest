package com.yinhetianze.business.customer.executor;

import com.yinhetianze.business.customer.model.BusiCustomerReceiveaddressModel;
import com.yinhetianze.business.customer.service.busi.BusiCustomerReceiveaddressService;
import com.yinhetianze.core.business.AbstractRestBusiExecutor;
import com.yinhetianze.core.business.BusinessException;
import com.yinhetianze.core.business.httprequest.BasicModel;
import com.yinhetianze.core.business.httpresponse.ResponseConstant;
import com.yinhetianze.core.business.httpresponse.message.ErrorMessage;
import com.yinhetianze.pojo.customer.BusiCustomerReceiveaddressPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 删除 消费者/会员 收获地址
 */

@Component
public class DeleteCustomerAddressExecutor extends AbstractRestBusiExecutor<String> {


    @Autowired
    private BusiCustomerReceiveaddressService busiCustomerReceiveaddressServiceImpl;

    @Override
    protected String executeBusiness(HttpServletRequest request, HttpServletResponse response, BasicModel model, Object... params) throws BusinessException {
        BusiCustomerReceiveaddressModel busiCustomerReceiveaddressModel = (BusiCustomerReceiveaddressModel)model;
        BusiCustomerReceiveaddressPojo  busiCustomerReceiveaddressPojo = new BusiCustomerReceiveaddressPojo();

        busiCustomerReceiveaddressPojo.setId(busiCustomerReceiveaddressModel.getId());
        busiCustomerReceiveaddressPojo.setDelFlag((short)1);
        int result = busiCustomerReceiveaddressServiceImpl.updateByPrimaryKeySelective(busiCustomerReceiveaddressPojo);
        if(result <= 0)
            throw new BusinessException("BC0049", ResponseConstant.RESULT_DESCRIPTION_FAILED);
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
        return errorMessage;
    }
}
