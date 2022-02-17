package com.f_lin.future.solution.week1;

/**
 * 旋转链表
 * <p>
 * -----      -----      -----      -----      -----      -----
 * | 1 | ---> | 2 | ---> | 3 | ---> | 4 | ---> | 5 | ---> | 6 | ---> NULL
 * -----      -----      -----      -----      -----      -----
 * ^                                |
 * |                                v
 * |           -----              -----
 * |<--------- | 6 |   <--------- | 5 |              //这里把尾巴指向header.
 * |           -----              -----
 * |
 * head
 * （newPoint）
 *
 *
 * <p>
 * 这题，单向链表，就用指针来处理嘛。
 * 按题意，可以理解成，寻找循环链表的 新头以及新尾巴
 * 1.首先，链表的size是必须要确定的，因为 K 值有 >> size的情况，（之前做没考虑，结果执行超时了，hhh）。所以需要遍历一次确定 size。
 * 2.处理 k，k > size的时候，其实只需要翻转 k % size 次。多余的次数不会影响翻转结果。
 * 3.处理size的时候，可以顺便把旧的尾巴删掉 。。 即将 tail.next --> head。。因为我们在找新尾巴以及新头的时候，不允许存在任何尾巴
 * 4. size计算完成后，
 * 这里给个新指针（newPoint），去找新尾巴，newTail.next 就是新的 head
 * 如 k = 1. newPoint 应该指向 《5》这个地方，移动了 链表长度(size) -k -1 次。
 * 《5》就是我们的新尾巴，《6》为新的head
 * 将当前 newPoint.next保存为新head, 后设置为NULL即可
 * <p>
 * <p>
 * ////不过 题解给的 闭链以及解链 的概念解释   可以更清晰的理解 hhhhhh
 *
 * @author F_lin fengjunlin@sobey.com
 * @version 1.0
 * @since 2021/2/7
 */
public class Solution_61 {

    public static void main(String[] args) {
        ListNode node5 = new ListNode(5);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode head = new ListNode(1, node2);


        ListNode listNode = new Solution().rotateRight(head, 5);

        System.out.println(listNode.toString());

    }


    static class Solution {

        public ListNode rotateRight(ListNode head, int k) {
            if (head == null) {
                return null;
            }
            if (head.next == null) {
                return head;
            }

            //先算size
            ListNode point = head;
            int size;
            for (size = 1; point.next != null; size++) {
                point = point.next;
            }

            //计算循环次数 可能会循环多个周期
            k = k % size;
            if (k == 0) {
                return head;
            }

            //把尾巴指向头
            point.next = head;

            ListNode newPoint = head;
            //找新的头 和 新的尾巴。 新指针需要移动，size - k -1 次，此时指向的是新尾巴，尾巴的下个节点是新的头。
            for (int i = 0; i < size - k - 1; i++) {
                newPoint = newPoint.next;
            }

            //找到头
            ListNode newHead = newPoint.next;

            //将尾巴指向Null..
            newPoint.next = null;

            return newHead;

        }


        public ListNode rotateRightBetter(ListNode head, int k) {
            if (head == null) return null;
            if (head.next == null) return head;

            // 先把链表闭环
            ListNode oldTail = head;
            //链表长度
            int n;
            for (n = 1; oldTail.next != null; n++) {
                oldTail = oldTail.next;
            }
            oldTail.next = head;

            ListNode newTail = head;

            for (int i = 0; i < n - k % n - 1; i++) {
                newTail = newTail.next;
            }
            ListNode newHead = newTail.next;


            // 断开链表
            newTail.next = null;

            return newHead;
        }
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

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }
}
