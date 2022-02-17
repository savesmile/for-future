package com.f_lin.future.solution.week5;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * * 吃苹果
 * * 思路：
 * * 优先吃保质期最近的苹果。
 * * 没过1天  保质期 -1。新增apple[i]个苹果 ..每个苹果按保质期排序。
 * * 一直吃到，没有可食用的苹果为止！
 * *
 * * 是不是可以先将苹果 * 保质期 铺开？
 *
 * @author F_lin fengjunlin@sobey.com
 * @version 1.0
 * @since 2021/3/16
 */
public class Solution_1705 {

    public static void main(String[] args) {
//        int[] apps = new int[]{1, 2, 3, 5, 2};
        int[] apps = new int[]{3,0,0,0,0,2};
//        int[] days = new int[]{3, 2, 1, 4, 2};
        int[] days = new int[]{3,0,0,0,0,2};

        System.out.println(eatenApples(apps, days));
    }


    /**
     * 执行用时：
     * 106 ms
     * , 在所有 Java 提交中击败了
     * 34.39%
     * 的用户
     * 内存消耗：
     * 41 MB
     * , 在所有 Java 提交中击败了
     * 36.42%
     * 的用户
     */
    public static int eatenApples(int[] apples, int[] days) {
        int result = 0;
        //优先队列。根据保质期的来排序
        PriorityQueue<Apple> queue = new PriorityQueue<>((Comparator.comparingInt(o -> o.shelfLife)));
        for (int i = 0; i < days.length || !queue.isEmpty(); i++) {
            //只有
            if (i < days.length && apples[i] != 0) {
                //第 i 天的苹果入队
                Apple apple = new Apple(apples[i], i + days[i] - 1 );
                queue.offer(apple);
            }
            //剔除过期的苹果。并吃掉
            while (!queue.isEmpty()) {
                Apple poll = queue.poll();
                //没过期。吃掉一个，重新放入队列
                if (i <= poll.shelfLife) {
                    result++;
                    if (poll.count > 1 && i <= poll.shelfLife - 1) {
                        queue.offer(new Apple(poll.count - 1, poll.shelfLife));
                    }
                    break;
                }
            }
        }
        return result;
    }


    static class Apple {
        int count;
        int shelfLife;

        public Apple() {
        }

        public Apple(int count, int shelfLife) {
            this.count = count;
            this.shelfLife = shelfLife;
        }
    }
}
