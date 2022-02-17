package com.f_lin.future.solution.week6;

import java.util.ArrayList;
import java.util.List;

/**
 * 树。递归
 * dfs  depth    深度
 * bfs  Breadth  广度
 * @author F_lin fengjunlin@sobey.com
 * @version 1.0
 * @since 2021/3/16
 */
public class Solution_102 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node9 = new TreeNode(9);
        TreeNode node20 = new TreeNode(20);
        TreeNode node15 = new TreeNode(15);
        TreeNode node7 = new TreeNode(7);
        root.left = node9;
        root.right = node20;

        node20.left = node15;
        node20.right = node7;

        List<List<Integer>> lists = levelOrder(root);
        lists.forEach(x -> {
            x.forEach(p -> {
                System.out.print(p + ",");

            });
            System.out.print("\n");
        });

    }

    /**
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 94.64%
     * 的用户
     * 内存消耗：
     * 38.7 MB
     * , 在所有 Java 提交中击败了
     * 50.12%
     * 的用户
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        //root
        recursion(root, 0, result);

        return result;
    }

    public static void recursion(TreeNode current, int depth, List<List<Integer>> result) {
        if (current == null) {
            return;
        }
        List<Integer> currentDepth;
        if (depth == result.size()) {
            currentDepth = new ArrayList<>();
            result.add(depth, currentDepth);
        } else {
            currentDepth = result.get(depth);
        }


        currentDepth.add(current.val);

        depth++;
        //左边
        recursion(current.left, depth, result);
        //右边
        recursion(current.right, depth, result);
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
