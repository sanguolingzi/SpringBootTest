package com.yinhetianze.business.customer.executor;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinhetianze.business.customer.model.BusiCustomerRecommendRelationModel;
import com.yinhetianze.business.customer.service.info.BusiCustomerRecommendRelationInfoService;
import com.yinhetianze.core.business.AbstractRestBusiExecutor;
import com.yinhetianze.core.business.BusinessException;
import com.yinhetianze.core.business.httprequest.BasicModel;
import com.yinhetianze.core.business.httpresponse.message.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 获取推荐人 推荐信息
 */

@Component
public class GetCustomerRecommendInfoExecutor extends AbstractRestBusiExecutor<Object> {


    @Autowired
    private BusiCustomerRecommendRelationInfoService busiCustomerRecommendRelationInfoServiceImpl;

    @Override
    protected Object executeBusiness(HttpServletRequest request, HttpServletResponse response, BasicModel model, Object... params) throws BusinessException {
        BusiCustomerRecommendRelationModel busiCustomerRecommendRelationModel = (BusiCustomerRecommendRelationModel)model;
        PageHelper.startPage(busiCustomerRecommendRelationModel.getCurrentPage(),busiCustomerRecommendRelationModel.getPageSize());
        PageInfo pageInfo = new PageInfo(
                busiCustomerRecommendRelationInfoServiceImpl.
                        selectRecommendRelationList( busiCustomerRecommendRelationModel.getRecomCustomerId()));
        return pageInfo;
    }

    @Override
    protected ErrorMessage validate(HttpServletRequest request, BasicModel model, Object[] params){
        ErrorMessage errorMessage  = new ErrorMessage();
        if(model == null){
            errorMessage.rejectNull("pageModel",null,"分业查询参数");
            return errorMessage;
        }
        BusiCustomerRecommendRelationModel busiCustomerRecommendRelationModel = (BusiCustomerRecommendRelationModel)model;
        if(busiCustomerRecommendRelationModel.getRecomCustomerId() == null){
            errorMessage.rejectNull("recomCustomerId",null,"推荐人id");
            return errorMessage;
        }
        return errorMessage;
    }

}
