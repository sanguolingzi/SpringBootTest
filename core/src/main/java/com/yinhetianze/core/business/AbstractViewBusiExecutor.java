package com.yinhetianze.core.business;

import com.yinhetianze.core.business.handlers.ErrorMessageHandler;
import com.yinhetianze.core.business.handlers.ExceptionHandler;
import com.yinhetianze.core.business.httpresponse.ResponseConstant;
import com.yinhetianze.core.business.httpresponse.Result;
import com.yinhetianze.core.business.httpresponse.message.ErrorMessage;
import com.yinhetianze.core.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator
 * on 2018/1/28.
 * 作为视图业务处理
 */
public abstract class AbstractViewBusiExecutor extends TemplateBusinessExecutor<ModelAndView>
{
    @Autowired(required = false)
    private ErrorMessageHandler<ModelAndView> errorMessageHandler;

    @Autowired(required = false)
    private ExceptionHandler<ModelAndView> exceptionHandler;

    @Override
    public final ModelAndView handleException(HttpServletRequest request, HttpServletResponse response, Exception e)
    {
        ModelAndView modelAndView;

        if (CommonUtil.isEmpty(exceptionHandler))
        {
            modelAndView = exceptionHandler.handleException(request, response, e);
            return modelAndView;
        }
        else
        {
            Map<String, Object> map = new HashMap<String, Object>();
            Result result = new Result("系统异常");
            map.put("resultInfo", result);
            String errorPath = "/error";
            modelAndView = new ModelAndView(errorPath, map);
        }

        return modelAndView;
    }

    @Override
    public final ModelAndView handleErrorMessage(HttpServletRequest request, HttpServletResponse response, ErrorMessage errorMessage)
    {
        ModelAndView modelAndView;

        if (CommonUtil.isNotEmpty(errorMessageHandler))
        {
            modelAndView = errorMessageHandler.handleErrorMessage(request, response, errorMessage);
            return modelAndView;
        }
        else
        {
            Map<String, Object> map = new HashMap<String, Object>();
            Result result = new Result(ResponseConstant.RESULT_CODE_BUSI_FAILED, "参数有误");
            map.put("resultInfo", result);
            modelAndView = new ModelAndView(errorMessage.getErrorPath(), map);
        }

        return modelAndView;
    }
}
