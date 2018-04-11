package com.yinhetianze.core.cache;

import com.yinhetianze.core.utils.CommonUtil;
import com.yinhetianze.core.utils.LoggerUtil;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;

/**
 * ehcache数据缓存工厂类
 * 缓存数据存放于ehcache组件中
 * @author Administrator
 *
 */
public class EhcacheDataFactory
{
    private static EhcacheDataFactory instance;
    
    public static final String SYS_CACHE = "sysCache"; // 默认存储数据的cacheName
    
    public static final String BUSI_CACHE = "busiCache"; // 业务数据本地临时缓存
    
    public synchronized static EhcacheDataFactory getInstance()
    {
        if (CommonUtil.isEmpty(instance))
        {
            instance = new EhcacheDataFactory();
        }
        return instance;
    }
    
    /**
     * 重载setCacheData(String cacheName, String key, Object data)
     * 根据isSysCache存储到系统缓存中，数据有效期随应用开始和结束
     * @param key
     * @param data
     * @param isSysCache
     */
    public void setCacheData(Boolean isSysCache, String key, Object data)
    {
        String cacheName = BUSI_CACHE;
        if (isSysCache)
        {
            cacheName = SYS_CACHE;
        }
        setCacheData(cacheName, key, data);
    }
    
    /**
     * 重载setCacheData(String cacheName, String key, Object data)
     * 默认存储到busi_cache中
     * @param key
     * @param data
     */
    public void setCacheData(String key, Object data)
    {
        setCacheData(BUSI_CACHE, key, data);
    }
    
    /**
     * 保存数据到指定的cache中
     * 如果cache不存在，存放到业务缓存中临时保存
     * 如果业务临时缓存不存在，新建busiCache存放临时业务数据
     * @param cacheName
     * @param key
     * @param data
     */
    public synchronized void setCacheData(String cacheName, String key, Object data)
    {
        CacheManager cacheManager = null;
        
        try
        {
            cacheManager = CacheManager.getInstance();
            Cache cache = cacheManager.getCache(cacheName);
            
            // 数据键值对
            Element e = new Element(key, data);
            // 如果缓存存在，直接存储
            if (CommonUtil.isNotEmpty(cache))
            {
                cache.put(e);
            }
            else
            {
                // 如果缓存不存在，先判断busi_cache存在否，不存在则新增，存在则存入数据
                if (CommonUtil.isEmpty(cacheManager.getCache(BUSI_CACHE)))
                {
                    LoggerUtil.info(EhcacheDataFactory.class, "busiCache不存在，新建busiCache");
                    // 创建一个临时业务数据存放的busiCache，临时存放时间120秒
                    cache = new Cache(new CacheConfiguration().name(BUSI_CACHE)
                            .eternal(false).maxEntriesLocalHeap(10000)
                            .overflowToOffHeap(true).maxElementsOnDisk(100000)
                            .timeToIdleSeconds(120).timeToLiveSeconds(120));
                    
                    // 添加到缓存管理单例中
                    cacheManager.addCache(cache);
                    LoggerUtil.info(EhcacheDataFactory.class, "新建busiCache完成");
                }
                
                cache.put(e);
            }
        }
        catch (Exception e)
        {
            LoggerUtil.error(EhcacheDataFactory.class, "Ehcache未被装载或发生异常导致获取缓存失败！异常信息：{}", new Object[]{e.toString()});
            return;
        }
    }
    
    /**
     * 重载getCacheData(String cacheName, String key)
     * @param key
     * @return
     */
    public Object getCacheData(String key)
    {
        return getCacheData(BUSI_CACHE, key);
    }
    
    /**
     * 从缓存中获取值，默认从业务临时缓存中获取
     * @param cacheName
     * @param key
     * @return
     */
    public Object getCacheData(String cacheName, String key)
    {
        // key值为空不操作
        if (CommonUtil.isEmpty(key))
        {
            return null;
        }
        
        try
        {
            CacheManager cm = CacheManager.getInstance();
            // 缓存名字为空默认获取sysCache
            if (CommonUtil.isEmpty(cacheName))
            {
                cacheName = BUSI_CACHE;
            }
            
            // cache不为空返回cache中key值对应的value
            Cache cache = cm.getCache(cacheName);
            if (CommonUtil.isNotEmpty(cache))
            {
                Element e = cache.get(key);
                if (CommonUtil.isNotEmpty(e))
                {
                    return e.getObjectValue();
                }
            }
        }
        catch (Exception e)
        {
            LoggerUtil.error(EhcacheDataFactory.class, "获取缓存数据发生异常!{}", new Object[]{e.toString()});
        }
        
        return null;
    }
}
