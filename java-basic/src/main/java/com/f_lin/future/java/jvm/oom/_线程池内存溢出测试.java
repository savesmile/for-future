package com.f_lin.future.java.jvm.oom;

import java.util.ArrayList;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * -Xmx10m -Xms10m
 * -XX:+HeapDumpOnOutOfMemoryError  -XX:HeapDumpPath=/oom_dump.hprof
 *
 * jmap -dump:format=b,file=111.hprof {pid}
 *
 *
 * 技术点：
 * https://mp.weixin.qq.com/s/id_3pMbUi2F-6PqQ9Qosyw
 *
 * @author F_lin fengjunlin@sobey.com
 * @version 1.0
 * @since 2023/2/28
 */
public class _线程池内存溢出测试 {

    private static final String THREAD_FACTORY_NAME_PRODUCT = "product";

    public static void main(String[] args) throws Exception {

        ThreadPoolExecutor productThreadPoolExecutor = new ThreadPoolExecutor(1,
                1,
                1,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1),
                new MyThreadFactory(THREAD_FACTORY_NAME_PRODUCT),
                new MyRejectedPolicy());

        while (true) {
            TimeUnit.SECONDS.sleep(1);
            new Thread(() -> {
                ArrayList<Future<Integer>> futureList = new ArrayList<>();
                //从数据库获取产品信息
                int productNum = 5;
                for (int i = 0; i < productNum; i++) {
                    try {
                        int finalI = i;
                        Future<Integer> future = productThreadPoolExecutor.submit(() -> {
                            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
                            return finalI * 10;
                        });
                        futureList.add(future);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                for (Future<Integer> integerFuture : futureList) {
                    try {
                        Integer integer = integerFuture.get();
                        System.out.println(integer);
                        System.out.println("future.get() = " + integer);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

    }

    static class MyThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;
        private final String threadFactoryName;

        public String getThreadFactoryName() {
            return threadFactoryName;
        }

        MyThreadFactory(String threadStartName) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = threadStartName + "-pool-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
            threadFactoryName = threadStartName;
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }

    public static class MyRejectedPolicy implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            if (e.getThreadFactory() instanceof MyThreadFactory) {
                MyThreadFactory myThreadFactory = (MyThreadFactory) e.getThreadFactory();
                if ("product".equals(myThreadFactory.getThreadFactoryName())) {
                    System.out.println(THREAD_FACTORY_NAME_PRODUCT + "线程池有任务被拒绝了,请关注");
                }
            }
            //由于future类新建出来的时候，就是NEW状态
            //这里不抛异常的话，线程池满了，拒绝任务后，future状态依然会处于NEW状态
            //后续再调用 future.get() 方法，而且不设置等待超时的话，就会导致此线程一直处于等待状态。
            //所以future对象一直被线程引用，造成内存泄露，也会导致线程过多创建且得不到销毁，浪费资源！！！
        }
    }

}
