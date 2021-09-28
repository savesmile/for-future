package com.f_lin.future.java.util.redis;

import com.alibaba.fastjson.JSONObject;
import com.f_lin.future.java.util.redis.po.UserInfo;
import com.f_lin.future.java.util.redis.redlock.RedisWithReentrantLock;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.UUID;

/**
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/11/22 11:32
 **/

@Slf4j
public class JedisTest {

    private Jedis jedis;

    private static final String KEY_USER_INFO = "user:test:user-info";

    private static final String KEY_LOCK = "user:test:lock";

    @Before
    public void testInit() {
        jedis = new Jedis("47.98.233.55", 6379);
        jedis.auth("fjl666");
    }

    @Test
    public void testJedis() throws InterruptedException {

        UserInfo userInfo = new UserInfo()
                .setUserId(UUID.randomUUID().toString())
                .setAge(10)
                .setHeight(1.86)
                .setName("tom")
                .setSex(true);

        Long hset = jedis.hset(KEY_USER_INFO, userInfo.getUserId(), JSONObject.toJSONString(userInfo));

        String hget = jedis.hget(KEY_USER_INFO, userInfo.getUserId());
        UserInfo parse = JSONObject.parseObject(hget, UserInfo.class);

        log.info("返回值::{}", parse);
    }

    @Test
    public void redLock() {
        RedisWithReentrantLock lock = new RedisWithReentrantLock(jedis);
        try {
            if (!lock.lock(KEY_LOCK)) {
                log.error("获取分布式锁发生失败，锁已经被持有！threadId: {}", Thread.currentThread().getId());
                return;
            }
            log.info("====================== success get red lock=============================");
            Thread.sleep(10000);

        } catch (Exception e) {
            log.error("获取分布式锁发生异常：e:{}", e.getMessage(), e);
        } finally {
            lock.unlock(KEY_LOCK);
        }
    }

    @Test
    public void redLock_2() {

    }

}
