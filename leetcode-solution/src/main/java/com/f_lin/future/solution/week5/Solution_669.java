package com.f_lin.future.solution.week5;

/**
 * @author F_lin fengjunlin@sobey.com
 * @version 1.0
 * @since 2021/3/16
 */
public class Solution_669 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        System.out.println(trimBST(root, 2, 2));
    }


    /**
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 37.9 MB
     * , 在所有 Java 提交中击败了
     * 92.92%
     * 的用户
     */
    public static TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
            //裁掉整个左子节点
        if (root.val < low) {
            root = root.right;
            root = trimBST(root, low, high);
            //裁掉整个右子节点
        } else if (root.val > high) {
            root = root.left;
            root = trimBST(root, low, high);
        } else {
            //左右各自裁剪
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);
        }
        return root;
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
