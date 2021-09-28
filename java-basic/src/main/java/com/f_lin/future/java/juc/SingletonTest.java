package com.f_lin.future.java.juc;

/**
 * @author F_lin
 * @since 2019/4/7
 **/
public class SingletonTest {
    /**
     * volatile 关键字
     * 在我们  uniqueInstance = new SingletonTest();
     * 实际发出的指令有：
     * 指令1：获取singleton对象的内存地址
     * 指令2：初始化singleton对象
     * 指令3：将这块内存地址，指向引用变量singleton。
     *
     * 由于 JVM 具有指令重排的特性，执行顺序有可能变成 1->3->2
     * 指令重排在单线程环境下不会出现问题，但是在多线程环境下会导致一个线程获得还没有初始化的实例。
     * 例如，线程 T1 执行了 1 和 3，此时 T2 调用 getUniqueInstance() 后发现 uniqueInstance 不为空，因此返回 uniqueInstance，
     * 但此时 uniqueInstance 还未被初始化。
     *
     * 使用 volatile 可以禁止 JVM 的指令重排，保证在多线程环境下也能正常运行。
     */
    private volatile static SingletonTest uniqueInstance;

    private SingletonTest() {
    }

    public static SingletonTest getUniqueInstance() {
        /**
         *  先判断对象是否已经实例过，防止直接进入加锁模块，减去锁竞争的时间，提升效率
         */
        if (uniqueInstance == null) {
            //类对象加锁
            synchronized (SingletonTest.class) {
                /**
                 * 第二次判断，假设不加此层校验。我们线程A已经通过第一层判断，然后进入同步代码块后，挂起，执行线程B。
                 * B也能通过第一次校验，然后new一个实例。此时A重新唤醒，又会再new一个实例。
                 */
                if (uniqueInstance == null) {
                    uniqueInstance = new SingletonTest();
                }
            }
        }
        return uniqueInstance;
    }
}
