package com.yinhetianze.security.custom;

import com.yinhetianze.core.utils.CommonConstant;
import com.yinhetianze.core.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class CustomAbstractSecurityFilter implements Filter
{

    /**
     * 默认失败地址为空
     */
    @Value("${security.failedUrl:}")
    protected String failedUrl;

    /**
     * 公共游客访问路径设置
     */
    @Value("${security.anonymous.uris:}")
    protected String anonymousUris;

    /**
     * 是否禁用过滤器
     */
    @Value("${security.disabled:true}")
    protected Boolean disabled;

    /**
     * 访问有效期设置
     * 默认设置30分钟
     * 单位：分钟
     */
    @Value("${security.timeout:30}")
    protected Integer timeout;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
    }

    @Override
    public void destroy()
    {
    }

    /**
     * 处理错误访问的返回结果
     * @param response
     * @throws IOException
     */
    protected void sendErrorResponse(HttpServletResponse response, Integer httpStatus) throws IOException
    {
        sendErrorResponse(response, httpStatus, null);
    }

    /**
     * 处理错误访问的返回结果
     * @param response
     * @throws IOException
     */
    protected void sendErrorResponse(HttpServletResponse response, Integer httpStatus, String message) throws IOException
    {
        if (CommonUtil.isNotEmpty(this.getFailedUrl()))
        {
            response.sendRedirect(this.getFailedUrl());
        }
        else
        {
            if (CommonUtil.isEmpty(message))
            {
                response.sendError(httpStatus);
            }
            else
            {
                response.sendError(httpStatus, message);
            }
        }
    }

    /**
     * 校验请求是否不需要校验
     * @param request
     * @return
     */
    protected Boolean isAnonymousUri(HttpServletRequest request)
    {
        Collection<String> uris = initAnonymousUris(anonymousUris);
        return isAnonymousUri(request, uris);
    }

    /**
     * 校验请求是否不需要校验
     * @param request
     * @param uris
     * @return
     */
    protected Boolean isAnonymousUri(HttpServletRequest request, Collection<String> uris)
    {
        // 如果是不需要校验的地址则下一步
        String requestUri = request.getRequestURI();
        if (CommonUtil.isNotEmpty(uris))
        {
            // 如果是以/结尾，去除结尾的/, 并且去掉首尾空格
            String uri = (requestUri.lastIndexOf(CommonConstant.CHAR_SLASH) == requestUri.length() - 1
                    ? requestUri.substring(0, requestUri.length() - 1) : requestUri).trim();
            if (uris.contains(uri))
            {
                return true;
            }
        }

        return false;
    }

    /**
     * 将配置的游客访问地址以逗号分割
     * @param anonymousUris
     * @return
     */
    protected Set initAnonymousUris(String anonymousUris)
    {
        if (CommonUtil.isNotEmpty(anonymousUris))
        {
            Set<String> anonymousUriList = new HashSet<>();
            String[] anonyUris = anonymousUris.split(CommonConstant.CHAR_COMMA);
            for (String uri : anonyUris)
            {
                // 去除uri地址结尾的/
                anonymousUriList.add((uri.lastIndexOf(CommonConstant.CHAR_SLASH) == uri.length() - 1
                        ? uri.substring(0, uri.length() - 1) : uri)
                        .trim()); // 去掉首尾空格
            }
            return anonymousUriList;
        }
        else
        {
            return null;
        }
    }



    protected String getFailedUrl()
    {
        return this.failedUrl;
    }

    protected void setFailedUrl(String failedUrl)
    {
        this.failedUrl = failedUrl;
    }

    protected String getAnonymousUris()
    {
        return anonymousUris;
    }

    protected void setAnonymousUris(String anonymousUris)
    {
        this.anonymousUris = anonymousUris;
    }

    protected Boolean getDisabled()
    {
        return disabled;
    }

    protected void setDisabled(Boolean disabled)
    {
        this.disabled = disabled;
    }

    public Integer getTimeout()
    {
        return timeout;
    }

    public void setTimeout(Integer timeout)
    {
        this.timeout = timeout;
    }
}
