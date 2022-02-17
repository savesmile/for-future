package com.f_lin.future.solution.week1;

/**
 * 二叉搜索树的最近公共祖先
 * <p>
 * 1. 从根节点开始搜索
 * 2. 左边节点比右边节点小
 * 3. 公共祖先--特点。 >= 小的节点  <= 大的节点。
 * <p>
 * 至此，递归基准条件也有了，循环break点也有了。
 * 解题
 * 使用循环
 * 从根节点遍历，如果当前节点的值  `大于` p、q中 的`最大值`，则证明 p、q应该位于当前节点的左侧，继续往左子节点搜索，
 * 反之则往右子节点继续搜索。
 * 使用递归
 * 基准条件：   current.val ∈ [min.val,max.val]  (注意边界)
 * 递归体为 current.val > max.val ? current.left : current.right;
 */
public class Solution_235 {

    public static void main(String[] args) {
        TreeNode head = new TreeNode();
        head.setVal(6);
        TreeNode node2 = new TreeNode();
        node2.setVal(2);
        TreeNode node8 = new TreeNode();
        node8.setVal(8);
        TreeNode node0 = new TreeNode();
        node0.setVal(0);
        TreeNode node4 = new TreeNode();
        node4.setVal(4);
        TreeNode node7 = new TreeNode();
        node7.setVal(7);
        TreeNode node9 = new TreeNode();
        node9.setVal(9);
        TreeNode node3 = new TreeNode();
        node3.setVal(3);
        TreeNode node5 = new TreeNode();
        node5.setVal(5);

        head.setLeft(node2);
        head.setRight(node8);

        node2.setLeft(node0);
        node2.setRight(node4);

        node4.setLeft(node3);
        node4.setRight(node5);

        node8.setLeft(node7);
        node8.setRight(node9);


        TreeNode treeNode = new Solution().lowestCommonAncestorBetter(head, node7, node9);

        System.out.println(treeNode.toString());

    }

    static class Solution {

        /**
         * 执行用时：6 ms, 在所有 Java 提交中击败了99.55%的用户
         * 内存消耗：39.4 MB, 在所有 Java 提交中击败了35.17%的用户
         */
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            TreeNode minNode;
            TreeNode maxNode;

            if (p.val < q.val) {
                minNode = p;
                maxNode = q;
            } else {
                minNode = q;
                maxNode = p;
            }


            if (between(root, minNode, maxNode)) {
                return root;
            }

            TreeNode tempP = root;

            //1.使用循环
            do {
                if (tempP.val > maxNode.val) {
                    tempP = tempP.left;
                }
                if (tempP.val < minNode.val) {
                    tempP = tempP.right;
                }
            } while (!between(tempP, minNode, maxNode));

            return tempP;
        }

        private boolean between(TreeNode tempP, TreeNode min, TreeNode max) {
            return tempP.val == min.val || tempP.val == max.val || (tempP.val < max.val && tempP.val > min.val);
        }


        /**
         * 执行用时： 6 ms , 在所有 Java 提交中击败了 99.55% 的用户
         * 内存消耗： 39.1 MB , 在所有 Java 提交中击败了 93.01% 的用户
         */
        public TreeNode lowestCommonAncestorBetter(TreeNode root, TreeNode p, TreeNode q) {

            TreeNode min;
            TreeNode max;

            if (p.val < q.val) {
                min = p;
                max = q;
            } else {
                min = q;
                max = p;
            }

            return recursion(root, min, max);
        }

        public TreeNode recursion(TreeNode current, TreeNode min, TreeNode max) {
            //基准条件
            if (current.val == min.val || current.val == max.val || (current.val < max.val && current.val > min.val)) {
                return current;
            }

            if (current.val > max.val) {
                return recursion(current.left, min, max);
            } else {
                return recursion(current.right, min, max);
            }

        }

    }


    static class TreeNode {

        private int val;

        private TreeNode left;

        private TreeNode right;

        public TreeNode() {
        }

        public void setVal(int val) {
            this.val = val;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}