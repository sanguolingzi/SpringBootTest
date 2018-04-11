package com.yinhetianze.springmvc;

import com.yinhetianze.core.business.BusinessException;
import com.yinhetianze.core.business.httpresponse.ResponseConstant;
import com.yinhetianze.core.business.httpresponse.Result;
import com.yinhetianze.core.utils.CommonUtil;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by Administrator
 * 泛型是当前controller处理异常时的返回类型
 * 例如页面跳转，使用ModelAndView；接口返回，使用ResponseData
 * on 2018/1/31.
 */
public abstract class BasicController<T>
{
    @ExceptionHandler
    protected abstract  T handleException(Exception e);

    protected void handleResponse(Exception e, Result result)
    {
        if (CommonUtil.isNotEmpty(e)  && e instanceof BusinessException)
        {
            BusinessException be = (BusinessException) e;
            result.setCode(be.getErrorCode());
            result.setDescription(ResponseConstant.RESULT_DESCRIPTION_FAILED);
            result.setMessage(ResponseConstant.RESULT_MESSAGE_FAILED);
        }
    }
}
