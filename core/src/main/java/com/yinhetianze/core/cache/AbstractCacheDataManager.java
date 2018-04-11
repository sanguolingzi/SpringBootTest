package com.yinhetianze.core.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 缓存实现的抽象模板类
 * @author Administrator
 *
 * @param <T>
 */
public abstract class AbstractCacheDataManager<T> implements CacheDataManager<T>
{
    /**
     * cache拓展名
     */
    public static final String CACHE_EXT = "_cache";

    private static Logger logger = LoggerFactory.getLogger(AbstractCacheDataManager.class);
    
    /**
     * cache名
     */
    protected String cacheName;
    
    /**
     * 初始化默认缓存名字
     */
    public AbstractCacheDataManager()
    {
        this.cacheName = this.getClass().getSimpleName() + CACHE_EXT;
    }

    public String getCacheName()
    {
        return cacheName;
    }
    
    /**
     * 刷新缓存
     */
    public T refreshCache()
    {
        try
        {
            // 获取缓存数据
            T obj = cacheData();
            
            // 保存缓存数据
            handleData(obj);
            
            return obj;
        }
        catch (Exception e)
        {
            logger.error("处理缓存数据异常：" + e.toString());
            return null;
        }
    }
    
    /**
     * 获取数据
     * 例如从数据库中获取数据，或者从接口调用返回的数据，通过此方法进行抓取需要的数据
     * @return
     */
    public abstract T cacheData();

    /**
     * 刷新的或者抓取到的数据需要如何缓存
     * @param obj 刷新的数据
     */
    public abstract void handleData(Object obj);

}
