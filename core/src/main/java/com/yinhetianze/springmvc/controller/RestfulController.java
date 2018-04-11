package com.yinhetianze.springmvc.controller;

import com.yinhetianze.core.business.httpresponse.ResponseConstant;
import com.yinhetianze.core.business.httpresponse.ResponseData;
import com.yinhetianze.core.business.httpresponse.Result;
import com.yinhetianze.springmvc.BasicController;

/**
 * Created by Administrator
 * on 2018/1/31.
 */
public class RestfulController extends BasicController<ResponseData>
{
    /**
     * 实现异常处理方法
     */
    @Override
    public ResponseData handleException(Exception e)
    {
        ResponseData data = new ResponseData();
        Result result = new Result(ResponseConstant.RESULT_CODE_SYS_EXCEPTION, ResponseConstant.RESULT_DESCRIPTION_EXCEPTION);
        data.setData(result);

        // 对业务异常进行处理
        handleResponse(e, result);

        return data;
    }

}
