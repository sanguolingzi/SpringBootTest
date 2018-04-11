package com.yinhetianze.core.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.SerializationUtils;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 没有使用@server，在确认需要的时候在配置文件中添加配置
 * @author Administrator
 *
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class RedisManager
{
    /**
     * 1分钟
     */
    public static final Long ONE_MINUTE = 60000L; 
    
    /**
     * 5分钟
     */
    public static final Long FIVE_MINUTE = 300000L; 
    
    /**
     * 10分钟
     */
    public static final Long TEN_MINUTE = 600000L; 
    
    /**
     * 15分钟
     */
    public static final Long QUARTER = 900000L; 
    
    /**
     *  半小时
     */
    public static final Long SESSION_CACHE_TIME = 1800000L;
    
    /**
     * 1小时
     */
    public static final Long ONE_HOUR = 3600000L;
    
    /**
     * 2小时
     */
    public static final Long TWO_HOUR = 7200000L;
    
    /**
     * 12小时
     */
    public static final Long HALF_DAY_TIME = 43200000L;
    
    /**
     * 24小时
     */
    public static final Long DAY_TIME = 86400000L;
    
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 设置持久化存值
     * @param key
     * @param data
     */
    public void setPersistValue(String key, Object data)
    {
        ValueOperations<String, Object> keyObj = redisTemplate.opsForValue();
        keyObj.set(key, data);
    }

    /**
     * redis设置值，存储默认保存时间15分钟
     * @param key
     * @param data
     */
    public void setValue(String key, Object data)
    {
        setValue(key, data, QUARTER);
    }

    /**
     * 指定redis设置值，存储指定时间，单位毫秒
     * @param key
     * @param data
     * @param millionSeconds
     */
    public void setValue(String key, Object data, Long millionSeconds)
    {
        ValueOperations<String, Object> keyObj = redisTemplate.opsForValue();
        keyObj.set(key, data, millionSeconds, TimeUnit.MILLISECONDS);
    }
    
    /**
     * 获取redis中的值，不移除
     * @param key
     * @return
     */
    public Object getValue(String key)
    {
        return getValue(key, false);
    }
    
    /**
     * 获取redis中的值，返回int类型，不移除
     * @param key
     * @return
     */
    public Object getIntValue(String key)
    {
        Object obj = getValue(key, false);
        return CommonUtil.isNotEmpty(obj) ? Integer.parseInt(obj.toString()) : null;
    }
    
    /**
     * 获取并从redis中移除存储的值
     * @param key
     * @param needRemove
     * @return
     */
    public Object getValue(String key, Boolean needRemove)
    {
        Object value = null;
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        if (CommonUtil.isNotEmpty(needRemove) && needRemove)
        {
            value = ops.get(key);
            redisTemplate.delete(key);
        }
        else
        {
            value = ops.get(key);
        }
        return value;
    }

    /**
     * 删除redis中的值
     * @param key
     * @return
     */
    public Object deleteValue(String key)
    {
        Object value = null;
        if (CommonUtil.isNotEmpty(key))
        {
            value = redisTemplate.opsForValue().get(key);
            redisTemplate.delete(key);
        }
        
        return value;
    }

    public List getList(String key)
    {
        return getList(key, 0l, null);
    }
    
    public List getList(String key, Long size)
    {
        return getList(key, 0l, size);
    }

    /**
     * 获取存储的list，与setlist匹配对应，只能获取setlist中的内容
     * @param key
     * @param index
     * @param size
     * @return
     */
    public List getList(String key, Long index, Long size)
    {
        if (CommonUtil.isEmpty(key))
        {
            return null;
        }
        
        ListOperations<String, Object> opsList = redisTemplate.opsForList();
        return opsList.range(key, CommonUtil.isEmpty(index) 
                ? 0 : index, CommonUtil.isEmpty(size) ? opsList.size(key) : size);
    }

    public void updateList(String key, List listData)
    {
        if (CommonUtil.isNotEmpty(listData))
        {
            ListOperations<String, Object> listOperation = redisTemplate.opsForList();
            listOperation.leftPushAll(key, listData);
        }
    }
    
    public Long clearList(String key)
    {
        ListOperations<String, Object> listOperation = redisTemplate.opsForList();
        Long result = listOperation.size(key);
        
        redisTemplate.delete(key);
        return result;
    }
    
    /**
     * 将对象使用redis的序列化方法转成数组
     * @param obj
     * @return
     */
    public byte[] valueSerialize(Object obj)
    {
        if (CommonUtil.isNotEmpty(obj))
        {
            return redisTemplate.getValueSerializer().serialize(obj);
        }
        return null;
    }
    
    /**
     * redis序列化存对象
     * 默认存储5分钟
     * @param key
     * @param obj
     */
    public void setSerializeObject(String key, Serializable obj)
    {
        setSerializeObject(key, obj, FIVE_MINUTE);
    }
    
    /**
     * redis序列化存对象
     * @param key 存储的键
     * @param obj 存储的值
     * @param millionSeconds redis中存储多久
     */
    public void setSerializeObject(String key, Serializable obj, Long millionSeconds)
    {
        if (CommonUtil.isNotEmpty(obj))
        {
            byte[] byteObj = SerializationUtils.serialize(obj);
            ValueOperations<String, Object> keyObj = redisTemplate.opsForValue();
            keyObj.set(key, byteObj, millionSeconds, TimeUnit.MILLISECONDS);
        }
    }

    /**
     * 设置永久的序列化数据到redis
     * @param key
     * @param obj
     */
    public void setPersistSerializeObject(String key, Serializable obj) {
        if (CommonUtil.isNotEmpty(obj))
        {
            byte[] byteObj = SerializationUtils.serialize(obj);
            setPersistValue(key, byteObj);
        }
    }
    
    /**
     * redis获取序列化对象
     * @param key
     * @param cls
     * @return
     */
    public <T> T getSerializeObject(String key, Class<T> cls)
    {
        return getSerializeObject(key, cls, false);
    }
    
    /**
     * redis获取序列化对象
     * @param key 匹配的键
     * @param cls 获取的值的类型
     * @param needRemove 是否存redis中清除
     * @return
     */
    public <T> T getSerializeObject(String key, Class<T> cls, Boolean needRemove)
    {
        if (CommonUtil.isNotEmpty(key))
        {
            byte[] value = null;
            ValueOperations<String, Object> ops = redisTemplate.opsForValue();
            try
            {
	            if (CommonUtil.isNotEmpty(needRemove) && needRemove)
	            {
	                value = (byte[]) ops.get(key);
	                redisTemplate.delete(key);
	            }
	            else
	            {
	                value = (byte[]) ops.get(key);
	            }
            }
            catch (Exception e)
            {
            	LoggerUtil.error(RedisManager.class, e);
            	return null;
            }
            
            return (T) SerializationUtils.deserialize(value);
        }
        return null;
    }
    
    /**
     * 反序列化对象
     * @param byteObj
     * @return
     */
    public Object serializeObject(byte[] byteObj)
    {
        if (CommonUtil.isEmpty(byteObj))
        {
            return null;
        }
        return redisTemplate.getValueSerializer().deserialize(byteObj);
    }
    
    /**
     * 返回剩余过期时间
     * @param key
     * @return
     */
    public Long getExpireTime(String key)
    {
        if (CommonUtil.isNotEmpty(key))
        {
            return redisTemplate.getExpire(key, TimeUnit.MILLISECONDS);
        }
        return null;
    }
    
    /**
     * 判断redis中是否含有此key
     * @param key
     * @return
     */
    public boolean exist(String key)
    {
        return redisTemplate.hasKey(key);
    }
    
    public RedisTemplate getRedisTemplate()
    {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate)
    {
        this.redisTemplate = redisTemplate;
    }
    
    public boolean isRedisAlive()
    {
        return this.redisTemplate.isExposeConnection();
    }

    public static void main(String[] args)
    {
        /*ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring-redis.xml");
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("D:\\zhangwenbin\\codes\\mall\\trunk\\HYCMall\\mallintf\\src\\main\\resources\\spring\\spring-redis.xml");
        
        RedisTemplate redisTemplate = (RedisTemplate) context.getBean("jedisTemplate");
        
        RedisManager fa = new RedisManager();
        fa.setRedisTemplate(redisTemplate);
        
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        ops.set("testtest", "asdfasdfaSD", 2000, TimeUnit.MILLISECONDS);
        System.err.println(ops.getOperations());
        System.err.println(ops.get("testtest"));
        System.err.println(ops.size("testtest"));
        
        fa.setValue("test", "test", 10000l);
        System.err.println(fa.getValue("S2rY77wcs7bu6Jr5S8MlJQ=="));
        System.err.println(fa.deleteValue("test"));
        System.err.println(fa.getValue("test"));*/
        
        /*ListOperations<String, XmlBean> lo = redisTemplate.opsForList();
        XmlBean xb = new XmlBean();*/
       /* xb.setPassword("123123");
        xb.setUsername("dada");
        lo.rightPush("lo", xb);
        
        xb = new XmlBean();
        xb.setPassword("23113");
        xb.setUsername("gadf");
        lo.rightPush("lo", xb);*/
//        System.err.println(lo.leftPop("lo"));
//        System.err.println(lo.leftPop("lo"));
        /*java.util.List<XmlBean> lis = lo.range("lo", 5, lo.size("lo"));
        System.err.println(lis.size());
        for (int i = 0; i < lis.size(); i ++)
        {
            System.err.println(lis.get(i));
        }*/
        
//        fa.deleteValue("lo");
    }
}
