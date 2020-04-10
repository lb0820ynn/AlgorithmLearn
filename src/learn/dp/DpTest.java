package learn.dp;

/**
 * Created by liubin on 2020/3/28.
 */
public class DpTest {

    public static void main(String[] args) {

//        zeroOnePack(10, 3, new int[]{0, 5, 4, 3}, new int[]{0, 20, 10, 12});
//        zeroOnePack2(10, 3, new int[]{5, 4, 3}, new int[]{20, 10, 12});
//        zeroOnePack3(10, 3, new int[]{5, 4, 1}, new int[]{20, 10, 12});

        int coinChange = coinChange(5, new int[]{1, 2, 5});
        System.out.println("coinChange == " + coinChange);
    }

    /**
     * 01背包问题
     * 有 N 件物品和一个容量为 V 的背包。放入第 i 件物品耗费的费用是 Ci
     * 得到的
     * 价值是 Wi。求解将哪些物品装入背包可使价值总和最大。
     */
    public static void zeroOnePack(int v, int n, int[] weight, int[] value) {

        int[][] dp = new int[n + 1][v + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= v; j++) {
                if (j >= weight[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
                    System.out.println("\t---------- j=" + j + "  i==" + i + "  d[i-1][j]=" + dp[i - 1][j] + "   d[i-1][j-weight[i]]+ value[i]=" + (dp[i - 1][j - weight[i]] + value[i]) + "   ---------");
                }
            }
        }
//        System.out.println(dp[n][v]);
        print_array(dp);
    }

    public static void zeroOnePack2(int v, int n, int[] weight, int[] value) {
        int[] dp = new int[v + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = v; j >= weight[i - 1]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight[i - 1]] + value[i - 1]);
            }
        }
        System.out.println(dp[v]);
    }

    public static void zeroOnePack3(int v, int n, int[] weight, int[] value) {
        int[] dp = new int[v + 1];

        for (int i = 1; i <= v; i++) {
            dp[i] = -9999;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = v; j >= weight[i - 1]; j--) {
                System.out.println("j== " + j + "  dp[j]==" + dp[j] + "  other == " + (dp[j - weight[i - 1]] + value[i - 1]));
                dp[j] = Math.max(dp[j], dp[j - weight[i - 1]] + value[i - 1]);
//                System.out.println("j== " + j + "  dp[j]==" + dp[j] + "  other == " + (dp[j - weight[i - 1]] + value[i - 1]));
            }
        }
        System.out.println(dp[v]);
    }

    /**
     * 零钱兑换 II
     * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
     *
     * @param amount
     * @param coins
     * @return
     */
    public static int coinChange(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }

        return dp[amount];

    }

    public static void print_array(int A[][]) {
        System.out.println("=======================================================================");
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                System.out.print(A[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("=======================================================================");
    }
}