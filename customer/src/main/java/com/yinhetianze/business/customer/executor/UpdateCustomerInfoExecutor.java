package com.yinhetianze.business.customer.executor;

import com.yinhetianze.business.customer.model.BusiCustomerModel;
import com.yinhetianze.business.customer.service.busi.BusiCustomerService;
import com.yinhetianze.business.customer.util.CustomerConstant;
import com.yinhetianze.core.business.AbstractRestBusiExecutor;
import com.yinhetianze.core.business.BusinessException;
import com.yinhetianze.core.business.httprequest.BasicModel;
import com.yinhetianze.core.business.httpresponse.ResponseConstant;
import com.yinhetianze.core.business.httpresponse.message.ErrorMessage;
import com.yinhetianze.core.utils.CommonUtil;
import com.yinhetianze.pojo.customer.BusiCustomerPojo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 消费者/会员 修改基础信息
 */

@Component
public class UpdateCustomerInfoExecutor extends AbstractRestBusiExecutor<String> {


    @Autowired
    private BusiCustomerService busiCustomerServiceImpl;

    @Override
    protected String executeBusiness(HttpServletRequest request, HttpServletResponse response, BasicModel model, Object... params) throws BusinessException {

        BusiCustomerPojo busiCustomerPojo = new BusiCustomerPojo();
        BeanUtils.copyProperties(model,busiCustomerPojo);
        int result = busiCustomerServiceImpl.updateByPrimaryKeySelective(busiCustomerPojo);
        if(result <= 0)
            throw new BusinessException("BC0037", ResponseConstant.RESULT_DESCRIPTION_FAILED);
        return "success";
    }


    @Override
    protected ErrorMessage validate(HttpServletRequest request, BasicModel model, Object[] params) {
        ErrorMessage errorMessage = new ErrorMessage();
        BusiCustomerModel busiCustomerModel = (BusiCustomerModel)model;

        /**
         * 校验 Id
         */
        if(CommonUtil.isEmpty(busiCustomerModel.getId())){
            errorMessage.rejectNull("id",null,"id");
            return errorMessage;
        }

        /**
         * 校验手机号码格式
         */
        if(CommonUtil.isNotEmpty(busiCustomerModel.getPhone())){
            if(!busiCustomerModel.getPhone().matches(CustomerConstant.phoneRegex)){
                errorMessage.rejectError("recommendPhone","BC0017","手机号");
                return errorMessage;
            }
        }

        /**
         * 校验 昵称 格式 为 汉字数字字母组合   长度小于10
         */
        if(CommonUtil.isNotEmpty(busiCustomerModel.getNickName())){

            if(!busiCustomerModel.getNickName().matches(CustomerConstant.nameRegex)){
                errorMessage.rejectError("nickName","BC0032","昵称","昵称");
                return errorMessage;
            }
            errorMessage.rejectMaxLength("nickName",busiCustomerModel.getNickName(),"昵称",10);
        }

        /**
         * 校验 性别 值 只能为 0  或者 1
         */
        if(CommonUtil.isNotEmpty(busiCustomerModel.getSex())){
                if(busiCustomerModel.getSex() != 1 && busiCustomerModel.getSex() != 0){
                    errorMessage.rejectError("sex","BC0032","性别","性别");
                    return errorMessage;
                }
        }

        /**
         * 校验 邮箱格式
         */
        if(CommonUtil.isNotEmpty(busiCustomerModel.getEmail())){
            errorMessage.rejectEmail("email",busiCustomerModel.getEmail());
        }

        return errorMessage;
    }
}
