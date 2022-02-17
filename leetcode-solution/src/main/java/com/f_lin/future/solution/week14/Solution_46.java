package com.f_lin.future.solution.week14;

import java.util.ArrayList;
import java.util.List;

/**
 * @author F_lin
 * @since 2021/6/5
 **/
public class Solution_46 {

    /**
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 97.46%
     * 的用户
     * 内存消耗：
     * 38.8 MB
     * , 在所有 Java 提交中击败了
     * 27.80%
     * 的用户
     */
    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        boolean[] used = new boolean[len];
        List<Integer> path = new ArrayList<>();

        dfs(nums, len, 0, path, used, res);
        return res;
    }

    private void dfs(int[] nums, int len, int depth,
                     List<Integer> path, boolean[] used,
                     List<List<Integer>> res) {
        if (depth == len) {
            res.add(new ArrayList<>(path));
            return;
        }
        //选还未使用的一个数，加入当前排列
        for (int i = 0; i < len; i++) {
            if (!used[i]) {
                path.add(nums[i]);
                used[i] = true;

                dfs(nums, len, depth + 1, path, used, res);
                // 回溯
                used[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        Solution_46 solution = new Solution_46();
        List<List<Integer>> lists = solution.permute(nums);
        System.out.println(lists);
    }
}
