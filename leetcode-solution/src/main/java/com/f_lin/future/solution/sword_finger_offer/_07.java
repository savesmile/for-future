package com.f_lin.future.solution.sword_finger_offer;

import java.util.HashMap;
import java.util.Map;

/**
 * 使用前序遍历和中序遍历唯一确定二叉树。
 * 前序遍历第一个节点即为根节点，
 * 中序遍历可以确定根节点左子树与右子树数据。
 * 递归即可确认一颗完整的二叉树
 *
 * @author F_lin fengjunlin@sobey.com
 * @version 1.0
 * @since 2023/3/29
 */
public class _07 {



    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int preLen = preorder.length;
        int inLen = inorder.length;
        // 构建哈希表，key为节点值，value为中序遍历中对应的下标位置
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inLen; i++) {
            map.put(inorder[i], i);
        }
        return build(map, preorder, 0, preLen - 1, 0, inLen - 1);
    }
    public TreeNode build(Map<Integer, Integer> map, int[] preorder,  int preStart, int preEnd, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);
        int rootIndex = map.get(rootVal);
        int leftLen = rootIndex - inStart;
        root.left = build(map, preorder,preStart + 1, preStart + leftLen, inStart, rootIndex - 1);
        root.right = build(map, preorder,  preStart + leftLen + 1, preEnd, rootIndex + 1, inEnd);
        return root;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
