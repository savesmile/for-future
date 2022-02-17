package com.f_lin.future.solution.week15;

import java.util.*;

/**
 * @author F_lin
 * @since 2021/6/5
 **/
public class Solution_103 {


    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        TreeNode node2 = new TreeNode(2);
        TreeNode node4 = new TreeNode(4);
        TreeNode nodey = new TreeNode(1);
        TreeNode node3 = new TreeNode(3);
        TreeNode nodex = new TreeNode(-1);
        TreeNode node5 = new TreeNode(5);
        TreeNode node1 = new TreeNode(1);
        TreeNode node6 = new TreeNode(6);
        TreeNode node8 = new TreeNode(8);
        root.left = node2;
        root.right = node4;
        node2.left = nodey;

        node4.left = node3;
        node4.right = nodex;

        nodey.left = node5;
        nodey.right = node1;
        node3.right = node6;
        nodex.right = node8;

        List<List<Integer>> lists = zigzagLevelOrder(root);

        System.out.println(lists.toString());

    }

    /**
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 98.66%
     * 的用户
     * 内存消耗：
     * 38.3 MB
     * , 在所有 Java 提交中击败了
     * 87.72%
     * 的用户
     */
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        dfs(0, root, result);

        for (int i = 0; i < result.size(); i++) {
            if (i % 2 != 0) {
                List<Integer> list = result.get(i);
                Collections.reverse(list);
            }
        }
        return result;
    }

    private static void dfs(int path, TreeNode root, List<List<Integer>> result) {

        if (root == null) {
            return;
        }

        List<Integer> current;
        if (path >= result.size()) {
            current = new ArrayList<>();
            result.add(current);
        } else {
            current = result.get(path);
        }

        current.add(root.val);

        ++path;
        //从左往右
        dfs(path, root.left, result);
        dfs(path, root.right, result);
    }


    public List<List<Integer>> better(TreeNode root){
        List<List<Integer>> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }

        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);
        boolean isOrderLeft = true;

        while (!nodeQueue.isEmpty()) {
            Deque<Integer> levelList = new LinkedList<>();
            int size = nodeQueue.size();
            for (int i = 0; i < size; ++i) {
                TreeNode curNode = nodeQueue.poll();
                if (isOrderLeft) {
                    levelList.offerLast(curNode.val);
                } else {
                    levelList.offerFirst(curNode.val);
                }
                if (curNode.left != null) {
                    nodeQueue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    nodeQueue.offer(curNode.right);
                }
            }
            ans.add(new LinkedList<>(levelList));
            isOrderLeft = !isOrderLeft;
        }

        return ans;
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
