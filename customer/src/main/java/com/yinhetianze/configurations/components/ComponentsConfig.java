package com.yinhetianze.configurations.components;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yinhetianze.core.utils.RedisManager;
import com.yinhetianze.security.custom.CustomACLFilter;
import com.yinhetianze.security.custom.CustomAccessLimitFilter;
import com.yinhetianze.security.custom.CustomChannelCheckFilter;
import com.yinhetianze.security.custom.CustomTokenCheckFilter;
import com.yinhetianze.security.custom.userdetails.impl.RedisUserDetailsService;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Created by Administrator
 * on 2018/1/30.
 */
@Configuration
// core包中的内置实现，基础框架使用时可以使用，也可以自己实现接口定制
@MapperScan(basePackages = {"com.yinhetianze.common.business", "com.yinhetianze.business"})
@ComponentScan(basePackages = "com.yinhetianze.common.business")
@ServletComponentScan("com.yinhetianze.web")
public class ComponentsConfig
{

    /**
     * 自定义redis管理工具类注册
     * @param redisTemplate
     * @return
     */
    @Bean
    public RedisManager getRedisManager(RedisTemplate redisTemplate)
    {
        RedisManager rm = new RedisManager();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        rm.setRedisTemplate(redisTemplate);
        return rm;
    }

    //@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("PUT", "DELETE","GET","POST","OPTIONS")
                        .allowedHeaders("*")
                        .exposedHeaders("access-control-allow-headers",
                                "access-control-allow-methods",
                                "access-control-allow-origin",
                                "access-control-max-age",
                                "X-Frame-Options")
                        .allowCredentials(false).maxAge(3600);
            }
        };

    }

    /**
     *
     * @param redisManager
     * @return
     */
    //@Bean("userDetailsServiceImpl")
    public RedisUserDetailsService userDetailsServiceImpl(RedisManager redisManager)
    {
        RedisUserDetailsService userDetailsService = new RedisUserDetailsService(redisManager);
        return userDetailsService;
    }

    //@Bean
    public CustomACLFilter customACLFilter()
    {
        CustomACLFilter filter = new CustomACLFilter();
        return filter;
    }

    //@Bean
    public CustomChannelCheckFilter customChannelCheckFilter()
    {
        CustomChannelCheckFilter filter = new CustomChannelCheckFilter();
        return filter;
    }

    //@Bean
    public CustomTokenCheckFilter customTokenCheckFilter()
    {
        CustomTokenCheckFilter filter = new CustomTokenCheckFilter();
        return filter;
    }

    //@Bean
    public CustomAccessLimitFilter customAccessLimitFilter()
    {
        CustomAccessLimitFilter filter = new CustomAccessLimitFilter();
        return filter;
    }
}
