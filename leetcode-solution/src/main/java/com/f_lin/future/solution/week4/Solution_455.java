package com.f_lin.future.solution.week4;

/**
 * 贪心策略：
 * 有限把胃口最小的孩子分了。
 * 直到将饼干分完或者孩子分完。
 *
 * @author F_lin
 * @since 2021/3/7
 **/
public class Solution_455 {

    public static void main(String[] args) {
        int[] g = new int[]{10, 9, 8, 7, 10, 9, 8, 7};
        int[] s = new int[]{10, 9, 8, 7};

        System.out.println(findContentChildren(g, s));
    }

    /**
     执行用时：
     9 ms
     , 在所有 Java 提交中击败了
     19.45%
     的用户
     内存消耗：
     39.2 MB
     , 在所有 Java 提交中击败了
     51.25%
     的用户
     * @param g
     * @param s
     * @return
     */
    public static int findContentChildren(int[] g, int[] s) {
        if (g.length == 0 || s.length == 0) {
            return 0;
        }
        sort(g, 0, g.length - 1);
        sort(s, 0, s.length - 1);
        int result = 0;
        //对g和s进行排序。
        //优先满足胃口最低的

        int pointS = -1;
        for (int value : g) {
            if (value > s[s.length - 1] || pointS == s.length) {
                break;
            }

            for (int j = pointS == -1 ? 0 : pointS; j < s.length; j++) {
                if (value <= s[j]) {
                    pointS = j + 1;
                    result++;
                    break;
                }
            }
        }

        return result;
    }


    public static void sort(int[] array, int left, int right) {
        if (left > right) {
            return;
        }
        // base中存放基准数
        int base = array[left];
        int i = left, j = right;
        while (i != j) {
            // 顺序很重要，先从右边开始往左找，直到找到比base值小的数
            while (array[j] >= base && i < j) {
                j--;
            }

            // 再从左往右边找，直到找到比base值大的数
            while (array[i] <= base && i < j) {
                i++;
            }

            // 上面的循环结束表示找到了位置或者(i>=j)了，交换两个数在数组中的位置
            if (i < j) {
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
            }
        }

        // 将基准数放到中间的位置（基准数归位）
        array[left] = array[i];
        array[i] = base;

        // 递归，继续向基准的左右两边执行和上面同样的操作
        // i的索引处为上面已确定好的基准值的位置，无需再处理
        sort(array, left, i - 1);
        sort(array, i + 1, right);
    }
}
