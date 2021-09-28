package com.f_lin.future.java.juc;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 * 问题：
 * 1.如果某个线程持有锁的时间过长，就会导致其它等待获取锁的线程进入循环等待，消耗CPU。使用不当会造成CPU使用率极高。
 * 2.上面Java实现的自旋锁不是公平的，即无法满足等待时间最长的线程优先获取锁。不公平的锁就会存在“线程饥饿”问题。
 * 优点：
 * 1.自旋锁不会使线程状态发生切换，一直处于用户态，即线程一直都是active的；不会使线程进入阻塞状态，减少了不必要的上下文切换，执行速度快
 * 2.非自旋锁在获取不到锁的时候会进入阻塞状态，从而进入内核态，当获取到锁的时候需要从内核态恢复，需要线程上下文切换。
 *
 * @author F_lin
 * @since 2019/4/7
 **/
public class SpainLockTest {

    private AtomicReference<Thread> cas = new AtomicReference<>();

    /**
     * 可重入的自旋锁
     * 同一个线程支持多次获取当前锁
     */
    private int holdCount = 0;

    public void lock() {
        Thread thread = Thread.currentThread();

        if (thread == cas.get()) {
            holdCount++;
            return;
        }

        /**
         *  尝试获取锁，即将自己放入cas中的 V 中， A=null,B=currentThread();
         *  CAS 当且仅当 V==A 时才执行 V=B操作
         */
        while (!cas.compareAndSet(null, thread)) {
            //do nothing 自旋并等待
        }
    }

    public void unlock() {
        //其它非锁持有者不能释放锁。
        Thread current = Thread.currentThread();
        //cas.compareAndSet(current, null);
        /**
         * 可重入锁释放锁。必须所有此线程持有锁数都释放完成后才能将释放此锁
         */
        if (current == cas.get()) {
            if (holdCount > 0) {// 如果大于0，表示当前线程多次获取了该锁，释放锁通过count减一来模拟
                holdCount--;
            } else {// 如果count==0，可以将锁释放，这样就能保证获取锁的次数与释放锁的次数是一致的了。
                cas.compareAndSet(current, null);
            }
        }
    }

}
