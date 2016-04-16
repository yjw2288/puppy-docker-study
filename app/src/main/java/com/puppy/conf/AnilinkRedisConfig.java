package com.puppy.conf;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@Configuration
@EnableCaching
public class AnilinkRedisConfig {

    @Resource
    private JedisConnectionFactory connectionFactory;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory f = new JedisConnectionFactory();

        f.setHostName("haroohana.bvvqca.ng.0001.apn2.cache.amazonaws.com");
        f.setPort(6379);
        f.setUsePool(true);

        f.afterPropertiesSet();
        return f;
    }

    @Bean(name = "defaultTemplate")
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate<String, Object> redisTemplate) {
        return new RedisCacheManager(redisTemplate);
    }
}