package com.example.demo1;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.nio.charset.StandardCharsets;

/**
 * @author xinhe.REN (Create on:2019/3/27)
 * @version 1.0
 */
@Configuration
public class RedisConfiguration {

    @Bean(name = "redisTemplate")
    RedisTemplate<String, Object> loginRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringRedisSerializer);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    private static class PrefixKeySerializer implements RedisSerializer<String> {
        private static final String separator = "|";
        private String prefix;
        PrefixKeySerializer(String prefix) {
            this.prefix = prefix+separator;
        }

        @Override
        public byte[] serialize(String s) throws SerializationException {
            return (prefix+s).getBytes(StandardCharsets.UTF_8);
        }

        @Override
        public String deserialize(byte[] bytes) throws SerializationException {
            return new String(bytes, StandardCharsets.UTF_8).substring(prefix.length());
        }
    }
}
