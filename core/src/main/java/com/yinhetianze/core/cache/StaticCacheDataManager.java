package com.yinhetianze.core.cache;

import com.yinhetianze.core.utils.CommonUtil;

/**
 * 静态缓存抽象基类
 * 凡是存储在服务器缓存中的实现类均继承此类
 * @author Administrator
 *
 */
@SuppressWarnings("unchecked")
public abstract class StaticCacheDataManager<T> extends AbstractCacheDataManager<T>
{

    /**
     * 获取缓存数据
     * 先从静态缓存中获取数据
     * 如果没有，再从具体实现中获取新数据，并吧刷新的数据存储在缓存中
     */
    public T getCacheData()
    {
        Object data = CacheDataFactory.getInstance().getCacheData(getCacheName());
        if (CommonUtil.isNotEmpty(data))
        {
            return (T) data;
        }
        else
        {
            data = refreshCache();
            if (CommonUtil.isNotEmpty(data))
            {
                return (T) data;
            }
        }
        return null;
    }

    /**
     * 如果缓存数据不为空，将数据保存到到cachedatafactory的静态集合中
     */
    @Override
    public void handleData(Object obj)
    {
        if (CommonUtil.isNotEmpty(obj))
        {
            CacheDataFactory.getInstance().setCacheData(getCacheName(), obj);
        }
    }

}
