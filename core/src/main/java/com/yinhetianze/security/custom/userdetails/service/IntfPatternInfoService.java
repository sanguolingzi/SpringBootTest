package com.yinhetianze.security.custom.userdetails.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IntfPatternInfoService
{
    Map<String, Object> findIntfPattern(String pattern);

    Collection<Map<String, Object>> getIntfPattern(Map<String, Object> params);

    Set<Map<String, Object>> getIntfPattern(Set<String> params);
}
