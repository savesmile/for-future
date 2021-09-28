package com.f_lin.future.java.util.redis.delay_queue;

import com.fasterxml.jackson.core.type.TypeReference;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Type;
import java.util.Set;
import java.util.UUID;

/**
 * 将消息封装成某个延时任务，丢到redis中的zset中。带权(延时时间)取出。可以以当前时间为准取出第一条数据，进行消费。
 * zrem() = removed当前主键的某个成员。
 * <p>
 * 优化点：
 * 多个进程取到之后再使用zrem进行争抢，那些没抢到的进程都是白取了一次任务，这是浪费。可以考虑使用lua scripting来优化一下这个逻辑，
 * 将zrangebyscore和zrem一同挪到服务器端进行原子化操作，这样多个进程之间争抢任务时就不会出现这种浪费了。
 *
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/11/22 15:32
 **/
public class RedisDelayingQueue<T> {

    /**
     * 任务相关信息
     *
     * @param <T>
     */
    static class TaskItem<T> {
        public String id;
        public T msg;
    }

    // fastjson 序列化对象中存在 generic 类型时，需要使用 TypeReference
    private Type TaskType = new TypeReference<TaskItem<T>>() {
    }.getType();

    private Jedis jedis;
    //延时队列结构主键
    private String queueKey;

    public RedisDelayingQueue(Jedis jedis, String queueKey) {
        this.jedis = jedis;
        this.queueKey = queueKey;
    }

    public void delay(T msg) {
        TaskItem task = new TaskItem();
        // 分配唯一的 uuid
        task.id = UUID.randomUUID().toString();
        task.msg = msg;
        //  序列化
        String s = null;//JSON.toJSONString(task);
        // 塞入延时队列 ,5s 后再试
        jedis.zadd(queueKey, System.currentTimeMillis() + 5000, s);
    }

    public void loop() {
        while (!Thread.interrupted()) {
            // 只取一条
            Set<String> values = jedis.zrangeByScore(queueKey, 0, System.currentTimeMillis(), 0, 1);
            if (values.isEmpty()) {
                try {
                    // 歇会继续
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    break;
                }
                continue;
            }
            String s = values.iterator().next();
            if (jedis.zrem(queueKey, s) > 0) {
                // 抢到了
                TaskItem task = null;//JSON.parseObject(s, TaskType);
                this.handleMsg(task.msg);
            }
        }
    }

    public <T> void handleMsg(T msg) {
        System.out.println(msg);
    }
}
