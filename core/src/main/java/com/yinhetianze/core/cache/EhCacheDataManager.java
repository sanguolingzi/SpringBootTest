package com.yinhetianze.core.cache;

import com.yinhetianze.core.utils.CommonUtil;
import com.yinhetianze.core.utils.LoggerUtil;

/**
 * 存储缓存数据放到ehcache中
 * @author Administrator
 *
 */
@SuppressWarnings("unchecked")
public abstract class EhCacheDataManager<T> extends AbstractCacheDataManager<T>
{

    /**
     * 从ehcache中获取数据
     */
    public T getCacheData()
    {
        Object data = EhcacheDataFactory.getInstance().getCacheData(getCacheName());
        if (CommonUtil.isEmpty(data))
        {
            // 如果缓存中为空，重新获取缓存
            data = refreshCache();
            if (CommonUtil.isNotEmpty(data))
            {
                return (T) data;
            }
            else
            {
                return null;
            }
        }
        return (T) data;
    }

    @Override
    public void handleData(Object obj)
    {
        // 如果数据不为空，放入ehcache中永久保存
        if (CommonUtil.isNotEmpty(obj))
        {
            try
            {
                EhcacheDataFactory.getInstance().setCacheData(getCacheName(), obj);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                LoggerUtil.error(RedisCacheDataManager.class, "刷新ehcache中的缓存数据发生异常：{}", new String[]{e.toString()});
            }
        }
    }

}
