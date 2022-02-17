package com.f_lin.future.solution.week3;

/**
 * 最长公共子序列。
 * 对于 S1 的 i 位置，与 S2 的 j 位置。
 * 若 S1[i] = S2[j]
 * 则 F[i][j] = F[i-1][j-1]
 * 若 S1[i] != S2[j]
 * 则分别将 i 或者 j 回退，求其最优解、
 * F[i][j] = max{ F[i-1][j], F[i][j-1] }
 *
 * @author F_lin
 * @since 2021/2/20
 **/
public class Solution_1143 {

    public static void main(String[] args) {

    }

    /**
     * 执行用时：
     * 7 ms
     * , 在所有 Java 提交中击败了
     * 91.53%
     * 的用户
     * 内存消耗：
     * 42.1 MB
     * , 在所有 Java 提交中击败了
     * 71.78%
     * 的用户
     */
    public int longestCommonSubsequence(String text1, String text2) {
        char[] charsA = text1.toCharArray();
        char[] charsB = text2.toCharArray();
        //假设默认都不相等
        int[][] dp = new int[charsA.length + 1][charsB.length + 1];
        int max = 0;

        for (int i = 1; i <= charsA.length; i++) {
            for (int j = 1; j <= charsB.length; j++) {
                if (charsA[i - 1] == charsB[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > max) {
                        max = dp[i][j];
                    }
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return max;
    }
}
