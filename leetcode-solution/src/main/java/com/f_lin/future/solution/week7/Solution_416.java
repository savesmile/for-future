package com.f_lin.future.solution.week7;

/**
 * 按照题意，可以将题目转换为 0-1背包。
 * <p>
 * 拆分成两个数组。意思为两个数组之和为  sum(数组总和) / 2 = half
 * 此时需要从剩下的数组中，选取几个数字凑整数 half 即可
 * 剪枝条件
 * 1. sum必须为偶数。不然无法拆分
 * 2. 数组中的最大数，必须小于 half。 不然无法拆分
 * 3. 若 maxNum = half ,直接返回true
 * <p>
 * 0-1背包状态转移方程
 * F[v] = max {F[v], F[v-w[i] + value[i]]}
 * <p>
 * 参考进行变形。
 * 状态转移方程为
 * F[j] = dp[j] | dp[j - num[i]] {j >= num[i]}
 *
 * @author F_lin
 * @since 2021/3/28
 **/
public class Solution_416 {


    public static void main(String[] args) {

    }

    public static boolean canPartition(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        int sum = 0, maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        if (sum % 2 != 0) {
            return false;
        }
        int half = sum / 2;
        if (maxNum > half) {
            return false;
        }
        if (maxNum == half) {
            return true;
        }
        //初始化
        boolean[] dp = new boolean[half + 1];
        dp[0] = true;

        for (int i = 1; i < n; i++) {
            int num = nums[i];
            for (int j = half; j >= num; --j) {
                dp[j] = dp[j] | dp[j - num];
            }
        }
        return dp[half];
    }
}
