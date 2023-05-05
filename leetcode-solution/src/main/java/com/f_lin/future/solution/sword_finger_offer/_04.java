package com.f_lin.future.solution.sword_finger_offer;

/**
 * 二维数组查找
 *
 * @author F_lin fengjunlin@sobey.com
 * @version 1.0
 * @since 2023/3/29
 */
public class _04 {

    /**
     * 提示：
     * 根据题干可知。m*n的矩阵，，从左下角的点(i-1,0)开始 向上删除行或者向右删除列！
     */
    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int i = matrix.length - 1, j = 0;

        while (i >= 0 && j <= matrix[0].length - 1) {
            if (matrix[i][j] > target) {
                i--;
            } else if (matrix[i][j] < target) {
                j++;
            } else return true;
        }
        return false;
    }
}
