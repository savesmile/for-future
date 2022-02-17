package com.f_lin.future.solution.week14;

/**
 * @author F_lin
 * @since 2021/6/5
 **/
public class Solution_162 {

    public static void main(String[] args) {
        System.out.println(findPeakElement(new int[]{1, 2, 3}));
    }

    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 37.6 MB
     * , 在所有 Java 提交中击败了
     * 99.28%
     * 的用户
     */
    public static int findPeakElement(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        for (int i = 0; i < nums.length; i++) {
            int L = i - 1, R = i + 1;
            if (L < 0 && nums[i] > nums[R]) {
                return i;
            }
            if (R == nums.length && nums[i] > nums[L]) {
                return i;
            }

            if (nums[i] > nums[R] && nums[i] > nums[L]) {
                return i;
            }
        }
        return 0;
    }

}
