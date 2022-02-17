package com.f_lin.future.solution.week14;

/**
 * dp[i] = max{dp[i−2]+nums[i],dp[i−1]}
 * dp[0] = nums[0];
 * dp[1] = max{nums[0], nums[1]}
 *
 * @author F_lin
 * @since 2021/6/5
 **/
public class Solution_198 {

    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 35.4 MB
     * , 在所有 Java 提交中击败了
     * 98.73%
     * 的用户
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }


        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[length - 1];
    }

}
