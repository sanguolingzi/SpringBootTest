package com.yinhetianze.business.customer.executor;

import com.yinhetianze.business.customer.model.BusiCustomerModel;
import com.yinhetianze.business.customer.util.CustomerConstant;
import com.yinhetianze.core.business.AbstractRestBusiExecutor;
import com.yinhetianze.core.business.BusinessException;
import com.yinhetianze.core.business.httprequest.BasicModel;
import com.yinhetianze.core.business.httpresponse.message.ErrorMessage;
import com.yinhetianze.core.utils.CommonUtil;
import com.yinhetianze.core.utils.RedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 消费者/会员 退出登陆
 */

@Component
public class LoginOutExecutor extends AbstractRestBusiExecutor<String> {


    @Autowired
    private RedisManager redisManager;

    @Override
    protected String executeBusiness(HttpServletRequest request, HttpServletResponse response, BasicModel model, Object... params) throws BusinessException {
        /**
         * 删除token信息
         */
        BusiCustomerModel busiCustomerModel = (BusiCustomerModel)model;
        redisManager.getValue(busiCustomerModel.getPhone()+CustomerConstant.tokenSufixKey,true);
        return "success";
    }


    @Override
    protected ErrorMessage validate(HttpServletRequest request, BasicModel model, Object[] params) {
        BusiCustomerModel busiCustomerModel = (BusiCustomerModel)model;

        ErrorMessage errorMessage  = new ErrorMessage();
        if(CommonUtil.isEmpty(busiCustomerModel.getPhone())){
            errorMessage.rejectNull("phone",null,"手机号");
            return errorMessage;
        }

        if(!busiCustomerModel.getPhone().matches(CustomerConstant.phoneRegex)){
            errorMessage.rejectError("recommendPhone","BC0017","手机号");
            return errorMessage;
        }
        return null;
    }
}
