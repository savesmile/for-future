package com.f_lin.future.java.headfirst.singleton;

/**
 * 双重验证加锁单例模式
 * <p>
 *
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/9/16 10:01
 **/
public class SingletonTarget {

    private SingletonTarget() {
    }

    /**
     * volatile 保持内存可见性
     */
    private volatile static SingletonTarget singleton = null;

    /**
     * 加锁对象。可以理解为对类加锁
     */
    private static final Object lockObj = new Object();

    /**
     * 获取单例模式
     */
    public static SingletonTarget getInstance() {
        //第一次判断 效率
        if (singleton == null) {
            //加锁 同步
            synchronized (lockObj) {
                //第二次判断，防止有线程获取到锁后，并没有执行完成就挂起了，其它线程实例化后，这里第二次验证。
                if (singleton == null) {
                    singleton = new SingletonTarget();
                }
            }
        }
        return singleton;
    }
}
