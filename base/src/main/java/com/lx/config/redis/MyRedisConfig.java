package com.lx.config.redis;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

//@Configuration
public class MyRedisConfig {
		
	private static Logger logger = Logger.getLogger(MyRedisConfig.class);  
	
	@Value("${spring.redis.host}")
	private String host;
	
	@Value("${spring.redis.port}")
	private String port;
	
	@Bean  
    public JedisPoolConfig getRedisConfig(){  
        JedisPoolConfig config = new JedisPoolConfig();
        return config;  
    }  
	
	@Bean  
    public JedisConnectionFactory getConnectionFactory(){  
        JedisConnectionFactory factory = new JedisConnectionFactory();  
        JedisPoolConfig config = getRedisConfig(); 
        factory.setPoolConfig(config); 
        factory.setHostName(host);
        factory.setPort(Integer.parseInt(port));
        logger.info("JedisConnectionFactory bean init success.");  
        return factory;  
    }  
	
	@Bean  
    public RedisTemplate<?, ?> getRedisTemplate(){  
        RedisTemplate<?,?> template = new StringRedisTemplate(getConnectionFactory());  
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new JdkSerializationRedisSerializer());
        template.afterPropertiesSet();
        return template;  
    }  
	
	@Bean
    public CacheManager cacheManager(RedisTemplate<String,Object> redisTemplate) {
        RedisCacheManager rcm = new RedisCacheManager(redisTemplate
        		,java.util.Arrays.asList(new String[]{"luoxiang","caijing"}));
        //设置缓存过期时间
        rcm.setDefaultExpiration(60);//秒
        rcm.afterPropertiesSet();
        //logger.info("cacheManager bean init success."+rcm.getCache("luoxiang")); 
        logger.info("cacheManager:"+rcm.getCacheNames());
        return rcm;
    }
}
