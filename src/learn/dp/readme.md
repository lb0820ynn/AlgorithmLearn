# 动态规划

### 030-连续子数组的最大和

```java
/**
 * 030-连续子数组的最大和
 * HZ偶尔会拿些专业问题来忽悠那些非计算机专业的同学。今天测试组开完会后,他又发话了:在古老的一维模式识别中,常常需要计算连续子向量的最大和,当向量全为正数的时候,
 * 问题很好解决。但是,如果向量中包含负数,是否应该包含某个负数,并期望旁边的正数会弥补它呢？例如:{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)。
 * 给一个数组，返回它的最大连续子序列的和，你会不会被他忽悠住？(子向量的长度至少是1)
 *
 * @param array
 * @return
 */
public static int FindGreatestSumOfSubArray(int[] array) {
    int res = Integer.MIN_VALUE;
    int g = 0;
    for (int i : array) {
        if (g < 0) {
            g = 0;
        }
        g += i;
        res = Math.max(res, g);
    }
    System.out.print("greatest num == " + res);
    System.out.println();
    return res;
}
```

### 052-正则表达式匹配

## 题目描述

请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配

```java
int m = 0;
int n = 0;
boolean[][] f;

/**
 * 052-正则表达式匹配
 *
 * @param str
 * @param pattern
 * @return
 */
public boolean match(char[] str, char[] pattern) {
    m = str.length;
    n = pattern.length;
    f = new boolean[m + 1][n + 1];
    return dp(0, 0, str, pattern);
}

public boolean dp(int i, int j, char[] s, char[] p) {
    if (f[i][j]) {
        return f[i][j];
    }
    if (j == n) {
        return f[i][j] = i == m;
    }
    boolean firstMatch = i < m && (s[i] == p[j] || p[j] == '.');
    if (j + 1 < n && p[j + 1] == '*') {
        f[i][j] = dp(i, j + 2, s, p) || (firstMatch && dp(i + 1, j, s, p));
    } else {
        f[i][j] = firstMatch && dp(i + 1, j + 1, s, p);
    }
    return f[i][j];
}
```

### 01背包问题

```java
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

    /**
     * 一维数组解法
     */
    public static void zeroOnePack2(int v, int n, int[] weight, int[] value) {
        int[] dp = new int[v + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = v; j >= weight[i - 1]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight[i - 1]] + value[i - 1]);
            }
        }
        System.out.println(dp[v]);
    }
```

### 零钱兑换

```java
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
```