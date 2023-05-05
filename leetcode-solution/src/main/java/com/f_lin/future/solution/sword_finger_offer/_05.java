package com.f_lin.future.solution.sword_finger_offer;

/**
 * 字符串不可变。
 * @author F_lin fengjunlin@sobey.com
 * @version 1.0
 * @since 2023/3/29
 */
public class _05 {
    //遍历法
    public static String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (' ' == c) {
                sb.append("%20");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    //直接替换
    public static String replaceSpace2(String s) {
        return s.replaceAll(" ", "%20");
    }
}
