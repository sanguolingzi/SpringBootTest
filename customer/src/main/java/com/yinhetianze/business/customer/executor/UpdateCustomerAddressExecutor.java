package com.yinhetianze.business.customer.executor;

import com.yinhetianze.business.customer.model.BusiCustomerReceiveaddressModel;
import com.yinhetianze.business.customer.service.busi.BusiCustomerReceiveaddressService;
import com.yinhetianze.business.customer.service.info.BusiCustomerInfoService;
import com.yinhetianze.core.business.AbstractRestBusiExecutor;
import com.yinhetianze.core.business.BusinessException;
import com.yinhetianze.core.business.httprequest.BasicModel;
import com.yinhetianze.core.business.httpresponse.ResponseConstant;
import com.yinhetianze.core.business.httpresponse.message.ErrorMessage;
import com.yinhetianze.pojo.customer.BusiCustomerPojo;
import com.yinhetianze.pojo.customer.BusiCustomerReceiveaddressPojo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 修改 消费者/会员 收获地址
 */

@Component
public class UpdateCustomerAddressExecutor extends AbstractRestBusiExecutor<String> {


    @Autowired
    private BusiCustomerReceiveaddressService busiCustomerReceiveaddressServiceImpl;

    @Autowired
    private BusiCustomerInfoService busiCustomerInfoServiceImpl;

    @Override
    protected String executeBusiness(HttpServletRequest request, HttpServletResponse response, BasicModel model, Object... params) throws BusinessException {
        BusiCustomerReceiveaddressModel busiCustomerReceiveaddressModel = (BusiCustomerReceiveaddressModel)model;
        BusiCustomerReceiveaddressPojo  busiCustomerReceiveaddressPojo = new BusiCustomerReceiveaddressPojo();
        BeanUtils.copyProperties(busiCustomerReceiveaddressModel,busiCustomerReceiveaddressPojo);
        int result = busiCustomerReceiveaddressServiceImpl.updateByPrimaryKeySelective(busiCustomerReceiveaddressPojo);
        if(result <= 0)
            throw new BusinessException("BC0037", ResponseConstant.RESULT_DESCRIPTION_FAILED);
        return "success";
    }


    @Override
    protected ErrorMessage validate(HttpServletRequest request, BasicModel model, Object[] params) {
        BusiCustomerReceiveaddressModel busiCustomerReceiveaddressModel = (BusiCustomerReceiveaddressModel)model;
        ErrorMessage errorMessage = new ErrorMessage();
        if(busiCustomerReceiveaddressModel.getId() == null){
            errorMessage.rejectNull("id",null,"收货地址id");
            return errorMessage;
        }

        if(busiCustomerReceiveaddressModel.getCustomerId() == null){
            errorMessage.rejectNull("customerId",null,"收货地址所属消费者id");
            return errorMessage;
        }
        BusiCustomerPojo busiCustomerPojo = new BusiCustomerPojo();
        busiCustomerPojo.setId(busiCustomerReceiveaddressModel.getCustomerId());
        busiCustomerPojo=busiCustomerInfoServiceImpl.selectOne(busiCustomerPojo);
        if(busiCustomerPojo.getId() == null){
            errorMessage.rejectError("customer","BC0001","收货地址所属消费者id","店铺所属消费者");
            return errorMessage;
        }

        errorMessage.rejectNull("address",busiCustomerReceiveaddressModel.getAddress(),"详细地址");
        errorMessage.rejectNull("city",busiCustomerReceiveaddressModel.getCity(),"所属城市");
        errorMessage.rejectNull("areaCounty",busiCustomerReceiveaddressModel.getAreaCounty(),"所属乡镇");
        errorMessage.rejectNull("regionLocation",busiCustomerReceiveaddressModel.getRegionLocation(),"所属州省");
        errorMessage.rejectNull("phone",busiCustomerReceiveaddressModel.getPhone(),"联系电话");

        return errorMessage;
    }
}
