package com.yinhetianze.business.customer.executor;

import com.yinhetianze.business.customer.model.BusiCustomerCenterModel;
import com.yinhetianze.business.customer.service.info.BusiCustomerBankrollInfoService;
import com.yinhetianze.business.customer.service.info.BusiCustomerInfoService;
import com.yinhetianze.core.business.AbstractRestBusiExecutor;
import com.yinhetianze.core.business.BusinessException;
import com.yinhetianze.core.business.httprequest.BasicModel;
import com.yinhetianze.core.business.httpresponse.message.ErrorMessage;
import com.yinhetianze.pojo.customer.BusiCustomerBankrollPojo;
import com.yinhetianze.pojo.customer.BusiCustomerPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

/**
 * 消费者/会员  个人中心
 */

@Component
public class GetCustomerCenterInfoExecutor extends AbstractRestBusiExecutor<Object> {


    @Autowired
    private BusiCustomerInfoService busiCustomerInfoServiceImpl;

    @Autowired
    private BusiCustomerBankrollInfoService busiCustomerBankrollInfoServiceImpl;

    @Override
    protected Object executeBusiness(HttpServletRequest request, HttpServletResponse response, BasicModel model, Object... params) throws BusinessException {
        BusiCustomerCenterModel busiCustomerCenterModel = new BusiCustomerCenterModel();
        BusiCustomerPojo busiCustomerPojo = new BusiCustomerPojo();
        busiCustomerPojo.setId(Integer.parseInt(params[0].toString()));
        busiCustomerPojo = busiCustomerInfoServiceImpl.selectOne(busiCustomerPojo);
        if(busiCustomerPojo != null){
            busiCustomerCenterModel.setId(busiCustomerPojo.getId());
            busiCustomerCenterModel.setPhotoUrl(busiCustomerPojo.getPhotoUrl());
            busiCustomerCenterModel.setNickName(busiCustomerPojo.getNickName());


            BusiCustomerBankrollPojo busiCustomerBankrollPojo =
                    busiCustomerBankrollInfoServiceImpl.selectByCustomerId(busiCustomerPojo.getId());
            busiCustomerCenterModel.setAmount(busiCustomerBankrollPojo.getAmount().movePointLeft(2));
            busiCustomerCenterModel.setStarCoin(busiCustomerBankrollPojo.getStarCoin().movePointLeft(2));

            /**
             * 模拟 消费者店铺/商品 收藏数
             */
            busiCustomerCenterModel.setCollectNum(String.valueOf(new Random().nextInt(50)));
        }
        return busiCustomerCenterModel;
    }

    public ErrorMessage validate(HttpServletRequest request, BasicModel model, Object[] params){
        ErrorMessage errorMessage  = new ErrorMessage();
        errorMessage.rejectNull("id",params[0].toString(),"id");
        errorMessage.rejectDigital("id",params[0].toString(),"id");
        return errorMessage;
    }

}
