package com.f_lin.future.solution.algorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 二分查找
 * O(logN)
 *
 * @author F_Lin fjl@jinzantech.com
 * @since 2019/6/18 10:44
 */
public class DichotomizeQuery {
    private static final Logger LOGGER = LoggerFactory.getLogger(DichotomizeQuery.class);

    public static void main(String[] args) {
        //Integer[] src = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 21, 23, 40, 51, 53, 57, 60, 64, 77, 85, 88, 90, 100};
        int[] test = new int[124 * 2];
        for (int i = 0; i < 124 * 2; i++) {
            test[i] = i;
        }

        //diLook(test, 247);

        Integer integer = diLookRecursive(test, 248);
        System.out.println(integer == null ? "没找到" : "找到了。。" + integer);


    }

    private static Integer diLook(Integer[] src, Integer target) {
        int max = src.length - 1;
        int min = 0;
        int count = 0;
        LOGGER.info("开始查找 {}", target);
        while (min <= max) {
            int mid = (max + min) / 2;
            LOGGER.info("第{}次查找，mid-index:{},max{},min{}", ++count, min, max, min);
            if (target > src[mid]) {
                min = mid + 1;
            } else if (target < src[mid]) {
                max = mid - 1;
            } else {
                LOGGER.info("FIND IT，index-mid: {} ,共查找了 {} 次", mid, count);
                return target;
            }
        }
        LOGGER.info("NOT FIND ,共查找了 {} 次", count);
        return null;
    }

    /**
     * 递归方式
     *
     * @param src
     * @param target
     * @return
     */
    private static Integer diLookRecursive(int[] src, int target) {
        int max = src.length - 1;
        int min = 0;
        return loopRe((min + max) / 2, min, max, src, target);
    }

    private static Integer loopRe(int mid, int min, int max, int[] src, int target) {
        //基准条件
        if (min > max) {
            return null;
        } else if (target == src[mid]) {
            return src[mid];
        }

        //递归逻辑
        if (target > src[mid]) {
            min = mid + 1;
        } else if (target < src[mid]) {
            max = mid - 1;
        }
        return loopRe((min + max) / 2, min, max, src, target);
    }


}
