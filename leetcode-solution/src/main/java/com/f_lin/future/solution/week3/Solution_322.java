package com.f_lin.future.solution.week3;

/**
 * 有硬币：   [0,1,2,3,,,,,   i  ]
 * 对于面值c, [1,2,3,4,,,,,c[i]] ]
 * 需要凑齐 面额为 a
 * dp思路：
 * 此题类似与完全背包问题，采用完全背包dp思路即可，
 * 对于第 i 件物品，有两个状态
 * 1. 不选,则 F[i][a] = F[i-1][a]。
 * 2. 至少选1个：则 F[i][a] = F[i][a-c[i]] + 1
 * F[i][a] = min { F[i-1][a], F[i][a-c[i]] + 1 | F[i-1][a] 能凑齐, F[i][a-c[i]]能凑齐, a > c[i]}
 * a = c[i] 时 F[i][a] = 1
 * a < c[i] 时 F[i][a] = F[i-1][a]
 *
 * 边界条件
 * dp[0][？] = 0
 * dp[？][0] = 0
 *
 * 空间优化：
 * 当前状态只与当前行以及上一行有关系。所以可以使用一维数组进行优化。具体优化思路，可以讲讲。也可以看看这个文章、
 *
 *
 * @author F_lin
 * @since 2021/2/20
 **/
public class Solution_322 {

    public static void main(String[] args) {
        int[] coins = new int[]{1, 2, 5};

        System.out.println(coinChangeBetter(coins, 11));
    }

    /**
     * 执行用时：
     * 35 ms
     * , 在所有 Java 提交中击败了
     * 15.34%
     * 的用户
     * 内存消耗：
     * 38.4 MB
     * , 在所有 Java 提交中击败了
     * 11.54%
     * 的用户
     *
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        //将所有 格子都填充为0。代表凑不齐整数。
        int[][] dp = new int[coins.length + 1][amount + 1];
        int cannot = 0;

        //循环
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                if (coins[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                }
                if (coins[i - 1] == j) {
                    dp[i][j] = 1;
                }
                if (coins[i - 1] < j) {
                    int withOut = dp[i - 1][j];
                    int with = dp[i][j - coins[i - 1]];
                    if (with != cannot && withOut == cannot) {
                        dp[i][j] = dp[i][j - coins[i - 1]] + 1;
                    }
                    if (with == cannot && withOut != cannot) {
                        dp[i][j] = dp[i - 1][j];
                    }

                    if (with != cannot && withOut != cannot) {
                        dp[i][j] = Math.min(withOut, with + 1);
                    }
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 1; i <= coins.length; i++) {
            if (dp[i][amount] != cannot && dp[i][amount] < result) {
                result = dp[i][amount];
            }
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }

    //空间优化

    /**
     * 执行用时：
     * 26 ms
     * , 在所有 Java 提交中击败了
     * 22.65%
     * 的用户
     * 内存消耗：
     * 37.9 MB
     * , 在所有 Java 提交中击败了
     * 46.16%
     * 的用户
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChangeBetter(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        //将所有 格子都填充为0。代表凑不齐整数。
        int[] dp = new int[amount + 1];
        int cannot = 0;

        //循环
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                if (coins[i - 1] > j) {
                    dp[j] = dp[j];
                }
                if (coins[i - 1] == j) {
                    dp[j] = 1;
                }
                if (coins[i - 1] < j) {
                    int withOut = dp[j];
                    int with = dp[j - coins[i - 1]];
                    if (with != cannot && withOut == cannot) {
                        dp[j] = dp[j - coins[i - 1]] + 1;
                    }
                    if (with == cannot && withOut != cannot) {
                        dp[j] = dp[j];
                    }

                    if (with != cannot && withOut != cannot) {
                        dp[j] = Math.min(withOut, with + 1);
                    }
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 1; i <= coins.length; i++) {
            if (dp[amount] != cannot && dp[amount] < result) {
                result = dp[amount];
            }
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }

    /**
     * 完全背包问题
     *  转换为 0 - 1 背包问题。完全可以将 k*i (k件i物品) 当做一个新的物品，费用为 k*w[i]，价值为k*value[i]。但是时间复杂度不变。
     * dp思路：
     * 1. 可以按照0-1背包的思想进行dp，但是对 第 i 件物品的最优解就不再是两个(选还是不选)。变为了，对第i件物品，选 0,1,2,3,4,5，，，，k次的最优解。对应的状态转移方程 为  F[i][v] = max{ F[i][v-k*w[i]] + k*value[i] } (  0<= k*w[i] <=v  )
     *   优化点，就是对价值的优化，即入参的优化。根据价值与费用，将费用又高，价值又低的物品筛掉。
     * 2. 优化dp思想，对第 i 件物品的最优解，为 以下两种情况的最大值。
     *    1> 不放 i 这件物品，则其最优解 为 i-1 在容量为 v 的情况下的最优解。
     *    2> 至少放1件 i 这件物品，则其最优解为 i 物品，在v-w[i] 的情况下的最优解(条件  v >= w[j])。（可以理解为一直放 i 的物品，直到放不下为止）。
     *  状态转移方程 F[i][v] = max{ F[i-1][v],  F[i][v-w[i]]+value[i]] (v > w[i] ) }
     *  可以发现和 0-1背包问题的状态转移方程  F[i][v] = max {F[i-1][v], F[i-1][v-w[i] + value[i]]} 类似。
     *
     * 同样，针对此状态转移方程，在实现的时候，可以对空间进行优化。
     * 此时，因为当前 第k件 i 物品的状态，与 第 i-1 件物品，在v容量下的最大价值，与第k-1件i物品在 v - (k-1)*w[i] 容量下的最大价值有关。
     * 所以 v的遍历方向应该是从左到右的来计算，即 0--->V。这样，一维数组中存在的数据才是正确的。
     */

