package com.f_lin.future.solution.week14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 排序后 遍历数组
 * <p>
 * //双指针
 * 当前值I 左指正L 右指针R   I < L < R
 * 当I+L+R = 0; 记录
 * 当I+L+R < 0 L右移
 * 当I+L+R > 0 R左移
 *
 * @author F_lin
 * @since 2021/6/5
 **/
public class Solution_15 {


    /**
     * 执行用时：
     * 21 ms
     * , 在所有 Java 提交中击败了
     * 97.88%
     * 的用户
     * 内存消耗：
     * 42.4 MB
     * , 在所有 Java 提交中击败了
     * 57.06%
     * 的用户
     */
    public List<List<Integer>> threeSum(int[] nums) {

        //排序先
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 3) {
            return result;
        }


        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] > 0) {
                break;
            }

            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int curr = nums[i];

            //双指针
            int L = i + 1, R = nums.length - 1;
            while (L < R) {
                int tmp = curr + nums[L] + nums[R];
                if (tmp == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(curr);
                    list.add(nums[L]);
                    list.add(nums[R]);
                    result.add(list);
                    while (L < R && nums[L + 1] == nums[L]) ++L;
                    while (L < R && nums[R - 1] == nums[R]) --R;
                    ++L;
                    --R;
                } else if (tmp < 0) {
                    ++L;
                } else {
                    --R;
                }
            }

        }
        return result;
    }
}
