package com.yinhetianze.security.custom.userdetails.impl;

import com.yinhetianze.security.custom.userdetails.HttpUserDetails;
import com.yinhetianze.security.custom.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 * 使用token令牌方式对应的用户登陆信息
 */
//public class TokenUser implements UserDetails
public class TokenUser implements HttpUserDetails
{
    /**
     * 登陆用户名
     */
    private final String username;

    /**
     * 登陆用户密码
     */
    private String password;

    /**
     * 用户token标志
     */
    private String token;

    /**
     * 用户拥有的权限
     */
    private Collection<String> authorizes;

    /**
     * 用户可访问的资源
     */
    private Collection<String> authorizedRes;

    /**
     * 创建时间
     */
    private final Date createTime;

    /**
     * 过期时间
     */
    private Date expireTime;

    private final String ipAddress;

    private final String sessionId;

    public TokenUser(String username, String password, String token, Collection<String> authorizes)
    {
        this(username, password, token, authorizes, null);
    }

    public TokenUser(String username, String password, String token, Collection<String> authorizes, String ipAddress)
    {
        this(username, password, token, authorizes, null, ipAddress);
    }

    public TokenUser(String username, String password, String token, Collection<String> authorizes, Collection<String> authorizedRes, String ipAddress)
    {
        this(username, password, token, authorizes, authorizedRes, ipAddress, null);
    }

    public TokenUser(String username, String password, String token, Collection<String> authorizes, Collection<String> authorizedRes, String ipAddress, String sessionId)
    {
        this.username = username;
        this.password = password;
        this.token = token;
        this.authorizes = authorizes;
        this.authorizedRes = authorizedRes;
        this.ipAddress = ipAddress;
        this.sessionId = sessionId;
        this.createTime = new Date();
    }

    @Override
    public String getUserId()
    {
        return this.token;
    }

    @Override
    public String getUsername()
    {
        return this.username;
    }

    @Override
    public String getPassword()
    {
        return this.password;
    }

    @Override
    public Collection<String> getAuthorizes()
    {
        return this.authorizes;
    }

    public Collection<String> getAuthorizedRes()
    {
        return this.authorizedRes;
    }

    public String getToken()
    {
        return this.token;
    }

    public Date getExpireTime()
    {
        return expireTime;
    }

    public void setExpireTime(Date expireTime)
    {
        this.expireTime = expireTime;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    @Override
    public String getIpAddress()
    {
        return ipAddress;
    }

    public String getSessionId()
    {
        return sessionId;
    }
}
