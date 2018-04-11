package com.yinhetianze.business.customer.executor;

import com.yinhetianze.business.customer.model.BusiSmsCodeModel;
import com.yinhetianze.business.customer.util.CustomerConstant;
import com.yinhetianze.core.business.AbstractRestBusiExecutor;
import com.yinhetianze.core.business.BusinessException;
import com.yinhetianze.core.business.httprequest.BasicModel;
import com.yinhetianze.core.business.httpresponse.message.ErrorMessage;
import com.yinhetianze.core.utils.CommonUtil;
import com.yinhetianze.core.utils.RedisManager;
import com.yinhetianze.core.utils.SecurityCode;
import com.yinhetianze.core.utils.SendSmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取短信验证码
 */

@Component
public class GetSmsCodeExecutor extends AbstractRestBusiExecutor<String> {


    @Autowired
    private RedisManager redisManager;

    @Override
    protected String executeBusiness(HttpServletRequest request, HttpServletResponse response, BasicModel model, Object... params) throws BusinessException {
        BusiSmsCodeModel busiSmsCodeModel = (BusiSmsCodeModel)model;
        String busiType = busiSmsCodeModel.getBusiType();
        try{
            String smsCode = SecurityCode.getSecurityCode();
            Map map = new HashMap();

            //------------------ 注册短信验证码 -----------------------------
            if("1".equalsIgnoreCase(busiType)){
                map.put("code",smsCode);
                if(SendSmsUtil.sendSms(SendSmsUtil.sms_template_regist,busiSmsCodeModel.getPhone(),map)){
                    //redis 3分钟有效期 和 短信平台默认过期时间 一致
                    String key = busiSmsCodeModel.getPhone()+ CustomerConstant.regeisterSufixKey;
                    redisManager.setValue(key,smsCode,new Long(1000*180));
                    return "success";
                }
            //------------------ 修改密码短信验证码 -----------------------------
            }else if("2".equalsIgnoreCase(busiType)){
                map.put("code",smsCode);
                if(SendSmsUtil.sendSms(SendSmsUtil.sms_template_verifyCode,busiSmsCodeModel.getPhone(),map)){
                    //redis 3分钟有效期 和 短信平台默认过期时间 一致
                    String key = busiSmsCodeModel.getPhone()+ CustomerConstant.updPasswordSufixKey;
                    redisManager.setValue(key,smsCode,new Long(1000*180));
                    return "success";
                }
            }
            throw new Exception("注册短信发送失败!");
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
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
        return errorMessage;
    }
}
