package com.f_lin.future.solution.algorithm.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author F_lin
 * @since 2019/4/9
 **/
public class Topic1 {

    public static void main(String[] args) {
        int length = lengthOfLongestSubstring2("pwwkew");
        System.out.println(length);
    }

    public static int numJewelsInStones(String J, String S) {
        int count = 0;
        for (int i = 0; i < S.length(); i++) {
            if (J.contains(S.substring(i, i + 1))) {
                ++count;
                String s = S.toLowerCase();
            }
        }
        return count;
    }


    /**
     * 双向指针排序
     */
    public static int[] sortedSquares(int[] A) {
       /* List<Integer> eg = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            eg.add(A[i]*A[i]);
        }
        return eg.parallelStream().sorted().mapToInt(Integer::intValue).toArray();*/
        int minPoint = 0;
        int maxPoint = A.length - 1;
        int i1;
        int i2;

        int length = maxPoint;
        int[] temp = new int[A.length];

        for (int j = 0; j < A.length; j++) {
            i1 = A[minPoint];
            i2 = A[maxPoint];

            if (i1 < 0) {
                i1 = i1 * -1;
            }

            if (i1 > i2) {
                temp[length] = i1 * i1;
                ++minPoint;
            } else {
                temp[length] = i2 * i2;
                --maxPoint;
            }
            --length;
        }

        return temp;
    }

    public static int[][] flipAndInvertImage(int[][] A) {
        int[][] result = new int[A.length][];
        for (int i = 0; i < A.length; i++) {
            int[] temp1 = A[i];
            int[] temp2 = new int[temp1.length];
            for (int j = 0; j < temp1.length; j++) {
                temp2[temp1.length - 1 - j] = temp1[j];
            }
            for (int j = 0; j < temp2.length; j++) {
                if (temp2[j] == 0) {
                    temp2[j] = 1;
                } else {
                    temp2[j] = 0;
                }
            }
            result[i] = temp2;
        }
        return result;
    }

    private static void deleteNode(ListNode node) {
        //思想  直接将本节点的内容替换成下一个节点 也就不需要head or pre
        // node.setVal(node.getNext().getVal());
        node.setNext(node.getNext().getNext());

    }

    public static class ListNode {
        private int val;
        private ListNode next;

        public Object getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }

        public ListNode(int val) {
            this.val = val;
        }
    }


    public static int[] twoSum(int[] nums, int target) {
        //key->nums[i] 的补集，v->nums[i] 的index i
        HashMap<Integer, Integer> temp = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer difference = nums[i];
            if (temp.containsKey(difference)) {
                return new int[]{temp.get(difference), i};
            }
            temp.put(target - nums[i], i);
        }
        throw new IllegalArgumentException("No expected value!");
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode current = pre;
        int carry = 0;

        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y;


            int val = (sum + carry) % 10;
            current.next = new ListNode(val);
            current = current.next;

            //val可能为10
            carry = (sum + carry) / 10;

            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        //兼容 4->null  + 6->null ....
        if (carry == 1) {
            current.next = new ListNode(1);
        }
        return pre.next;
    }


    /**
     * abcdjgkhmhkdkkf
     * i j
     * <p>
     * 最长不重复子串
     */
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int diff = 0, i = 0, j = 0;
        while (i < n && j < n) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                diff = Math.max(diff, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return diff;
    }

    public static int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        int diff = 0;
        Map<Character, Integer> temp = new HashMap<>();
        for (int i = 0, j = 0; j < n; j++) {
            if (temp.containsKey(s.charAt(j))) {
                i = Math.max(temp.get(s.charAt(j)), i);
            }

            diff = Math.max(j - i + 1, diff);

            temp.put(s.charAt(j), j + 1);
        }

        return diff;

    }

}
