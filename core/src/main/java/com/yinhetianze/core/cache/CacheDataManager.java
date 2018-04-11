package com.yinhetianze.core.cache;

/**
 * 刷新缓存接口
 * @author Administrator
 *
 */
public interface CacheDataManager<T>
{
    
    /**
     * 刷新缓存
     */
    public T refreshCache();
    
    /**
     * 获取缓存中的数据
     * @return
     */
    public T getCacheData();
    
}
