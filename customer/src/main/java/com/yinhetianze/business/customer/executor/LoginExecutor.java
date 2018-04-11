package com.yinhetianze.business.customer.executor;

import com.yinhetianze.business.customer.model.BusiCustomerModel;
import com.yinhetianze.business.customer.service.info.BusiCustomerInfoService;
import com.yinhetianze.business.customer.util.CustomerConstant;
import com.yinhetianze.business.customer.util.CustomerUtil;
import com.yinhetianze.core.business.AbstractRestBusiExecutor;
import com.yinhetianze.core.business.BusinessException;
import com.yinhetianze.core.business.httprequest.BasicModel;
import com.yinhetianze.core.business.httpresponse.message.ErrorMessage;
import com.yinhetianze.core.utils.CommonUtil;
import com.yinhetianze.core.utils.RedisManager;
import com.yinhetianze.pojo.customer.BusiCustomerPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 消费者/会员 登录
 */

@Component
public class LoginExecutor extends AbstractRestBusiExecutor<String> {


    @Autowired
    private BusiCustomerInfoService busiCustomerInfoServiceImpl;

    @Autowired
    private RedisManager redisManager;

    @Override
    protected String executeBusiness(HttpServletRequest request, HttpServletResponse response, BasicModel model, Object... params) throws BusinessException {
        /**
         * 模拟生成token
         */
        BusiCustomerModel busiCustomerModel = (BusiCustomerModel)model;
        redisManager.setValue(busiCustomerModel.getPhone()+CustomerConstant.tokenSufixKey,System.nanoTime(),RedisManager.TWO_HOUR);
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

        if(CommonUtil.isEmpty(busiCustomerModel.getLoginPassword())){
            errorMessage.rejectNull("password",null,"密码");
            return errorMessage;
        }

        BusiCustomerPojo busiCustomerPojo = busiCustomerInfoServiceImpl.selectByPhone(busiCustomerModel.getPhone());
        if(busiCustomerPojo == null){
            errorMessage.rejectError("info","BC0007","");
            return errorMessage;
        }

        if(!CustomerUtil.checkPassword(busiCustomerPojo.getLoginPassword(),busiCustomerModel.getLoginPassword())){
            errorMessage.rejectError("info","BC0007","");
            return errorMessage;
        }

        return null;
    }
}
