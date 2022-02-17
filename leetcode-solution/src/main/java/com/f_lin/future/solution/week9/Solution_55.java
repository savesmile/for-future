package com.f_lin.future.solution.week9;

/**
 * @author F_lin
 * @since 2021/4/11
 **/
public class Solution_55 {


    public static void main(String[] args) {
        int[] test = new int[]{2,3,1,1,4};

        System.out.println(canJump(test));
    }

    /**
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 99.98%
     * 的用户
     * 内存消耗：
     * 39.9 MB
     * , 在所有 Java 提交中击败了
     * 98.40%
     * 的用户
     */
    public static boolean canJump(int[] nums) {
        //设定一个最大步长
        //当跳到第i步是，需要判断最大步长能否到达i即可
        int maxStep = nums[0];

        if (maxStep == 0) {
            return false;
        }

        for (int i = 1; i < nums.length; i++) {
            if (maxStep < i) {
                return false;
            }

            if (nums[i] + i > maxStep) {
                maxStep = nums[i] + i ;
            }
        }
        return true;
    }
}
