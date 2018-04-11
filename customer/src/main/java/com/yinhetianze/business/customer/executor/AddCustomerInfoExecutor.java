package com.yinhetianze.business.customer.executor;

import com.yinhetianze.business.customer.service.busi.BusiCustomerService;
import com.yinhetianze.core.business.AbstractRestBusiExecutor;
import com.yinhetianze.core.business.BusinessException;
import com.yinhetianze.core.business.httprequest.BasicModel;
import com.yinhetianze.core.business.httpresponse.message.ErrorMessage;
import com.yinhetianze.pojo.customer.BusiCustomerPojo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 消费者/会员 新增基础信息
 */

@Component
public class AddCustomerInfoExecutor  extends AbstractRestBusiExecutor<String> {


    @Autowired
    private BusiCustomerService busiCustomerServiceImpl;

    @Override
    protected String executeBusiness(HttpServletRequest request, HttpServletResponse response, BasicModel model, Object... params) throws BusinessException {

        BusiCustomerPojo busiCustomerPojo = new BusiCustomerPojo();
        BeanUtils.copyProperties(model,busiCustomerPojo);
        busiCustomerServiceImpl.addCustomerInfo(busiCustomerPojo);
        return "success";
    }


    @Override
    protected ErrorMessage validate(HttpServletRequest request, BasicModel model, Object[] params) {

        return null;
    }
}
