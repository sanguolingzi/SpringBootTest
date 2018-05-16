package com.yinhetianze.security.custom.userdetails.mapper;

import java.util.*;

public interface IntfPatternInfoMapper
{
    /**
     * 根据资源地址获取资源详细信息
     * @param pattern
     * @return
     */
    Map<String, Object> findIntfPattern(String pattern);

    /**
     * 根据条件查询所有符合的资源
     * @param params
     * @return
     */
    LinkedHashSet<Map<String, Object>> getIntfPattern(Map<String, Object> params);

    /**
     * 根据角色列表查询所有符合当前角色的资源
     * @param roles
     * @return
     */
    Set<Map<String, Object>> getIntfPatternRes(Set<String> roles);
}
