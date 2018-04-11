package com.yinhetianze.business.customer.executor;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinhetianze.business.customer.model.BusiCustomerBankrollFlowModel;
import com.yinhetianze.business.customer.service.info.BusiCustomerBankrollFlowInfoService;
import com.yinhetianze.business.customer.service.info.BusiCustomerBankrollInfoService;
import com.yinhetianze.core.business.AbstractRestBusiExecutor;
import com.yinhetianze.core.business.BusinessException;
import com.yinhetianze.core.business.httprequest.BasicModel;
import com.yinhetianze.core.business.httpresponse.message.ErrorMessage;
import com.yinhetianze.pojo.customer.BusiCustomerBankrollPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 消费者/会员 获取账户流水信息
 */

@Component
public class GetCustomerBankrollFlowInfoExecutor extends AbstractRestBusiExecutor<Object> {

    @Autowired
    private BusiCustomerBankrollFlowInfoService busiCustomerBankrollFlowInfoServiceImpl;

    @Autowired
    private BusiCustomerBankrollInfoService busiCustomerBankrollInfoServiceImpl;

    @Override
    protected Object executeBusiness(HttpServletRequest request, HttpServletResponse response, BasicModel model, Object... params) throws BusinessException {
        BusiCustomerBankrollFlowModel busiCustomerBankrollFlowModel = (BusiCustomerBankrollFlowModel)model;
        BusiCustomerBankrollPojo busiCustomerBankrollPojo = busiCustomerBankrollInfoServiceImpl
                .selectByCustomerId(busiCustomerBankrollFlowModel.getCustomerId());
        busiCustomerBankrollFlowModel.setBankrollId(busiCustomerBankrollPojo.getId());
        PageHelper.startPage(busiCustomerBankrollFlowModel.getCurrentPage(),busiCustomerBankrollFlowModel.getPageSize());
        PageInfo pageInfo = new PageInfo(busiCustomerBankrollFlowInfoServiceImpl.selectList(busiCustomerBankrollFlowModel));
        return pageInfo;
    }

    @Override
    protected ErrorMessage validate(HttpServletRequest request, BasicModel model, Object[] params){
        ErrorMessage errorMessage  = new ErrorMessage();
        if(model == null){
            errorMessage.rejectNull("pageModel",null,"分业查询参数");
            return errorMessage;
        }

        BusiCustomerBankrollFlowModel busiCustomerBankrollFlowModel = (BusiCustomerBankrollFlowModel)model;
        if(busiCustomerBankrollFlowModel.getCustomerId() == null){
            errorMessage.rejectNull("customerId",null,"账户所属者");
            return errorMessage;
        }
        return errorMessage;
    }

}
