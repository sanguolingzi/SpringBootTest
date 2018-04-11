package com.yinhetianze.business.customer.executor;

import com.yinhetianze.business.customer.model.BusiCustomerModel;
import com.yinhetianze.business.customer.service.busi.BusiCustomerService;
import com.yinhetianze.core.business.AbstractRestBusiExecutor;
import com.yinhetianze.core.business.BusinessException;
import com.yinhetianze.core.business.httprequest.BasicModel;
import com.yinhetianze.core.business.httpresponse.ResponseConstant;
import com.yinhetianze.core.business.httpresponse.message.ErrorMessage;
import com.yinhetianze.pojo.customer.BusiCustomerPojo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 消费者/会员 实名认证
 */

@Component
public class UpdateRealNameInfoExecutor extends AbstractRestBusiExecutor<String> {


    @Autowired
    private BusiCustomerService busiCustomerServiceImpl;

    @Override
    protected String executeBusiness(HttpServletRequest request, HttpServletResponse response, BasicModel model, Object... params) throws BusinessException {


        /**
         * 模拟实名认证处理逻辑
         * 成功后 更新用户基本信息
         */
        boolean success = false; //success 模拟验证接口
        if(success){
            BusiCustomerPojo busiCustomerPojo = new BusiCustomerPojo();
            BeanUtils.copyProperties(model,busiCustomerPojo);
            int result = busiCustomerServiceImpl.updateByPrimaryKeySelective(busiCustomerPojo);
            if(result <= 0)
                throw new BusinessException("BC0037", ResponseConstant.RESULT_DESCRIPTION_FAILED);

            return "success";
        }else{
            throw new BusinessException("BC0052", ResponseConstant.RESULT_DESCRIPTION_FAILED);
        }
    }


    @Override
    protected ErrorMessage validate(HttpServletRequest request, BasicModel model, Object[] params) {
        ErrorMessage errorMessage = new ErrorMessage();
        BusiCustomerModel busiCustomerModel = (BusiCustomerModel)model;
        errorMessage.rejectNull("idCard",busiCustomerModel.getIdCard(),"身份证号码");
        errorMessage.rejectNull("relName",busiCustomerModel.getRealName(),"姓名");

        if(busiCustomerModel.getId() == null){
            errorMessage.rejectNull("id",null,"id");
        }
        return errorMessage;
    }
}
