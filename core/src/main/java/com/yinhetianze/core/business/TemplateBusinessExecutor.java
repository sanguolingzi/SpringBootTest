package com.yinhetianze.core.business;

import com.yinhetianze.core.business.httprequest.BasicModel;
import com.yinhetianze.core.business.httpresponse.message.ErrorMessage;
import com.yinhetianze.core.utils.CommonUtil;
import com.yinhetianze.core.utils.LoggerUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator
 * on 2018/1/28.
 */
public abstract class TemplateBusinessExecutor<T> implements BusinessExecutor<T>
{
    public T execute(HttpServletRequest request, HttpServletResponse response, BasicModel model, Object... params)
    {
        // 校验
        ErrorMessage errorMessage = validate(request, model, params);
        if (CommonUtil.isNotEmpty(errorMessage) && errorMessage.hasError())
        {
            // 对校验信息进行处理
            return handleErrorMessage(request, response, errorMessage);
        }

        try
        {
            // 执行业务
            return handleBusiness(request, response, model, params);
        }
        catch (Exception e)
        {
            LoggerUtil.error(getClass(), e);
            return handleException(request, response, e);
        }
    }

    protected abstract T handleException(HttpServletRequest request, HttpServletResponse response, Exception e);

    /**
     * 处理入参校验结果
     * @param request
     * @param response
     * @param errorMessage
     * @return T
     */
    protected abstract T handleErrorMessage(HttpServletRequest request, HttpServletResponse response, ErrorMessage errorMessage);

    /**
     * 执行业务前的请求入参校验方法
     * 默认不做校验，返回空
     * 子类添加校验重写此方法
     * @param request
     * @param model
     * @param params
     * @return
     */
    protected ErrorMessage validate(HttpServletRequest request, BasicModel model, Object[] params)
    {
        return null;
    }

    /**
     * 结果处理
     * @param request
     * @param response
     * @param model
     * @param params
     * @return T
     */
    protected abstract T handleBusiness(HttpServletRequest request, HttpServletResponse response, BasicModel model, Object[] params) throws BusinessException;
}
