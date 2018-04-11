package com.yinhetianze.core.cache;

import com.yinhetianze.core.utils.ApplicationContextFactory;
import com.yinhetianze.core.utils.CommonUtil;
import com.yinhetianze.core.utils.LoggerUtil;
import com.yinhetianze.core.utils.RedisManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 抽象静态数据类，处理公共静态数据缓存方法
 * 默认初始化缓存名字为类名
 * @author Administrator
 *
 */
@SuppressWarnings("unchecked")
public abstract class RedisCacheDataManager<T> extends AbstractCacheDataManager<T>
{
    private static Logger logger = LoggerFactory.getLogger(RedisCacheDataManager.class);
    
    public RedisCacheDataManager()
    {
        super();
        logger.info("初始化Redis缓存：", getCacheName());
    }
    
    /**
     * redis默认获取缓存数据
     */
    public T getCacheData()
    {
        Object bean = ApplicationContextFactory.getBean("redisManager");
        if (CommonUtil.isNotEmpty(bean)
                && bean instanceof RedisManager)
        {
            RedisManager manager = (RedisManager) bean;
            
            // 先从缓存中获取缓存数据，如果不存在，重新获取子类实现数据，否则返回缓存数据
            Object obj = manager.getValue(cacheName);
            if (CommonUtil.isEmpty(obj))
            {
                try
                {
                    return refreshCache();
                }
                catch (Exception e)
                {
                    logger.error("刷新redis中的缓存数据发生异常：{}", e.toString());
                }
            }
            else
            {
                return (T) obj;
            }
            
        }
        
        return null;
    }

    /**
     * 更新redis中的数据
     * @param obj
     */
    @Override
    public void handleData(Object obj)
    {
        // 如果数据不为空，放入redis保存一天
        if (CommonUtil.isNotEmpty(obj))
        {
            try
            {
                RedisManager manager = (RedisManager) ApplicationContextFactory.getBean("redisManager");
                manager.setValue(cacheName, obj, RedisManager.DAY_TIME);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                LoggerUtil.error(RedisCacheDataManager.class, "刷新redis中的缓存数据发生异常：{}", new String[]{e.toString()});
            }
        }
    }
    
    /**
     * 静态数据实现类实现方法
     * 获取需要缓存的数据
     * @return
     */
    public abstract T cacheData();

}
