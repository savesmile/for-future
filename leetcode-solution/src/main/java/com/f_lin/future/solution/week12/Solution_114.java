package com.f_lin.future.solution.week12;

/**
 * @author F_lin
 * @since 2021/5/6
 **/
public class Solution_114 {

    //父节点
    TreeNode parentNode = new TreeNode();

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        TreeNode left = root.left;
        TreeNode right = root.right;

        //将当前节点设置为父节点的右子树
        parentNode.right = root;
        parentNode.left = null;
        //将父节点下移
        parentNode = root;

        //递归左右节点
        flatten(left);
        flatten(right);

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


