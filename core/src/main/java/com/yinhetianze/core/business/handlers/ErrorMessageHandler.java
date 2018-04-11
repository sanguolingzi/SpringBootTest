package com.yinhetianze.core.business.handlers;

import com.yinhetianze.core.business.httpresponse.message.ErrorMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator
 * on 2018/1/28.
 */
public interface ErrorMessageHandler<T>
{
    T handleErrorMessage(HttpServletRequest request, HttpServletResponse response, ErrorMessage errorMessage, Object...param);
}
