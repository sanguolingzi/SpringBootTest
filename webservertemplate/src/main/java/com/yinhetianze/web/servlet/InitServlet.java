package com.yinhetianze.web.servlet;

import com.yinhetianze.core.utils.ApplicationContextFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Created by Administrator
 * on 2018/1/28.
 */
@WebServlet(loadOnStartup = 2, name = "initServlet", value = "")
public class InitServlet extends HttpServlet
{
    @Override
    public void init(ServletConfig config) throws ServletException
    {
        // 初始化spring容器工具类
        ApplicationContextFactory.init(config.getServletContext());
    }
}
