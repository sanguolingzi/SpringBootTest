package com.yinhetianze.business.customer.executor;

import com.yinhetianze.business.customer.model.BusiRegeisterModel;
import com.yinhetianze.business.customer.service.busi.BusiCustomerService;
import com.yinhetianze.business.customer.service.info.BusiCustomerInfoService;
import com.yinhetianze.business.customer.util.CustomerConstant;
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
 *  消费者注册接口
 */

@Component
public class RegeisterExecutor extends AbstractRestBusiExecutor<String> {


    @Autowired
    private BusiCustomerService busiCustomerServiceImpl;

    @Autowired
    private BusiCustomerInfoService busiCustomerInfoServiceImpl;

    @Autowired
    private RedisManager redisManager;

    @Override
    protected String executeBusiness(HttpServletRequest request, HttpServletResponse response, BasicModel model, Object... params) throws BusinessException {
        BusiRegeisterModel busiRegeisterModel = (BusiRegeisterModel)model;
        busiCustomerServiceImpl.addRegeisterCustomer(busiRegeisterModel);
        redisManager.deleteValue(busiRegeisterModel.getPhone()+"_regeister");
        return "success";
    }


    @Override
    protected ErrorMessage validate(HttpServletRequest request, BasicModel model, Object[] params) {
        ErrorMessage errorMessage  = new ErrorMessage();
        BusiRegeisterModel busiRegeisterModel = (BusiRegeisterModel)model;
        if(CommonUtil.isEmpty(busiRegeisterModel.getPhone())){
            errorMessage.rejectNull("phone",null,"手机号");
            return errorMessage;
        }

        if(busiRegeisterModel.getSmsCode() == null){
            errorMessage.rejectNull("smsCode",null,"短信验证码");
            return errorMessage;
        }

        String phone = busiRegeisterModel.getPhone();
        if(!phone.matches(CustomerConstant.phoneRegex)){
            errorMessage.rejectError("phone","BC0017","");
            return errorMessage;
        }

        //--------------------- 查看注册号码是否已存在 --------------------------------------------------------
        BusiCustomerPojo busiCustomerPojo = new BusiCustomerPojo();
        busiCustomerPojo.setPhone(phone);
        busiCustomerPojo = busiCustomerInfoServiceImpl.selectOne(busiCustomerPojo);
        if(busiCustomerPojo != null){
            errorMessage.rejectError("phone","BC0026","");
            return errorMessage;
        }

        String smsCode = busiRegeisterModel.getSmsCode();
        //--------------------- 校验短信验证码内容非空 以及 内容是否匹配 start-----------------------------------
        Object code = redisManager.getValue(phone+CustomerConstant.regeisterSufixKey);
        if(CommonUtil.isEmpty(code)){
            errorMessage.rejectError("smsCode","BC0029","");
            return errorMessage;
        }

        if(!code.toString().equals(smsCode)){
            errorMessage.rejectError("smsCode","BC0053","");
            return errorMessage;
        }
        //--------------------- 校验短信验证码内容非空 以及 内容是否匹配 start-----------------------------------


        //--------------------- 校验推荐人号码 以及 推荐人身份 是否合格 start-----------------------------------
        if(busiRegeisterModel.getRecommendPhone() != null){

            String recommendPhone =  busiRegeisterModel.getRecommendPhone();
            if(!recommendPhone.matches(CustomerConstant.phoneRegex)){
                errorMessage.rejectError("recommendPhone","BC0017","推荐人手机号");
                return errorMessage;
            }

            busiCustomerPojo = new BusiCustomerPojo();
            busiCustomerPojo.setPhone(recommendPhone);
            busiCustomerPojo = busiCustomerInfoServiceImpl.selectOne(busiCustomerPojo);
            if(busiCustomerPojo == null || busiCustomerPojo.getIsMember() != 0){
                errorMessage.rejectError("phone","BC0025","");
                return errorMessage;
            }
        }
        //--------------------- 校验推荐人号码 以及 推荐人身份 是否合格 end-----------------------------------

        return null;
    }
}
