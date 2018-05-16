package com.yinhetianze.security.custom.userdetails;

import java.io.Serializable;
import java.util.Collection;

/**
 * 用户信息实体信息接口
 */
public interface UserDetails extends Serializable
{
    /**
     * 获取用户唯一标志
     * 不同的实现类可以自定义返回各自的用户标志
     * 例如：token用户使用的token，数据库用户的用户名，openID等
     * @return
     */
    String getUserId();

    String getUsername();

    String getPassword();

    /**
     * 获取权限与角色集合
     * @return
     */
    Collection<String> getAuthorizes();
}
