package com.f_lin.future.solution.week2;

/**
 * 自底向上。第N个台阶 只能 从 N-1 阶 或者 N-2 阶上来，所以。从N-1阶上来假设有f(n-1)种方式，从N-2阶上来假设有f(n-2)种方式。
 * 则踏上N阶的方式总共有 f(n) = f(n-1) + f(n-2) 种。
 * 边界条件 f(1) = 1; f(2) = 2;
 *
 * @author F_lin
 * @since 2021/2/20
 **/
public class Solution_70 {

    public static void main(String[] args) {
        System.out.println(climbStairs(5));
    }

    public static int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        /**
         * n-1 的方法数
         */
        int n1 = 1;
        /**
         * n-2 的方法数
         */
        int n2 = 2;

        int result = 0;

        for (int i = 0; i < n - 2 ; i++) {
            result = n1 + n2;
            n1 = n2;
            n2 = result;
        }
        return result;
    }
}
