package com.yinhetianze.business.customer.executor;

import com.yinhetianze.business.customer.model.BusiCustomerModel;
import com.yinhetianze.business.customer.service.busi.BusiCustomerService;
import com.yinhetianze.core.business.AbstractRestBusiExecutor;
import com.yinhetianze.core.business.BusinessException;
import com.yinhetianze.core.business.httprequest.BasicModel;
import com.yinhetianze.core.business.httpresponse.ResponseConstant;
import com.yinhetianze.core.business.httpresponse.message.ErrorMessage;
import com.yinhetianze.pojo.customer.BusiCustomerPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 消费者/会员 删除信息
 */

@Component
public class DeleteCustomerInfoExecutor extends AbstractRestBusiExecutor<Object> {


    @Autowired
    private BusiCustomerService busiCustomerServiceImpl;

    @Override
    protected Object executeBusiness(HttpServletRequest request, HttpServletResponse response, BasicModel model, Object... params) throws BusinessException {
        BusiCustomerModel busiCustomerModel = (BusiCustomerModel)model;
        BusiCustomerPojo busiCustomerPojo = new BusiCustomerPojo();
        busiCustomerPojo.setId(busiCustomerModel.getId());
        busiCustomerPojo.setDelFlag((short)1);
        int result = busiCustomerServiceImpl.updateByPrimaryKeySelective(busiCustomerPojo);
        if(result <= 0)
            throw new BusinessException("BC0049", ResponseConstant.RESULT_DESCRIPTION_FAILED);
        return "success";
    }

    @Override
    protected ErrorMessage validate(HttpServletRequest request, BasicModel model, Object[] params){
        ErrorMessage errorMessage  = new ErrorMessage();
        if(model == null){
            errorMessage.rejectNull("id",params[0].toString(),"id");
            return  errorMessage;
        }

        BusiCustomerModel busiCustomerModel = (BusiCustomerModel)model;
        if(busiCustomerModel.getId() == null){
            errorMessage.rejectNull("id",null,"id");
            return errorMessage;
        }

        return errorMessage;
    }

}
