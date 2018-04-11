package com.yinhetianze.core.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * 自定义本地spring容器管理类
 * @author Administrator
 *
 */
@Service
public class ApplicationContextFactory
{
    /**
     * 由Servlet容器注入Context
     */
    private static ServletContext sc;
    
    /**
     * 初始化ServletContext
     * @param sc
     * @throws ServletException
     */
    public static void init(ServletContext sc)
    {
        Assert.notNull(sc, "ServletContext can not be null!");
        ApplicationContextFactory.sc = sc;
    }
    
    public static ApplicationContext getInstance()
    {
        // 通过servlet容器获取application
        ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(sc);
        return ac;
    }
    
    public static ServletContext getServletContext()
    {
        return sc;
    }
    
    /**
     * 获取spring托管bean的方法
     * @param beanId
     * @return
     */
    public static Object getBean(String beanId)
    {
        Object bean = null;
        
        try
        {
            bean = getInstance().getBean(beanId);
        }
        catch (Exception e)
        {
            LoggerUtil.error(ApplicationContextFactory.class, e);
        }
        
        return bean;
    }

}
