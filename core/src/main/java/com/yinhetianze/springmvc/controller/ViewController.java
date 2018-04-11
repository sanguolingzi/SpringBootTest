package com.yinhetianze.springmvc.controller;

import com.yinhetianze.core.business.httpresponse.ResponseConstant;
import com.yinhetianze.core.business.httpresponse.Result;
import com.yinhetianze.core.utils.CommonUtil;
import com.yinhetianze.springmvc.BasicController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator
 * on 2018/1/31.
 */
public class ViewController extends BasicController<ModelAndView>
{
    @Value("${server.error.path}")
    private String errorPath;

    @Override
    public ModelAndView handleException(Exception e)
    {
        ModelAndView view = new ModelAndView(errorPath);

        if (CommonUtil.isNotEmpty(e))
        {
            Result result = new Result(ResponseConstant.RESULT_CODE_SYS_EXCEPTION, ResponseConstant.RESULT_DESCRIPTION_EXCEPTION);

            // 对业务异常进行处理
            handleResponse(e, result);

            view.getModelMap().put("resultInfo", result);
        }

        return view;
    }

}
