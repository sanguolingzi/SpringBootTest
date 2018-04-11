package com.yinhetianze.business.customer.executor;

import com.yinhetianze.business.customer.model.BusiUpdatePasswordModel;
import com.yinhetianze.business.customer.service.busi.BusiCustomerService;
import com.yinhetianze.business.customer.service.info.BusiCustomerInfoService;
import com.yinhetianze.business.customer.util.CustomerUtil;
import com.yinhetianze.core.business.AbstractRestBusiExecutor;
import com.yinhetianze.core.business.BusinessException;
import com.yinhetianze.core.business.httprequest.BasicModel;
import com.yinhetianze.core.business.httpresponse.ResponseConstant;
import com.yinhetianze.core.business.httpresponse.message.ErrorMessage;
import com.yinhetianze.core.utils.CommonUtil;
import com.yinhetianze.pojo.customer.BusiCustomerPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 消费者/会员 修改密码
 */

@Component
public class UpdateLoginPasswordExecutor extends AbstractRestBusiExecutor<String> {


    @Autowired
    private BusiCustomerService busiCustomerServiceImpl;

    @Autowired
    private BusiCustomerInfoService busiCustomerInfoServiceImpl;

    @Override
    protected String executeBusiness(HttpServletRequest request, HttpServletResponse response, BasicModel model, Object... params) throws BusinessException {

        BusiCustomerPojo busiCustomerPojo = new BusiCustomerPojo();
        BusiUpdatePasswordModel busiUpdatePasswordModel = (BusiUpdatePasswordModel)model;
        busiCustomerPojo.setId(busiUpdatePasswordModel.getCustomerId());
        busiCustomerPojo.setLoginPassword(CustomerUtil.createPassword(busiUpdatePasswordModel.getNewPassword()));
        int result = busiCustomerServiceImpl.updateByPrimaryKeySelective(busiCustomerPojo);
        if(result <= 0)
            throw new BusinessException("BC0037", ResponseConstant.RESULT_DESCRIPTION_FAILED);
        return "success";
    }


    @Override
    protected ErrorMessage validate(HttpServletRequest request, BasicModel model, Object[] params) {
        ErrorMessage errorMessage = new ErrorMessage();
        BusiUpdatePasswordModel busiUpdatePasswordModel = (BusiUpdatePasswordModel)model;

        /**
         * 校验 customerId
         */
        if(CommonUtil.isEmpty(busiUpdatePasswordModel.getCustomerId())){
            errorMessage.rejectNull("id",null,"id");
            return errorMessage;
        }

        if(CommonUtil.isEmpty(busiUpdatePasswordModel.getLoginPassword())){
            errorMessage.rejectNull("password",null,"原密码");
            return errorMessage;
        }

        if(CommonUtil.isEmpty(busiUpdatePasswordModel.getNewPassword())){
            errorMessage.rejectNull("password",null,"新密码");
            return errorMessage;
        }

        if(CommonUtil.isEmpty(busiUpdatePasswordModel.getConfirmPassword())){
            errorMessage.rejectNull("password",null,"确认新密码");
            return errorMessage;
        }


        /**
         * 校验新密码是否与原密码相同
         */
        if(busiUpdatePasswordModel.getLoginPassword().equalsIgnoreCase(busiUpdatePasswordModel.getNewPassword())){
            errorMessage.rejectError("BC0039","password");
            return errorMessage;
        }


        /**
         * 校验 新密码和确认密码是否相同
         */
        if(!busiUpdatePasswordModel.getNewPassword().equalsIgnoreCase(busiUpdatePasswordModel.getConfirmPassword())){
            errorMessage.rejectError("BC0027","password");
            return errorMessage;
        }


        BusiCustomerPojo busiCustomerPojo  = new BusiCustomerPojo();
        busiCustomerPojo.setId(busiUpdatePasswordModel.getCustomerId());
        busiCustomerPojo = busiCustomerInfoServiceImpl.selectOne(busiCustomerPojo);

        /**
         * 输入原密码是否与数据库密码相同
         */
        if(!CustomerUtil.checkPassword(busiCustomerPojo.getLoginPassword(),busiUpdatePasswordModel.getLoginPassword())){
            errorMessage.rejectError("BC0016","password");
            return errorMessage;
        }
        return errorMessage;
    }
}