    /*
    物品 [1,2,3,4,,,i]
    w[1,2,3,4,,w[i]]
    value[1,2,3,4,,,value[i]]
    背包容量 S

    物品 i
    选：   S-w[i] , value[i], 最大价值 ( S > w[i])
    不选： S,[i-1] 物品。 最大价值。


   w[1,2,3,4,,w[i]]
   value[1,2,3,4,,,value[i]]

   2 +   2 - 2
     F[i][S] = max {F[i-1][S], F[i-1][S-w[i] + value[i]] | S > w[i] }

     for (i;)
       for (S;)
       with = F[i-1][S-w[i] + value[i]] | S > w[i]
       without = F[i-1][S]
       dp[i][S] = max（with，without ）


   dp[S]  0  1  2  3  3  3.....3

       0  1, 2, 3, 4, 5.....S
    0  0  0  0  0  0  0.....0
    1  0  1  1  1  1  1.....1
    2  0  1  2  3  3  3.....3
    3  0  1  2  3  4  5.....5
    4  0
    5  0
    ...0
    i  0
    * */

    /**
     * 0-1 背包。状态转移方程： F[i][v] = max {F[i-1][v], F[i-1][v-w[i] + value[i]]}
     *
     * 关于 0-1 背包 空间优化的两种方案
     * 1. 使用滚动数组进行空间优化。
     *  根据我们的状态转移方程，可以知道的是，当前物品 i 放与不放的最优解 都只和 i - 1这个物品的状态有关。所以我们可以使用滚动数组只记录下 F[i-1] 即可。具体实现可以使用奇欧数的方式，判断当前i应该放到哪一行，滚动计算即可。
     * 2. 使用1维数组降维。
     *  我们在计算dp[i][v]的实现里边。毫无疑问是会有两个循环。外层 i, 内层v......
     *  我们在计算 F[i][v]的时候，按理说只需要知道 F[i-1][v-w[i]]+value[i] 以及 F[i-1][v] 这两个值。在画出的二维数组中可以很直观的看到。当前F[i][v]只会跟 他上边一行 以及上边一行左边的 数据 状态有关。右边是不会用到的。
     *  所以我们直接使用一维素组 记录当前行的状态即可。然后 从 右 到 左(v--->0)，依次计算即可。
     *  这里可以抽象的想一下，更新 当前 1维素组 为第 2 个物品的状态时，这个时候1维数组中的数据还是记录的 第1个物品的状态。因为第2个物品在不同V下的状态，是由当前格子内的旧数据(即当前背包容量下，第一个物品的最优解)
     *  以及  v-w[2] + value[2] 这个格子的数据 一起决定的.所以 从一维数组的后边往前更新的话，整个数据是没得问题。试想一下从前往后更新，更新第2个物品在v这个格子的数据是，前边的数据已经被更新成第2个物品的数据了。这个时候状态转移就混乱了、
     */
}
