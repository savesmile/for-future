package com.f_lin.future.java.juc;

/**
 * @author F_lin
 * @since 2019/4/7
 **/
public class SynchronizedTests {

    private static int num = 0;

    private final Object lock = new Object();

    public static void main(String[] args) {

    }

    /**
     * 修饰方法
     * 作用于当前对象实例加锁，进入同步代码前要获得当前对象实例的锁
     */
    public synchronized void methodTest() {
        System.out.println(++num);
    }

    /**
     * 修饰静态方法
     * 作用于此类加锁，进入同步代码前要获得当前类对象的锁
     */
    public synchronized static void staticMethodInsideTest() {
        System.out.println(++num);
    }

    /**
     * 修饰代码块
     */
    public void objectLockTest() {
        //指定加锁对象，对给定对象加锁，进入同步代码库前要获得给定对象的锁
        synchronized (lock) {
            System.out.println(++num);
        }
    }


    public void classLockTest() {
        //给 Class 类上锁  需要获取到此类的锁
        synchronized (SynchronizedTests.class) {
            System.out.println(++num);
        }
    }

    public void methodInsideTest() {
        //作用于当前对象加锁，进入同步代码前要获得当前对象实例
        synchronized (this) {
            System.out.println(++num);
        }
    }
}
