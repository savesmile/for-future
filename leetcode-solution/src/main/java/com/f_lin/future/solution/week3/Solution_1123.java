package com.f_lin.future.solution.week3;

/**
 * 深度优先, 递归将左右子节点的深度都查出来。
 * hleft hright
 * 1> hleft == hright , 则当前节点为最近公共祖先
 * 2> hleft < hright , 则当前节点为在右侧，继续递归搜寻即可
 * 3> hleft > hright , 则当前节点为在左侧，继续递归搜寻即可
 *
 * @author F_lin
 * @since 2021/2/20
 **/
public class Solution_1123 {


    public static void main(String[] args) {

    }

    /**
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 80.92%
     * 的用户
     * 内存消耗：
     * 38.2 MB
     * , 在所有 Java 提交中击败了
     * 30.87%
     * 的用户
     * @param root
     * @return
     */
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        if (root == null) return null;
        //深度优先遍历  查左右节点的深度
        int left = dfs(root.left);
        int right = dfs(root.right);
        //如果左右深度相等，那么当前节点就是最近公共祖先
        if (left == right) {
            return root;
        }//否则。继续找深度最大的那边
        else if (left < right) {
            return lcaDeepestLeaves(root.right);
        }

        return lcaDeepestLeaves(root.left);
    }

    public int dfs(TreeNode node) {
        if (node == null) return 0;
        return 1 + Math.max(dfs(node.right), dfs(node.left));
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
