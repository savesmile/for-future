package com.f_lin.future.solution.sword_finger_offer;

import java.util.ArrayList;
import java.util.List;

/**
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）
 *
 * @author F_lin fengjunlin@sobey.com
 * @version 1.0
 * @since 2023/3/29
 */
public class _06 {

    public static void main(String[] args) {
        ListNode h = new ListNode(1);
        ListNode _1 = new ListNode(2);
        ListNode _2 = new ListNode(3);
        ListNode _3 = new ListNode(4);
        h.next = _1;
        _1.next = _2;
        _2.next = _3;

        final int[] ints = reversePrint2(h);
        for (int anInt : ints) {
            System.out.println(anInt);
        }

    }

    //双重遍历
    public static int[] reversePrint(ListNode head) {
        int len = 0;
        ListNode c = head;
        while (c != null) {
            len++;
            c = c.next;
        }
        int[] r = new int[len];
        while (len > 0) {
            r[len - 1] = head.val;
            head = head.next;
            len--;
        }
        return r;
    }

    //递归
    public static int[] reversePrint2(ListNode head) {
        List<Integer> r = new ArrayList<>();
        recursion(r, head);
        return r.stream().mapToInt(i -> i).toArray();
    }

    public static void recursion(List<Integer> r, ListNode h) {
        if (h == null) {
            return;
        }
        recursion(r, h.next);
        r.add(h.val);
    }



    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
