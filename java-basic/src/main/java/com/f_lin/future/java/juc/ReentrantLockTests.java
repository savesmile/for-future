package com.f_lin.future.java.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 典型的生产者消费者模型
 *
 * @author F_lin
 * @since 2019/4/7
 **/
public class ReentrantLockTests {

    private final ReentrantLock lock = new ReentrantLock();
    /**
     * 信号量 控制生产者
     * notFull.await()
     */
    private final Condition notFull = lock.newCondition();
    /**
     * 信号量 控制消费者
     */
    private final Condition notEmpty = lock.newCondition();

    /**
     * 商品池子。。上限100
     */
    private final Object[] items = new Object[100];
    private int putptr, //上一次生产 商品放置的位置，防止重复放置
            takeptr, //上一次消费 商品获取的位置，防止重复获取  因为没有做商品清空处理 也没得必要。
            count; //当前池子商品总数

    public void put(Object x) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length) {
                //等待可以生产了的信号。 notFull.signal();
                notFull.await();
            }
            items[putptr] = x;
            //当生产
            if (++putptr == items.length) {
                putptr = 0;
            }
            //总数+1
            ++count;
            //通知消费者可以消费了
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)
                notEmpty.await();
            Object x = items[takeptr];
            if (++takeptr == items.length) takeptr = 0;
            --count;
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }

}
