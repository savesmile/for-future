package com.f_lin.future.solution.week14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author F_lin
 * @since 2021/6/5
 **/
public class Solution_56 {

    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1, 4}, {0, 0}};

        int[][] merge = merge(intervals);

        for (int i = 0; i < merge.length; i++) {
            System.out.println("left:" + merge[i][0] + " right:" + merge[i][1]);
        }
    }

    /**
     * 执行用时：
     * 7 ms
     * , 在所有 Java 提交中击败了
     * 76.33%
     * 的用户
     * 内存消耗：
     * 40.8 MB
     * , 在所有 Java 提交中击败了
     * 93.26%
     * 的用户
     */
    public static int[][] merge(int[][] intervals) {

        List<int[]> result = new ArrayList<>();

        if (intervals.length <= 1) {
            return intervals;
        }

        //排序先
        Arrays.sort(intervals,
                (o1, o2) -> {
                    if (o1[0] == o2[0]) return o1[1] - o2[1];
                    return o1[0] - o2[0];
                });

        int lastLeft = intervals[0][0];
        int lastRight = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            int left = intervals[i][0];
            int right = intervals[i][1];

            if (left > lastRight) {
                //记录前驱
                result.add(new int[]{lastLeft, lastRight});

                lastLeft = left;
                lastRight = right;

                continue;
            }


            if (left <= lastLeft) {
                lastLeft = left;
            }

            if (right >= lastRight) {
                lastRight = right;
            }
        }
        result.add(new int[]{lastLeft, lastRight});

        int[][] res = new int[result.size()][2];

        for (int i = 0; i < result.size(); i++) {
            res[i][0] = result.get(i)[0];
            res[i][1] = result.get(i)[1];
        }

        return res;
    }
}
