package com.f_lin.future.solution.week7;

import java.util.Stack;
import java.util.stream.Stream;

/**
 * @author F_lin
 * @since 2021/3/28
 **/
public class Solution_739 {

    public static void main(String[] args) {
        int[] t = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        int[] ints = dailyTemperatures(t);
        Stream.of(ints).forEach(System.out::println);

    }

    /**
     * 暴力解法
     *
     * 执行用时：
     * 1072 ms
     * , 在所有 Java 提交中击败了
     * 17.65%
     * 的用户
     * 内存消耗：
     * 46.6 MB
     * , 在所有 Java 提交中击败了
     * 52.87%
     * 的用户
     */
    public static int[] dailyTemperatures(int[] T) {
        int[] R = new int[T.length];
        for (int i = 0; i < T.length - 1; i++) {
            int cur = T[i];
            for (int j = i + 1; j <= T.length - 1; j++) {
                if (cur < T[j]) {
                    R[i] = j - i;
                    break;
                }
            }
        }
        return R;
    }

    /**
     * 采用栈
     * 把当前值压入栈之前，将栈里边所有的小于当前值的数据 出栈。
     * 间隔即为下标的差值
     *
     * 执行用时：
     * 20 ms
     * , 在所有 Java 提交中击败了
     * 56.75%
     * 的用户
     * 内存消耗：
     * 46.2 MB
     * , 在所有 Java 提交中击败了
     * 94.12%
     * 的用户
     */
    public static int[] dailyTemperaturesBetter(int[] T) {
        Stack<Integer> stack = new Stack<>();
        int length = T.length;
        int[] R = new int[length];

        for (int i = 0; i < length; i++) {
            while (!stack.isEmpty() && T[i] > T[stack.peek()]) {
                int pre = stack.pop();
                R[pre] = i - pre;
            }
            stack.add(i);

        }
        return R;
    }
}
