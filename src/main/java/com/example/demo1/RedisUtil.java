package com.example.demo1;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author xinhe.REN (Create on:2019/3/27)
 * @version 1.0
 */
@Component
public class RedisUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 缓存手机验证码
     *
     * @param key   key
     * @param value value
     * @param time  超时（单位：毫秒）
     * @return
     */
    public boolean setSMSCode(String key, Object value, long time) {
        return this.set(UserConstant.SMSCODE_PREFIX, key, value, time);
    }

    /**
     * 缓存用户token
     *
     * @param key   key
     * @param value value
     * @param time  超时（单位：毫秒）
     * @return
     */
    public boolean setUserToken(String key, Object value, long time) {
        return this.set(UserConstant.USER_TOKEN, key, value, time);
    }

    /**
     * 缓存用户token相关信息
     *
     * @param key   key
     * @param value value
     * @param time  超时（单位：毫秒）
     * @return
     */
    public boolean setUserTokenData(String key, Object value, long time) {
        return this.set(UserConstant.USER_TOKEN_DATA, key, value, time);
    }


    /**
     * 普通key获取
     *
     * @param key key
     * @return
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 带前缀key获取
     *
     * @param prefix prefix
     * @param key    key
     * @return
     */
    public Object get(String prefix, String key) {
        return key == null ? null : redisTemplate.opsForValue().get(prefix + UserConstant.SEPARATOR + key);
    }

    /**
     * 设置key&value
     *
     * @param key   key
     * @param value value
     * @return
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置key&value
     *
     * @param prefix prefix
     * @param key    key
     * @param value  value
     * @return
     */
    public boolean set(String prefix, String key, Object value) {
        try {
            redisTemplate.opsForValue().set(prefix + UserConstant.SEPARATOR + key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置key&value
     *
     * @param prefix prefix
     * @param key    key
     * @param value  value
     * @param time   超时（单位：毫秒）
     * @return
     */
    public boolean set(String prefix, String key, Object value, long time) {
        try {
            redisTemplate.opsForValue().set(prefix + UserConstant.SEPARATOR + key, value);
            redisTemplate.expire(prefix + UserConstant.SEPARATOR + key, time, TimeUnit.MILLISECONDS);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*public boolean set(String key, Object value, long time) {
        try {
            redisTemplate.opsForValue().set(key, value);
            redisTemplate.expire(key, time, TimeUnit.MILLISECONDS);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }*/

    public boolean setAndSet(String key, Object value) {
        try {
            redisTemplate.opsForValue().getAndSet(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * key设置超时
     *
     * @param key  key
     * @param time time
     * @return
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.MILLISECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * key设置超时
     *
     * @param prefix prefix
     * @param key    key
     * @param time   time
     * @return
     */
    public boolean expire(String prefix, String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(prefix + UserConstant.SEPARATOR + key, time, TimeUnit.MILLISECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除带前缀的key
     *
     * @param prefix prefix
     * @param key    key
     */
    public void del(String prefix, String key) {
        this.del(prefix + UserConstant.SEPARATOR + key);
    }

    /**
     * 删除key
     *
     * @param key keys
     */
    @SuppressWarnings("unchecked")
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }
    /**
     * 删除指定前缀的key
     *
     * @param prex
     */
    public void deleteByPrex(String prex) {
        Set<String> keys = redisTemplate.keys(prex);
        if (!CollectionUtils.isEmpty(keys)) {
            redisTemplate.delete(keys);
        }
    }
    /**
     * 校验key是否存在
     *
     * @param key key
     * @return
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Long getExpire(String key){
        key= UserConstant.SMSCODE_PREFIX + UserConstant.SEPARATOR + key;
        return redisTemplate.getExpire(key, TimeUnit.MILLISECONDS);
    }


}
