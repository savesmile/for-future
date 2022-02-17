package com.f_lin.future.solution.week9;

/**
 * 矩阵特性。从左下角开始找。
 * 已知，上边的点比右边的点小。
 * 若当前点比目标点小，则往右
 * 若当前点比目标大，则往上
 * 一直到找到，或者某一边出界
 *
 * @author F_lin
 * @since 2021/4/11
 **/
public class Solution_240 {


    public static void main(String[] args) {
        int[][] test = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
        System.out.println(searchMatrix(test, 20));
    }

    /**
     * 执行用时：
     * 6 ms
     * , 在所有 Java 提交中击败了
     * 90.21%
     * 的用户
     * 内存消耗：
     * 43.8 MB
     * , 在所有 Java 提交中击败了
     * 88.45%
     * 的用户
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix[0][0] > target) {
            return false;
        }
        if (matrix[0][0] == target) {
            return true;
        }
        //初始变量
        int i = matrix.length - 1, j = 0;
        while (true) {
            if (i < 0 || j > matrix[0].length - 1) {
                return false;
            }

            //当前值是否为寻找的目标值
            if (target == matrix[i][j]) {
                return true;
            }

            if (target > matrix[i][j]) {
                j++;
            } else {
                i--;
            }
        }
    }
}
