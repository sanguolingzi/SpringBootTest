package com.yinhetianze.core.cache;

import com.yinhetianze.core.utils.CommonUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 静态数据缓存工厂类
 * 缓存数据存放于此类的静态缓存对象中
 * @author Administrator
 *
 */
public class CacheDataFactory
{
    private static CacheDataFactory instance;
    
    private static Map<String, Object> cacheData;
    
    private CacheDataFactory()
    {
        cacheData = new HashMap<String, Object>();
    }
    
    public synchronized static CacheDataFactory getInstance()
    {
        if (CommonUtil.isEmpty(instance))
        {
            instance = new CacheDataFactory();
        }
        return instance;
    }
    
    public synchronized void setCacheData(String key, Object data)
    {
        if (CommonUtil.isNotEmpty(key))
        {
            cacheData.put(key, data);
        }
    }
    
    public Object getCacheData(String key)
    {
        if (CommonUtil.isNotEmpty(key))
        {
            return cacheData.get(key);
        }
        return null;
    }
    
}
