package com.f_lin.future.java.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import org.w3c.dom.Node;

/**
 * @author F_lin
 * @since 2019/4/8
 **/
public class AQSTests {

    static ReentrantLock nonFairLock = new ReentrantLock(false); //default
    static ReentrantLock fairLock = new ReentrantLock(true);

    public static void main(String[] args) throws InterruptedException {
        //countDownLatchTest1();
        //cyclicBarrierTest();
        lockTest();
//        fairLockTest();
        //conditionTest();
//        CountDownLatch cdl = new CountDownLatch(4);
//        for (int i = 1; i < 5; i++) {
//            new Thread(() -> {
//                try {
//                    System.out.println(Thread.currentThread().getName() + " sleep 5s");
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                }
//                cdl.countDown();
//            }, "Thread-" + i).start();
//        }
//
//        cdl.await();
    }



    private static void conditionTest() throws InterruptedException {
        Condition condition = nonFairLock.newCondition();
        Condition condition2 = nonFairLock.newCondition();
        new Thread(() -> {
            nonFairLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " get lock");
                condition.await();
                System.out.println(Thread.currentThread().getName() + " get lock22222");
                condition2.await();
                System.out.println(Thread.currentThread().getName() + " get lock and sleep 5s!");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                nonFairLock.unlock();
            }
        }, "thread-" + 1).start();

        new Thread(() -> {
            nonFairLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " get lock");
                condition.await();
                condition2.signal();
                System.out.println(Thread.currentThread().getName() + " get lock and sleep 5s!");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                nonFairLock.unlock();
            }
        }, "thread-" + 2).start();

        Thread.sleep(500);

        new Thread(() -> {
            nonFairLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " get lock and sleep 5s!");
                Thread.sleep(5000);
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                nonFairLock.unlock();
            }
        }, "thread-" + 3).start();
    }

    private static void lockTest() {
        for (int i = 1; i < 4; i++) {
            new Thread(() -> {
                nonFairLock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " get lock and sleep 5s!");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    nonFairLock.unlock();
                }
            }, "thread-" + i).start();
        }
    }

    private static void fairLockTest() {
        for (int i = 1; i < 4; i++) {
            new Thread(() -> {
                fairLock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " get lock and sleep 5s!");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    fairLock.unlock();
                }
            }, "thread-" + i).start();
        }
    }


    private static void countDownLatchTest1() throws InterruptedException {
        int threadCount = 100;
        // 创建一个具有固定线程数量的线程池对象（如果这里线程池的线程数量给太少的话你会发现执行的很慢）
        ExecutorService threadPool = Executors.newFixedThreadPool(60);
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            final int threadnum = i;
            threadPool.execute(() -> {// Lambda 表达式的运用
                try {
                    test1(threadnum);
                } catch (InterruptedException e) {

                } finally {
                    countDownLatch.countDown();// 表示一个请求已经被完成
                }

            });
        }
        countDownLatch.await();
        threadPool.shutdown();
        System.out.println("finish");
    }


    public static void test1(int threadnum) throws InterruptedException {
        Thread.sleep(1000);// 模拟请求的耗时操作
        System.out.println("threadnum:" + threadnum);
        Thread.sleep(1000);// 模拟请求的耗时操作
    }

    private static void cyclicBarrierTest() {
        int threadNum = 100;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        for (int i = 0; i < threadNum; i++) {
            int threadIndex = i;
            threadPool.execute(() -> {
                System.out.println("threadnum: 【 " + threadIndex + " 】 is ready");
                try {
                    //通知 CyclicBarrier 该线程已就绪
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {

                }
                System.out.println("threadnum: 【 " + threadIndex + " 】 is finish");
            });
        }

        threadPool.shutdown();
    }

}
