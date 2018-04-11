package com.yinhetianze.core.cache;

import com.yinhetianze.core.utils.ApplicationContextFactory;
import com.yinhetianze.core.utils.CommonUtil;

import java.util.List;

/**
 * 缓存配置类，初始化cacheDataManager接口的所有实现类
 * @author Administrator
 *
 */
public class CacheDataConfig
{
    
    /**
     * cacheDataManager实现类ID集合
     * 在spring-datachace.xml中配置
     * 配置为启动时加载的缓存项
     */
    private List<String> cacheDataBeanIdList;

    public List<String> getCacheDataBeanIdList()
    {
        return cacheDataBeanIdList;
    }

    public void setCacheDataBeanIdList(List<String> cacheDataBeanIdList)
    {
        this.cacheDataBeanIdList = cacheDataBeanIdList;
    }
    
    /**
     * 初始化所有配置的系统需要缓存的cacheDataManager实现类
     */
    @SuppressWarnings("rawtypes")
    public void initCacheBean()
    {
        if (CommonUtil.isNotEmpty(cacheDataBeanIdList))
        {
            // 循环获取cacheDataManager进行缓存数据refresh操作
            Object obj = null;
            for (String cacheDataManager : cacheDataBeanIdList)
            {
                obj = ApplicationContextFactory.getBean(cacheDataManager);
                if (CommonUtil.isNotEmpty(obj))
                {
                    CacheDataManager manager = (CacheDataManager) obj;
                    manager.refreshCache();
                }
            }
        }
    }
    
}
