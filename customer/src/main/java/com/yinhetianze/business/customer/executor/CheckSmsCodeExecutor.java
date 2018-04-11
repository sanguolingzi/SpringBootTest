package com.yinhetianze.business.customer.executor;

import com.yinhetianze.business.customer.model.BusiSmsCodeModel;
import com.yinhetianze.business.customer.util.CustomerConstant;
import com.yinhetianze.core.business.AbstractRestBusiExecutor;
import com.yinhetianze.core.business.BusinessException;
import com.yinhetianze.core.business.httprequest.BasicModel;
import com.yinhetianze.core.business.httpresponse.ResponseConstant;
import com.yinhetianze.core.business.httpresponse.message.ErrorMessage;
import com.yinhetianze.core.utils.CommonUtil;
import com.yinhetianze.core.utils.RedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 校验短信验证码
 */

@Component
public class CheckSmsCodeExecutor extends AbstractRestBusiExecutor<String> {


    @Autowired
    private RedisManager redisManager;

    @Override
    protected String executeBusiness(HttpServletRequest request, HttpServletResponse response, BasicModel model, Object... params) throws BusinessException {
        BusiSmsCodeModel busiSmsCodeModel = (BusiSmsCodeModel)model;
        String busiType = busiSmsCodeModel.getBusiType();
        Object cacheCode = null;
        //------------------ 注册短信验证码 -----------------------------
        if("1".equalsIgnoreCase(busiType)){
                String key = busiSmsCodeModel.getPhone()+ CustomerConstant.regeisterSufixKey;
                cacheCode = redisManager.getValue(key);
        //------------------ 修改密码短信验证码 -----------------------------
        }else if("2".equalsIgnoreCase(busiType)){
                String key = busiSmsCodeModel.getPhone()+ CustomerConstant.updPasswordSufixKey;
                cacheCode = redisManager.getValue(key);
        }


        if(cacheCode == null){
            throw new BusinessException("BC0029", ResponseConstant.RESULT_DESCRIPTION_FAILED);
        }else if(!cacheCode.equals(busiSmsCodeModel.getSmsCode())){
            throw new BusinessException("BC0053", ResponseConstant.RESULT_DESCRIPTION_FAILED);
        }
        return "success";
    }


    @Override
    protected ErrorMessage validate(HttpServletRequest request, BasicModel model, Object[] params) {
        ErrorMessage errorMessage = new ErrorMessage();
        BusiSmsCodeModel busiSmsCodeModel = (BusiSmsCodeModel)model;
        String phone = busiSmsCodeModel.getPhone();
        if(CommonUtil.isEmpty(phone)){
            errorMessage.rejectNull("phone",null,"手机号");
            return errorMessage;
        }

        if(!phone.matches(CustomerConstant.phoneRegex)){
            errorMessage.rejectError("phone","BC0017","");
            return errorMessage;
        }

        if(CommonUtil.isEmpty(busiSmsCodeModel.getBusiType())){
            errorMessage.rejectNull("busiType",null,"短信业务类型");
            return errorMessage;
        }

        if(CommonUtil.isEmpty(busiSmsCodeModel.getSmsCode())){
            errorMessage.rejectNull("smsCode",null,"验证码");
            return errorMessage;
        }
        return errorMessage;
    }
}
