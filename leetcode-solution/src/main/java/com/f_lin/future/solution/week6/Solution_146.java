package com.f_lin.future.solution.week6;

import java.util.HashMap;
import java.util.Map;

/**
 * @author F_lin fengjunlin@sobey.com
 * @version 1.0
 * @since 2021/3/16
 */
public class Solution_146 {

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(2, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(2));

        lruCache.put(1, 1);
        lruCache.put(4, 1);
        System.out.println(lruCache.get(2));
    }


}


/**
 * 执行用时：
 * 19 ms
 * , 在所有 Java 提交中击败了
 * 72.19%
 * 的用户
 * 内存消耗：
 * 46.5 MB
 * , 在所有 Java 提交中击败了
 * 55.04%
 * 的用户
 */
class LRUCache {
    int size;
    int capacity;

    Map<Integer, TreeNode> hashCache = new HashMap<>();

    TreeNode head;
    TreeNode tail;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        head = new TreeNode();
        tail = new TreeNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        TreeNode node = hashCache.get(key);
        if (node == null) {
            return -1;
        }
        //移到头部
        toHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        TreeNode node = hashCache.get(key);
        if (node != null) {
            //移到头部
            node.val = value;
            toHead(node);
        } else {
            TreeNode newNode = new TreeNode(key, value);
            hashCache.put(key, newNode);
            // 添加到头部
            addToHead(newNode);
            //容量满了
            if (size == capacity) {
                //删除尾节点
                TreeNode tail = removeTail();
                //删除缓存
                hashCache.remove(tail.key);
            } else {
                size++;
            }
        }
    }

    private void addToHead(TreeNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(TreeNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void toHead(TreeNode node) {
        removeNode(node);
        addToHead(node);
    }

    private TreeNode removeTail() {
        TreeNode res = tail.prev;
        removeNode(res);
        return res;
    }
}

class TreeNode {
    int key;
    int val;
    TreeNode next;
    TreeNode prev;

    TreeNode() {
    }

    public TreeNode(int key, int val) {
        this.key = key;
        this.val = val;
    }

}


