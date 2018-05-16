package com.yinhetianze.security.custom.userdetails.impl;

import com.yinhetianze.core.utils.CommonUtil;
import com.yinhetianze.core.utils.LoggerUtil;
import com.yinhetianze.core.utils.RedisManager;
import com.yinhetianze.security.custom.userdetails.UserDetails;
import com.yinhetianze.security.custom.userdetails.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户详情信息操作接口
 * 用户详情信息通过redis管理
 */
public class RedisUserDetailsService implements UserDetailsService
{
    private RedisManager redisManager;

    public RedisUserDetailsService(RedisManager redisManager)
    {
        this.redisManager = redisManager;
    }

    @Override
    public UserDetails getUserDetails(String userId)
    {
        UserDetails userDetails = redisManager.getSerializeObject(userId, UserDetails.class);
        return userDetails;
    }

    @Override
    public void saveUserDetails(UserDetails userDetails)
    {
        if (CommonUtil.isNotEmpty(userDetails))
        {
            redisManager.setSerializeObject(userDetails.getUserId(), userDetails, RedisManager.SESSION_CACHE_TIME);
            LoggerUtil.info(RedisUserDetailsService.class, "用户[{}]登陆成功，用户标志为：[{}]", new Object[]{userDetails.getUsername(), userDetails.getUserId()});
        }
        else
        {
            LoggerUtil.info(RedisUserDetailsService.class, "用户[{}]登陆失败，用户标志为：[{}]", new Object[]{userDetails.getUsername(), userDetails.getUserId()});
        }
    }

    @Override
    public void deleteUserDetails(String userId)
    {
        if (CommonUtil.isNotEmpty(userId))
        {
            UserDetails user = redisManager.getSerializeObject(userId, UserDetails.class, true);
            if (CommonUtil.isNotEmpty(user))
            {
                LoggerUtil.info(RedisUserDetailsService.class, "删除用户[{}]信息成功，用户[{}]退出", new Object[]{userId, user.getUsername()});
            }
            else
            {
                LoggerUtil.info(RedisUserDetailsService.class, "删除用户信息未成功，用户[{}]不存在", new Object[]{user.getUserId()});
            }
        }
    }

    @Override
    public void updateUserDetails(UserDetails userDetails)
    {
        if (CommonUtil.isNotEmpty(userDetails))
        {
            UserDetails user = getUserDetails(userDetails.getUserId());
            if (CommonUtil.isNotEmpty(user))
            {
                redisManager.setSerializeObject(userDetails.getUserId(), userDetails, RedisManager.SESSION_CACHE_TIME);
                LoggerUtil.info(RedisUserDetailsService.class, "登陆用户[{}]信息更新，用户ID为：{}", new Object[]{userDetails.getUsername(), userDetails.getUserId()});
            }
            else
            {
                LoggerUtil.info(RedisUserDetailsService.class, "用户[{}]信息未更新成功", new Object[]{userDetails.getUsername()});
            }
        }
    }

}
