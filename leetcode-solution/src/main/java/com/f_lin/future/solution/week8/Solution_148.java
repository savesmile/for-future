package com.f_lin.future.solution.week8;

/**
 * 归并排序
 * 快慢指针： 链表常用套路，快指针每次比慢指针多移动1次，下表是慢指针的两倍
 *
 * @author F_lin
 * @version 1.0
 * @since 2021/4/5
 */
class Solution {

    public ListNode sortList(ListNode head) {
        //空直接返回
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fast = head.next;
        ListNode slow = head;
        //使用 快慢指针 进行二分 分割
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        //后边半截
        ListNode post = slow.next;
        //把链表断开
        slow.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(post);

        ListNode root = new ListNode(0);
        ListNode res = root;
        //合并
        while (left != null && right != null) {
            if (left.val < right.val) {
                root.next = left;
                left = left.next;
            } else {
                root.next = right;
                right = right.next;
            }
            root = root.next;
        }
        root.next = left != null ? left : right;
        return res.next;
    }


    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}