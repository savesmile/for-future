package com.f_lin.future.java.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 问题
 * https://mp.weixin.qq.com/s?__biz=Mzg3NjU3NTkwMQ==&mid=2247505154&idx=1&sn=80a62d02ec48a75307d69001fd2eeb32&scene=21#wechat_redirect
 *
 * @author F_lin fengjunlin@sobey.com
 * @version 1.0
 * @since 2023/2/28
 */
public class 线程池中异常处理 {

    public static void main(String[] args) {
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(2, 2,
                30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        Future future = executorService.submit(() -> {
            try {
                sayHi("submit");
            } catch (Exception e) {
                System.out.println("sayHi Exception");
                e.printStackTrace();
            }
        });

        try {
            //future 的 outcome 属性，用于存放异步future的计算结果，如果计算发生异常的话，计算子线程允许中未捕获的异常会同样被封装到此对象中，
            //同时，future的状态会被设置为 EXCEPTIONAL
            //此时调用 future.get()方法时，就会将异常抛出！！
            future.get();
        } catch (Exception e) {
            System.out.println("future.get Exception");
            e.printStackTrace();
        }
    }

    private static void sayHi(String name) throws RuntimeException {
        String printStr = "【thread-name:" + Thread.currentThread().getName() + ",执行方式:" + name + "】";
        System.out.println(printStr);
        throw new RuntimeException(printStr + ",我异常啦!哈哈哈!");
    }
}
