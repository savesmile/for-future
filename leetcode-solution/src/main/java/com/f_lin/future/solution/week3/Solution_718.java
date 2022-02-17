package com.f_lin.future.solution.week3;

/**
 * 暴力解法不可取。哈哈哈哈哈
 * A1[0,1,2,3,,,i]  A2[0,1,2,3,,,j]
 * dp思路。最长公共子串。类似。
 *
 * 对 A1 第 i 个数字, 以及A2第 j 个数字, 最长子数组的解 F[i][j]
 * 必须要满足 A1[i] = A2[j]
 * 此时的F[i][j] = F[i-1][j-1] + 1
 * 其它 A1[i] != A2[j] 时，都为0
 * <p>
 * //F[0][?] = 0 F[?][0] = 0
 *
 *
 *
 * @author F_lin
 * @since 2021/2/20
 **/
public class Solution_718 {

    public static void main(String[] args) {
        int[] A = new int[]{1, 0, 0, 0, 1};
        int[] B = new int[]{1, 0, 0, 1, 1};
        System.out.println(findLength(A, B));
    }

    /**
     * 执行用时：
     * 58 ms
     * , 在所有 Java 提交中击败了
     * 54.12%
     * 的用户
     * 内存消耗：
     * 47.4 MB
     * , 在所有 Java 提交中击败了
     * 29.04%
     * 的用户
     */
    public static int findLength(int[] A, int[] B) {

        //假设默认都不相等
        int[][] dp = new int[A.length + 1][B.length + 1];
        int max = 0;

        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= B.length; j++) {
                if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > max) {
                        max = dp[i][j];
                    }
                }
            }
        }
        return max;
    }
}
