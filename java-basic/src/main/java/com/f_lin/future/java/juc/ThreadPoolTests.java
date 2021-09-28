package com.f_lin.future.java.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author F_lin
 * @since 2019/4/10
 **/
public class ThreadPoolTests {

    public static void main(String[] args) {
        //singleThreadPoolTest();

        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
        executorService1.submit(() -> {
            System.out.println("hello");
            return 1;
        });
        executorService1.shutdown();

    }

    /**
     * 创建一个单线程的线程池。
     * 这个线程池只有一个线程在工作，也就是相当于单线程串行执行所有任务。
     * 如果这个唯一的线程因为异常结束，那么会有一个新的线程来替代它。
     * 此线程池保证所有任务的执行顺序按照任务的提交顺序执行。
     */
    private static void singleThreadPoolTest() {

        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
        /**
         * 线程异常。
         */
        executorService1.execute(
                () -> {
                    for (int i = 0; i <= 10; i++) {
                        if (i == 3) {
                            throw new IllegalArgumentException("线程破坏测试");
                        }
                        System.out.println(Thread.currentThread().getId() + "===>" + i);
                        if (i == 10) {
                            return;
                        }
                    }
                });
        executorService1.submit(() -> {
            System.out.println("hello");
            return 1;
        });

        executorService1.submit(() -> {
            System.out.println("hello");
        });
        /**
         * 再次执行时 会有新的线程替换异常的线程
         */
        executorService1.execute(
                () -> {
                    for (int i = 0; i <= 5; i++) {
                        System.out.println(Thread.currentThread().getId() + "===>" + i);
                    }
                });
        executorService1.shutdown();

    }
}
