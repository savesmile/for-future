package com.f_lin.future.solution.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {@link System#arraycopy(Object src, int srcPos, Object des, int desPos, int length)}
 * 将源数组覆盖到目标数组中，将根据其实位置覆盖目标数组中的数据
 * src 源数组
 * src 源数组复制起始位置
 * des 目标数组
 * des 目标数组覆盖起始位置
 * length 复制长度(原素组)
 *
 * @author F_Lin fjl@jinzantech.com
 * @since 2019/6/18 15:20
 */
public class FastSort {

    public static void main(String[] args) {
        Integer[] test = {1, 2, 3, 445, 5, 3, 6, 10, 3, 44, 2};
        //System.out.println(sum(test));
        //System.out.println(countArrNum(test));
        //System.out.println(maxOfArr(test));

        Integer[] sort = fastSort(test);
        for (Integer i : sort) {
            System.out.print(i + ",");
        }
        Map map = new HashMap();
    }

    private static List<Integer> fastSort(List<Integer> src) {
        //基准条件。当数组中只剩一个元素时，其默认有序
        if (src.size() < 2) {
            return src;
        }

        //基准对比值 亦可称为递归条件
        int point = src.get(0);
        List<Integer> left = new ArrayList<Integer>();
        List<Integer> right = new ArrayList<Integer>();
        for (Integer val : src) {
            if (point >= val) {
                left.add(val);
            }
            if (point < val) {
                right.add(val);
            }
        }
        //递归逻辑
        List<Integer> results = new ArrayList<>(fastSort(left));
        results.add(point);
        results.addAll(fastSort(right));
        return results;
    }


    private static Integer[] fastSort(Integer[] src) {
        //基准条件。当数组中只剩一个元素时，其默认有序
        if (src.length < 2) {
            return src;
        }

        //基准对比值 亦可称为递归条件
        int point = src[0];
        List<Integer> left = new ArrayList<Integer>();
        List<Integer> right = new ArrayList<Integer>();
        for (int i = 1; i < src.length; i++) {
            if (point >= src[i]) {
                left.add(src[i]);
            }
            if (point < src[i]) {
                right.add(src[i]);
            }
        }

        //递归逻辑
        Integer[] leftArr = fastSort(left.toArray(new Integer[0]));
        Integer[] rightArr = fastSort(right.toArray(new Integer[0]));

        //结果拼接，，java数组是真的麻烦。。。不然就是用循环的方式手动拼接,
        //TODO 后边直接考虑做成ArrayList类型的。。
        Integer[] result = new Integer[src.length];
        System.arraycopy(leftArr, 0, result, 0, leftArr.length);
        System.arraycopy(new Integer[]{point}, 0, result, leftArr.length, 1);
        System.arraycopy(rightArr, 0, result, leftArr.length + 1, rightArr.length);
        return result;
    }

    private static int sum(int[] src) {
        //基准条件
        if (src.length == 1) {
            return src[0];
        } else {
            int[] temp = new int[src.length - 1];
            //这个底层方法记住！！！
            System.arraycopy(src, 1, temp, 0, src.length - 1);
            //递归逻辑
            return src[0] + sum(temp);
        }
    }

    private static int countArrNum(int[] src) {
        //基准条件
        if (src.length == 1) {
            return 1;
        } else {
            int[] temp = new int[src.length - 1];
            //这个底层方法记住！！！
            System.arraycopy(src, 1, temp, 0, src.length - 1);
            //递归逻辑
            return 1 + countArrNum(temp);
        }
    }

    private static int maxOfArr(int[] src) {
        //基准条件
        if (src.length == 2) {
            return src[0] > src[1] ? src[0] : src[1];
        } else {
            int[] temp = new int[src.length - 1];
            //这个底层方法记住！！！
            System.arraycopy(src, 1, temp, 0, src.length - 1);
            //递归逻辑。。。
            int maxOfArr = maxOfArr(temp);
            return src[0] > maxOfArr ? src[0] : maxOfArr(temp);
        }
    }
}
