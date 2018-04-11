package com.yinhetianze.web.filter;

import com.yinhetianze.core.utils.LoggerUtil;
import org.tuckey.web.filters.urlrewrite.Conf;
import org.tuckey.web.filters.urlrewrite.UrlRewriteFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import java.net.URL;

@WebFilter(urlPatterns = "/*", dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class UrlRewriter extends UrlRewriteFilter
{
    @Override
    protected void loadUrlRewriter(FilterConfig filterConfig) throws ServletException
    {
        Conf conf = null;
        try
        {
            URL url = getClass().getResource("/urlrewriter.xml");
            conf = new Conf(url);
        }
        catch (Exception e)
        {
            LoggerUtil.error(UrlRewriteFilter.class, e);
            throw new ServletException("URL Rewriter 加载配置失败！", e);
        }

        checkConf(conf);
    }
}
