package com.yinhetianze.core.business;

import com.yinhetianze.core.business.httprequest.BasicModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator
 * on 2018/1/26.
 */
public interface BusinessExecutor<T>
{
    T execute(HttpServletRequest request, HttpServletResponse response, BasicModel model, Object...params);
}
