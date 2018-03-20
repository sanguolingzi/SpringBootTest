package com.lx;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


/**
 * 服务消费方
 * @author luoxiang
 *
 */
@SpringBootApplication
@EnableCaching
//@EnableRedisHttpSession(maxInactiveIntervalInSeconds=300)
public class MainStart{
	
	public static void main(String[] args) {
        SpringApplication.run(new Object[]{MainStart.class}, args);
        
    }
}