package com.yinhetianze.security.custom;

import com.yinhetianze.core.utils.CommonUtil;
import com.yinhetianze.core.utils.LoggerUtil;
import com.yinhetianze.core.utils.RedisManager;
import com.yinhetianze.security.custom.userdetails.UserDetails;
import com.yinhetianze.security.custom.userdetails.UserDetailsService;
import com.yinhetianze.security.custom.userdetails.impl.TokenUser;
import com.yinhetianze.security.custom.util.CustomSecurityHeader;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

/**
 * token有效校验过滤器
 * 此过滤器需要配合com.yinhetianze.security.custom.impl.TokenUser使用
 * token是否在有效时间内判断，token在即将过期的5分钟时自动刷新
 */
@Order(2)
public class CustomTokenCheckFilter extends CustomAbstractSecurityFilter
{
    @Autowired
    private UserDetailsService userDetailsServiceImpl;

    @Value("${security.token.check.disabled:}")
    private Boolean disabled;
    /**
     * 去重设置，内容为anonymousUris分割后的去重集合
     */
    private Set<String> anonymousUriList;

    @Value("${security.token.check.anonymous.uris:}")
    private String anonymousUris;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        if (CommonUtil.isEmpty(disabled))
        {
            this.disabled = super.disabled;
        }
        if (CommonUtil.isEmpty(anonymousUris))
        {
            this.anonymousUris = super.anonymousUris;
        }

        this.anonymousUriList = initAnonymousUris(this.anonymousUris);
        if (CommonUtil.isNotEmpty(failedUrl))
        {
            anonymousUriList.add(this.failedUrl);
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        // 是否禁用
        if (CommonUtil.isEmpty(disabled) || disabled)
        {
            chain.doFilter(req, resp);
            return;
        }

        // 如果是不需要校验的地址则下一步
        if (isAnonymousUri(req, anonymousUriList))
        {
            chain.doFilter(request, response);
            return;
        }

        // token 有效性校验
        String token = req.getHeader(CustomSecurityHeader.SECURITY_REQUEST_TOKEN);
        if (CommonUtil.isNotEmpty(token))
        {
            // 已登录用户信息
            UserDetails userDetails = userDetailsServiceImpl.getUserDetails(token);
            if (userDetails instanceof TokenUser)
            {
                TokenUser user = (TokenUser) userDetails;
                Date expireTime = user.getExpireTime(); // 过期时间
                if (CommonUtil.isEmpty(expireTime))
                {
                    // 为空时默认为创建时间延长配置的分钟数
                    expireTime = new Date(user.getCreateTime().getTime() + getTimeout() * 60 * 1000);
                }

                // 时间控制
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.MINUTE, 5); // 设置延迟5分钟
                Date expireTimeDelay = cal.getTime(); // 延迟5分钟的时间对象

                // 是否过期
                if (expireTime.before(new Date()))
                {
                    // 已经过期
                    LoggerUtil.info(CustomTokenCheckFilter.class, "用户[{}]登陆请求已经超时，用户标志：[{}]", new Object[]{user.getUsername(), user.getUserId()});
                    sendErrorResponse(resp, HttpStatus.SC_REQUEST_TIMEOUT, "访问被禁止");
                    return;
                }
                else if (expireTime.before(expireTimeDelay))
                {
                    // 过期时间离当前时间只有5分钟时，刷新token
                    user.setExpireTime(new Date(new Date().getTime() + getTimeout() * 60 * 1000));
                    userDetailsServiceImpl.updateUserDetails(user);
                }
            }
            else
            {
                LoggerUtil.info(CustomTokenCheckFilter.class, "Token信息无效，请确定UserDetails使用的是TokenUser的实现");
                sendErrorResponse(resp, HttpStatus.SC_FORBIDDEN);
                return;
            }

            chain.doFilter(request, response);
        }
        else
        {
            sendErrorResponse(resp, HttpStatus.SC_FORBIDDEN, "访问被禁止");
            return;
        }
    }
}
