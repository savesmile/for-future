package com.f_lin.future.solution.week13;

/**
 * 两种情况。偷第一间就不能投最后一间  0<i<n-1
 * 偷最后一间就不偷第一间。          1<i<n
 * dp[i]=max(dp[i−2]+nums[i],dp[i−1])
 * <p>
 * dp[start]=nums[start]                        length = 1
 * dp[start+1]=max(nums[start],nums[start+1])   length = 2
 * ​
 * 状态转移方程
 * f(n)=max(nums[n]+f(n-2),f(n-1))
 * <p>
 * 执行用时：
 * 0 ms
 * , 在所有 Java 提交中击败了
 * 100.00%
 * 的用户
 * 内存消耗：
 * 35.5 MB
 * , 在所有 Java 提交中击败了
 * 97.45%
 * 的用户
 */

public class Solution_213 {
    public static int rob(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        } else if (length == 2) {
            return Math.max(nums[0], nums[1]);
        }


        int prevMax = 0;
        int situation1 = 0;
        //偷第一间房
        for (int i = 0; i <= length - 2; i++) {
            int temp = situation1;
            situation1 = Math.max(nums[i] + prevMax, situation1);
            prevMax = temp;
        }

        //不偷第一间房
        prevMax = 0;
        int situation2 = 0;
        for (int i = 1; i <= length - 1; i++) {
            int temp = situation2;
            situation2 = Math.max(nums[i] + prevMax, situation2);
            prevMax = temp;
        }

        return Math.max(situation1, situation2);
    }
}
