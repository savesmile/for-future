package com.f_lin.future.solution.algorithm;

/**
 * 选择排序
 *
 * @author F_Lin fjl@jinzantech.com
 * @since 2019/6/18 13:59
 */
public class ChooseSort {

    public static void main(String[] args) {
        int[] test = {2, 3, -4, 5, 1, 5, 6, -8, 9, 0, 32, 12, 3, 5, 890, 36, 7, 353469, 12, 34, 542121, 532, 211};
        int[] ints = chooseSort(test);
        for (int i : ints) {
            System.out.print(i + ",");
        }
    }

    /**
     * 思路：
     * 将最大值选出来并从原数组排除
     *
     * @param src
     * @return
     */
    private static int[] chooseSort(int[] src) {
        int[] result = new int[src.length];
        int temMax = src[0];
        for (int i = 0; i < src.length; i++) {
            int[] tempArr = new int[src.length];
            for (int j = 1; j < src.length; j++) {
                if (temMax < src[j]) {
                    tempArr[j] = temMax;
                    temMax = src[j];
                } else {
                    tempArr[j] = src[j];
                }

            }
            result[i] = temMax;
            //兼容负数
            temMax = Integer.MIN_VALUE;
            src = tempArr;
        }
        return result;
    }

}
