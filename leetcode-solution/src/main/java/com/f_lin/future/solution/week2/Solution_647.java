package com.f_lin.future.solution.week2;

/**
 * 回文：正反顺序 都是一致的。单独的   a  b  c 这种也是。
 * 按题意，首先想到的是，遍历字符串，以当前字符开始为中心点，寻找最大回文串。
 * 中心点有两种情况。一种是 单个 [如 bab 中的 a]，一种是两个 [如 baab 中的 aa]
 * 所以我们在扩散的时候，直接基于这两种情况下的回文串来计算即可。反正就这两种情况的存在性而且是互斥的。
 * 依次遍历即可。。
 *
 *
 * dp:
 * 问题： 字符串的一个子串是否为回文串，然后统计有多少个回文串。
 *
 * 问题拆分：
 * 若一个子串是回文串，那么除开首位相同的字母，剩下的子串也是回文串。
 * 即 s[i] = s[j] 只需要判断 字符串s 在[i+1, j-1]是否为回文串即可。
 *
 * 是否为回文串基准条件：
 * 1. i=j  ===> 一个字母 （类比扩散的中心点概念）
 * 2. i = j-1 && s[i] = s[j]  ===> 两个字母（类比扩散的中心点概念）
 * 3. i < j-1 && s[i] = s[j] && s[i+1, j-1] 也为回文串 ===> 多个字母组成的时候。抛去收尾相同的字母，剩余的子串也为回文。
 *
 * 1.2 就是边界条件。
 * 3就是我们的状态转移方程。
 *
 * 遍历顺序。
 * 由于 i <= j。。根据条件3，s[i,j]需要根据s[i+1,j-1]的状态决定。
 * 我们需要从左上角  往右下角遍历。  外层循环j  内存循环 I
 *     | j-1 |  j  |
 * ----------------
 * i   |     |  ?  |    dp[i][j]  状态需要由 dp[i+1][j-1] 转移过来
 * ----------------
 * i+1 |  √  |     |
 *
 * @author F_lin
 * @since 2021/2/20
 **/
public class Solution_647 {

    public static void main(String[] args) {
        System.out.println(countSubstringsDP("qqqqq"));
    }


    /**
     * 指针扩散  从中心向两边扩撒
     *
     * 有两种情况
     */
    public static int countSubstrings(String s) {
        char[] chars = s.toCharArray();
        int result = 0;
        for (int i = 0; i < chars.length; i++) {
            //这里直接将两种结果递加起来就行了。
            //因为会有这种情况。
            // s = aaaa , i = 1时。。这个时候， s[i,i] 以及 s[i,i+1]都符合回文条件

            //以中心点为 一个字符 的情况 向两头遍历
            //如 s = bab , i = 1
            // aaa
            result += count(chars, i, i);
            //以中心点为 两个字符 的情况 向两头遍历
            //如 s = baab ，i = 1
            result += count(chars, i, i + 1);
        }

        return result;
    }

    /**
     * dp方式
     */
    public static int countSubstringsDP(String s) {
        char[] chars = s.toCharArray();

        int result = 0;
        //默认都是false
        boolean[][] dp = new boolean[chars.length][chars.length];

        for (int j = 0; j < chars.length; j++) {
            for (int i = 0; i <= j; i++) {
                if (i == j) {
                    dp[i][j] = true;
                    result++;
                } else if (i == j - 1 && chars[i] == chars[j]) {
                    dp[i][j] = true;
                    result++;
                } else if (i < j - 1 && chars[i] == chars[j] && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    result++;
                }
            }
        }
        return result;
    }

    /**
     * aaaa
     *
     * @param chars  数组
     * @param pStart 起点指针
     * @param pNext  第二个起点指针
     */
    public static int count(char[] chars, int pStart, int pNext) {
        int res = 0;
        while (pStart >= 0 && pNext < chars.length && chars[pStart] == chars[pNext]) {
            pStart--;
            pNext++;
            res++;
        }
        return res;
    }

}
