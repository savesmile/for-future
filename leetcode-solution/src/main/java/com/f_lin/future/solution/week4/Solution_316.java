package com.f_lin.future.solution.week4;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 字典序：在字典中的顺序
 * 比如说输入字符串 s = "babc"，去重且符合相对位置的字符串有两个，分别是 "bac" 和 "abc"，但是我们的算法得返回 "abc"，因为它的字典序更小。
 *
 * 
 *
 * @author F_lin
 * @since 2021/3/7
 **/
public class Solution_316 {

    public static void main(String[] args) {
        /**
         * a-z,,97~122
         */
        String s = "cbacdcbc";

        System.out.println(removeDuplicateLetters(s));
    }

    public static String removeDuplicateLetters(String s) {
        // 维护一个计数器记录字符串中字符的数量
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }

        Deque<Character> stk = new ArrayDeque<>();

        //栈中是否有此字母
        boolean[] inStack = new boolean[26];
        StringBuffer sb = new StringBuffer();
        for (char charAt : s.toCharArray()) {
            // 每遍历过一个字符，都将对应的计数减一
            count[charAt - 'a']--;

            if (inStack[charAt - 'a']) continue;

            while (!stk.isEmpty() && stk.peek() > charAt) {
                //当前字符在后边不在出现了。
                if (count[stk.peek()] == 0) {
                    break;
                }
                // 若之后还有，则可以 pop
                inStack[stk.pop()] = false;
            }
            stk.push(charAt);
            inStack[charAt - 'a'] = true;
            sb.append(charAt);
        }

        return sb.toString();
    }
}
