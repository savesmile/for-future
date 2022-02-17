package com.f_lin.future.solution.week15;

/**
 * dp[i] = dp[i-1] - cost[i] > 0
 *
 * @author F_lin
 * @since 2021/6/5
 **/
public class Solution_134 {


    public static void main(String[] args) {
        System.out.println(canCompleteCircuit(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}));
    }

    /**
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 48.27%
     * 的用户
     * 内存消耗：
     * 38.7 MB
     * , 在所有 Java 提交中击败了
     * 49.91%
     * 的用户
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {

        int allOil = 0;
        int needOil = 0;
        for (int i = 0; i < gas.length; i++) {
            allOil += gas[i];
            needOil += cost[i];
        }

        if (needOil > allOil) {
            return -1;
        }

        int total = 0, start = 0;
        for (int i = 0; i < gas.length; i++) {
            total += gas[i] - cost[i];
            if (total < 0) {
                total = 0;
                start = i + 1;
            }
        }

        return start;
    }
}
