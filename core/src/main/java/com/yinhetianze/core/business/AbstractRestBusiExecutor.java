package com.yinhetianze.core.business;

import com.yinhetianze.core.business.handlers.ErrorMessageHandler;
import com.yinhetianze.core.business.handlers.ExceptionHandler;
import com.yinhetianze.core.business.httprequest.BasicModel;
import com.yinhetianze.core.business.httpresponse.ResponseConstant;
import com.yinhetianze.core.business.httpresponse.ResponseData;
import com.yinhetianze.core.business.httpresponse.Result;
import com.yinhetianze.core.business.httpresponse.message.ErrorMessage;
import com.yinhetianze.core.utils.CommonUtil;
import com.yinhetianze.core.utils.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator
 * on 2018/1/28.
 * 作为Rest接口响应业务处理
 */
public abstract class AbstractRestBusiExecutor<T> extends TemplateBusinessExecutor<ResponseData<T>>
{
    @Autowired(required = false)
    private ExceptionHandler<ResponseData<T>> exceptionHandler;

    @Autowired(required = false)
    private ErrorMessageHandler<ResponseData<T>> errorMessageHandler;

    @Override
    protected ResponseData<T> handleException(HttpServletRequest request, HttpServletResponse response, Exception e)
    {
        // 结果返回值
        ResponseData<T> responseData;

        // 如果有自定义异常处理器，则使用异常处理器，否则使用通用异常处理
        if (CommonUtil.isNotEmpty(exceptionHandler))
        {
            responseData = exceptionHandler.handleException(request, response, e);
            return responseData;
        }
        else
        {
            LoggerUtil.info(AbstractRestBusiExecutor.class, "没有匹配的exceptionHandler，使用默认异常处理");
            // 通用处理异常结果
            responseData = new ResponseData<T>();
            Result result = new Result("系统异常");
            responseData.setResultInfo(result);
        }

        return responseData;
    }

    @Override
    protected ResponseData<T> handleErrorMessage(HttpServletRequest request, HttpServletResponse response, ErrorMessage errorMessage)
    {
        ResponseData<T> responseData;

        // 如果有自定义消息处理器，则使用消息处理器，否则使用通用消息处理
        if (CommonUtil.isNotEmpty(errorMessageHandler))
        {
            responseData = errorMessageHandler.handleErrorMessage(request, response, errorMessage);
            return responseData;
        }
        else
        {
            LoggerUtil.info(AbstractRestBusiExecutor.class, "没有匹配的ErrorMessageHandler，使用默认信息处理");
            // 通用业务校验处理
            responseData = new ResponseData<T>();
            Result result = new Result(ResponseConstant.RESULT_CODE_BUSI_FAILED, "参数有误");
            responseData.setResultInfo(result);
        }

        return responseData;
    }

    @Override
    public final ResponseData<T> handleBusiness(HttpServletRequest request, HttpServletResponse response, BasicModel model, Object[] params) throws BusinessException
    {
        // 业务返回的数据
        T data = executeBusiness(request, response, model, params);

        // 结果处理
        ResponseData<T> responseData = new ResponseData<T>();
        responseData.setResultInfo(new Result());
        responseData.setData(data);

        return responseData;
    }

    protected abstract T executeBusiness(HttpServletRequest request, HttpServletResponse response, BasicModel model, Object...params) throws BusinessException;
}
